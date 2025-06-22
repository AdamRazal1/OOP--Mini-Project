package Models;
import java.util.ArrayList;


public abstract class User{
    private String userId;
    private String password;
    protected static ArrayList<Course> courses;  


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

    public static User login(String userId, String password, ArrayList<User> allUser) throws LoginFailedException{

        for (User user : allUser) {
            if (user.getUserId().equalsIgnoreCase(userId) && user.getPassword().equals(password)) {
                System.out.println("Student login successful! Welcome, " + user.getUserId());
                return user;
            }
        }
        throw new LoginFailedException("Invalid username or password. Please try again.");
    }

    public void logout(){
        System.out.println(" Logout successfully!");
    }
    
    // Abstract method for polymorphism
    public abstract void displayMenu();
}
