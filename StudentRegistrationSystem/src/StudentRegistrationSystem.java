import java.util.ArrayList;
import java.util.Scanner;

abstract class User{
    private String userId;
    private String password;

    public User(String userId, String password){
        this.userId = userId;
        this.password = password;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }

    // Abstract method for polymorphism
    public abstract void displayMenu();
}

// Demonstrate Inheritance
class Admin extends User{

    public Admin(String userId, String password){
        super(userId, password);
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
}

// Demonstrate Inheritance
class Student extends User{

    // Demonstrate Aggregation - 'has a' relationship between Student and Course
    private ArrayList<Course> enrolledCourses;

    public Student(String userId, String password){
        super(userId, password);
        enrolledCourses = new ArrayList<>();
    }

    public void enrollCourse(Course course){
        enrolledCourses.add(course);
    }

    public void dropCourse(Course course){
        enrolledCourses.remove(course);
    }

    public ArrayList<Course> getEnrolledCourses(){
        return enrolledCourses;
    }

    // Demonstrate Polymorphism - overriding abstract method
    @Override
    public void displayMenu(){
        System.out.println("\n--- Student Menu ---");
        System.out.println("1. Register for a Course");
        System.out.println("2. Drop a Course");
        System.out.println("3. View My Registered Courses");
        System.out.println("4. Logout");
    }
}

class Course{
    
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


public class StudentRegistrationSystem {

    public static Admin admin;
    public static ArrayList<Student> students;
    public static ArrayList<Course> courses;
    public static User currentUser = null; // Creating current User for dynamic runtime
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        InitializeData();

        System.out.println("----- Welcome to the Student Registration System -----\n");
        System.out.println("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        login(userId, password);

        while(currentUser != null){
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if(currentUser instanceof Student){
                // Student functionalities
                if(choice == 1){
                    registerCourse((Student) currentUser);
                } else if(choice == 2){
                    dropCourse((Student) currentUser);
                } else if(choice == 3){
                    viewRegisteredCourses((Student) currentUser);
                } else if(choice == 4){
                    logout();
                }
            } else if(currentUser instanceof Admin){
                // Admin functionalities
            }
        }

    }

    public static void InitializeData(){

        System.out.println("Initializing data...");

        // Instantiate Admin, Students and Courses objects
        admin = new Admin("Admin", "admin123");

        students = new ArrayList<>();
        students.add(new Student("Adam", "adam123"));
        students.add(new Student("Afiq", "afiq123"));
        students.add(new Student("Afif", "afif123"));

        courses = new ArrayList<>();
        courses.add(new Course("Java Programming", "CS101", "S01", "3", 24));
        courses.add(new Course("Python Programming", "CS102", "S02", "4", 24));
        courses.add(new Course("C Programming", "CS103", "S03", "3", 24));

        System.out.println("Data initialized successfully!\n" );

    }

    public static void login(String userId, String password) {
        // Check for Admin login
        if (admin.getUserId().equalsIgnoreCase(userId) && admin.getPassword().equals(password)) {
            currentUser = admin; // dynamic runtime
            System.out.println("Admin login successful!");
            admin.displayMenu();
        }

        // Check for Student login
        for (Student student : students) {
            if (student.getUserId().equalsIgnoreCase(userId) && student.getPassword().equals(password)) {
                currentUser = student; // dynamic runtime
                System.out.println("Student login successful! Welcome, " + student.getUserId());
                student.displayMenu();
            }
        }

    }

    public static void logout(){
        currentUser = null;
        System.out.println("Logout successful!");
    }

    public static void registerCourse(Student student){
        System.out.println("Enter course code: ");
        String courseCode = scanner.nextLine();

        for(Course course : courses){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                if(student.getEnrolledCourses().size() < course.getMaxCapacity()){
                    student.enrollCourse(course);
                    System.out.println(course.getCourseName() + " Course registered successfully!");
                } else{
                    System.out.println("Course is not found!");
                }
            }
        }
    }

    public static void dropCourse(Student student){
        System.out.println("Enter course code: ");
        String courseCode = scanner.nextLine();

        for(Course course : student.getEnrolledCourses()){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                student.dropCourse(course);
                System.out.println(course.getCourseName() + " Course dropped successfully!");
            } else{
                System.out.println("Course not found!");
            }
        }
    }

    public static void viewRegisteredCourses(Student student){
        for(Course course : student.getEnrolledCourses()){
            System.out.println(course);
        }
    }

    public static void addNewCourse(){

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
    }

    public static void deleteCurrentCourse(Admin admin){
        
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
    }

    public static void viewAllStudents(){
        for(Student student : students){
            System.out.println(student);
        }
    }

    public static void viewAllCourses(){
        for(Course course : courses){
            System.out.println(course);
        }
    }
}
