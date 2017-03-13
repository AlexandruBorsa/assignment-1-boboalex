package tema2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static tema2.StudentDB.getConnection;

/**
 * Created by Alex on 3/13/2017.
 */
public class EnrollDB {

    //insert cu citire de la tastatura
    public static void insertConsoleE() throws Exception{
        Scanner scan = new Scanner(System.in);

        try{
            Connection con = getConnection();
            String student = scan.next();
            String course = scan.next();
            PreparedStatement ins = con.prepareStatement("INSERT INTO enrolled (Student,Course) VALUES ('"+student+"', '"+course+"')");
            ins.executeUpdate();
            System.out.println("Operatia de inserare a fost realizata");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    //insert fara citire de la tastatura
    public static void insertConsoleE(String student, String course) throws Exception{
        Scanner scan = new Scanner(System.in);

        try{
            Connection con = getConnection();
            PreparedStatement ins = con.prepareStatement("INSERT INTO enrolled (Student,Course) VALUES ('"+student+"', '"+course+"')");
            ins.executeUpdate();
            System.out.println("Operatia de inserare a fost realizata");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static void updateByIdE(int id) throws Exception{
        Scanner scan = new Scanner(System.in);
        id = scan.nextInt();
        final String student = scan.next();
        final String course = scan.next();

        try{
            Connection con = getConnection();//creeaza conexiunea

            Statement upd = con.createStatement();
            String sql = "update enrolled set Student='"+student+"', Course='"+course+"' where id = '"+id+"'";
            upd.executeUpdate(sql);
            System.out.println("Modificarea tabelului a fost reusita.");

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static ArrayList<String> printAllE() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement x = con.prepareStatement("SELECT * FROM enrolled");
            ResultSet result = x.executeQuery();

            ArrayList<String>  array = new ArrayList<String>();

            System.out.println("Enrolled table.");

            while(result.next()){
                System.out.print(result.getString("ID") + " " +
                        (result.getString("Student")) + " " +
                        (result.getString("Course")) + "\n");
                array.add(result.getString("ID"));
                array.add(result.getString("Student"));
                array.add(result.getString("Course"));

            }
            System.out.println("................");
            return array;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void deleteE(Integer id) throws Exception{
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
