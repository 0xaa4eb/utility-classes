package com.xaa4eb.watcher;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

class WatcherTest {

    public static class X {

        private int prop1;

        public X(int prop1) {
            this.prop1 = prop1;
        }
    }

    @Test
    public void shouldDetectChangeOfPrimitiveIntField() {
        AttributeChangesOutputStub out = new AttributeChangesOutputStub();
        try (Watcher watcher = new Watcher(out)) {

            X x = new X(5);

            watcher.watchFields(x);

            Assertions.assertEquals(1, out.getResults().size());
            Assertions.assertEquals("5", out.getResults().get(0).getAttributeValue().toString());

            x.prop1 = 6;

            Awaitility.await().atMost(Duration.ofMinutes(1)).untilAsserted(
                    () -> {
                        Assertions.assertEquals(2, out.getResults().size());
                        AttributeChangeResult change = out.getResults().get(1);
                        Assertions.assertEquals("6", change.getAttributeValue().toString());
                    }
            );
        }
    }

    public static class Y {

        private String prop1;

        public Y(String prop1) {
            this.prop1 = prop1;
        }
    }

    @Test
    public void shouldDetectChangeOfStringField() {
        AttributeChangesOutputStub out = new AttributeChangesOutputStub();
        try (Watcher watcher = new Watcher(out)) {

            Y y = new Y("ABC");

            watcher.watchFields(y);

            Assertions.assertEquals(1, out.getResults().size());
            Assertions.assertEquals("ABC", out.getResults().get(0).getAttributeValue().toString());

            y.prop1 = "CDE";

            Awaitility.await().atMost(Duration.ofMinutes(1)).untilAsserted(
                    () -> {
                        Assertions.assertEquals(2, out.getResults().size());
                        AttributeChangeResult change = out.getResults().get(1);
                        Assertions.assertEquals("CDE", change.getAttributeValue().toString());
                    }
            );
        }
    }

    public static class A {

        private int val;

        public int getVal() {
            return val + 5;
        }
    }

    @Test
    public void shouldDetectMethodReturnValueChanges() {

        AttributeChangesOutputStub out = new AttributeChangesOutputStub();
        try (Watcher watcher = new Watcher(out)) {
            A a = new A();
            a.val = 42;

            watcher.watchMethods(a);

            Assertions.assertEquals(1, out.getResults().size());
            Assertions.assertEquals("47", out.getResults().get(0).getAttributeValue().toString());

            a.val = 55;

            Awaitility.await().atMost(Duration.ofMinutes(1)).untilAsserted(
                    () -> {
                        Assertions.assertEquals(2, out.getResults().size());
                        AttributeChangeResult change = out.getResults().get(1);
                        Assertions.assertEquals("60", change.getAttributeValue().toString());
                    }
            );
        }
    }

    public static class B {

        private List<String> listProp = new ArrayList<>();
    }

    @Test
    public void shouldDetectedChangesToCollections() {

        AttributeChangesOutputStub out = new AttributeChangesOutputStub();
        try (Watcher watcher = new Watcher(out)) {
            B b = new B();

            watcher.watchFields(b);

            Assertions.assertEquals(1, out.getResults().size());
            Assertions.assertEquals("[]", out.getResults().get(0).getAttributeValue().toString());

            b.listProp.add("A");

            Awaitility.await().atMost(Duration.ofMinutes(1)).untilAsserted(
                    () -> {
                        Assertions.assertEquals(2, out.getResults().size());
                        AttributeChangeResult change = out.getResults().get(1);
                        Assertions.assertEquals("[A]", change.getAttributeValue().toString());
                    }
            );
        }
    }
}