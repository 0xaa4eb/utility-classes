package com.xaa4eb.watcher.fields;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCallValue implements Attribute {

    private final Method method;

    public MethodCallValue(Method method) {
        this.method = method;
    }

    @Override
    public Value get(Object target) {
        try {
            return new ObjectValue(method.invoke(target));
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
