/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmangptit;

import java.util.*;
import java.io.*;
import java.net.*;
/**
 *
 * @author nvqua
 */
public class TCP_02 {
    public static void main(String[] args) throws Exception{
        String serverIP = "203.162.10.109";
        String studentCode = "B22DCCN650";
        String qCode = "cchKeSPP";
        String message = studentCode + ";" + qCode;
        Socket socket = new Socket(serverIP, 2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
        out.flush();
        byte[] bytes = new byte[1024];
        
        int len = in.read(bytes);
        String s = new String(bytes, 0, len);
//        System.out.println(s);
        Long res = 0l;
        s = s.trim();
        String[] numbers = s.split("\\|");
        for(String n : numbers){
            res += Long.parseLong(n);
        }
//        System.out.println(res);
        String response = res.toString();
        out.write(response.getBytes());
        out.flush();
        
        out.close();
        in.close();
        socket.close();
        
    }
}
