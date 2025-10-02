/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.util.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author nvqua
 */
public class Main {
    public static int calDiscount(double price){
        int beforeComma = (int)Math.floor(price);
        int res = 0;
        while(beforeComma != 0){
            res += beforeComma%10;
            beforeComma/=10;
        }
        return res;
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
        Address address = (Address)in.readObject();
        address.setAddressLine(formatAddressLine(address.getAddressLine()));
        address.setPostalCode(formatPostal(address.getPostalCode()));
        out.writeObject(address);
        out.flush();
        
        in.close();
        out.close();
        sk.close();
    }
    public static String formatAddressLine(String s){
        String[] words = s.trim().split("\\s+");
        List<String> l_words = new ArrayList<>();
        for(String w : words){
            StringBuilder temp = new StringBuilder();
            int len = w.length();
            for(int i=0;i<len ;i++){
                if(Character.isAlphabetic(w.charAt(i)) || Character.isDigit(w.charAt(i))){
                    temp.append(Character.toLowerCase(w.charAt(i)));
                }
            }
            
            if(temp.length() >=1){
                temp.replace(0, 1, Character.toUpperCase(temp.charAt(0)) + "");
            }
            System.out.println(temp.toString());
            l_words.add(temp.toString());
        }
        double x = 1.332425d;
        String formatted = String.format("%.2f", x);
        System.out.println(formatted);
        
        return String.join(" ", l_words);
        
        
    }
    public static String formatPostal(String s){
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        for(int i=0;i<len;i++){
            if(s.charAt(i) == '-' || Character.isDigit(s.charAt(i))){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
