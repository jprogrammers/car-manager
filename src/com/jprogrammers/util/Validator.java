package com.jprogrammers.util;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Vahid Forghani on 1/12/14.
 */
public class Validator {

    private static final Pattern numberPattern = Pattern.compile("[0-9]+");
    private static final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    public static boolean isNumber(String number){

        return (number != null && numberPattern.matcher(number.trim()).matches());

    }

    public static boolean isNullOrEmpty(String str) {

        return (str == null || str.trim().equals("") || str.trim().equals("null"));
    }

    public static boolean isNationalCode(String nationalCode) {
        
        if(!isNumber(nationalCode))
            return false;

        if (nationalCode.length() != 10)
            return false;
        
        int c = Character.getNumericValue(nationalCode.charAt(9));

        int n = Character.getNumericValue(nationalCode.charAt(0)) * 10 +
                Character.getNumericValue(nationalCode.charAt(1)) * 9 +
                Character.getNumericValue(nationalCode.charAt(2)) * 8 +
                Character.getNumericValue(nationalCode.charAt(3)) * 7 +
                Character.getNumericValue(nationalCode.charAt(4)) * 6 +
                Character.getNumericValue(nationalCode.charAt(5)) * 5 +
                Character.getNumericValue(nationalCode.charAt(6)) * 4 +
                Character.getNumericValue(nationalCode.charAt(7)) * 3 +
                Character.getNumericValue(nationalCode.charAt(8)) * 2;
        int r = n % 11;

        if(r < 2){
            if(r == c)
                return true;
        } else {
            if(c == 11 - r)
                return true;
        }

        return false;
    }

    public static boolean isEmailAddress(String emailAddress) {

        return emailAddress != null && emailPattern.matcher(emailAddress).matches();

    }
}
