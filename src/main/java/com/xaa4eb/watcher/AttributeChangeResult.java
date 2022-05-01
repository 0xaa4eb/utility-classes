package com.xaa4eb.watcher;

import com.xaa4eb.watcher.fields.Attribute;
import com.xaa4eb.watcher.fields.Value;
import com.xaa4eb.watcher.utils.ObjectUtils;

import java.util.Objects;

public class AttributeChangeResult {

    public static final AttributeChangeResult NOT_CHANGED = new AttributeChangeResult(false, null, null, null, -1L, -1L);

    private final boolean valueChanged;
    private final Attribute field;
    private final Value attributeValue;
    private final Object objectValue;
    private final long millisTime;
    private final long nanoTime;

    public AttributeChangeResult(boolean changed, Attribute field, Value attributeValue, Object objectValue, long millisTime, long nanoTime) {
        this.valueChanged = changed;
        this.field = field;
        this.attributeValue = attributeValue;
        this.objectValue = objectValue;
        this.millisTime = millisTime;
        this.nanoTime = nanoTime;
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

    public long getMillisTime() {
        return millisTime;
    }

    public long getNanoTime() {
        return nanoTime;
    }

    @Override
    public String toString() {
        return nanoTime +
                ";; " + millisTime +
                ";; " + field +
                ";; " + attributeValue +
                ";; " + ObjectUtils.toIdentityString(objectValue);
    }
}
