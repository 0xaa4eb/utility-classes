package com.xaa4eb.watcher.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public class NamedThreadFactory implements ThreadFactory {

    private final String name;
    private final boolean daemon;
    private final AtomicLong nextIndex = new AtomicLong(-1);

    public NamedThreadFactory(String name, boolean daemon) {
        this.name = name;
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r, name + "-" + nextIndex.incrementAndGet());
        t.setDaemon(daemon);
        return t;
    }
}
