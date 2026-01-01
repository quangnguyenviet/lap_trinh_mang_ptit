/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst.ObjectStream.TCP03;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) {
        try {
            String host = "203.162.10.109";
            int port = 2209;
            Socket sk = new Socket(host, port);
            String s = "B22DCCN650;5LQeKr5N";
            
            ObjectInputStream in = new ObjectInputStream(sk.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
            out.writeObject(s);
            System.out.println(s);
            out.flush();
            
            Customer c = (Customer) in.readObject();
            System.out.println(c);
            
            c.setUserName(fixUsername(c.getName()));
            c.setName(fixName(c.getName()));
            c.setDayOfBirth(fixDate(c.getDayOfBirth()));
            
            System.out.println(c);
            out.writeObject(c);
            out.flush();
            out.close();
            in.close();
            sk.close();
            
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
    
    public static String toTitle(String s){
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
    public static String fixName(String s){
        List<String> l = new ArrayList<>(
                Arrays.asList(
                        s.trim().toLowerCase().split("\\s+")
                )
        );
        String last = l.get(l.size()-1).toUpperCase();
        l.remove(l.size() -1);
        List<String> l1 = new ArrayList<>();
        for(String str : l){
            l1.add(toTitle(str));
        }
        return last + ", " + String.join(" ", l1);
    }
    public static String fixDate(String s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse(s, dtf);
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf1);
    }
    public static ArrayList<String> mySplit(String s, String delimiter){
        return new ArrayList<>(
                Arrays.asList(
                        s.trim().toLowerCase().split(delimiter)
                )
        );
    }
    public static String fixUsername(String s){
        List<String> l = mySplit(s, "\\s+");
        System.out.println(l);
        int len = l.size();
        String res = "";
        for(int i=0;i<len;i++){
            if(i == len-1){
                res += l.get(i);
            }
            else{
                res += l.get(i).charAt(0);
            }
        }
        return res;
    }
}

