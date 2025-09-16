/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class Main {
    public static int solve(double x){
        long n = (long)Math.floor(x);
        int res = 0;
        while(n!= 0){
            res += n%10;
            n/=10;
        }
        return res;
    }
    
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "aGcxmGS4";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2209;
        Socket sk = new Socket(host, port);
        ObjectInputStream in = new ObjectInputStream(sk.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
        out.writeObject(message);
        out.flush();
        
        Product p = (Product)in.readObject();
        System.out.println(p);
        p.setDiscount(solve(p.getPrice()));
        
        System.out.println(p);
        
        out.writeObject(p);
        out.flush();
        
        sk.close();
        in.close();
        out.close();
        
    }
}
