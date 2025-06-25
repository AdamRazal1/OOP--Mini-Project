import Models.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class StudentRegistrationSystem {

    public static Admin admin;
    public static ArrayList<User> users = new ArrayList<>(); // Dynamic runtime implementation
    public static ArrayList<Student> students = new ArrayList<>();
    public static User currentUser = null;
    public static boolean running = true;

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
            String userId = JOptionPane.showInputDialog(null, "Enter User ID:");
            if (userId == null){
                running = false;
                break;
            }
            String password = JOptionPane.showInputDialog(null, "Enter Password:");
            if (password == null){
                running = false;
                break;
            }
            try {
                currentUser = User.login(userId, password, users);
            } catch (LoginFailedException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Login Failed", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            while(currentUser != null){
                if(currentUser instanceof Student){ // if the user is Student
                    Student stu = (Student) currentUser;
                    stu.displayMenu();
                    String choiceStr = JOptionPane.showInputDialog(null, "Enter your choice:");
                    if (choiceStr == null){
                        currentUser.logout();
                        currentUser = null;
                        break;
                    }
                    int choice;
                    try {
                        choice = Integer.parseInt(choiceStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                    switch(choice){
                        case 1 -> stu.viewAvailableCourses();
                        case 2 -> {
                            try {
                                stu.registerCourse();
                            } catch (DuplicateCourseException e) {
                                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        case 3 -> stu.dropCourse();
                        case 4 -> stu.viewRegisteredCourses();
                        case 5 -> {
                            currentUser.logout();
                            currentUser = null;
                        }
                        default -> JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (currentUser instanceof Admin){ // if the user is Admin
                    Admin adm = (Admin) currentUser;
                    adm.displayMenu();
                    String choiceStr = JOptionPane.showInputDialog(null, "Enter your choice:");
                    if (choiceStr == null){
                        currentUser.logout();
                        currentUser = null;
                        break;
                    }
                    int choice;
                    try {
                        choice = Integer.parseInt(choiceStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
                        continue;
                    }
                    switch(choice){
                        case 1 -> adm.addNewCourse();
                        case 2 -> adm.deleteCurrentCourse();
                        case 3 -> adm.viewAllCourses();
                        case 4 -> adm.viewCourseDetails();
                        case 5 -> adm.viewAllStudents();
                        case 6 -> {
                            currentUser.logout();
                            currentUser = null;
                        }
                        default -> JOptionPane.showMessageDialog(null, "Invalid choice", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}
