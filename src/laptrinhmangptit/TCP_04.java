package laptrinhmangptit;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.io.*;
import java.util.*;

public class TCP_04 {

    private static boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "lkVQyNbu";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2206;
        Socket sk = new Socket(host, port);
        InputStream in = sk.getInputStream();
        OutputStream out = sk.getOutputStream();
        out.write(message.getBytes());
        out.flush();
        byte[] bs = new byte[1024];
        int len = in.read(bs);
//        System.out.println(len);

        String s = new String(bs);
        String[] words = s.trim().split(",");
//        System.out.println(s);
        Long res = 0l;
        for(String w : words){
            Long n = Long.parseLong(w);
            if(isPrime(n)){
                res += n;
            }
        }
        out.write(res.toString().getBytes());
        out.flush();
        sk.close();
        in.close();
        out.close();
    }
}
