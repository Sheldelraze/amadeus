package com.minh.nguyen.util;

import com.minh.nguyen.constants.Constants;

/**
 * @author Mr.Minh
 * @since 09/01/2018
 * Purpose:
 */
public class StringUtil {


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

    public static String convertNull(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }

    public static String buildString(String... args) {
        StringBuilder string = new StringBuilder();
        for (String s : args) {
            string.append(s);
        }
        return string.toString();
    }

    public static String makeTextBoldInHTML(String s) {
        return buildString("<b>", s, "</b>");
    }
    public static boolean isNull(Object str) {
        return str == null || str.toString().length() <= 0;
    }
    public static boolean isBlank(String s){
        if (s == null){
            return true;
        }
        for(int i = 0;i < s.length();i++){
            char x = s.charAt(i);
            if (x == ' ' || x == '\n' || x == '\r' || x == '\t'){
                continue;
            }
            return false;
        }
        return true;
    }
    public static String trimStr(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    public static CompareResult compareString(String output, String answer) {
        CompareResult compareResult = new CompareResult();
        String[] out = output.split("\r\n");
        String[] ans = answer.split("\r\n");
        String result = "";
        if (out.length == 0 && ans.length > 0) {
            compareResult.setStatus(Constants.STATUS_WRONG_ANSWER);
            result = new StringBuilder().append("Sai! Không có output!").toString();
        } else if (out.length > ans.length) {
            compareResult.setStatus(Constants.STATUS_WRONG_ANSWER);
            result = new StringBuilder().append("Số dòng người dùng xuất (")
                    .append(out.length)
                    .append(") nhiều hơn so với kết quả (")
                    .append(ans.length).append(")!").toString();
        } else if(out.length < ans.length){
            compareResult.setStatus(Constants.STATUS_WRONG_ANSWER);
            result = new StringBuilder().append("Số dòng người dùng xuất (")
                    .append(out.length)
                    .append(") ít hơn so với kết quả (")
                    .append(ans.length).append(")!").toString();
        } else{
            boolean correct = true;
            for(int i = 0;i < ans.length;i++){
                if (!out[i].trim().equals(ans[i].trim())) {
                    result =  new StringBuilder().append("Sai kết quả ở dòng thứ ")
                            .append(i + 1)
                            .append(":\r\n")
                            .append("Output: ")
                            .append(getFirstPartOfString(out[i], 100))
                            .append("\r\n")
                            .append("Kết quả: ")
                            .append(getFirstPartOfString(ans[i], 100)).toString();
                    compareResult.setStatus(Constants.STATUS_WRONG_ANSWER);
                    correct = false;
                    break;
                }
            }
            if (correct) {
                result = "Đúng";
                compareResult.setStatus(Constants.STATUS_ACCEPTED);
            }
        }
        compareResult.setResult(result);
        return compareResult;
    }

    public static class CompareResult {
        private String result;
        private int status;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String getFirstPartOfString(String s, int length) {
        if (null == s) {
            return null;
        }
        if (s.length() > length) {
            s = s.substring(0, length);
            s += "...";
        }
        return s;
    }

}
