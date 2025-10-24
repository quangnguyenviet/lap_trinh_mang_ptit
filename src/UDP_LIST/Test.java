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
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2208;
        String ms1 = ";B22DCCN650;zckbhNbE";
        InetAddress sA = InetAddress.getByName(host);
        DatagramSocket sk = new DatagramSocket();
        DatagramPacket out = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out);
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, 1024);
        sk.receive(in);
        String s = new String(in.getData());
        System.out.println(s);
        String[] words = s.trim().split(";");
        String rid = words[0];
        String s1 = words[1];
        String s2 = words[2];
        String ms2 = rid + ";" + solve(s1, s2);
        out = new DatagramPacket(ms2.getBytes(), ms2.length(), sA, port);
        sk.send(out);
        System.out.println(ms2);
        sk.close();
        
    }
    public static String solve(String s1, String s2){
        String res = "";
        int len = s1.length();
        for(int i=0;i<len;i++){
            if(!s2.contains(s1.charAt(i) + "")){
                res+= s1.charAt(i);
            }
        }
        return res;
    }

}
