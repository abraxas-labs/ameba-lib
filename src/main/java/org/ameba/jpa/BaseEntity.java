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
package org.ameba.jpa;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * A BaseEntity is a base superclass for JPA entities that comes with a mandatory ID field and a optimistic locking field.
 *
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version 1.1
 * @since 1.4
 */
@MappedSuperclass
public class BaseEntity {

    /** Persistent key, synonym for technical key or primary key. */
    @Id
    private Long id;
    /** Optimistic locking field (Property name {@code version} might be used differently). */
    @Version
    private long ol;

    /** Dear JPA ... */
    protected BaseEntity(){};

    /**
     * Checks whether this entity is a transient one or not.
     *
     * @return {@literal true} if transient, {@literal false} if detached or managed but not transient
     */
    public boolean isNew() {
        return id != null;
    }

    /**
     * Get the persistent key.
     *
     * @return The id property
     */
    public Long getId() {
        return id;
    }

    /**
     * Get the current optimistic locking version number.
     *
     * @return The number of the optimistic locking field
     */
    protected long getOl() {
        return ol;
    }
}
