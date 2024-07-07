package clui;
import Server.DataBase;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    private static Scanner scan = new Scanner(System.in);
    private static boolean Access = false;
    private static int choice = 0;
    private static Teacher teacher;
    private static File teacherFile = new File("C:\\Users\\Asus\\Desktop\\project\\Teachers.txt");
    private static File studentFile = new File("C:\\Users\\Asus\\Desktop\\project\\Students.txt");
    private static File courseFile = new File("C:\\Users\\Asus\\Desktop\\project\\Courses.txt");
    private static File projectFile = new File("C:\\Users\\Asus\\Desktop\\project\\Projects.txt");
    private static File homeworkFile = new File("C:\\Users\\Asus\\Desktop\\project\\Homeworks.txt");
    private static void clean(){
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }
    private static void tryAgain(){
        clean();
        System.out.println("Please try again!");
    }
    private static void comeback(){
        System.out.println("Click 'Enter' to come back.");
        String s = scan.nextLine();
        scan.nextLine();
        if(s == null)
            s = scan.nextLine();
    }
    private static int menu(){
        System.out.println("I am a/an ...\n1)Admin\n2)Teacher\n3)Exit");
        return scan.nextInt();
    }
    private static int adminChoices(){
        System.out.println("Choose one.\n1)Teachers\n2)Students\n3)Courses\n4)Projects And Homeworks\n5)Back");
        return scan.nextInt();
    }
    private static int teacherChoices(){
        System.out.println("Choose one.\n1)Students\n2)Courses\n3)Projects And Homeworks\n4)Back");
        return scan.nextInt();
    }
    private static int partTeachersByAdmin(){
        System.out.println("What do you want to do?\n1)Add Teacher\n2)Remove Teacher\n3)List Of Teachers\n4)List Of Courses For A Teacher\n" +
                "5)List Of Assignments For A teacher");
        return scan.nextInt();
    }
    private static int partStudentByAdmin() {
        System.out.println("What do you want to do?\n1)Add Student\n2)Remove Student\n3)List Of Students\n4)Add To Course\n" +
                "5)Remove From Course\n6)List Of Courses For A Student\n7)List OF Assignments For A Student\n8)Set Grade\n" +
                "9)grades of student");
        return scan.nextInt();
    }
    private static int partStudentByTeacher() {
        System.out.println("What do you want to do?\n1)Add Student To Course\n2)Remove Student From Course\n3)List Of Students\n" +
                "4)List Of Courses For A Student\n5)List OF Assignments For A Student");
        return scan.nextInt();
    }
    private static int partCourseByAdmin() {
        System.out.println("What do you want to do?\n1)Add Course\n2)Remove Course\n3)List Of Courses");
        return scan.nextInt();
    }
    private static int partCourseByTeacher() {
        System.out.println("What do you want to do?\n1)Add Course\n2)Remove Course\n3)Add Student To Course\n" +
                "4)List Of Courses\n5)Remove Student From Course");
        return scan.nextInt();
    }
    private static int partAssignmentByAdmin() {
        System.out.println("What do you want to do?\n1)Add Project\n2)Add Homework\n3)Remove Project\n4)Remove Homework\n5)List Of Projects\n6)List Of Homeworks");
        return scan.nextInt();
    }
    private static int partAssignmentByTeacher() {
        System.out.println("What do you want to do?\n1)Add Project\n2)Add Homework\n3)Remove Project\n4)Remove Homework\n5)List Of Assignments");
        return scan.nextInt();
    }
    public static String fillOutPeople(){
        String s = "";
        try{
            System.out.println("Fill out the items");
            System.out.println("-Name : ");
            scan.nextLine();
            String name = scan.nextLine();
            System.out.println("-Id : ");
            Long id = scan.nextLong();
            s = name + "-" + id;
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }
    private static String fillOutCourse(){
        String s = "";
        try{
            System.out.println("Fill out the items");
            System.out.println("-Name : ");
            scan.nextLine();
            String name = scan.nextLine();
            System.out.println("-Code : ");
            int code = scan.nextInt();
            System.out.println("-Unit : ");
            int unit = scan.nextInt();
            System.out.println("-Name Of Professor : ");
            scan.nextLine();
            String nameOfProfessor = scan.nextLine();
            System.out.println("-Id Of Professor : ");
            long idOfprofessor = scan.nextLong();
            System.out.println("-Date Of Exam(Example : 1403/02/31) : ");
            scan.nextLine();
            String date = scan.nextLine();
            s = name + "-" + code + "-" + unit + "-" + nameOfProfessor + "-" + idOfprofessor + "-" + date;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  s;
    }
    private static String fillOutAssignment(TypeOfAssignment typeOfAssignment) {
        String s = "";
        try{
            System.out.println("Fill out the items");
            System.out.println("-Name : ");
            scan.nextLine();
            String name = scan.nextLine();
            System.out.println("-Code Of Course : ");
            int codeOfCourse = scan.nextInt();
            System.out.println("-DeadLine and Estimated Time(Example : 1403/01/05,15:30,5(hour) : ");
            scan.nextLine();
            String deadline = scan.nextLine();
            System.out.println("-Description : ");
            String description = scan.nextLine();
            s = name + "-" + codeOfCourse + "-" + deadline + "-" + typeOfAssignment + "-" + description;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  s;
    }
    private static void adding(String s , String object , File file , boolean person , boolean course){
        try{
            if(person){
                Signup(object , file);
            }
            else {
                List<String> lines = Files.readAllLines(file.toPath());
                String line = "";
                if (lines.isEmpty())
                    line = s;
                else
                    line = "\n" + s;
                for (String l : lines) {
                    if (l.equals(s)) {
                        System.out.println("This " + object + " exists.");
                        return;
                    }
                }
                FileWriter writer = new FileWriter(file, true);
                writer.write(line);
                writer.flush();
                writer.close();
            }
            if(course){
                String[] parts = s.split("-");
                Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher"+ parts[4]+".txt");
                if (!p.toFile().exists())
                    Files.createFile(p);
                FileWriter writer = new FileWriter(p.toFile() , true);
                if(Files.readAllLines(file.toPath()).isEmpty())
                    writer.write(s);
                else writer.write("\n" + s);
                writer.flush();
                writer.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Adding "+object+" was successful.");
    }
    private static void removing(String line, String object, File file , boolean person , boolean course) {
        try{
            List<String> lines = Files.readAllLines(file.toPath());
            boolean exists = false;
            for(int i=lines.size()-1 ; i>=0 ; i--){
                if(lines.get(i).equals(line)) {
                    lines.remove(i);
                    exists = true;
                }
            }
            if(!exists) {
                System.out.println("This "+object+" doesn't exist.");
                return;
            }
            FileWriter writer = new FileWriter(file);
            for(int i=0 ; i<lines.size() ; i++) {
                if (i == 0)
                    writer.write(lines.get(i));
                else writer.write("\n" + lines.get(i));
            }
            writer.flush();
            writer.close();
            if(person){
                Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\passwords\\"+object+"Password.txt");
                List<String> str = Files.readAllLines(p);
                FileWriter writer1 = new FileWriter(p.toFile());
                String[] parts = line.split("-");
                for(int i=0 ; i<str.size() ; i++) {
                    if(!str.get(i).contains(parts[1])) {
                        if (i == 0)
                            writer1.write(str.get(i));
                        else writer1.write("\n" + str.get(i));
                    }
                }
                writer1.flush();
                writer1.close();
                p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOf"+object+"\\"+object+parts[1]+".txt");
                if (p.toFile().exists())
                    Files.delete(p);
            }
            if(file.equals(teacherFile)){
                Teacher teacher = Teacher.convertToTeacher(line);
                Path p = Path.of("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher"+ teacher.getId()+".txt");
                if(!p.toFile().exists())
                    return;
                List<String> courses = Files.readAllLines(p);
                for(String c : courses){
                    removing(c , "course" , courseFile , false , true);
                }
                Files.delete(p);
            }
            if(course){
                Course c = Course.convertToCourse(line);
                Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher"+c.getProfessor().getId()+".txt");
                remove(line , p.toFile());
                p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfcourse\\course"+c.getCode()+".txt");
                if(p.toFile().exists()) {
                    List<String> strs = Files.readAllLines(p);
                    Files.delete(p);
                    for (String s : strs) {
                        Student student = Student.convertToStudent(s);
                        p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfstudent\\student" + student.getId() + ".txt");
                        if(p.toFile().exists())
                            remove(line, p.toFile());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Removing "+object+" was successful.");
    }
    private static void remove(String s , File file) throws IOException {
        List<String> strs = Files.readAllLines(file.toPath());
        for(int i=strs.size()-1 ; i>=0 ; i--)
            if(strs.get(i).contains(s))
                strs.remove(i);
        FileWriter writer = new FileWriter(file);
        for(int i=0 ; i<strs.size() ; i++){
            if(i==0)
                writer.write(strs.get(i));
            else writer.write("\n" + strs.get(i));
        }
            writer.close();
    }
    public static void Signup(String object , File file){
        while(true) {
            Login_Signup LS = Login_Signup.signup(object , file);
            if(LS.b) {
                teacher = LS.teacher;
                break;
            }
            else{
                tryAgain();
            }
        }
    }
    public static void Login(String object , File file){
        while(true) {
            Boolean wrong = false;
            Login_Signup LS = Login_Signup.login(object, wrong);
            if(LS.b) {
                teacher = LS.teacher;
                break;
            }
            else if(!wrong){
                System.out.println("Do you want to signup?\n1)Yes\n2)No");
                int respond = scan.nextInt();
                if(respond == 1) {
                    clean();
                    Signup(object, file);
                }
                else break;
            }
        }
    }
    private static void listOfteacher(){
        try {
            List<String> list = Files.readAllLines(teacherFile.toPath());
            for (int i = 0; i < list.size(); i++) {
                Teacher t = Teacher.convertToTeacher(list.get(i));
                System.out.println((i + 1) + ")Name:" + t.getName() + " - ID:" + t.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void listOfCoursesForTeacher(long id){
        try {
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + id + ".txt");
            List<String> courses = Files.readAllLines(p);
            for (int i = 0; i < courses.size(); i++) {
                Course t = Course.convertToCourse(courses.get(i));
                System.out.println((i + 1) + ")Name:" + t.getName() + " - Code:" + t.getCode()
                        + " - Unit:" + t.getUnit() + " - Date Of Exam:" + t.getDate());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void listOfCoursesForStudent(long id , boolean b){
        try {
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfstudent\\student" + id + ".txt");
            List<String> courses = Files.readAllLines(p);
            if(b)
                courses = courses.stream().filter(s -> s.contains(String.valueOf(teacher.getId()))).toList();
            for (int i = 0; i < courses.size(); i++) {
                Course t = Course.convertToCourse(courses.get(i));
                System.out.println((i + 1) + ")Name:" + t.getName() + " - Code:" + t.getCode()
                        + " - Unit:" + t.getUnit() + " - Name Of Professor:" + t.getProfessor().getName()
                        + " - ID Of Professor:" + t.getProfessor().getId() + " - Date Of Exam:" + t.getDate());
            }
        }catch (Exception e){
            System.out.println("This course doesn't exist.");
        }
    }
    private static void listOfAssignmentForTeacher(long id){
        try {
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfteacher\\teacher" + id + ".txt");
            List<String> strs = Files.readAllLines(p);

            for (int i = 0; i < strs.size(); i++) {
                String[] parts = strs.get(i).split("-");
                System.out.println((i + 1) + ")Name:" + parts[0] + " - DeadLine:" + parts[2] + " - Type Of Assignment:" + parts[3]);
            }
        }catch (Exception e){
            System.out.println("This course doesn't exist.");
        }
    }
    private static void listOfAssignmentForStudent(long id , boolean b){
        try {
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmetOfstudent\\student" + id + ".txt");
            List<String> strs = Files.readAllLines(p);
            List<String> strcourses = Files.readAllLines(courseFile.toPath());
            Map<Integer , Course> courses = new HashMap<>();
            for(String s : strcourses){
                Course c = Course.convertToCourse(s);
                courses.put(c.getCode() , c);
            }
            if(b){
                Set<Integer> codes = courses.keySet();
                List<String> all = new ArrayList<>();
                for (Integer code : codes)
                    for(String s : strs)
                        if(s.contains(String.valueOf(code)))
                            all.add(s);
                strs = all;
            }
            for (int i = 0; i < strs.size(); i++) {
                String[] parts = strs.get(i).split("-");
                System.out.println((i + 1) + ")Name Of Assignment:" + parts[0] + " - DeadLine:" + parts[2] +
                        " - Type Of Assignment:" + parts[3] + " - Name Of Course:" + courses.get(Integer.parseInt(parts[1])).getName());
            }
        }catch (Exception e){
            System.out.println("This course doesn't exist.");
        }
    }
    private static void addToCourse(boolean b){
        System.out.println("Enter the id of student.");
        Long id = scan.nextLong();
        System.out.println("Enter the code of course.");
        int code = scan.nextInt();
        if(b){
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher"+teacher.getId()+".txt");
            try {
                List<String> str = Files.readAllLines(p);
                for(String s : str)
                    if(s.contains(String.valueOf(code)))
                        b = false;
                if(!b){
                    System.out.println("This course doesn't exist.");
                    return;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            List<String> strStudent = Files.readAllLines(studentFile.toPath());
            List<String> strCourse = Files.readAllLines(courseFile.toPath());
            Course c = null;
            Student s = null;
            for(String l : strCourse)
                if(Course.convertToCourse(l).getCode() == code)
                    c = Course.convertToCourse(l);
            for(String l : strStudent)
                if(Student.convertToStudent(l).getId() == id)
                    s = Student.convertToStudent(l);
            if(s == null) {
                System.out.println("This student doesn't exist.");
                return;
            }
            if(c == null) {
                System.out.println("This course doesn't exist.");
                return;
            }
            s.addCourse(c);
            c.addStudent(s);
            DataBase.add(new File("C:\\Users\\Asus\\Desktop\\project\\studentOfCourse\\course"+c.getCode()+".txt") , Student.convertToString(s) , true);
            DataBase.add(new File("C:\\Users\\Asus\\Desktop\\project\\courseOfStudent\\student"+s.getId()+".txt") , Course.convertToString(c) , true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void teacherAffairs(int respond){
        clean();
        respond = partTeachersByAdmin();
        switch (respond){
            case 1 :{
                clean();
                adding(null , "teacher" , teacherFile , true , false);
                break;
            }
            case 2 :{
                clean();
                removing(fillOutPeople() , "teacher" , teacherFile , true , false);
                break;
            }
            case 3 :{
                clean();
                listOfteacher();
                comeback();
                break;
            }
            case 4 :{
                clean();
                System.out.println("Enter the id of teacher.");
                listOfCoursesForTeacher(scan.nextLong());
                comeback();
                break;
            }
            case 5 :{
                clean();
                System.out.println("Enter the id of teacher.");
                long id = scan.nextLong();
                listOfAssignmentForTeacher(id);
                comeback();
                break;
            }
        }

    }
    private static void studentAffairs(int respond){
        clean();
        respond = partStudentByAdmin();
        switch (respond){
            case 1 :{
                clean();
                adding(null , "student" , studentFile , true , false);
                break;
            }
            case 2 :{
                clean();
                String s = fillOutPeople();
                removing(s , "student" , studentFile , true , false);
                try {
                    removeStudentofCourses(s);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case 3 :{
                clean();
                try {
                    List<String> list = Files.readAllLines(studentFile.toPath());
                    for (int i = 0; i < list.size(); i++) {
                        Student t = Student.convertToStudent(list.get(i));
                        System.out.println((i + 1) + ")Name:" + t.getName() + " - ID:" + t.getId());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Click 'Enter' to come back.");
                String s = scan.nextLine();
                scan.nextLine();
                if(s == null)
                    s = scan.nextLine();
                break;
            }
            case 4 :{
                clean();
                addToCourse(false);
                break;
            }
            case 5 :{
                clean();
                try {
                    removeStudentofCourse(false);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case 6 : {
                clean();
                System.out.println("Enter the id of student.");
                listOfCoursesForStudent(scan.nextLong() , false);
                comeback();
                break;
            }
            case 7 :{
                clean();
                System.out.println("Enter the id of student.");
                long id = scan.nextLong();
                listOfAssignmentForStudent(id , false);
                comeback();
                break;
            }
            case 8 :{
                clean();
                System.out.println("Enter the id of student.");
                long id = scan.nextLong();
                System.out.println("Enter the code of course.");
                int code = scan.nextInt();
                System.out.println("Enter the grade.");
                double grade = scan.nextDouble();
                try {
                    setGrade(id , code , grade , new File("C:\\Users\\Asus\\Desktop\\project\\gradeOfstudentForcourse\\course"+code+".txt"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 9 :{
                clean();
                System.out.println("Enter the code of course.");
                int code = scan.nextInt();
                allGrades(code);
                break;
            }
        }
    }
    private static void courseAffairs(int respond){
        clean();
        respond = partCourseByAdmin();
        switch (respond){
            case 1 :{
                clean();
                adding(fillOutCourse() , "course" , courseFile , false , true);
                try {
                    //coursesOfTeacher();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 2 :{
                clean();
                removing(fillOutCourse() , "course" , courseFile , false , true);
                break;
            }
            case 3 :{
                clean();
                try {
                    List<String> list = Files.readAllLines(courseFile.toPath());
                    for (int i = 0; i < list.size(); i++) {
                        Course t = Course.convertToCourse(list.get(i));
                        System.out.println((i + 1) + ")Name:" + t.getName() + " - Code:" + t.getCode()
                                + " - Unit:" + t.getUnit() + " - Name Of Professor:" + t.getProfessor().getName()
                                + " - ID Of Professor:" + t.getProfessor().getId() + " - Date Of Exam:" + t.getDate());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Click 'Enter' to come back.");
                String s = scan.nextLine();
                scan.nextLine();
                if(s == null)
                    s = scan.nextLine();
                break;
            }
        }
    }
    private static void assignmentAffairs(int respond){
        clean();
        respond = partAssignmentByAdmin();
        switch (respond){
            case 1 :{
                clean();
                adding(fillOutAssignment(TypeOfAssignment.project) , "project" , projectFile , false , false);
                addAssignmentToCourse(TypeOfAssignment.project);
                break;
            }
            case 2 :{
                clean();
                adding(fillOutAssignment(TypeOfAssignment.homework) , "homework" , homeworkFile , false , false);
                addAssignmentToCourse(TypeOfAssignment.homework);
                break;
            }
            case 3 :{
                clean();
                try {
                    removing(fillOutAssignment(TypeOfAssignment.project) , "project" , projectFile , false , false);
                    removeAssignmentFromCourse(TypeOfAssignment.project);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case 4 :{
                clean();
                try {
                    removing(fillOutAssignment(TypeOfAssignment.homework) , "homework" , homeworkFile , false , false);
                    removeAssignmentFromCourse(TypeOfAssignment.homework);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case 5 :{
                clean();
                try {
                    List<String> list = Files.readAllLines(projectFile.toPath());
                    for (int i = 0; i < list.size(); i++) {
                        Assignment t = Assignment.convertToAssignment(list.get(i), TypeOfAssignment.project);
                        System.out.println((i + 1) + ")Name:" + t.getName() + " - Code Of Course:" + t.getCourse().getCode()
                                + " - DeadLine:" + t.getDeadLine() + " - Type Of Assignment:" + t.getTypeOfAssignment());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Click 'Enter' to come back.");
                String s = scan.nextLine();
                scan.nextLine();
                if(s == null)
                    s = scan.nextLine();
                break;
            }
            case 6 :{
                clean();
                try {
                    List<String> list = Files.readAllLines(homeworkFile.toPath());
                    for (int i = 0; i < list.size(); i++) {
                        Assignment t = Assignment.convertToAssignment(list.get(i), TypeOfAssignment.homework);
                        System.out.println((i + 1) + ")Name:" + t.getName() + " - Code Of Course:" + t.getCourse().getCode()
                                + " - DeadLine:" + t.getDeadLine() + " - Type Of Assignment:" + t.getTypeOfAssignment());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Click 'Enter' to come back.");
                String s = scan.nextLine();
                scan.nextLine();
                if(s == null)
                    s = scan.nextLine();
                break;
            }
        }
    }
    private static void coursesOfTeacher() throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        List<String> strs = Files.readAllLines(teacherFile.toPath());
        for(String l : strs){
            teachers.add(Teacher.convertToTeacher(l));
        }
        List<Course> courses = new ArrayList<>();
        strs = Files.readAllLines(courseFile.toPath());
        for(String l : strs){
            courses.add(Course.convertToCourse(l));
        }
        for(Course c : courses)
            for(Teacher t : teachers)
                if(c.getProfessor().getId() == t.getId()){
                    t.addCourse(c);
                    c.setProfessor(t);
                }
        for(Teacher t : teachers) {
            try (Formatter out = new Formatter(new FileOutputStream("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + t.getId() + ".txt"))){
                for(Course c : t.getCourses())
                    out.format(Course.convertToString(c));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static void removeStudentofCourses(String id) throws Exception{
        List<String> lines = Files.readAllLines(courseFile.toPath());
        List<Course> courses = new ArrayList<>();
        for(String s : lines)
            courses.add(Course.convertToCourse(s));
        for(Course c : courses){
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfCourse\\course"+c.getCode()+".text");
            if(p.toFile().exists()) {
                List<String> str = Files.readAllLines(p);
                FileWriter writer = new FileWriter(p.toFile());
                for (String s : str) {
                    if (!s.contains(id)) {
                        writer.write(s + "\n");
                    }
                }
                writer.close();
            }
        }

    }
    private static void removeStudentofCourse(boolean b){
        System.out.println("Enter the id of student.");
        long id = scan.nextLong();
        System.out.println("Enter the code of course.");
        int code = scan.nextInt();
        if(b){
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher"+teacher.getId()+".txt");
            try {
                List<String> str = Files.readAllLines(p);
                for(String s : str)
                    if(s.contains(String.valueOf(code)))
                        b = false;
                if(!b){
                    System.out.println("This course doesn't exist.");
                    return;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        try {
            List<String> courses = Files.readAllLines(courseFile.toPath());
            Course c = null;
            for(String s : courses)
                if(s.contains(String.valueOf(code)))
                    c = Course.convertToCourse(s);
            if(c == null) {
                System.out.println("This course doesn't exist.");
                return;
            }
            Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfCourse\\course"+c.getCode()+".text");
            List<String> str =Files.readAllLines(p);
            FileWriter writer = new FileWriter(p.toFile());
            for(String s : str){
                if(!s.contains(String.valueOf(id))){
                    writer.write(s + "\n");
                }
            }
            writer.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void addAssignmentToCourse(TypeOfAssignment typeOfAssignment){
        try {
            Path p;
            if(typeOfAssignment == TypeOfAssignment.project)
                p = projectFile.toPath();
            else p = homeworkFile.toPath();
            List<String> assignments = Files.readAllLines(p);
            List<String> courses = Files.readAllLines(courseFile.toPath());
            List<Course> coursesAll = new ArrayList<>();
            for(String course : courses){
                Course c = Course.convertToCourse(course);
                coursesAll.add(c);
                for(String assignment : assignments){
                    System.out.println(assignment);
                    Assignment a = Assignment.convertToAssignment(assignment , typeOfAssignment);
                    if(a.getCourse().getCode().equals(c.getCode())){
                        a.setCourse(c);
                        c.addAssignment(a);
                        p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfcourse\\course"+c.getCode()+".txt");
                        if(!DataBase.checkOut(p.toFile() , Assignment.convertToString(a)))
                            DataBase.add(p.toFile() , Assignment.convertToString(a), true);
                    }
                }
            }
            for(Course c : coursesAll){
                Path p1 = Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfcourse\\course" + c.getCode() + ".txt");
                List<String> strs = Files.readAllLines(p1);
                for (String s : strs) {
                    Student student = Student.convertToStudent(s);
                    p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmetOfstudent\\student" + student.getId() + ".txt");
                    for(Assignment a : c.getAssignments()) {
                        if(!DataBase.checkOut(p.toFile() , Assignment.convertToString(a)))
                            DataBase.add(p.toFile() , Assignment.convertToString(a), true);
                    }
                }
                p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfteacher\\teacher" + c.getProfessor().getId() + ".txt");
                for(Assignment a : c.getAssignments()) {
                    if (!DataBase.checkOut(p.toFile(), Assignment.convertToString(a)))
                        DataBase.add(p.toFile(), Assignment.convertToString(a), true);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private static void removeAssignmentFromCourse(TypeOfAssignment typeOfAssignment) throws IOException {
        Path p;
        List<String> strs = Files.readAllLines(courseFile.toPath());
        Course course = null;
        if(typeOfAssignment == TypeOfAssignment.project) {
            p = projectFile.toPath();
        }
        else p = homeworkFile.toPath();
        System.out.println("Enter the name od assignment.");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("Enter the code of course.");
        int code =  scan.nextInt();
        for(String s : strs)
            if(s.contains(String.valueOf(code)))
                course = Course.convertToCourse(s);
        if(course == null) {
            System.out.println("This course doesn't exist.");
            return;
        }
        strs = Files.readAllLines(p);
        for(int i=strs.size()-1 ; i>=0 ; i--)
            if(strs.get(i).contains(name+"-"+code)) {
                strs.remove(i);
            }
        FileWriter writer = new FileWriter(p.toFile());
        for(String s : strs)
            writer.write(s + "\n");
        writer.close();
        p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfcourse\\course"+code+".txt");
        Files.delete(p);
        strs = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfCourse\\course"+code+".txt"));
        for(String s : strs) {
            Student student = Student.convertToStudent(s);
            p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmetOfstudent\\student"+student.getId()+".txt");
            Files.delete(p);
        }
        p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfteacher\\teacher"+course.getProfessor().getId()+".txt");
        writer = new FileWriter(p.toFile());
        strs = Files.readAllLines(p);
        for(int i=strs.size()-1 ; i>=0 ; i--)
            if(!strs.get(i).contains(name+"-"+code))
                writer.write(strs.get(i) + "\n");
        writer.close();
    }
    private static String addCourseToTeacher() throws IOException {
        System.out.println("Fill out the items.\n-Name: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("-Code: ");
        int code = scan.nextInt();
        System.out.println("-Unit: ");
        int unit = scan.nextInt();
        System.out.println("-Date Of Exam(Example : 1403/02/31) : ");
        scan.nextLine();
        String date = scan.nextLine();
        String s = name + "-" + code + "-" + unit + "-" + teacher.getName() + "-" + teacher.getId() + "-" + date;
        FileWriter writer = new FileWriter(courseFile , true);
        writer.write(s);
        writer = new FileWriter("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt" , true);
        writer.write(s);
        writer.close();
        return s;
    }
    private static void removeCourseFromTeacher() throws IOException, InterruptedException {
        System.out.println("Fill out the items.\n-Name: ");
        scan.nextLine();
        String name = scan.nextLine();
        System.out.println("-Code: ");
        int code = scan.nextInt();
        System.out.println("-Unit: ");
        int unit = scan.nextInt();
        System.out.println("-Date Of Exam(Example : 1403/02/31) : ");
        scan.nextLine();
        String date = scan.nextLine();
        String s = name + "-" + code + "-" + unit + "-" + teacher.getName() + "-" + teacher.getId() + "-" + date;
        List<String> courses = Files.readAllLines(Path.of("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"));
        boolean b = false;
        for(String c : courses)
            if(c.contains(s))
                b = true;
        if(!b) {
            System.out.println("This course doesn't exist.");
            Thread.sleep(3000);
            return;
        }
        remove(s , courseFile);
        remove(s , new File("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"));
        Path p = Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfCourse\\course" + code + ".txt");
        List<String> students = Files.readAllLines(p);
        for(String student : students){
            remove(s , new File("C:\\Users\\Asus\\Desktop\\project\\courseOfstudent\\student" + Student.convertToStudent(s).getId() + "txt"));
        }
        Files.delete(Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfcourse\\course" + code + ".txt"));
        Files.delete(Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfcourse\\course" + code + ".txt"));
    }
    private static boolean Contains(String s, String object , File file) throws IOException, InterruptedException {
        List<String> strs = Files.readAllLines(file.toPath());
        boolean b = false;
        for(String c : strs)
            if(c.contains(s))
                b = true;
        if(!b) {
            System.out.println("This "+object+" doesn't exist.");
            Thread.sleep(3000);
        }
        return b;
    }
    private static void studentAccess(int respond) {
        clean();
        respond = partStudentByTeacher();
        switch (respond){
                case 1 :{
                    clean();
                    try {
                        addToCourse(true);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case 2 :{
                    clean();
                    try {
                        removeStudentofCourse(false);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case 3 :{
                    clean();
                    try {
                        System.out.println("Enter the code of course.");
                        respond = scan.nextInt();
                        List<String> list = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher"+teacher.getId()+".txt"));
                        boolean ii = false;
                        for (String s : list)
                            if(s.contains(String.valueOf(respond)))
                                ii = true;
                        if(!ii){
                            System.out.println("This course doesn't exist.");
                            break;
                        }
                        list = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\studentOfCourse\\course"+respond+".txt"));
                        for (int i = 0; i < list.size(); i++) {
                            Student t = Student.convertToStudent(list.get(i));
                            System.out.println((i + 1) + ")Name:" + t.getName() + " - ID:" + t.getId());
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    comeback();
                    break;
                }
                case 4 :{
                    clean();
                    System.out.println("Enter the id of student.");
                    listOfCoursesForStudent(scan.nextLong() , true);
                    comeback();
                    break;
                }
                case 5 :{
                    clean();
                    System.out.println("Enter the id of student.");
                    long id = scan.nextLong();
                    listOfAssignmentForStudent(id , true);
                    comeback();
                    break;
                }
            }
    }
    private static void courseAccess(int respond){
        clean();
        respond = partCourseByTeacher();
        switch (respond){
            case 1 :{
                clean();
                try {
                    adding(addCourseToTeacher() , "course" , courseFile , false , true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 2 :{
                clean();
                try {
                    try {
                        removeCourseFromTeacher();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 3 :{
                clean();
                addToCourse(true);
                break;
            }
            case 4 :{
                clean();
                removeStudentofCourse(true);
                break;
            }
            case 5 :{
                clean();
                try {
                    List<String> list = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"));
                    for (int i = 0; i < list.size(); i++) {
                        Course t = Course.convertToCourse(list.get(i));
                        System.out.println((i + 1) + ")Name:" + t.getName() + " - Code:" + t.getCode()
                                + " - Unit:" + t.getUnit() + " - Date Of Exam:" + t.getDate());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Click 'Enter' to come back.");
                String s = scan.nextLine();
                scan.nextLine();
                if(s == null)
                    s = scan.nextLine();
                break;
            }
        }
    }
    private static void assignmentAccess(int respond){
        clean();
        respond = partAssignmentByTeacher();
        switch (respond){
            case 1 :{
                clean();
                String s = fillOutAssignment(TypeOfAssignment.project);
                try {
                    if(Contains(Course.convertToString(Assignment.convertToAssignment(s , TypeOfAssignment.project).getCourse()) , "corse" , new File("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"))) {
                        adding(s, "project", projectFile, false , false);
                        addAssignmentToCourse(TypeOfAssignment.project);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 2 :{
                clean();
                String s = fillOutAssignment(TypeOfAssignment.homework);
                try {
                    if(Contains(Course.convertToString(Assignment.convertToAssignment(s , TypeOfAssignment.homework).getCourse()) , "corse" , new File("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"))) {
                        adding(s, "project", homeworkFile, false , false);
                        addAssignmentToCourse(TypeOfAssignment.homework);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            case 3 : {
                try {
                    clean();
                    String s = fillOutAssignment(TypeOfAssignment.project);
                    try {
                        if (Contains(Course.convertToString(Assignment.convertToAssignment(s, TypeOfAssignment.project).getCourse()), "corse", new File("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"))) {
                            removing(s, "project", projectFile, false , false);
                            removeAssignmentFromCourse(TypeOfAssignment.project);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case 4 :{
                try {
                    clean();
                    String s = fillOutAssignment(TypeOfAssignment.homework);
                    try {
                        if (Contains(Course.convertToString(Assignment.convertToAssignment(s, TypeOfAssignment.homework).getCourse()), "corse", new File("C:\\Users\\Asus\\Desktop\\project\\courseOfteacher\\teacher" + teacher.getId() + ".txt"))) {
                            removing(s, "project", homeworkFile, false , false);
                            removeAssignmentFromCourse(TypeOfAssignment.homework);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
            case 5 :{
                clean();
                try {
                    List<String> list = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\assignmentOfteacher\\teacher" + teacher.getId() + ".txt"));
                    for (int i = 0; i < list.size(); i++) {
                        String[] parts = list.get(i).split("-");
                        System.out.println((i + 1) + ")Name:" + parts[0] + " - Code Of Course:" + parts[1]
                                + " - DeadLine:" + parts[2] + " - Type Of Assignment:" + parts[3]);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("Click 'Enter' to come back.");
                String s = scan.nextLine();
                scan.nextLine();
                if(s == null)
                    s = scan.nextLine();
                break;
            }

        }
    }
    private static void setGrade(long id , int code , double grade , File file) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        if(Files.readAllLines(file.toPath()).isEmpty())
            writer.write(id + "-"+grade);
        else  writer.write("\n" + id + "-" +grade);
        writer.close();
        DataBase.add(new File("C:\\Users\\Asus\\Desktop\\project\\gradeOfstudent\\student" + id + ".txt") , code+"-"+grade , true );
    }
    private static void allGrades(int code){
        Path p = Path.of("C:\\Users\\Asus\\Desktop\\project\\gradeOfstudentForcourse\\course" + code + ".txt");
        try {
            if(!p.toFile().exists()) {
                System.out.println("This course doesn't set the grades.");
                return;
            }
            List<String> strings = Files.readAllLines(p);
            List<String> students = Files.readAllLines(studentFile.toPath());
            for(String string : strings) {
                String[] parts = string.split("-");
                for (String student : students) {
                    if (student.contains(parts[0])) {
                        String[] pieces  = student.split("-");
                        System.out.println("Name:"+pieces[0] + " - Id: "+pieces[1]+" - Grade: "+parts[1]);
                    }
                }
            }
            Thread.sleep(3000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        boolean continues = true;
        int respond = 0;
        if(!Access)
            choice = menu();
        switch (choice){
            case 1 :{
                if(!Access){
                    clean();
                    System.out.println("Enter the code.");
                    respond = scan.nextInt();
                    ;
                    if (respond == 134679)
                        Access = true;
                    else {
                        System.out.println("You are not an Admin!");
                        Thread.sleep(5000);
                        break;
                    }
                }
                clean();
                respond = adminChoices();
                switch (respond){
                    case 1 :{
                        teacherAffairs(respond);
                        break;
                    }
                    case 2 :{
                        studentAffairs(respond);
                        break;
                    }
                    case 3 :{
                        courseAffairs(respond);
                        break;
                    }
                    case 4 : {
                        assignmentAffairs(respond);
                        break;
                    }
                    case 5 : choice = 3;
                }
                break;
            }
            case 2 :{
                if(!Access){
                    clean();
                    System.out.println("1)Signup\n2)Login");
                    respond = scan.nextInt();
                    switch (respond) {
                        case 1: {
                            Signup("teacher", teacherFile);
                            System.out.println("Signup was successful");
                            Access = true;
                            break;
                        }
                        case 2: {
                            Login("teacher", teacherFile);
                            Access = true;
                            break;
                        }
                    }
                    break;
                }
                clean();
                respond = teacherChoices();
                clean();
                switch (respond){
                    case 1 :{
                        studentAccess(respond);
                        break;
                    }
                    case 2 :{
                        courseAccess(respond);
                        break;
                    }
                    case 3 : {
                        assignmentAccess(respond);
                        break;
                    }
                    case 4 : choice = 3;
                }
                break;
            }
            case 3 :{
                clean();
                System.out.println("Are you sure to leave?\n1)Yes\n2)No");
                respond = scan.nextInt();
                if(respond == 1) {
                    System.out.println("See you late!");
                    continues = false;
                    return;
                }
                else {
                    clean();
                }
                break;
            }
            default:{
                tryAgain();
                main(new String[0]);
            }
        }
        if(continues)
            main(new String[0]);
    }
}
