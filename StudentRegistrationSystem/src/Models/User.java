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
    
    // Abstract method for polymorphism
    public abstract void displayMenu();
}
