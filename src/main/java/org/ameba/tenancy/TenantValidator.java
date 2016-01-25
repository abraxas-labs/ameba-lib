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
package org.ameba.tenancy;

/**
 * A TenantValidator validates the current tenant against a set of valid tenant names.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.1
 */
public interface TenantValidator {

    /**
     * Validate the name of the tenant against a whitelist of tenants.
     *
     * @param tenantName The name of the tenant to validate
     * @throws IllegalTenantException If the tenantName is not valid
     */
    void validate(String tenantName) throws IllegalTenantException;
}
