package Models;

import java.util.ArrayList;
import javax.swing.JOptionPane;

// Demonstrate Inheritance
public class Student extends User { // Inheritance - Student is a subclass of User

    // Demonstrate Aggregation - 'has a' relationship between Student and Course
    private ArrayList<Course> enrolledCourses;

    public Student(String userId, String password){
        super(userId, password);
        enrolledCourses = new ArrayList<>();
    }

    public ArrayList<Course> getEnrolledCourses(){
        return enrolledCourses;
    }

    public void registerCourse() throws DuplicateCourseException { // Exception handling - Detect duplicate registration
        String courseCode = JOptionPane.showInputDialog(null, "Enter course code:");
        if (courseCode == null) return;

        // Check if courseCode is already registered
        for(Course course : enrolledCourses){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                throw new DuplicateCourseException("You have already registered for this course!");
            }
        }

        for(Course course : courses){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                if(course.getCurrentCapacity() < course.getMaxCapacity()){
                    enrolledCourses.add(course);
                    course.addCurrentCapacity();
                    course.addEnrolledStudent(this.getUserId());
                    JOptionPane.showMessageDialog(null, "You have registered for " + course.getCourseName() + " successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Course is full! Cannot register.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Course with code " + courseCode + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void dropCourse() {
        String courseCode = JOptionPane.showInputDialog(null, "Enter course code to drop:");
        if (courseCode == null) return;

        for(Course course : new ArrayList<>(enrolledCourses)){
            if(course.getCourseCode().equalsIgnoreCase(courseCode)){
                enrolledCourses.remove(course);
                course.minusCurrentCapacity();
                JOptionPane.showMessageDialog(null, course.getCourseName() + " dropped successfully!");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Course not found in your registered courses!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void viewRegisteredCourses() {
        if(enrolledCourses.isEmpty()){
            JOptionPane.showMessageDialog(null, "You have not registered for any courses.");
            return;
        }
        StringBuilder msg = new StringBuilder("--- My Registered Courses ---\n");
        for(Course course : enrolledCourses){
            msg.append(course.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, msg.toString());
    }

    public void viewAvailableCourses() {
        if(courses.isEmpty()){
            JOptionPane.showMessageDialog(null, "No courses available.");
            return;
        }
        StringBuilder msg = new StringBuilder("--- Available Courses ---\n");
        for(Course course : courses){
            msg.append(course.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, msg.toString());
    }

    public void removeEnrolledStudents(Course course) {
        enrolledCourses.remove(course);
        course.minusCurrentCapacity();
    }

    @Override // Polymorphism - method overloading from User
    public void displayMenu(){
        String menu = "1. View Available Courses\n" +
                      "2. Register for a Course\n" +
                      "3. Drop a Course\n" +
                      "4. View My Registered Courses\n" +
                      "5. Logout";
        JOptionPane.showMessageDialog(null, menu, "Student Menu", JOptionPane.PLAIN_MESSAGE);
    }
}
