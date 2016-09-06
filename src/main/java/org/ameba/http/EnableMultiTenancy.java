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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.MultiTenancySelector;

/**
 * A EnableMultiTenancy is able to enable Spring configuration for multi-tenancy.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.7
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MultiTenancySelector.class)
public @interface EnableMultiTenancy {

    /**
     * Turn multi-tenancy support on or off. Default is {@literal true}.
     *
     * @return Set to {@literal false}, to disable
     */
    boolean enabled() default true;

    /**
     * Define whether an exception should be thrown when multi-tenancy is enabled but no tenant information is available. Default is
     * {@literal false}.
     *
     * @return Set to {@literal true} to throw exception if not present
     */
    boolean throwIfNotPresent() default false;
}