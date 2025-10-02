/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_Obj1;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String ms = ";B22DCCN650;cFNhj5m6";
        String host = "203.162.10.109";
        int port = 2209;
        DatagramSocket sk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket out1 = new DatagramPacket(ms.getBytes(), ms.length(), sA, port);
        sk.send(out1);
        
        byte[] bs = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs, bs.length);
        sk.receive(in);
        
        String requestId = new String(in.getData(), 0, 8);
        System.out.println(requestId);
        ByteArrayInputStream bais = new  ByteArrayInputStream(in.getData(), 8, in.getLength()-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Product p = (Product) ois.readObject();
        System.out.println(p);
        p.setName(fixName(p.getName()));
        p.setQuantity(fixQuantity(p.getQuantity()));
        System.out.println(p);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(p);
        oos.flush();
        
        byte[] byte_data = new byte[8 + baos.size()];
        System.arraycopy(requestId.getBytes(), 0, byte_data, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, byte_data, 8, baos.size());
        DatagramPacket out2 = new DatagramPacket(byte_data, byte_data.length, sA, port);
        sk.send(out2);
        sk.close();
        
    }
    public static int fixQuantity(int x){
        String s = String.valueOf(x);
        StringBuilder sb = new StringBuilder(s);
        return Integer.parseInt(sb.reverse().toString());
    }
    public static String fixName(String s){
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        String temp = words.getFirst();
        words.set(0, words.getLast());
        words.set(words.size() -1, temp);
        return String.join(" ", words);
    }
}
