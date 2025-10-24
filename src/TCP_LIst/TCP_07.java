/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst;

/**
 *
 * @author nvqua
 */
// 56
import java.io.*;
import java.util.*;
import java.net.*;
public class TCP_07 {
    public static String solve(String s){
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        
        return myTransform(sb.toString());
    }
    
    public static String myTransform(String s){
        StringBuilder res = new StringBuilder("");
        int len = s.length();
        for(int i=0;i<len;i++){
            res.append(s.charAt(i));
            Integer count = 1;
            while(i+1<len && s.charAt(i) == s.charAt(i+1)){
                count++;
                i++;
            }
            if(count >1){
                res.append(count.toString());
            }
        }
        return res.toString();
    }
    
    public static void main(String[] args) throws Exception {
        
        String studentCode = "B22DCCN650";
        String qCode = "UuGk9nOB";
        String m = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2208;
        Socket sk = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(sk.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sk.getOutputStream()));
        out.write(m);
        out.newLine();
        out.flush();
        String s = in.readLine();
        System.out.println(s);
        String[] words = s.trim().split("\\s+");
        List<String> res = new ArrayList<>();
        for(String w : words){
            res.add(solve(w));
        }
        String response = String.join(" ", res);
        out.write(response);
        out.flush();
        System.out.println(response);
        
        in.close();
        out.close();
        sk.close();
    }
}
