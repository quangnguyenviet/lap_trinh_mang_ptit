/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_Obj2;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.util.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) throws Exception {
        String ms1 = ";B22DCCN650;FffPLhiU";
        String host = "203.162.10.109";
        int port = 2209;
        DatagramSocket sk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket out1 = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out1);
        
        byte[] bs = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs, bs.length);
        sk.receive(in);
        String rId = new String(in.getData(), 0, 8);
        System.out.println(rId);
        ByteArrayInputStream bais = new ByteArrayInputStream(bs, 8, in.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Customer c = (Customer) ois.readObject();
        System.out.println(c);
        c.userName = formatAcc(c.name);
        c.name = formatName(c.name);
        
        c.dayOfBirth = formatDOB(c.dayOfBirth);
        System.out.println(c);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(c);
        oos.flush();
        byte[] bs2 = new byte[8 + baos.size()];
        System.arraycopy(rId.getBytes(), 0, bs2, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, bs2, 8, baos.size());
        DatagramPacket out2 = new  DatagramPacket(bs2, bs2.length, sA, port);
        sk.send(out2);
        sk.close();
    }
    public static String formatName(String s){
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        List<String> new_words = new ArrayList<>();
        words.forEach((t) -> {
            StringBuilder sb = new StringBuilder(t.toLowerCase());
            sb.replace(0, 1, Character.toUpperCase(sb.charAt(0)) + "");
            new_words.add(sb.toString());
        });
        String lastWord = new_words.getLast().toUpperCase();
        new_words.removeLast();
        String res = lastWord + ", ";
        res += String.join(" ", new_words);
        return res;
        
    }
    public static String formatAcc(String s){
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().split("\\s+")));
        System.out.println(s);
        String res = "";
        for(int i=0;i<words.size()-1;i++){
            res+=words.get(i).toLowerCase().charAt(0);
        }
        res += words.getLast().toLowerCase();
        return res;
    }
    public static String formatDOB(String s){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date =  LocalDate.parse(s, formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String res = date.format(formatter2);
        return res;
    }
}
