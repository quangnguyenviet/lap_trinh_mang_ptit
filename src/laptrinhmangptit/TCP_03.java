/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmangptit;

import java.net.*;
import java.io.*;

public class TCP_03 {
    static boolean isPrime(long n){
        if (n<2) return false;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String studentCode = "B22DCCN650";
        String qCode = "lkVQyNbu";
        String message = studentCode + ";" + qCode;
        Socket sk = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            sk = new Socket("203.162.10.109", 2206);
            in = sk.getInputStream();
            out = sk.getOutputStream();
            out.write(message.getBytes());
            out.flush();
            byte[] bs = new byte[1024];
            int len = in.read(bs);
            String s = new String(bs, 0, len);
//            System.out.println(s);
            Long res = 0l;
            String[] words = s.trim().split(",");
            for(String w : words){
                Long num = Long.parseLong(w);
                if(isPrime(num)){
                    res += num;
                }
            }
            out.write(res.toString().getBytes());
//            System.out.println(res);
            out.flush();
            sk.close();
            in.close();
            out.close();
        } catch (IOException ex) {
//            System.getLogger(TCP_03.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }
}
