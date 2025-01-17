/*
 * Copyright 2015-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ameba.oauth2;

import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A JwtValidationStrategy may be used as Servlet Filter to extract a JWT from an
 * authorization header and validates the JWT.
 *
 * @author Heiko Scherrer
 */
public class JwtValidationStrategy implements FilterStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtValidationStrategy.class);
    private final List<TokenExtractor> extractors;
    private JwtValidator validator;

    public JwtValidationStrategy(List<TokenExtractor> extractors, @Autowired(required = false) JwtValidator validator) {
        this.extractors = extractors;
        this.validator = validator;
    }

    public JwtValidationStrategy(List<TokenExtractor> extractors) {
        this.extractors = extractors;
    }
    /**
     * {@inheritDoc}
     *
     * - Extract JWT from header
     * - Validate JWT
     */
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && isBearer(authHeader)) {

            LOGGER.debug("Authorization Header detected, start extracting and validating...");
            Jwt jwt = extractToken(authHeader);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Extracted JWT: [{}]", jwt);
            }
            if (null != validator) {
                validator.validate(jwt, request);
            }
        }
    }

    private Jwt extractToken(String authHeader) {
        List<ExtractionResult> result = extractors
                .stream()
                .map(extractor -> {
                    ExtractionResult vr = extractor.canExtract(authHeader);
                    return vr.isExtractionPossible() ? extractor.extract(authHeader) : vr;
                })
                .collect(Collectors.toList());

        ExtractionResult extractionResult = result
                .stream()
                .filter(ExtractionResult::hasJwt)
                .findFirst()
                .orElseThrow(() -> new InvalidTokenException("Could not extract JWT from token"));

        return extractionResult.getJwt();
    }

    private boolean isBearer(String authHeader) {
        return authHeader.startsWith("Bearer");
    }
}
