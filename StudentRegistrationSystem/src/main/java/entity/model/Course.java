package entity.model;

import java.util.Objects;

public class Course {
    private Long id;
    private String courseName;

    public Course(Long id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
