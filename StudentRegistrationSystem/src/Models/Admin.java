package Models;

import java.util.ArrayList;
import javax.swing.JOptionPane;

// Demonstrate Inheritance
public class Admin extends User { // Inheritance - Admin is a subclass of User
    private ArrayList<Student> students; // Association - Admin has a list of students

    public Admin(String userId, String password, ArrayList<Student> students) {
        super(userId, password);
        this.students = students;
    }

    @Override // Polymorphism - method overloading from User
    public void displayMenu() {
        String menu = "1. Add a Course\n" +
                      "2. Remove a Course\n" +
                      "3. View All Courses\n" +
                      "4. View Course Details\n" +
                      "5. View All Students\n" +
                      "6. Logout";
        JOptionPane.showMessageDialog(null, menu, "Admin Menu", JOptionPane.PLAIN_MESSAGE);
    }

    public void addNewCourse() {
        String courseName = JOptionPane.showInputDialog(null, "Enter Course Name:");
        if (courseName == null) return;
        String courseCode = JOptionPane.showInputDialog(null, "Enter Course Code:");
        if (courseCode == null) return;
        String section = JOptionPane.showInputDialog(null, "Enter Section:");
        if (section == null) return;
        String creditHours = JOptionPane.showInputDialog(null, "Enter Credit Hours:");
        if (creditHours == null) return;
        String maxCapStr = JOptionPane.showInputDialog(null, "Enter Maximum Capacity:");
        if (maxCapStr == null) return;
        int maxCapacity;
        try {
            maxCapacity = Integer.parseInt(maxCapStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number for capacity", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check duplicates
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                JOptionPane.showMessageDialog(null, "A course with this code already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Course newCourse = new Course(courseName, courseCode, section, creditHours, maxCapacity);
        courses.add(newCourse);
        JOptionPane.showMessageDialog(null, "Course added successfully!");
    }

    public void deleteCurrentCourse() {
        String courseCode = JOptionPane.showInputDialog(null, "Enter Course Code to Delete:");
        if (courseCode == null) return;
        Course toRemove = null;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                toRemove = course;
                break;
            }
        }
        if (toRemove != null) {
            courses.remove(toRemove);
            for (Student student : students) {
                student.removeEnrolledStudents(toRemove);
            }
            JOptionPane.showMessageDialog(null, toRemove.getCourseName() + " deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void viewAllCourses() {
        if (courses.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No courses available.");
            return;
        }
        StringBuilder msg = new StringBuilder("--- List of Courses ---\n");
        for (int i = 0; i < courses.size(); i++) {
            msg.append(i + 1).append(". ").append(courses.get(i).toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, msg.toString());
    }

    public void viewCourseDetails() {
        String courseCode = JOptionPane.showInputDialog(null, "Enter Course Code to View Details:");
        if (courseCode == null) return;
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                course.listDetails();
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Course not found!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No students found.");
            return;
        }
        StringBuilder msg = new StringBuilder("--- List of Students ---\n");
        for (int i = 0; i < students.size(); i++) {
            msg.append(i + 1).append(". ").append(students.get(i).getUserId()).append("\n");
        }
        JOptionPane.showMessageDialog(null, msg.toString());
    }
}