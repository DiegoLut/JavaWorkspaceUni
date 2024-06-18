package pl.lodz.eksoc.userlistmanagement.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    public static boolean isAgeValid(int age)
    {
        if(age > 123 || age < 1)
        {
            return false;
        }

        return true;
    }

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isEmailValid(String email)
    {
        if (email == null)
        {
            return false;
        }

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

}
