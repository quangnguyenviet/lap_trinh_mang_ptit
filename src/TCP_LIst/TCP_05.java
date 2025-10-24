/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst;

/**
 *
 * @author nvqua
 */
import java.net.*;
import java.io.*;
import java.util.*;
import javax.net.ssl.SSLSocket;
public class TCP_05 {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "bOdvmYnv";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2207;
        Socket sk = new Socket(host, port);
        DataInputStream in = new DataInputStream(sk.getInputStream());
        DataOutputStream out = new DataOutputStream(sk.getOutputStream());
        out.writeUTF(message);
        out.flush();
        
        int k = in.readInt();
        String s = in.readUTF();
        System.out.println(k);
        System.out.println(s);
        
        String[] words = s.trim().split(",");
        List<String> res = new ArrayList<>();
        int len = words.length;
        for(int i=0;i<len;i+=k){
            List<String> temp = new ArrayList<>();
            for(int j=0;j<k;j++){
                if(i+j<len) temp.add(words[i+j]);
                else break;
            }
            Collections.reverse(temp);
            res.addAll(temp);
        }
        String response = String.join(",", res);
        System.out.println(response);
        out.writeUTF(response);
        out.flush();
        sk.close();
        out.close();
        in.close();
        
        
        
    }
}
