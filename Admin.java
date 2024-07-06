package clui;
import java.util.*;

public class Admin {
    private static Map<Long , Admin> allAdmins = new HashMap<>();
    private String name;
    private Long id;

    public Admin(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public void addTeacher(Teacher teacher){
        if(teacher != null)
            Teacher.getAllTeachers().put(teacher.getId() , teacher);
    }

    public void removeTeacher(Teacher teacher){
        if(teacher != null)
            Teacher.getAllTeachers().remove(teacher.getId());
    }

    public void addStudent(Student student , Course course){
        if(course == null || student == null)
            return;
        course.addStudent(student);
    }

    public void removeStudent(Student student , Course course){
        if(course == null || student == null)
            return;
        course.removeStudent(student);
    }

    public void removeStudentFromAll(Student student){
        if(student == null)
            return;
        Student.getAllStudents().remove(student.getId() , student);
        Collection<Course> courses = Course.getAllCourses().values();
        for(Course c : courses){
            if(c.getStudents().contains(student))
                c.getStudents().remove(student);
        }
    }

    public void addProjectByTeacher(Teacher teacher , Assignment project){
        if(teacher == null || project == null)
            return;
        teacher.addProject(project);
    }

    public void removeProjectByTeacher(Teacher teacher , Assignment project){
        if(teacher == null || project == null)
            return;
        teacher.removeProject(project);
    }

    public void setGrades(Course course , Student student , Double grade){
        if(course == null || student == null || grade == null)
            return;
        course.setGrades(student , grade);
    }

    public void addProjectByAssignment(Teacher teacher , Assignment project){
        if(teacher == null || project == null)
            return;
        Assignment.addProject(teacher , project);
    }

    public void addHomeworkByAssignment(Teacher teacher , Assignment homework){
        if(teacher == null || homework == null)
            return;
        Assignment.addHomework(teacher , homework);
    }

    public void removeProjectByAssignment(Teacher teacher , Assignment project){
        if(teacher == null || project == null)
            return;
        Assignment.removeProject(teacher , project);
    }

    public void removeHomeworkByAssignment(Teacher teacher , Assignment homework){
        if(teacher == null || homework == null)
            return;
        Assignment.removeProject(teacher , homework);
    }
}
