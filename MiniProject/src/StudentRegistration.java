class User{
    protected String name;

    public User(String name) {
        this.name = name;
    }
}

class Admin extends User{
    
}

class Student extends User{

}

class Course{

}


public class StudentRegistration {
    public static void main(String[] args){
        System.out.println("Hello, World!");
    }
}
