package com.xaa4eb.watcher;

import com.xaa4eb.watcher.utils.NamedThreadFactory;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemOutAttributeChangesOutput implements AttributeChangesOutput {

    private final Queue<AttributeChangeResult> changes = new LinkedBlockingQueue<>();
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(
            1,
            new NamedThreadFactory("SystemOutAttributeChangesOutput", true)
    );

    public SystemOutAttributeChangesOutput() {
        executor.scheduleAtFixedRate(this::processChanges, 10, 10, TimeUnit.MILLISECONDS);
    }

    private void processChanges() {
        while (!changes.isEmpty()) {
            AttributeChangeResult change = changes.poll();
            System.out.println(change);
        }
    }

    @Override
    public void onChange(AttributeChangeResult result) {
        changes.add(result);
    }

    @Override
    public void close() {

    }
}
