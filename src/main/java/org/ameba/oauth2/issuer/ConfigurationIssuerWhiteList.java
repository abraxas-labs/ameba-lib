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
package org.ameba.oauth2.issuer;

import org.ameba.oauth2.InvalidTokenException;
import org.ameba.oauth2.Issuer;
import org.ameba.oauth2.IssuerWhiteList;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A ConfigurationIssuerWhiteList.
 *
 * @author Heiko Scherrer
 */
public class ConfigurationIssuerWhiteList implements IssuerWhiteList<Issuer> {

    private final String issuerId;
    private final int skewSeconds;
    private final URL baseURL;
    private final String signingKey;
    private final URL jwkURL;
    private final String kid;

    public ConfigurationIssuerWhiteList(String issuerId, int skewSeconds, String baseURL, String signingKey, URL jwkURL, String kid) {
        this.issuerId = issuerId;
        this.skewSeconds = skewSeconds;
        this.signingKey = signingKey;
        this.jwkURL = jwkURL;
        this.kid = kid;
        try {
            this.baseURL = new URL(baseURL);
        } catch (MalformedURLException e) {
            throw new InvalidConfigurationPropertyValueException("baseURL", baseURL, "BaseURL is not a proper URL");
        }
    }

    public ConfigurationIssuerWhiteList(String issuerId, String baseURL, String signingKey, URL jwkURL, String kid) {
        this.issuerId = issuerId;
        this.signingKey = signingKey;
        this.jwkURL = jwkURL;
        this.kid = kid;
        this.skewSeconds = Issuer.DEFAULT_MAX_SKEW_SECONDS;
        try {
            this.baseURL = new URL(baseURL);
        } catch (MalformedURLException e) {
            throw new InvalidConfigurationPropertyValueException("baseURL", baseURL, "BaseURL is not a proper URL");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Issuer> getIssuers(String issuerId) {
        if (!this.issuerId.equals(issuerId)) {
            throw new InvalidTokenException("Token issuer not accepted");
        }
        var result = new ArrayList<Issuer>(1);
        result.add(new ConfiguredIssuer(issuerId, skewSeconds, baseURL, signingKey, jwkURL, kid));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Issuer getIssuer(String issuerId, String kid) {
        if (!this.issuerId.equals(issuerId)) {
            throw new InvalidTokenException("Token issuer not accepted");
        }
        if (!this.kid.equals(kid)) {
            throw new InvalidTokenException("Token kid not accepted");
        }
        return new ConfiguredIssuer(issuerId, skewSeconds, baseURL, signingKey, jwkURL, kid);
    }
}
