/*
 * Copyright 2005-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ameba.http;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * A RestTemplateConfiguration instantiates a default {@link RestTemplate} when Spring Cloud is NOT on the classpath and registers a bean
 * with name {@literal aLoadBalanced}.
 *
 * @author Heiko Scherrer
 */
@ConditionalOnMissingClass("org.springframework.cloud.client.loadbalancer.LoadBalanced")
@Configuration
class RestTemplateConfiguration {

    @ConditionalOnMissingBean(name = "aLoadBalanced")
    @Bean
    RestTemplate aLoadBalanced(List<BaseClientHttpRequestInterceptor> baseInterceptors) {
        var restTemplate = new RestTemplate();
        restTemplate.getInterceptors().addAll(baseInterceptors);
        return restTemplate;
    }
}
