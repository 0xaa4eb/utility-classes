package com.xaa4eb.watcher;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class WatchTask implements Runnable {

    private final List<AttributeWatcher> fieldsToWatch = new CopyOnWriteArrayList<>();
    private final AttributeChangesOutput output;

    public WatchTask(AttributeChangesOutput output) {
        this.output = output;
    }

    public void registerForWatching(AttributeWatcher watcher) {

        // First time check in caller thread
        AttributeChangeResult change = watcher.checkAttribute();
        if (change.hasValueChanged()) {
            output.onChange(change);
        }

        this.fieldsToWatch.add(watcher);
    }

    @Override
    public void run() {
        for (AttributeWatcher field : fieldsToWatch) {
            try {

                AttributeChangeResult change = field.checkAttribute();

                if (change.hasValueChanged()) {
                    output.onChange(change);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }
}
