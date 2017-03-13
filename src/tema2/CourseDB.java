package tema2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static tema2.StudentDB.getConnection;

/**
 * Created by Alex on 3/13/2017.
 */
public class CourseDB {

    //Insert cu citire de la tastatura
    public static void insertConsoleC() throws Exception{
        Scanner scan = new Scanner(System.in);

        try{
            Connection con = getConnection();
            String course = scan.next();
            String teacher = scan.next();
            int year = scan.nextInt();
            PreparedStatement ins = con.prepareStatement("INSERT INTO courses (CourseName,Teacher,Year) VALUES ('"+course+"', '"+teacher+"', '"+year+"')");
            ins.executeUpdate();
            System.out.println("Operatia de inserare a fost realizata");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    //Insert fara citire
    public static void insertConsoleC(String course, String teacher, int year) throws Exception{
        Scanner scan = new Scanner(System.in);

        try{
            Connection con = getConnection();
            PreparedStatement ins = con.prepareStatement("INSERT INTO courses (CourseName,Teacher,Year) VALUES ('"+course+"', '"+teacher+"', '"+year+"')");
            ins.executeUpdate();
            System.out.println("Operatia de inserare a fost realizata");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static void updateByIdC(int id) throws Exception{
        Scanner scan = new Scanner(System.in);
        id = scan.nextInt();
        final String course = scan.next();
        final String teacher = scan.next();
        final int year = scan.nextInt();

        try{
            Connection con = getConnection();//creeaza conexiunea

            Statement stm = con.createStatement();
            String sql = "update courses set CourseName='"+course+"', Teacher='"+teacher+"', Year='"+year+"' where id = '"+id+"'";
            stm.executeUpdate(sql);
            System.out.println("Modificarea tabelului a fost reusita.");

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static ArrayList<String> printAllC() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement x = con.prepareStatement("SELECT * FROM courses");
            ResultSet result = x.executeQuery();

            ArrayList<String>  array = new ArrayList<String>();

            System.out.println("Courses table.");

            while(result.next()){
                System.out.print(result.getString("ID") + " " +
                        (result.getString("CourseName")) + " " +
                        (result.getString("Teacher")) + " " +
                        (result.getString("Year")) + "\n");
                array.add(result.getString("ID"));
                array.add(result.getString("CourseName"));
                array.add(result.getString("Teacher"));
                array.add(result.getString("Year"));

            }
            System.out.println("................");
            return array;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void deleteC(Integer id) throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement del = con.prepareStatement("DELETE FROM courses WHERE ID = '"+id+"' ");
            del.executeUpdate();
            System.out.println("Elementul cu ID-ul: " + id.toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
