package com.epam.university.java.core.task041;

import java.util.Objects;

/**
 * Author Dmitry Novikov 22-Oct-20.
 */
public class EntityImpl implements Entity {
    private int id = 0;
    private String value;

    public EntityImpl(String value) {
        this.value = value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityImpl entity = (EntityImpl) o;
        return Objects.equals(getValue(), entity.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
