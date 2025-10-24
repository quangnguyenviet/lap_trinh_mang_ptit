/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Main {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;
        String ms1 = ";B22DCCN650;qvlxjDSw";
        InetAddress sA = InetAddress.getByName(host);
        DatagramSocket sk = new DatagramSocket();
        DatagramPacket out = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out);
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, bs1.length);
        sk.receive(in);
        String rid = new String(in.getData(), 0, 8);
        System.out.println(rid);
        ByteArrayInputStream bais = new ByteArrayInputStream(bs1, 8, in.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book) ois.readObject();
        System.out.println(book);
        book.title = fixTitle(book.title);
        book.author = fixAuthor(book.author);
        book.isbn = fixIS(book.isbn);
        book.publishDate = fixDate(book.publishDate);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();
        byte[] bs2 = new byte[8 + baos.size()];
        System.arraycopy(rid.getBytes(), 0, bs2, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, bs2, 8, baos.size());
        out = new DatagramPacket(bs2, bs2.length, sA, port);
        sk.send(out);
        
    }
    public static String toTitle(String s){
        StringBuilder sb = new StringBuilder(s.trim().toLowerCase());
        sb.replace(0, 1, Character.toUpperCase(sb.charAt(0)) + "");
        return sb.toString();
    }
    public static String fixTitle(String s){
        String words[] = s.trim().split("\\s+");
        List<String> list = new ArrayList<>();
        for(String w : words){
            list.add(toTitle(w));
        }
        return String.join(" ", list);
    }
    public static String fixAuthor(String s){
        String[] words = s.trim().split("\\s+");
        List<String> list = new ArrayList<>();
        for(String w : words){
            list.add(toTitle(w));
        }
        list.set(0, list.get(0).toUpperCase() + ",");
        return String.join(" ", list);
        
    }
    public static String fixIS(String s){
        String res = "";
        for(int i=0;i<s.length();i++){
            res += s.charAt(i) + "";
            if(i == 2 || i==3 || i==5 || i==11){
                res += "-";
            }
        }
        return res;
    }
    public static String fixDate(String s){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(s, dateTimeFormatter);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM/yyyy");
        return date.format(dateTimeFormatter1);
    }
}
