package Models;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Course{
    
    private String courseName;
    private String courseCode;
    private String section;
    private String creditHours;
    private int maxCapacity;
    private int currentCapacity;

    private ArrayList<String> enrolledStudents; // Association - Course has many enrolled students

    public Course(String courseName, String courseCode, String section, String creditHours, int maxCapacity){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.section = section;
        this.creditHours = creditHours;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
        this.currentCapacity = 0; // Initialize current capacity to 0
    }

    public String getCourseName(){
        return courseName;
    }

    public String getCourseCode(){
        return courseCode;
    }

    public String getSection(){
        return section;
    }

    public String getCreditHours(){
        return creditHours;
    }

    public int getMaxCapacity(){
        return maxCapacity;
    }

    public int getCurrentCapacity(){
        return currentCapacity;
    }

    public void addCurrentCapacity() {
            currentCapacity++;
    }

    public void minusCurrentCapacity() {
        if (currentCapacity > 0) {
            currentCapacity--;
        }
    }

    public void addEnrolledStudent(String studentId) {
        if (!enrolledStudents.contains(studentId)) {
            enrolledStudents.add(studentId);
        }
    }

    public void listDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Course Name: ").append(courseName).append("\n");
        details.append("Course Code: ").append(courseCode).append("\n");
        details.append("Section: ").append(section).append("\n");
        details.append("Credit Hours: ").append(creditHours).append("\n");
        details.append("Max Capacity: ").append(maxCapacity).append("\n");
        details.append("Current Capacity: ").append(currentCapacity).append("\n\n");
        if (enrolledStudents.isEmpty()) {
            details.append("No students enrolled in this course.");
        } else {
            for (int i = 0; i < enrolledStudents.size(); i++) {
                details.append(i + 1).append(". ").append(enrolledStudents.get(i)).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, details.toString(), "Course Details", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Name: %s, Section: %s, Credit Hours: %s, Enrolled: %d/%d",
            courseCode, courseName, section, creditHours, currentCapacity, maxCapacity);
    }

}
