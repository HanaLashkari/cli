package clui;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Assignment {
    public String getName() {
        return name;
    }

    private String name;
    private Course course;
    private String deadLine;
    private TypeOfAssignment typeOfAssignment;
    private boolean isAvailable = true;
    private static List<Assignment> assignments = new ArrayList<>();
    private Map<Student, Double> grades = new HashMap<>();

    public Assignment(String name ,Course course , String deadLine , TypeOfAssignment typeOfAssignment) {
        this.name = name;
        this.course = course;
        this.deadLine = deadLine;
        this.typeOfAssignment = typeOfAssignment;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public static Assignment convertToAssignment(String l, TypeOfAssignment typeOfAssignment) throws IOException {
        String[] s = l.split("-");
        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\Courses.txt"));
        Course c = new Course(null , null , null , Integer.parseInt(s[1]));
        for(String line : lines)
            if(line.split("-")[1].equals(s[1])) {
                c = Course.convertToCourse(line);
                break;
            }
        if(c.getCode() != Integer.parseInt(s[1])){
            System.out.println("The course for this assignment doesn't exist.");
            return  null;
        }
        Assignment t = new Assignment(s[0] , c , s[2] , typeOfAssignment);
        return t;
    }

    public static String convertToString(Assignment t){
        return t.name + "-" + t.getCourse().getCode() + "-" + t.getDeadLine() + "-" + t.getTypeOfAssignment();
    }

    public void setGradesByStudent(Student student, Double grade) {
        if(student == null || grade == null)
            return;
        grades.put(student, grade);
    }

    public Double getGrade(Student student){
        return grades.get(student);
    }

    /*public boolean isAvailable(Date date) {
        if(date == null)
            return false;
        if(date.before(deadLine))
            isAvailable = true;
        else
            isAvailable = false;
        return isAvailable;
    }*/

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Course getCourse() {
        return course;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        if(deadLine == null)
            return;
        this.deadLine = deadLine;
    }

    public void changeDeadLine(Teacher teacher , String deadLine){
        if(teacher == null || deadLine == null)
            return;
        if(course.getProfessor().equals(teacher))
            this.deadLine = deadLine;
        else
            System.out.println("YOU ARE NOT THE PROFESSOR OF THIS COURSE!");
    }


    public static void addProject(Teacher teacher , Assignment project){
        if(teacher == null || project == null)
            return;
        if(project.course.getProfessor().equals(teacher) && project.typeOfAssignment == TypeOfAssignment.project )
            project.course.getProjects().add(project);
        else if(!project.course.getProfessor().equals(teacher))
            System.out.println("YOU ARE NOT THE PROFESSOR OF THIS COURSE!");
        else
            System.out.println("THIS NOT A PROJECT!");
    }

    public static void removeProject(Teacher teacher , Assignment project){
        if(teacher == null || project == null)
            return;
        if(project.course.getProfessor().equals(teacher) && project.typeOfAssignment == TypeOfAssignment.project)
            project.course.getProjects().remove(project);
        else if(!project.course.getProfessor().equals(teacher))
            System.out.println("YOU ARE NOT THE PROFESSOR OF THIS COURSE!");
    }

    public static void addHomework(Teacher teacher , Assignment homework){
        if(teacher == null || homework == null)
            return;
        if(homework.course.getProfessor().equals(teacher) && homework.typeOfAssignment == TypeOfAssignment.homework )
            homework.course.getHomework().add(homework);
        else if(!homework.course.getProfessor().equals(teacher))
            System.out.println("YOU ARE NOT THE PROFESSOR OF THIS COURSE!");
        else
            System.out.println("THIS NOT A HOMEWORK!");
    }

    public static void removeHomework(Teacher teacher , Assignment homework){
        if(teacher == null || homework == null)
            return;
        if(homework.course.getProfessor().equals(teacher) && homework.typeOfAssignment == TypeOfAssignment.homework)
            homework.course.getHomework().remove(homework);
        else if(!homework.course.getProfessor().equals(teacher))
            System.out.println("YOU ARE NOT THE PROFESSOR OF THIS COURSE!");
    }

    public TypeOfAssignment getTypeOfAssignment(){
        return this.typeOfAssignment;
    }
}

