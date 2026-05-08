import java.util.regex.Pattern;

public class User {

    private String username;
    private String password;

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9._\\-+%]+@[a-zA-Z0-9][a-zA-Z0-9.-]*\\.[a-zA-Z]{2,}$";

    public User(String username, String password) throws Exception {

        validateUsername(username);
        validatePassword(password);

        this.username = username;
        this.password = password;
    }

    private void validateUsername(String email) throws Exception {

        if (!Pattern.matches(EMAIL_PATTERN, email)) {
            throw new Exception("Please enter a valid Email as username");
        }

        if (email.length() > 50) {
            throw new Exception("Username is too long");
        }
    }

    private void validatePassword(String pass) throws Exception {

        if (pass.length() < 8) {
            throw new Exception("Password is too short");
        }

        if (pass.length() > 12) {
            throw new Exception("Password is too long");
        }

        boolean hasLetter = pass.matches(".*[a-zA-Z].*");
        boolean hasDigit = pass.matches(".*[0-9].*");
        boolean hasSymbol = pass.matches(".*[,#@!+\\.].*");

        if (!hasLetter || !hasDigit || !hasSymbol) {
            throw new Exception("Please enter a valid password");
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User: " + username + " Password: " + password;
    }
}