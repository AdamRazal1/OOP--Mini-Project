package Models;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public abstract class User{ // abstract class
    private String userId;
    private String password;
    protected static ArrayList<Course> courses;  // Association - User has many courses


    public User(String userId, String password){
        this.userId = userId;
        this.password = password;
        courses = new ArrayList<>();
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }

    public static User login(String userId, String password, ArrayList<User> allUser) throws LoginFailedException{ // Exception Handling - Login failed

        for (User user : allUser) {
            if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(null, "Login successful! Welcome, " + user.getUserId());
                return user;
            }
        }
        throw new LoginFailedException("\nInvalid username or password. Please try again.");
    }

    public void logout(){
        JOptionPane.showMessageDialog(null, "Logout successfully!");
    }
    
    // Abstract method for polymorphism
    public abstract void displayMenu();
}
