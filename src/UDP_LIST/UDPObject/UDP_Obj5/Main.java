/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_LIST.UDPObject.UDP_Obj5;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.net.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2209;
        String ms1 = ";B22DCCN650;ReF5X1NK";
        DatagramSocket sk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket out1 = new DatagramPacket(ms1.getBytes(), ms1.length(), sA, port);
        sk.send(out1);
        
        byte[] bs1 = new byte[1024];
        DatagramPacket in = new DatagramPacket(bs1, bs1.length, sA, port);
        sk.receive(in);
        String rid = new String(in.getData(), 0, 8);
        System.out.println(rid);
        ByteArrayInputStream bais = new ByteArrayInputStream(bs1, 8, in.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Employee e = (Employee) ois.readObject();
        System.out.println(e);
        e.name = formatName(e.name);
        e.salary = formatSalary(e.salary, e.hireDate);
        e.hireDate = formatDate(e.hireDate);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(e);
        oos.flush();
        
        byte[] bs2 = new byte[8 + baos.size()];
        System.arraycopy(rid.getBytes(), 0, bs2, 0, 8);
        System.arraycopy(baos.toByteArray(), 0, bs2, 8, baos.size());
        DatagramPacket out2 = new DatagramPacket(bs2, bs2.length, sA, port);
        sk.send(out2);
        sk.close();
        
        
    }
    public static String toTitle(String s){
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        return sb.replace(0, 1, Character.toUpperCase(sb.charAt(0)) + "").toString();
    }
    public static String formatName(String s){
        String words[] = s.trim().split("\\s+");
        List<String> l = new ArrayList<>();
        for(String w: words){
            l.add(toTitle(w));
        }
        return String.join(" ", l);
    }
    public static double formatSalary(double s, String d){
        String words[] = d.trim().split("-");
        return s + s*(tongChuSo(Integer.valueOf(words[0])) /100.0d);
    }
    public static int tongChuSo(int x){
        int res = 0;
        while(x!=0){
            res += x%10;
            x/=10;
        }
        return res;
    }
    public static String formatDate(String s){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(s, dateTimeFormatter);
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dateTimeFormatter1);
    }
}
