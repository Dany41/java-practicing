package org.practicalunittesting;

public class PasswordValidator {

    private static final int MINIMUM_POSSIBLE_PASSWORD_LENGTH = 10;
    private static final int MINIMUM_DIGITS_CONTAINING = 3;


    public static boolean validate(String password) {
        return validateLength(password) && validateContainingDigits(password) && validateSpecialSymbols(password);
    }

    public static boolean validateLength(String password) {
        return password.length() >= MINIMUM_POSSIBLE_PASSWORD_LENGTH;
    }

    public static boolean validateContainingDigits(String password) {
        return password.chars().filter(Character::isDigit).toArray().length >= MINIMUM_DIGITS_CONTAINING;
    }


    public static boolean validateSpecialSymbols(String password) {
        return password.contains("_");
    }
}
