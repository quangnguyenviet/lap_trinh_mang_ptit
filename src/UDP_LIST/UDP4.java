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
import java.net.*;
import java.util.*;
public class UDP4 {
    public static void main(String[] args) throws Exception {
        String ms1 = ";B22DCCN650;xLFA58SA";
        String host = "203.162.10.109";
        int port = 2207;
        DatagramSocket sk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket out1 = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out1);
        
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, bs1.length);
        sk.receive(in);
        String s = new String(in.getData());
        System.out.println(s);
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().replace(';', ',').split(",")));
        String rid = words.getFirst();
        words.removeFirst();
        Collections.sort(words, (o1, o2) -> {
            Integer x1 = Integer.valueOf(o1);
            Integer x2 = Integer.valueOf(o2);
            return x1.compareTo(x2);
        });
        int len = words.size();
        String ms2 = rid + ";" + words.get(len - 2) + "," + words.get(1);
        System.out.println(ms2);
        DatagramPacket out2 = new DatagramPacket(ms2.getBytes(), ms2.length(), sA, port);
        sk.send(out2);
        sk.close();
    }
}
