package com.xaa4eb.watcher.fields;

import java.util.Objects;

public class PrimitiveField implements Attribute {

    private final java.lang.reflect.Field field;
    private final int type;

    public PrimitiveField(java.lang.reflect.Field field) {
        this.field = field;
        Class<?> type = field.getType();
        if (type == Long.TYPE) {
            this.type = 1;
        } else if (type == Byte.TYPE) {
            this.type = 2;
        } else if (type == Short.TYPE) {
            this.type = 3;
        } else if (type == Double.TYPE) {
            this.type = 4;
        } else if (type == Float.TYPE) {
            this.type = 5;
        } else if (type == Boolean.TYPE) {
            this.type = 6;
        } else if (type == Integer.TYPE) {
            this.type = 7;
        } else if (type == Character.TYPE) {
            this.type = 8;
        } else {
            throw new RuntimeException("Unknown primitive type " + type);
        }
    }

    @Override
    public Value get(Object target) {
        try {
            if (type == 1) {
                return new PrimitiveValue(type, field.getLong(target));
            } else if (type == 2) {
                return new PrimitiveValue(type, field.getByte(target));
            } else if (type == 3) {
                return new PrimitiveValue(type, field.getShort(target));
            } else if (type == 4) {
                return new PrimitiveValue(type, Double.doubleToLongBits(field.getDouble(target)));
            } else if (type == 5) {
                return new PrimitiveValue(type, Float.floatToIntBits(field.getFloat(target)));
            } else if (type == 6) {
                return new PrimitiveValue(type, field.getBoolean(target) ? 1 : 0);
            } else if (type == 7) {
                return new PrimitiveValue(type, field.getInt(target));
            } else if (type == 8) {
                return new PrimitiveValue(type, field.getChar(target));
            } else {
                throw new RuntimeException("Unknown primitive type " + type);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static class PrimitiveValue implements Value {

        private final int type;
        private final long value;

        private PrimitiveValue(int type, long value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PrimitiveValue that = (PrimitiveValue) o;
            return type == that.type && value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, value);
        }

        @Override
        public Value copy() {
            return this;
        }

        @Override
        public String toString() {
            if (type == 1) {
                return String.valueOf(value);
            } else if (type == 2) {
                return "b" + String.valueOf((byte) value);
            } else if (type == 3) {
                return String.valueOf(value);
            } else if (type == 4) {
                return String.valueOf(Double.longBitsToDouble(value));
            } else if (type == 5) {
                return Float.intBitsToFloat((int) value) + "f";
            } else if (type == 6) {
                return value == 1 ? "true" : "false";
            } else if (type == 7) {
                return String.valueOf(value);
            } else if (type == 8) {
                return "'" + (char) value + "'";
            } else {
                throw new RuntimeException("Unknown primitive type " + type);
            }
        }
    }

    @Override
    public String toString() {
        return field.toString();
    }
}
