package clui;
import java.util.*;

public class Student{
    private final String name;
    private final Long id;
    private String password;
    private final List<Integer> allCourseNumber = new ArrayList<>();
    private final List<Integer> allCourseUnits = new ArrayList<>();
    private List<Course> currentCourses = new ArrayList<>();
    private final List<List<Course>> allCourses = new ArrayList<>();
    private Double currentAverage = 0.0;
    private Double totalAverage = 0.0;

    public static Map<Long, Student> getAllStudents() {
        return allStudents;
    }

    private static Map<Long , Student> allStudents = new HashMap<>();
    public Student(String name , Long id , String password){
        this.name = name;
        this.id = id;
        this.password = password;
        allStudents.put(id , this);
    }
    //TODO
    public static String checkPassword(String password){
        return "";
    }

    public static Student convertToStudent(String l) {
        String[] s = l.split("-");
        Student t = new Student(s[0] , Long.parseLong(s[1]) , null);
        return t;
    }
    public static String convertToString(Student t){
        String s = t.name + "-" + t.id;
        return s;
    }

    public void addCourse(Course course){
        if(course == null)
            return;
        for(Course c : currentCourses)
            if(c.equals(course))
                return;
        this.currentCourses.add(course);
    }

    public void nextSemester(){
        totalAverage += getCurrentAverage() * getCurrentCourseUnits();
        currentAverage = 0.0;
        allCourseUnits.add(getCurrentCourseUnits());
        allCourses.add(currentCourses);
        currentCourses.clear();
    }

    public int getCurrentCourseUnits() {
        int all = 0;
        for(Course c : currentCourses)
            all += c.getUnit();
        return all;
    }

    public List<Course> getCurrentCourses(){
        return currentCourses;
    }

    public List<List<Course>> getAllCurrentCourses(){
        return allCourses;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public double getCurrentAverage() {
        int n = 0;
        currentAverage = 0.0;
        for(Course c : currentCourses)
            if(c.getGrade(this) != null) {
                currentAverage += (c.getGrade(this) * c.getUnit());
            }
            else {
                n += c.getUnit();
            }
        currentAverage /= (getCurrentCourseUnits() - n);
        return currentAverage;
    }

    public double getTotalAverage() {
        double all = totalAverage + getCurrentAverage() * getCurrentCourseUnits();
        all /= getAllCourseCredit();
        return all;
    }

    public int getAllCourseNumber() {
        Integer all = currentCourses.size();
        for(Integer i : allCourseNumber)
            all += i;
        return all;
    }

    public int getAllCourseCredit() {
        Integer all = getCurrentCourseUnits();
        for(Integer i : allCourseUnits)
            all += i;
        return all;
    }

}