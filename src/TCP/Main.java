/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.util.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) throws Exception {
       String code = "5LQeKr5N";
       String ms1 = "B22DCCN650;" + code;
       int port = 2209;
       String host = "203.162.10.109";
       Socket sk =  new Socket(host, port);
       ObjectInputStream in = new ObjectInputStream(sk.getInputStream());
       ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
       out.writeObject(ms1);
       out.flush();
       Customer c = (Customer) in.readObject();
       System.out.println(c);
       c.setUserName(fixUserName(c.getName()));
       c.setName(fixName(c.getName()));
       c.setDayOfBirth(fixDate(c.getDayOfBirth()));
       
       out.writeObject(c);
       out.flush();
       out.close();
       in.close();
       sk.close();
    }
    public static String fixName(String s){
        String[] words = s.trim().split("\\s+");
        List<String> l = new ArrayList<>(Arrays.asList(words));
        int len = l.size();
        String last  = l.get(len-1);
        l.remove(len-1);
        List<String> l1 = new ArrayList<>();
        l1.add(last.toUpperCase() + ",");
        for(String w : l){
            l1.add(toTitle(w.toLowerCase()));
        }
        return String.join(" ", l1);
    }
    public static String fixDate(String s){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyy");
        LocalDate date = LocalDate.parse(s, dtf);
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf1);
    }
    public static String fixUserName(String s){
        String words[] = s.trim().toLowerCase().split("\\s+");
        String res = "";
        int len = words.length;
        for(int i=0;i<len;i++){
            if(i!= len-1) res += words[i].charAt(0);
            else{
                res+=words[i];
            }
        }
            return res;
    }
    public static String toTitle(String s){

        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
  
}
