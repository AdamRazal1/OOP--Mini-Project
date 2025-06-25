package Models; // Make sure this matches your package structure

// Throw exception when course is already registered
public class DuplicateCourseException extends Exception {
    public DuplicateCourseException(String message) {
        super(message);
    }
}