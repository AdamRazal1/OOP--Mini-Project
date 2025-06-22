package Models;

public class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}