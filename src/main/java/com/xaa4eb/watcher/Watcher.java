package com.xaa4eb.watcher;

import com.xaa4eb.watcher.fields.MethodCallValue;
import com.xaa4eb.watcher.fields.ObjectField;
import com.xaa4eb.watcher.fields.PrimitiveField;
import com.xaa4eb.watcher.utils.NamedThreadFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Watcher implements AutoCloseable {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(
            1,
            new NamedThreadFactory("Watcher", true)
    );
    private final WatchTask watchTask;

    Watcher(AttributeChangesOutput output) {
        this.watchTask = new WatchTask(output);

        this.executor.scheduleAtFixedRate(watchTask, 10, 10, TimeUnit.MILLISECONDS);
    }

    public synchronized void watchFields(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            AttributeWatcher watcher;
            if (field.getType().isPrimitive()) {
                watcher = new AttributeWatcher(object, new PrimitiveField(field));
            } else {
                watcher = new AttributeWatcher(object, new ObjectField(field));
            }
            watchTask.registerForWatching(watcher);
        }
    }

    public synchronized void watchMethods(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getParameterCount() == 0 && method.getReturnType() != Void.TYPE) {
                watchTask.registerForWatching(new AttributeWatcher(object, new MethodCallValue(method)));
            }
        }
    }

    @Override
    public void close() {
        try {

            watchTask.close();
        } finally {

            executor.shutdownNow();
            try {
                executor.awaitTermination(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
