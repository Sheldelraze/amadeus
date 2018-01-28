package com.minh.nguyen.util;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author Mr.Minh
 * @since 09/01/2018
 * Purpose:
 */
@Component("StringUtil")
public class StringUtil {

    private static final int SPACE_HEX_IN_ASCII = 0x20;

    /**
     * Loại bỏ đường dẫn khi có compile Error, ví dụ:
     * E:\main\example\test.cpp: In function 'int main()':
     * sẽ trở thành
     * test.cpp: In function 'int main()':
     * @param s
     * @param extension
     * @return
     */
    public static String trimLocation(String s, String extension) {
        String[] lines = s.split("\n");
        for (int i = 0; i < lines.length; i++) {
            int pos = lines[i].indexOf("." + extension);
            for (int j = pos - 1; j >= 0; j--) {
                if (lines[i].charAt(j) == '\\'
                        || lines[i].charAt(j) == '/') {
                    lines[i] = lines[i].substring(j + 1);
                    break;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < lines.length; i++) {
            stringBuilder.append(lines[i]);
            if (i != lines.length - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
    public static String convertToWellForm(String s){
        return s.replaceAll("\r","");
    }
    public static String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static boolean isNull(Object str) {
        return str == null || str.toString().length() <= 0;
    }

    public static String trimStr(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isUppercaseAlpha(char c) {
        return (c >= 'A') && (c <= 'Z');
    }

    public static boolean isLowercaseAlpha(char c) {
        return (c >= 'a') && (c <= 'z');
    }

    public static char toUpperAscii(char c) {
        if (isLowercaseAlpha(c)) {
            c -= (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    public static char toLowerAscii(char c) {
        if (isUppercaseAlpha(c)) {
            c += (char) SPACE_HEX_IN_ASCII;
        }
        return c;
    }

    public static String camelhumpToUnderline(String str) {
        final int size;
        final char[] chars;
        final StringBuilder sb = new StringBuilder(
                (size = (chars = str.toCharArray()).length) * 3 / 2 + 1);
        char c;
        for (int i = 0; i < size; i++) {
            c = chars[i];
            if (isUppercaseAlpha(c)) {
                sb.append('_').append(toLowerAscii(c));
            } else {
                sb.append(c);
            }
        }
        return sb.charAt(0) == '_' ? sb.substring(1) : sb.toString();
    }

    public static Integer toInt(String str) {
        if (str != null && str.length() > 0) {
            return Integer.valueOf(str);
        } else {
            return 0;
        }
    }

    public static Long toLong(String str) {
        if (str != null && str.length() > 0) {
            return Long.valueOf(str);
        } else {
            return 0L;
        }
    }
    public String trimString(String s){
        if (null == s){
            return null;
        }
        if (s.length() > 100){
            s = s.substring(0,100);
            s += "...";
        }
        return s;
    }
    public static BigInteger toBigInteger(String str) {
        if (str != null && str.length() > 0) {
            return new BigInteger(str);
        } else {
            return new BigInteger("0");
        }
    }
}
