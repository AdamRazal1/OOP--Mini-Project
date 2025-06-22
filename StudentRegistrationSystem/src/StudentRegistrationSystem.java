import Models.*;
import java.util.*;


public class StudentRegistrationSystem {

    public static Admin admin;
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Student> students = new ArrayList<>();
    public static User currentUser = null; // Creating current User for dynamic runtime
    public static boolean running = true;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){

        // creating students
        students.add(new Student("Adam", "adam123"));
        students.add(new Student("Afiq", "afiq123"));
        students.add(new Student("Afif", "afif123"));

        // creating admin
        admin = new Admin("Admin", "admin123", students);

        // adding users
        users.add(admin);
        for(Student student : students){
            users.add(student);
        }

        while(running){
            System.out.println("\n----- Welcome to the Student Registration System -----");
            System.out.println("Enter User ID: ");
            String userId = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();

            try {
                currentUser = User.login(userId, password, users);
            } catch (LoginFailedException e) { // Catch the User login error
                System.out.println(e.getMessage());
            }

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
                        try {
                            ((Student)currentUser).registerCourse();
                        } catch (DuplicateCourseException e) {
                            System.out.println(e.getMessage());
                        }
                    } else if(choice == 3){
                        ((Student)currentUser).dropCourse();
                    } else if(choice == 4){
                        ((Student)currentUser).viewRegisteredCourses();
                    } else if(choice == 5){
                        currentUser.logout();
                        currentUser = null;
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
                        currentUser.logout();
                        currentUser = null;
                    }
                }
            }
        }
    }

}
