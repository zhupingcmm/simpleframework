package org.simplefrawork.util;

public class ValidationUtil {
    public static boolean isEmpty (String obj) {
        return (obj == null || "".equalsIgnoreCase(obj));
    }
}
