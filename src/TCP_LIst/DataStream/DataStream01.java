/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst.DataStream;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class DataStream01 {
    public static void main(String[] args) {
        try {
            String host = "203.162.10.109";
            int port = 2207;
            Socket sk = new Socket(host, port);
            String s = "B22DCCN650;GYZfNuW7";
            DataInputStream in = new DataInputStream(sk.getInputStream());
            DataOutputStream out = new DataOutputStream(sk.getOutputStream());
            out.writeUTF(s);
            out.flush();
            int n = in.readInt();
            String bin = Integer.toBinaryString(n);
            String hex = Integer.toHexString(n).toUpperCase();
            String res = bin + ";" + hex;
            out.writeUTF(res);
            out.flush();
            
            out.close();
            in.close();
            sk.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}
