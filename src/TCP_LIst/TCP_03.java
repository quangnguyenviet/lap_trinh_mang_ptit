/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst;

import java.net.*;
import java.io.*;
import java.util.*;

public class TCP_03 {

    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "N3M9lGvZ";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2207;
        Socket sk = new Socket(host, port);
        DataInputStream in = new DataInputStream(sk.getInputStream());
        DataOutputStream out = new DataOutputStream(sk.getOutputStream());
        out.writeUTF(message);
        out.flush();
        
        int a = in.readInt();
        int b = in.readInt();
        System.out.println(a);
        System.out.println(b);
        int sum = a + b;
        int product =a * b;
        System.out.println(product);
        
        out.writeInt(sum);
        out.writeInt(product);
        out.flush();
        
        sk.close();
        in.close();
        out.close();

    }
}
