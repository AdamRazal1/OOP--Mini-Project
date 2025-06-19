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
        System.out.println("4. View All Students");
        System.out.println("5. Logout");
    }

    public void addNewCourse(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course Name: ");
        String courseName = scanner.nextLine();

        System.out.println("Enter Course Code: ");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Section: ");
        String section = scanner.nextLine();

        System.out.println("Enter Credit Hours: ");
        String creditHours = scanner.nextLine();

        System.out.println("Enter Maximum Capacity: ");
        int maxCapacity = scanner.nextInt();
        scanner.nextLine(); // clear newline

        // Check if course code already exists
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                System.out.println("A course with this code already exists!");
                return;
            }
        }

        Course newCourse = new Course(courseName, courseCode, section, creditHours, maxCapacity);
        courses.add(newCourse);
        System.out.println("Course added successfully!");

        scanner.close();
    }

    public void deleteCurrentCourse(){

        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter Course Code to Delete: ");
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
                student.dropCourse(toRemove);
            }

            System.out.println(toRemove.getCourseName() + "Course deleted successfully!");
        } else {
            System.out.println("Course not found!");
        }

        scanner.close();
    }

    public void viewAllCourses(){
        for(Course course : courses){
            System.out.println(course);
        }
    }

    public void viewAllStudents(){
        for(Student student : students){
            System.out.println(student);
        }
    }
}