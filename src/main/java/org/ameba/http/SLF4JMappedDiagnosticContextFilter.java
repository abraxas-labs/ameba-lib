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
package org.ameba.http;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ameba.Constants;
import org.slf4j.MDC;

/**
 * A SLF4JMappedDiagnosticContextFilter adds the current tenant to SLF4J's Mapped Diagnostics Context.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.1
 * @see org.slf4j.MDC
 * @since 0.9
 */
public class SLF4JMappedDiagnosticContextFilter extends AbstractTenantAwareFilter {

    /**
     * {@inheritDoc}
     * Set the MDC properly.
     */
    @Override
    protected void doBefore(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String tenant) {
        MDC.put(Constants.HEADER_VALUE_TENANT, tenant);
    }

    /**
     * {@inheritDoc}
     * Remove the tenant information from MDC.
     */
    @Override
    protected void doAfter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String tenant) {
        MDC.remove(Constants.HEADER_VALUE_TENANT);
    }
}
