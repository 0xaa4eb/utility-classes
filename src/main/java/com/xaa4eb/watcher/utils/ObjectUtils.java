package com.xaa4eb.watcher.utils;

import java.io.*;

public class ObjectUtils {

    public static String toIdentityString(Object object) {
        if (object == null) {
            return "null";
        }
        return object.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(object));
    }

    public static Object deepCopy(Object object) {
        if (!(object instanceof Serializable)) {
            throw new RuntimeException("Can not deep copy object which is not Serializable");
        }

        try (ByteArrayOutputStream bytes = new ByteArrayOutputStream(); ObjectOutputStream out = new ObjectOutputStream(bytes)) {

            out.writeObject(object);

            try (ByteArrayInputStream inputBytes = new ByteArrayInputStream(bytes.toByteArray());
                ObjectInputStream in = new ObjectInputStream(inputBytes)) {

                return in.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
