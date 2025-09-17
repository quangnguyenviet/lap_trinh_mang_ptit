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
import java.net.*;
import java.util.*;
public class Main {
    public static String formatAddressLine(String s){
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        List<String> newWord = new ArrayList<>();
        for(String w : words){
            StringBuilder temp = new StringBuilder();
            for(int i=0;i<w.length();i++){
                if(Character.isAlphabetic(w.charAt(i)) || Character.isDigit(w.charAt(i))){
                    temp.append(Character.toLowerCase(w.charAt(i)));
                }
            }
            if(temp.length() ==0) continue;
            temp.setCharAt(0, Character.toUpperCase(temp.charAt(0)));
            newWord.add(temp.toString());
        }
        return String.join(" ", newWord);
    }
    public static String formatPostalCode(String s){
        StringBuilder res = new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '-' || Character.isDigit(s.charAt(i))){
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "qC6ikvIO";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2209;
        Socket sk = new Socket(host, port);
        ObjectInputStream in = new ObjectInputStream(sk.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
        out.writeObject(message);
        out.flush();
        Address ad = (Address)in.readObject();
        System.out.println(ad);
        ad.setAddressLine(formatAddressLine(ad.getAddressLine()));
        ad.setPostalCode(formatPostalCode(ad.getPostalCode()));
        System.out.println(ad);
        out.writeObject(ad);
        out.flush();
        
        in.close();
        out.close();
        sk.close();
    }
}
