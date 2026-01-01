/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_LIST;

/**
 *
 * @author nvqua
 */
import java.net.*;
import java.util.*;
import java.io.*;
public class UDP6 {
    public static void main(String[] args) {
        try {
            String host = "203.162.10.109";
            InetAddress sA = InetAddress.getByName(host);
            int port = 2208;
            DatagramSocket sk = new DatagramSocket();
            String s = ";B22DCCN650;icRBHCj6";
            DatagramPacket out1 = new DatagramPacket(s.getBytes(), s.length(), sA, port);
            sk.send(out1);
            
            byte[] bs = new byte[1024];
            DatagramPacket in = new DatagramPacket(bs, 1024);
            sk.receive(in);
            String s1 = new String(in.getData());
            System.out.println(s1);
            List<String> l = mySplit(s1.replace(";", ","), ",");
            int num1 = Integer.parseInt(l.get(1), 2);
            int num2 = Integer.parseInt(l.get(2), 2);
            int sum = num1 + num2;
            String res = l.get(0) + ";" + sum;
            DatagramPacket dp = new DatagramPacket(res.getBytes(), res.length(), sA, port);
            sk.send(dp);
            
            sk.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<String> mySplit(String s, String delimiter){
        return new ArrayList<>(Arrays.asList(
                s.trim().split(delimiter)
        ));
    }
}
