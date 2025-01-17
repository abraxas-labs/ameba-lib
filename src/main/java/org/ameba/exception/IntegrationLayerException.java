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
package org.ameba.exception;

/**
 * A IntegrationLayerException is used to signal exceptions around the integration layer. This type of exception might be caught by an AOP
 * aspect.
 *
 * @author Heiko Scherrer
 * @version 1.1
 * @since 0.2
 */
public class IntegrationLayerException extends TechnicalRuntimeException {

    /**
     * {@inheritDoc}
     */
    public IntegrationLayerException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public IntegrationLayerException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public IntegrationLayerException(String message, String messageKey) {
        super(message, messageKey);
    }

    /**
     * {@inheritDoc}
     */
    public IntegrationLayerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public IntegrationLayerException(String message, String messageKey, Throwable cause) {
        super(message, messageKey, cause);
    }
}
