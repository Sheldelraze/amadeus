package com.minh.nguyen.Util;
import com.sun.javafx.beans.annotations.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
public class ExceptionUtil {
    private ExceptionUtil() {
        throw new UnsupportedOperationException();
    }

    public static String getMessage(@NonNull Throwable t) {
        String message = t.getMessage();

        while (message == null) {
            if ((t = t.getCause()) == null) {
                break;
            }

            message = t.getMessage();
        }

        return message;
    }

    public static String toString(Throwable e) {
        Set<Throwable> used = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        while (e != null && !used.contains(e)) {
            if (!used.isEmpty()) {
                sb.append('\n');
            }

            used.add(e);
            sb.append(e.getClass().getName()).append(": ").append(e.getMessage()).append('\n');
            StackTraceElement[] elements = e.getStackTrace();
            for (StackTraceElement element : elements) {
                sb.append("    ").append(element.toString()).append('\n');
            }
            e = e.getCause();
        }

        return sb.toString();
    }
}