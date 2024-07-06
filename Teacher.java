package clui;
import java.io.Serializable;
import java.util.*;

public class Teacher implements Serializable {
    public String getName() {
        return name;
    }

    private String name;

    public Long getId() {
        return id;
    }

    private Long id;
    private List<Course> courses = new ArrayList<>();

    public static Map<Long, Teacher> getAllTeachers() {
        return allTeachers;
    }

    private static Map<Long , Teacher> allTeachers = new HashMap<>();

    public Teacher(String name ,Long id){
        this.name = name;
        this.id = id;
        allTeachers.put(id , this);
    }

    public void addCourse(Course course){
        if(course == null)
            return;
        this.courses.add(course);
    }

    public int getCountOfCourses() {
        return courses.size();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addStudent(Course course , Student student){
        if(course == null || student == null)
            return;
        if(courses.contains(course)){
            course.addStudent(student);
            student.addCourse(course);
        }
    }

    public void removeStudent(Course course , Student student){
        if(course == null || student == null)
            return;
        if(courses.contains(course)){
            course.removeStudent(student);
        }
    }

    public void addProject(Assignment project){
        if(project == null)
            return;
        if(project.getTypeOfAssignment() == TypeOfAssignment.project){
            Assignment.addProject(this , project);
        }
    }

    public void removeProject(Assignment project){
        if(project == null)
            return;
        if(project.getTypeOfAssignment() == TypeOfAssignment.project){
            Assignment.removeProject(this , project);
        }
    }

    public void setGrades(Course course , Student student , Double grade){
        if(course == null || student == null || grade == null)
            return;
        if(courses.contains(course))
            course.setGrades(student , grade);
        courses.add(course);
    }
    public static Teacher convertToTeacher(String l){
        String[] s = l.split("-");
        Teacher t = new Teacher(s[0] , Long.parseLong(s[1]));
        return t;
    }

    public static String convertToString(Teacher t){
        String s = t.name + "-" + t.id;
        return s;
    }
}