package com.xaa4eb.watcher;

import com.xaa4eb.watcher.fields.Attribute;
import com.xaa4eb.watcher.fields.ObjectValue;
import com.xaa4eb.watcher.fields.Value;

import java.util.Objects;

class AttributeWatcher {

    private final Object object;
    private final Attribute field;
    private Value lastSeenValue;

    AttributeWatcher(Object object, Attribute field) {
        this.object = object;
        this.field = field;
    }

    AttributeChangeResult checkAttribute() {
        try {
            Value value = field.get(object);
            if (!Objects.equals(value, lastSeenValue)) {
                lastSeenValue = value.copy();
                return new AttributeChangeResult(true, field, value, object, System.currentTimeMillis());
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return AttributeChangeResult.NOT_CHANGED;
    }
}
