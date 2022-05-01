package com.xaa4eb.watcher.fields;

import com.xaa4eb.watcher.utils.ObjectUtils;

import java.io.Serializable;
import java.util.Objects;

public class ObjectValue implements Value {

    private final Object value;

    public ObjectValue(Object value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectValue that = (ObjectValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Value copy() {
        if (value instanceof Serializable) {
            return new ObjectValue(ObjectUtils.deepCopy(value));
        } else {
            return new ObjectValue(value);
        }
    }

    @Override
    public String toString() {
        if (value == null) {
            return "null";
        } else {
            return Objects.toString(value);
        }
    }
}
