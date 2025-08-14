package converter;

import entity.model.Course;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.List;
import java.util.Map;

@FacesConverter(value = "courseConverter", managed = true)
public class CourseConverter implements Converter<Course> {

    @Override
    public Course getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }

        // Retrieve the list of courses from the component's attributes
        @SuppressWarnings("unchecked")
        Map<String, Object> attributes = component.getAttributes();
        List<Course> courses = (List<Course>) attributes.get("courseList");

        if (courses != null) {
            for (Course course : courses) {
                if (course.getId().toString().equals(value)) {
                    return course;
                }
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Course value) {
        if (value == null) {
            return "";
        }
        return value.getId().toString();
    }
}
