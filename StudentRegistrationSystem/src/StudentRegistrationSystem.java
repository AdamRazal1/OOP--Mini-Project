import java.util.*;
import Models.*;

public class StudentRegistrationSystem {

    public static Admin admin;
    public static ArrayList<Student> students;
    // public static ArrayList<Course> courses;
    public static User currentUser = null; // Creating current User for dynamic runtime
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        students = new ArrayList<>();
        students.add(new Student("Adam", "adam123"));
        students.add(new Student("Afiq", "afiq123"));
        students.add(new Student("Afif", "afif123"));

        admin = new Admin("Admin", "admin123", students);


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
                    ((Student)currentUser).registerCourse();
                } else if(choice == 2){
                    ((Student)currentUser).dropCourse();
                } else if(choice == 3){
                    ((Student)currentUser).viewRegisteredCourses();
                } else if(choice == 4){
                    logout();
                }
            } else if(currentUser instanceof Admin){
                // Admin functionalities
                 if(choice == 1){
                    ((Admin)currentUser).addNewCourse();
                } else if(choice == 2){
                    ((Admin)currentUser).deleteCurrentCourse();
                } else if(choice == 3){
                    ((Admin)currentUser).viewAllCourses();
                } else if(choice == 4){
                    ((Admin)currentUser).viewAllStudents();
                } else{
                    logout();
                }
            }
        }

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

    }
