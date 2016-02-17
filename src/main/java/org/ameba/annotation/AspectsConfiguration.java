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
package org.ameba.annotation;

import org.ameba.aop.IntegrationLayerAspect;
import org.ameba.aop.PresentationLayerAspect;
import org.ameba.aop.ServiceLayerAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * A AspectsConfiguration.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.0
 * @since 1.0
 */
@Configuration
@EnableAspectJAutoProxy
class AspectsConfiguration {

    @Bean(name = ServiceLayerAspect.COMPONENT_NAME)
    public ServiceLayerAspect serviceLayerAspect() {
        return new ServiceLayerAspect();
    }

    @Bean(name = IntegrationLayerAspect.COMPONENT_NAME)
    public IntegrationLayerAspect integrationLayerAspect() {
        return new IntegrationLayerAspect();
    }

    @Bean(name = PresentationLayerAspect.COMPONENT_NAME)
    public PresentationLayerAspect presentationLayerAspect() {
        return new PresentationLayerAspect();
    }
}
