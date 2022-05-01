package com.xaa4eb.watcher.fields;

public class ObjectField implements Attribute {

    private final java.lang.reflect.Field field;

    public ObjectField(java.lang.reflect.Field field) {
        this.field = field;
    }

    @Override
    public Value get(Object target) {
        try {
            return new ObjectValue(field.get(target));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return field.toString();
    }
}
