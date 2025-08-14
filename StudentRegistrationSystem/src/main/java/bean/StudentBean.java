package bean;

import entity.model.Student;
import entity.model.Course;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("studentBean")
@SessionScoped
public class StudentBean implements Serializable {

    private String name;
    private String email;
    private String course;  // user-entered course name

    private List<Student> registeredStudents = new ArrayList<>();

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (name == null || name.trim().length() < 3) {
            context.addMessage("form:name",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name must be at least 3 characters", null));
            return null;
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            context.addMessage("form:email",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Enter a valid email address", null));
            return null;
        }

        if (course == null || course.trim().isEmpty()) {
            context.addMessage("form:course",
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please enter a course", null));
            return null;
        }

        // Create Student and set properties
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        // Wrap the user-entered course string into a Course object
        Course c = new Course(null, course);
        List<Course> courseList = new ArrayList<>();
        courseList.add(c);
        student.setCourses(courseList);

        registeredStudents.add(student);

        // Reset fields after successful registration
        name = "";
        email = "";
        course = "";

        // Add success message
        context.addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Registration successful!", null));

        // Stay on this page
        return null;
    }

    // === Getters and Setters ===

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }
}
