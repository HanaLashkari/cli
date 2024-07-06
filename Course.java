package clui;
import java.util.*;

public class Course {
    private final String name;
    private final Integer unit; //vahed in dars
    private Teacher professor;
    private boolean available = false;
    private final Integer code;

    private List<Student> students = new ArrayList<>();
    private Map<Student , Double> grades = new HashMap<>();
    private List<Assignment> homework = new ArrayList<>();
    private List<Assignment> projects = new ArrayList<>();
    private String date;
    public Map<Student, Double> getGrades() {
        return grades;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    private List<Assignment> assignments = new ArrayList<>();
    public void addAssignment(Assignment a){
        assignments.add(a);
    }

    public Integer getCode() {
        return code;
    }

    public static Map<Integer, Course> getAllCourses() {
        return allCourses;
    }

    private static Map<Integer , Course> allCourses = new HashMap<>();

    public Course(String name , Teacher professor , Integer unit , int code) {
        this.name = name;
        this.professor = professor;
        this.unit = unit;
        this.code = code;
        allCourses.put(code , this);
    }

    public static boolean isExisted(int code){
        return allCourses.containsKey(code);
    }

    public void addStudent(Student student){
        if (student == null)
            return;
        for(Student s : students)
            if(s.getId() == student.getId())
                return;
        students.add(student);
    }

    public void addAllStudents(Collection<Student> students){
        if (students == null)
            return;
        for(Student s : students)
            if (s != null)
                this.addStudent(s);
    }

    public void removeStudent(Student student){
        if (student == null)
            return;
       students.remove(student);
       grades.remove(student);
    }

    public void doGrades(){
        for(Student s : students){
            Double g = 0.0;
            int n = 0;
            for(Assignment p : projects)
                if(p.getGrade(s) != null)
                    g += p.getGrade(s);
                else
                    n++;
            for(Assignment h : homework)
                if(h.getGrade(s) != null)
                    g += h.getGrade(s);
                else
                    n++;
            g /= (projects.size() + homework.size() - n);
            grades.put(s , g);
        }
    }
    public Student topStudent(){
        Student topStudent = null;
        Double topGrade = 0.0;
        doGrades();
        for(Student s : students){
            if(grades.get(s) > topGrade) {
                topGrade = grades.get(s);
                topStudent = s;
            }
        }
        return topStudent;
    }

    public Integer getUnit(){
        return unit;
    }

    public String getName() {
        return name;
    }

    public Teacher getProfessor() {
        return professor;
    }

    public void setProfessor(Teacher professor){
        this.professor = professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public List<Assignment> getHomework() {
        return homework;
    }

    public Integer getNumberofHomework(){
        return homework.size();
    }
    public List<Assignment> getProjects() {
        return projects;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getGrade(Student student) {
        if (student == null)
            return 0.0;
        return grades.get(student);
    }

//    public List<Assignment> getAvailableProjects(){
//        List<Assignment> l = new ArrayList<>();
//        for(Assignment p : projects)
//            if(p.isAvailable()) {
//                l.add(p);
//            }
//        return l;
//    }

    public void setGrades(Student student , Double grade){
        if (student == null || grade == null)
            return;
        grades.put(student , grade);
    }

    public static Course convertToCourse(String l){
        String[] s = l.split("-");
        Course t = new Course(s[0] , new Teacher( s[3] , Long.parseLong(s[4])) , Integer.parseInt(s[2]) , Integer.parseInt(s[1]));
        t.setDate(s[5]);
        return t;
    }

    public static String convertToString(Course t){
        return t.name + "-" + t.code + "-" + t.unit + "-" + t.professor.getName() + "-" + t.professor.getId() + "-" + t.date;
    }

}