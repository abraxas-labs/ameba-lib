/*
 * Copyright 2014-2015 the original author or authors.
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
package org.ameba.oauth2.parser;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.ameba.oauth2.InvalidTokenException;
import org.ameba.oauth2.Symmetric;
import org.ameba.oauth2.TokenParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A HS256TokenParserImpl uses a symmetric SHA-512 signing key to verify the signature.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 */
public class HS512TokenParserImpl implements TokenParser<Symmetric, Jwt> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HS512TokenParserImpl.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String supportAlgorithm() {
        return SignatureAlgorithm.HS512.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Jwt parse(String token, Symmetric issuer) {
        Jwt jwt;
        try {
            jwt = Jwts.parser()
                    .setAllowedClockSkewSeconds(issuer.getSkewSeconds())
                    .setSigningKey(issuer.getSigningKey())
                    .parseClaimsJws(token);
            return jwt;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new InvalidTokenException(e.getMessage());
        }
    }
}
