package com.minh.nguyen.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author Mr.Minh
 * @since 06/01/2018
 * Purpose:
 */
@Component("CheckUtil")
public class CheckUtil {

    public boolean isDateFormat(String dateString, String formatString) {
        DateFormat formatter = new SimpleDateFormat(formatString);
        try {
            Date date = formatter.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\"
                + ".[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public boolean isSignNumber(String number) {
        if (isNull(number)) {
            return true;
        }

        Pattern p = Pattern.compile("^[-]?[0-9]+$");
        Matcher m = p.matcher(number);

        return m.find();
    }

    public boolean isNull(String str) {
        return (str == null || str.length() <= 0);
    }

    public boolean isDigit(char c) {
        int x = (int) c;
        if ((x >= 48 && x <= 57) || x == 45) {
            return true;
        }
        return false;
    }
}
