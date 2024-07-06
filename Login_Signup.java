package clui;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Scanner;

public class Login_Signup {
    boolean b;
    Teacher teacher;
    private static Scanner scan = new Scanner(System.in);
    public static Login_Signup signup(String object , File file){
        Login_Signup LS = new Login_Signup();
        try{
            List<String> list = Files.readAllLines(file.toPath());
            String s = Main.fillOutPeople();
            LS.teacher = Teacher.convertToTeacher(s);
            if(!list.isEmpty())
                s = "\n" + s;
            for(String l : list)
                if(l.equals(s)){
                    System.out.println("This "+object+" exists.Try again later!");
                    Thread.sleep(3000);
                    LS.b = false;
                    return LS;
                }
            System.out.println("-password: ");
            while(true){
                String p = scan.nextLine();
                if(checkPassword(p)){
                    try(FileWriter writer1 = new FileWriter(file , true) ;
                    FileWriter writer2 = new FileWriter("C:\\Users\\Asus\\Desktop\\project\\passwords\\"+object+"Password.txt" , true)) {
                        writer1.write(s);
                        String[] parts = s.split("-");
                        if(list.isEmpty())
                            writer2.write(parts[1] + "-" + p);
                        else
                            writer2.write("\n" + parts[1] + "-" + p);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        LS.b = true;
        return LS;
    }

    public static Login_Signup login(String object , Boolean b){
        Login_Signup LS = new Login_Signup();
        try{
            List<String> list = Files.readAllLines(Paths.get("C:\\Users\\Asus\\Desktop\\project\\passwords\\"+object+"Password.txt"));
            String s = Main.fillOutPeople();
            LS.teacher = Teacher.convertToTeacher(s);
            System.out.println("Enter your password.");
            String p = scan.nextLine();
            String[] parts = s.split("-");
            s = parts[1] + "-" + p;
            for(String l : list)
                if(l.equals(s)){
                    System.out.println("Login was successful.\nWelcome!");
                    LS.b = true;
                    return LS;
                }
            for(String l : list)
                if(l.contains(parts[1])) {
                    System.out.println("Your password is wrong.Try later!");
                    Thread.sleep(3000);
                    LS.b = false;
                    return LS;
                }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("You don't have an account.Signup first.");
        LS.b = false;
        return LS;
    }
    private static boolean checkPassword(String p) {
        return true;
    }
}
