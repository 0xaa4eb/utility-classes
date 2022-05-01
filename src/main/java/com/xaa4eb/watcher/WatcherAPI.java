package com.xaa4eb.watcher;

public class WatcherAPI {

    private static Watcher instance = null;

    private static Watcher getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (WatcherAPI.class) {
            if (instance == null) {
                instance = new Watcher(new SystemOutAttributeChangesOutput());
            }
            return instance;
        }
    }

    public static synchronized void configure(AttributeChangesOutput output) {
        if (instance != null) {
            throw new IllegalStateException("Already configured");
        }
        instance = new Watcher(output);
    }

    public static void watchFields(Object object) {
        getInstance().watchFields(object);
    }

    public static void watchMethods(Object object) {
        getInstance().watchFields(object);
    }
}
