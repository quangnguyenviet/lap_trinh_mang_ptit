/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_LIST;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class Test {
    public static void main(String[] args) {
        try {
            int port = 2208;
            String host = "203.162.10.109";
            InetAddress sA = InetAddress.getByName(host);
            String ms1 = ";B22DCCN650;icRBHCj6";
            DatagramSocket sk = new DatagramSocket();
            DatagramPacket out = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
            sk.send(out);
            byte[] bs = new byte[1024];
            DatagramPacket in = new DatagramPacket(bs, 1024);
            sk.receive(in);
            String s = new String(in.getData());
            s = s.replace(";", ",");
             System.out.println(s);
            String[] words = s.trim().split(",");
            String rid = words[0];
            int num1 = Integer.valueOf(words[1], 2);
            int num2 = Integer.valueOf(words[2], 2);
            int sum = num1 + num2;
            String res = rid + ";" + sum;
            out = new DatagramPacket(res.getBytes(), res.length(), sA, port);
            sk.send(out);
            
            sk.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    
    

}
