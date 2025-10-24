/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

import java.io.*;
import java.util.*;
import java.net.*;
/**
 *
 * @author nvqua
 */
public class Main {
    public static void main(String[] args) throws Exception{
        String host = "203.162.10.109";
        int port = 2209;
        String ms1 = "B22DCCN650;MYN8Ntpl";
        Socket sk = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(sk.getInputStream());
        out.writeObject(ms1);
        out.flush();
        Laptop laptop = (Laptop) in.readObject();
        System.out.println(laptop);
        laptop.name = fixName(laptop.name);
        laptop.quantity = fixQuan(laptop.quantity);
        out.writeObject(laptop);
        out.flush();
        System.out.println(laptop);
        
        in.close();
        out.close();
        sk.close();
    }
    public static String fixName(String s){
        String[] words = s.trim().split("\\s+");
        List<String> list1 = new ArrayList<>(Arrays.asList(words));
        int len = list1.size();
        String lastItem = list1.get(len-1);
        list1.set(len-1, list1.get(0));
        list1.set(0, lastItem);
        
        return String.join(" ", list1);
    }
    public static int fixQuan(int n){
        StringBuilder sb = new StringBuilder(n + "");
        sb.reverse();
        return Integer.valueOf(sb.toString());
    }
}
