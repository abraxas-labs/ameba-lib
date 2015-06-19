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
package org.ameba.i18n;

/**
 * A Translator resolves an unique message by key, interpolates and translates into String message.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 0.1
 * @since 0.3
 */
public interface Translator {

    /**
     * Resolve a message by {@literal key}, interpolate with arguments {@literal objects} and return the translated
	 * message.
     *
     * @param key
     *            The error code to search the message text for
     * @param objects
     *            Any arguments that are passed into the message text
     * @return The message as String
     */
    String translate(String key, Object... objects);
}
