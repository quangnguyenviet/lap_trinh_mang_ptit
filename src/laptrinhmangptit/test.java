/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmangptit;

import java.net.*;
import java.io.*;
import java.util.*;

public class test {
    public static boolean isPrime(int n){
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return n>=2;
    }
            
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "qC6ikvIO";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2209;
        Socket sk = new Socket(host, port);
        ObjectInputStream in = new ObjectInputStream(sk.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(sk.getOutputStream());
        
        out.w
        
        out.flush();
        in.close();
        out.close();
        sk.close();
    }
    public static String rev(String s){
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
    public static String rle(String s){
        String rev_s = rev(s);
        StringBuilder res = new StringBuilder();
        int len = s.length();
        for(int i=0;i<len;i++){
            int count = 1;
            res.append(rev_s.charAt(i));
            while(i+1<len && rev_s.charAt(i) == rev_s.charAt(i+1)){
                count ++;
                i++;
            }
            if(count>1){
                res.append(count+"");
            }
        }
        return res.toString();
    }
}