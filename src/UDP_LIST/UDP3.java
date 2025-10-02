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
import java.net.*;
import java.io.*;
public class UDP3 {
    public static void main(String[] args) throws Exception {
        String ms = ";B22DCCN650;GJDVa07Q";
        String host = "203.162.10.109";
        int port = 2208;
        InetAddress sA = InetAddress.getByName(host);
        DatagramSocket sk = new DatagramSocket();
        DatagramPacket pkOut = new DatagramPacket(ms.getBytes(), ms.length(), sA, port);
        sk.send(pkOut);
        
        byte[] bs = new byte[1024];
        DatagramPacket pkIn = new DatagramPacket(bs, bs.length);
        sk.receive(pkIn);
        
        String s = new String(pkIn.getData());
        System.out.println(s);
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().replace(';', ' ').split("\\s+")));
        System.out.println(words);
        String requestId = words.getFirst();
        words.removeFirst();
        Collections.sort(words, (o1, o2) -> {
            return o2.toLowerCase().compareTo(o1.toLowerCase());
        });
        System.out.println(words);
        StringBuilder resSb = new StringBuilder(requestId + ";");
        resSb.append(
        String.join(",", words)
        );
        String res = resSb.toString();
        System.out.println(res);
        DatagramPacket pkOut2 = new DatagramPacket(res.getBytes(), res.length(), sA, port);
        sk.send(pkOut2);
        sk.close();
    }
    
}
