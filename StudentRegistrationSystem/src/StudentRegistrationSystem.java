import java.util.*;
import Models.*;

public class StudentRegistrationSystem {

    public static Admin admin;
    public static ArrayList<Student> students;
    public static User currentUser = null; // Creating current User for dynamic runtime
    public static boolean running = true;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        students = new ArrayList<>();
        students.add(new Student("Adam", "adam123"));
        students.add(new Student("Afiq", "afiq123"));
        students.add(new Student("Afif", "afif123"));

        admin = new Admin("Admin", "admin123", students);

        while(running){
            System.out.println("\n----- Welcome to the Student Registration System -----");
            System.out.println("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            login(userId, password);

            while(currentUser != null){

                if(currentUser instanceof Student){

                    ((Student)currentUser).displayMenu();
                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    // Student functionalities
                    if(choice == 1){
                        ((Student)currentUser).viewAvailableCourses();
                    } else if(choice == 2){
                        ((Student)currentUser).registerCourse();
                    } else if(choice == 3){
                        ((Student)currentUser).dropCourse();
                    } else if(choice == 4){
                        ((Student)currentUser).viewRegisteredCourses();
                    } else if(choice == 5){
                        logout();
                    }
                } else if(currentUser instanceof Admin){
                    ((Admin)currentUser).displayMenu();
                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    // Admin functionalities
                    if(choice == 1){
                        ((Admin)currentUser).addNewCourse();
                    } else if(choice == 2){
                        ((Admin)currentUser).deleteCurrentCourse();
                    } else if(choice == 3){
                        ((Admin)currentUser).viewAllCourses();
                    } else if(choice == 4){
                        ((Admin)currentUser).viewCourseDetails();
                    } else if(choice == 5){
                        ((Admin)currentUser).viewAllStudents();
                    } else if (choice == 6){
                        logout();
                    }
                }
            }
        }
    }

    public static void login(String userId, String password) {
        // Check for Admin login
        if (admin.getUserId().equalsIgnoreCase(userId) && admin.getPassword().equals(password)) {
            currentUser = admin; // dynamic runtime
            System.out.println("\nAdmin login successful!");
        }

        // Check for Student login
        for (Student student : students) {
            if (student.getUserId().equalsIgnoreCase(userId) && student.getPassword().equals(password)) {
                currentUser = student; // dynamic runtime
                System.out.println("Student login successful! Welcome, " + student.getUserId());
            }
        }

    }

    public static void logout(){
        currentUser = null;
        System.out.println("\nLogout successful!");
    }

}
