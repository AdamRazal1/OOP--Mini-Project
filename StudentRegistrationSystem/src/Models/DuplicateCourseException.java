package Models; // Make sure this matches your package structure

public class DuplicateCourseException extends Exception {
    public DuplicateCourseException(String message) {
        super(message);
    }
}