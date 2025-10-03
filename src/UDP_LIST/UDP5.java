/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_LIST;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.*;
public class UDP5 {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;
        String ms1 = ";B22DCCN650;gZ9ZeqCy";
        InetAddress sA = InetAddress.getByName(host);
        DatagramSocket sk = new DatagramSocket();
        DatagramPacket out1 = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out1);
        
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, bs1.length, sA, port);
        sk.receive(in);
        String s = new String(in.getData());
        System.out.println(s);
        
        String[] words = s.trim().split(";");
        BigInteger a = new BigInteger(words[1]);
        BigInteger b = new BigInteger(words[2]);
        String sum = a.add(b).toString();
        String diff = a.subtract(b).abs().toString();
        String ms2 = words[0] + ";" + sum + "," + diff;
        DatagramPacket out2 = new DatagramPacket(ms2.getBytes(), ms2.length(), sA, port);
        sk.send(out2);
        sk.close();
    }
}
