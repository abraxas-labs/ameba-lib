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
package org.ameba.system;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.Validator;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * A ValidationUtil.
 *
 * @author Heiko Scherrer
 */
public final class ValidationUtil {

    public static <T> T validate(Validator validator, T obj, Class<?>... clazz) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj, clazz);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(format("Validation error. Invalid fields [%s]",
                    violations
                            .stream()
                            .map(ConstraintViolation::getPropertyPath)
                            .map(Path::toString)
                            .collect(Collectors.joining(","))),
            violations);
        }
        return obj;
    }
}
