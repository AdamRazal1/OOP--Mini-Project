package Models;
import java.util.ArrayList;
import java.util.Scanner;

// Demonstrate Inheritance
public class Admin extends User{

    private ArrayList<Student> students;
    
    public Admin(String userId, String password, ArrayList<Student> students){
        super(userId, password);
        this.students = students;
    }

    @Override
    public void displayMenu(){
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add a Course");
        System.out.println("2. Remove a Course");
        System.out.println("3. View All Courses");
        System.out.println("4. View Course Details");
        System.out.println("5. View All Students");
        System.out.println("6. Logout");
    }

    public void addNewCourse(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.println("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Section: ");
        String section = scanner.nextLine();

        System.out.println("Enter Credit Hours: ");
        String creditHours = scanner.nextLine();

        System.out.println("Enter Maximum Capacity: ");
        int maxCapacity = scanner.nextInt();
        // scanner.nextLine(); // clear newline

        // Check if course code already exists
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                System.out.println("\nA course with this code already exists!");
                return;
            }
        }

        Course newCourse = new Course(courseName, courseCode, section, creditHours, maxCapacity);
        courses.add(newCourse);
        System.out.println("\nCourse added successfully!");

    }

    public void deleteCurrentCourse(){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nEnter Course Code to Delete: ");
        String courseCode = scanner.nextLine();

        Course toRemove = null;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                toRemove = course;
                break;
            }
        }

        if (toRemove != null) {
            courses.remove(toRemove);

            // Also remove this course from all students' enrolled lists
            for (Student student : students) {
                student.removeEnrolledStudents(toRemove);
            }

            System.out.println("\n" + toRemove.getCourseName() + " deleted successfully!");
        } else {
            System.out.println("\nCourse not found!");
        }
    }

    public void viewAllCourses(){
        if (courses.isEmpty()) {
            System.out.println("\nNo courses available.");
        }else {
            System.out.println("\n--- List of Courses ---");

            for(int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
                System.out.println((i+1) + ". " + course);
            }  
        }
    }

    public void viewCourseDetails(){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter Course Code to View Details: ");
        String courseCode = scanner.nextLine();

        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                course.listDetails();
            }
        }
    }

    public void viewAllStudents(){
        System.out.println("\n--- List of Students ---");

        for(int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ". " + student.getUserId());
        }
    }
}