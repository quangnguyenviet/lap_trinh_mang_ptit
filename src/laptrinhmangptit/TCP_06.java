/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmangptit;

/**
 *
 * @author nvqua
 */
import java.net.*;
import java.util.*;
import java.io.*;
public class TCP_06 {
    public static void main(String[] args) throws Exception {
        String qCode = "6eeMrLYi";
        String studentCode = "B22DCCN650";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2208;
        Socket sk = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));
        
        out.write(message);
        out.newLine();
        out.flush();
        
        String s = in.readLine();
        System.out.println(s);
        String[] words = s.split("\\s+");
        String res2 = String.join(", ", words);
        Comparator<String> byLen = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        Arrays.sort(words, byLen);
        String res1 = String.join(", ", words);
        System.out.println(res1);
        out.write(res1);
//        out.newLine();
//        out.flush();
        out.write(res2);
        out.newLine();
        out.flush();
        System.out.println(res2);
        in.close();
        out.close();
        sk.close();
        
    }
}
