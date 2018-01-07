package com.minh.nguyen.util;

import com.sun.javafx.beans.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mr.Minh
 * @since 04/01/2018
 * Purpose:
 */
@Service
public class ExceptionUtil {

    public String getMessage(@NonNull Throwable t) {
        String message = t.getMessage();

        while (message == null) {
            if ((t = t.getCause()) == null) {
                break;
            }

            message = t.getMessage();
        }

        return message;
    }

    public String toString(Throwable e) {
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