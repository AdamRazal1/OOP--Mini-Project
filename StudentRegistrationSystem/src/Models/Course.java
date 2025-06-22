package Models;
import java.util.ArrayList;

public class Course{
    
    private String courseName;
    private String courseCode;
    private String section;
    private String creditHours;
    private int maxCapacity;
    private int currentCapacity;

    private ArrayList<String> enrolledStudents; 

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
        System.out.println("\nCourse Name: " + courseName);
        System.out.println("Course Code: " + courseCode);
        System.out.println("Section: " + section);
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Max Capacity: " + maxCapacity);
        System.out.println("Current Capacity: " + currentCapacity);

        if(enrolledStudents.isEmpty()) {
            System.out.println("No students enrolled in this course.");
        }else{
            for (int i = 0; i < enrolledStudents.size(); i++) {
                System.out.println("Enrolled Student: ");
                System.out.println((i+1) + ". " + enrolledStudents.get(i));
            }
        }     
    }

    @Override
    public String toString() {
        return String.format("Code: %s, Name: %s, Section: %s, Credit Hours: %s, Enrolled: %d/%d",
            courseCode, courseName, section, creditHours, currentCapacity, maxCapacity);
    }

}
