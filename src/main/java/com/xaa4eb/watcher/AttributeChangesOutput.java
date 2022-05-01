package com.xaa4eb.watcher;

public interface AttributeChangesOutput extends AutoCloseable {

    void onChange(AttributeChangeResult result);

    void close();
}
