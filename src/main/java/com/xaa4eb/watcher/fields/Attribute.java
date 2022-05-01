package com.xaa4eb.watcher.fields;


/**
 * Some attribute of java object to be watched like instance field, method call return value, etc
 */
public interface Attribute {

    Value get(Object target);
}
