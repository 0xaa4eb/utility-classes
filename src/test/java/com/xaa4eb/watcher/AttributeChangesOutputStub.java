package com.xaa4eb.watcher;

import java.util.ArrayList;
import java.util.List;

public class AttributeChangesOutputStub implements AttributeChangesOutput {

    private final List<AttributeChangeResult> results = new ArrayList<>();

    @Override
    public synchronized void onChange(AttributeChangeResult result) {
        this.results.add(result);
    }

    public List<AttributeChangeResult> getResults() {
        return new ArrayList<>(results);
    }
}
