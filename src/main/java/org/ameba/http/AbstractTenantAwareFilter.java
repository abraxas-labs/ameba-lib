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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.ameba.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * A AbstractTenantAwareFilter is a super class that handles resolution and validation of the current tenant from the
 * incoming request. The tenant information is expected to be present as http header attribute with name {@value Constants#HEADER_VALUE_TENANT}.
 * The behavior of the validation of attribute existence can be configured via two {@code ServletContext} properties:
 * <ul>
 * <li>{@value Constants#PARAM_MULTI_TENANCY_ENABLED}: If {@literal true}, the filter expects the tenant attribute
 * set in the current request and goes further with validation and processing the tenant information.</li>
 * <li>{@value Constants#PARAM_MULTI_TENANCY_THROW_IF_NOT_PRESENT}: If the tenant attribute is not present in the
 * current request and this property is set to {@literal true}, the filter will throw an exception. If set to {@literal false}
 * the filter ignores the missing attribute and goes further in the {@code filterChain} without calling the
 * subclasses {@link #doBefore(HttpServletRequest, HttpServletResponse, FilterChain, String)} method</li>
 * </ul>
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 * @see org.springframework.web.filter.OncePerRequestFilter
 */
public abstract class AbstractTenantAwareFilter extends OncePerRequestFilter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected final void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        boolean multiTenancyEnabled = Boolean.valueOf((String) request.getServletContext().getAttribute(Constants.PARAM_MULTI_TENANCY_ENABLED));
        String tenant = null;
        if (multiTenancyEnabled && !"OPTIONS".equalsIgnoreCase(request.getMethod())) {
            tenant = request.getHeader(Constants.HEADER_VALUE_TENANT);
            if (null == tenant || tenant.isEmpty()) {
                boolean throwIfNotPresent = Boolean.valueOf((String) request.getServletContext().getAttribute(Constants.PARAM_MULTI_TENANCY_THROW_IF_NOT_PRESENT));
                if (throwIfNotPresent) {
                    response.setStatus(HttpStatus.BAD_REQUEST.value());
                    throw new IllegalArgumentException(String.format("No tenant information available in http header. Expected header %s attribute not present.", Constants.HEADER_VALUE_TENANT));
                }
            } else {
                doBefore(request, response, filterChain, tenant);
            }
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            doAfter(request, response, filterChain, tenant);
        }
    }

    /**
     * Do something before the {@code request} is passed to the next filter in the {@code filterChain}.
     *
     * @param request Incoming request
     * @param response Passed response
     * @param filterChain The chain of filters
     * @param tenant The current tenant
     */
    protected void doBefore(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String tenant) {
    }

    /**
     * Do something after the is through the {@code filterChain} and before it is passed to the next outer filter in chain.
     *
     * @param request Incoming request
     * @param response Passed response
     * @param filterChain The chain of filters
     * @param tenant The current tenant or {@literal null}
     */
    protected void doAfter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain, String tenant) {
    }
}
