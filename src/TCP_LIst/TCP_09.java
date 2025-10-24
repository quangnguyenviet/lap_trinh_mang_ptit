/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_09 {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "WePfBLda";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2207;
        Socket sk = new Socket(host, port);
        DataInputStream in = new DataInputStream(sk.getInputStream());
        DataOutputStream out = new DataOutputStream(sk.getOutputStream());
        out.writeUTF(message);
        out.flush();
        int num = in.readInt();
        System.out.println(num);
        String oct = Integer.toOctalString(num);
        String hex = Integer.toHexString(num).toUpperCase();
        System.out.println(oct);
        System.out.println(hex);
        String response = String.join(";", Arrays.asList(oct, hex));
        out.writeUTF(response);
//        out.writeUTF("\n");
//        out.flush();
//        out.writeUTF(hex);
        out.flush();
        
        in.close();
        out.close();
        sk.close();
    }
}
