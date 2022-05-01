package com.xaa4eb.watcher.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Objects;

class ObjectUtilsTest {

    public static class X implements Serializable {
        private final int d;

        public X(int d) {
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            X x = (X) o;
            return d == x.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(d);
        }
    }

    @Test
    public void testString() {

        String s = new String("ABC");
        Object t = ObjectUtils.deepCopy(s);

        Assertions.assertEquals(t, s);
        Assertions.assertNotSame(t, s);
    }
}