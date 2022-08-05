package api.testapi.validators;

public class UserDataValidator {
    private static String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+[.][a-zA-Z0-9.-]+$";

    public static boolean isValidEmail(String email) {
        return email.matches(regex);
    }
}
