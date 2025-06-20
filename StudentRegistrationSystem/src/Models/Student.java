package Models;
import java.util.ArrayList;
import java.util.Scanner;

// Demonstrate Inheritance
public class Student extends User{

    // Demonstrate Aggregation - 'has a' relationship between Student and Course
    private ArrayList<Course> enrolledCourses;

    public Student(String userId, String password){
        super(userId, password);
        enrolledCourses = new ArrayList<>();
    }

    public ArrayList<Course> getEnrolledCourses(){
        return enrolledCourses;
    }

    public void registerCourse(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter course code: ");
        String courseCode = scanner.nextLine();

        for(Course course : courses){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                if(course.getCurrentCapacity() < course.getMaxCapacity()){
                    enrolledCourses.add(course);
                    System.out.println("You have register " + course.getCourseName() + " successfully!");
                    course.addCurrentCapacity();
                    course.addEnrolledStudent(this.getUserId());
                } else{
                    System.out.println("Course is full! Cannot register.");
                }
                break; // Exit loop after finding the course
            }
        }
    }

    public void removeEnrolledStudents(Course course){
            enrolledCourses.remove(course);
            course.minusCurrentCapacity(); // Decrease the current capacity    
    }

    public void dropCourse(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter course code: ");
        String courseCode = scanner.nextLine();

        for(Course course : enrolledCourses){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                enrolledCourses.remove(course);
                course.minusCurrentCapacity(); // Decrease the current capacity
                System.out.println(course.getCourseName() + " dropped successfully!");
                return;
            }
        }
        System.out.println("Course not found in your registered courses!");

    }

    public void viewRegisteredCourses(){
        for(Course course : this.getEnrolledCourses()){
            System.out.println(course);
        }
    }

    public void viewAvailableCourses(){
        System.out.println("\n--- Available Courses ---");
        for(Course course : courses){
            System.out.println(course);
        }
    }

    // Demonstrate Polymorphism - overriding abstract method
    @Override
    public void displayMenu(){
        System.out.println("\n--- Student Menu ---");
        System.err.println("1. View Available Courses");
        System.out.println("2. Register for a Course");
        System.out.println("3. Drop a Course");
        System.out.println("4. View My Registered Courses");
        System.out.println("5. Logout");
    }
}
