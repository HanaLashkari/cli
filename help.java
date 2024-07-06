package clui;
import org.junit.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class help {
    Scanner scan = new Scanner(System.in);
    @Test
    public void test() throws IOException {
        File file = new File("C:\\Users\\Asus\\Desktop\\project\\Teachers.txt");
        List<String> list = Files.readAllLines(file.toPath());
        String s = "hana-1384";
        System.out.println("Enter your password.");
        String p = "147258369";
        String[] parts = s.split("-");
        s = parts[1] + "-" + p;
        assertEquals(list.get(0) , s);
    }
    public static void main(String[] args)throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\Asus\\Desktop\\project\\Courses.txt"));
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("hana" , 1425L));
        teachers.add(new Teacher("nirvana" , 85468L));
        out.writeObject(teachers);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\Asus\\Desktop\\project\\Courses.txt"));
        Object o = in.readObject();
        out.close();
        List<Teacher> teachers1 = (ArrayList<Teacher>)o;
        for(Teacher t : teachers1)
            System.out.println(t.getName());
        System.out.println(o.getClass().getSimpleName());
    }
}
