/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;

public class Test {

    public static void main(String[] args) {
        try{
            String ms1= "B22DCCN650;GYZfNuW7";
            int port = 2207;
            String host = "203.162.10.109";
            Socket sk = new Socket(host, port);
            DataInputStream in = new DataInputStream(sk.getInputStream());
            DataOutputStream out = new DataOutputStream(sk.getOutputStream());
            out.writeUTF(ms1);
            out.flush();
            int n = in.readInt();
            String res = Integer.toBinaryString(n) + ";" + Integer.toHexString(n).toUpperCase();
            out.writeUTF(res);
            out.flush();
            in.close();
            out.close();
            sk.close();
            
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    public static String toBin(Integer n){
        return Integer.toBinaryString(n);
    }
    
    

}
