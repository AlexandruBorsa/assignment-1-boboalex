package tema2;


import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Alex on 3/12/2017.
 */
public class StudentDB {
    @SuppressWarnings("unchecked")
    public static Connection getConnection() throws Exception {
        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/tema2?verifyServerCertificate=false&useSSL=true";
            String username = "root";
            String password = "ketchup";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url, username, password);
            //System.out.println("Connected");

            return conn;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    //insert cu citire de la tastatura
    public static void insertConsoleS() throws Exception{
        Scanner scan = new Scanner(System.in);
        try{
            Connection con = getConnection();
            String name = scan.next();
            String birth = scan.next();
            String address = scan.next();
            PreparedStatement ins = con.prepareStatement("INSERT INTO students (Name,Birthdate,Address) VALUES ('"+name+"', '"+birth+"', '"+address+"')");
            ins.executeUpdate();
            System.out.println("Operatia de inserare a fost realizata");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    //insert fara citire de la tastatura
    public static void insertConsoleS(String name, String birth, String address) throws Exception{
        Scanner scan = new Scanner(System.in);
        try{
            Connection con = getConnection();
            PreparedStatement ins = con.prepareStatement("INSERT INTO students (Name,Birthdate,Address) VALUES ('"+name+"', '"+birth+"', '"+address+"')");
            ins.executeUpdate();
            System.out.println("Operatia de inserare a fost realizata");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public static void updateByIdS(int id) throws Exception{
        Scanner scan = new Scanner(System.in);
        id = scan.nextInt();
        final String name = scan.next();
        final String birth = scan.next();
        final String address = scan.next();

        try{
            Connection con = getConnection();//creeaza conexiunea

            Statement stm = con.createStatement();
            String sql = "update students set Name='"+name+"', Birthdate='"+birth+"', Address='"+address+"' where id = '"+id+"'";
            stm.executeUpdate(sql);
            System.out.println("Modificarea tabelului a fost reusita.");

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public static ArrayList<String> printAllS() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement x = con.prepareStatement("SELECT * FROM students");
            ResultSet result = x.executeQuery();

            ArrayList<String>  array = new ArrayList<String>();

            System.out.println("Students table.");

            while(result.next()){
                System.out.print(result.getString("ID") + " " +
                        (result.getString("Name")) + " " +
                        (result.getString("Birthdate")) + " " +
                        (result.getString("Address")) + "\n");
                array.add(result.getString("ID"));
                array.add(result.getString("Name"));
                array.add(result.getString("Birthdate"));
                array.add(result.getString("Address"));

            }
            System.out.println("................");
            return array;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    public static void deleteS(Integer id) throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement del = con.prepareStatement("DELETE FROM students WHERE ID = '"+id+"' ");
            del.executeUpdate();
            System.out.println("Elementul cu ID-ul: " + id.toString());
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
