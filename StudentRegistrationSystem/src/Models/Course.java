package Models;
import java.util.ArrayList;

public class Course{
    
    private String courseName;
    private String courseCode;
    private String section;
    private String creditHours;
    private int maxCapacity;

    private ArrayList<String> enrolledStudents;

    public Course(String courseName, String courseCode, String section, String creditHours, int maxCapacity){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.section = section;
        this.creditHours = creditHours;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
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

    @Override
    public String toString() {
        return String.format("Code: %s, Name: %s, Section: %s, Credit Hours: %s, Enrolled: %d/%d",
            courseCode, courseName, section, creditHours, enrolledStudents.size(), maxCapacity);
    }

}
