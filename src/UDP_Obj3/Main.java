/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_Obj3;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;
        String ms1 = ";B22DCCN650;LrDO0FKm";
        DatagramSocket sk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket out1 = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out1);
        
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, bs1.length);
        sk.receive(in);
        String rid = new String(in.getData(), 0, 8);
        System.out.println(rid);
        ByteArrayInputStream bais = new ByteArrayInputStream(bs1, 8, in.getLength()-8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Student s = (Student) ois.readObject();
        System.out.println(s);
        s.name = formatName(s.name);
        s.email = setEmail(s.name);
        System.out.println(s);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(s);
        oos.flush();
        byte[] bs2 = new byte[8 + baos.size()];
        System.arraycopy(rid.getBytes(), 0, bs2, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, bs2, 8, baos.size());
        DatagramPacket out2 = new DatagramPacket(bs2, bs2.length, sA, port);
        sk.send(out2);
        sk.close();
    }
    public static String formatName(String s){
        String[] words = s.trim().split("\\s+");
        String res = "";
        for(String w : words){
            StringBuilder sb = new StringBuilder(w.toLowerCase());
            sb.replace(0, 1, Character.toUpperCase(sb.charAt(0)) + "");
            res += sb.toString() + " ";
        }
        return res.trim();
    }
    public static String setEmail(String s){
        List<String> words = new ArrayList<>(Arrays.asList(s.toLowerCase().split(" ")));
        String email = words.getLast();
        for(int i=0;i<words.size()-1;i++){
            email += words.get(i).charAt(0);
        }
        email += "@ptit.edu.vn";
        return email;
    }
}
