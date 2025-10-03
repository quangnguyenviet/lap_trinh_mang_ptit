/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_Obj4;

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
        String ms1 = ";B22DCCN650;QKFrCQFK";
        DatagramSocket sk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket out1 = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out1);
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, bs1.length);
        sk.receive(in);
        String rid = new String(in.getData(), 0, 8);
        ByteArrayInputStream bais = new ByteArrayInputStream(bs1, 8, bs1.length - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Book book = (Book) ois.readObject();
        System.out.println(book);
        book.title = formatTitle(book.title);
        book.author = formatAuthor(book.author);
        book.isbn = formatISBN(book.isbn);
        book.publishDate = formatDate(book.publishDate);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(book);
        oos.flush();
        
        System.out.println(book);
        byte[] bs2 = new byte[8 + baos.size()];
        System.arraycopy(rid.getBytes(), 0, bs2, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, bs2, 8, baos.size());
        DatagramPacket out2 = new DatagramPacket(bs2, bs2.length, sA, port);
        sk.send(out2);
        sk.close();

    }

    public static String toTitle(String s) {
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        sb.replace(0, 1, Character.toUpperCase(sb.charAt(0)) + "");
        return sb.toString();
    }

    public static String formatTitle(String s) {
        String[] words = s.trim().split("\\s+");
        List<String> res = new ArrayList<>();
        for (String w : words) {
            res.add(toTitle(w));
        }
        return String.join(" ", res);
    }
    public static String formatAuthor(String s){
        String[] words = s.trim().split("\\s+");
        int len = words.length;
        List<String> l = new ArrayList<>();
//        l.add(words[0]);
        for(int i=1;i<len;i++){
            l.add(formatTitle(words[i]));
        }
        return (words[0].toUpperCase()) + ", " + String.join(" ", l);
    }
    public static String formatISBN(String s){
        s = s.trim();
        int len = s.length();
        String res = "";
        for(int i=0;i<len;i++){
            res+= s.charAt(i);
            if(i == 2 || i == 3 || i==5 || i==11) res += "-";
        }
        return res;
    }
    public static String formatDate(String s){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(s, dateTimeFormatter);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("MM/yyyy");
        return date.format(dateTimeFormatter1);
    }
            
}
