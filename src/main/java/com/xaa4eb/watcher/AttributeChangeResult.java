package com.xaa4eb.watcher;

import com.xaa4eb.watcher.fields.Attribute;
import com.xaa4eb.watcher.fields.Value;
import com.xaa4eb.watcher.utils.ObjectUtils;

import java.util.Objects;

public class AttributeChangeResult {

    public static final AttributeChangeResult NOT_CHANGED = new AttributeChangeResult(false, null, null, null, -1L);

    private final boolean valueChanged;
    private final Attribute field;
    private final Value attributeValue;
    private final Object objectValue;
    private final long millisTimeDetected;

    public AttributeChangeResult(boolean changed, Attribute field, Value attributeValue, Object objectValue, long millisTimeDetected) {
        this.valueChanged = changed;
        this.field = field;
        this.attributeValue = attributeValue;
        this.objectValue = objectValue;
        this.millisTimeDetected = millisTimeDetected;
    }

    public boolean hasValueChanged() {
        return valueChanged;
    }

    public Value getAttributeValue() {
        return attributeValue;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public long getMillisTimeDetected() {
        return millisTimeDetected;
    }

    @Override
    public String toString() {
        return "FieldChangeResult{" +
                " field=" + field +
                ", newValue=" + attributeValue +
                ", objectValue=" + ObjectUtils.toIdentityString(objectValue) +
                ", millisTimeDetected=" + millisTimeDetected +
                '}';
    }
}
