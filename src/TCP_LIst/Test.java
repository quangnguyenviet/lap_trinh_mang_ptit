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
public class Test {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 2207;
        String ms1 = "B22DCCN650;NfBtDLTj";
        Socket sk = new Socket(host, port);
        DataInputStream in = new DataInputStream(sk.getInputStream());
        DataOutputStream out = new DataOutputStream(sk.getOutputStream());
        out.writeUTF(ms1);
        out.flush();
        
        Integer n = in.readInt();
        System.out.println(n);
        
        String ms2 = Integer.toBinaryString(n);
        out.writeUTF(ms2);
        out.flush();
        
        out.close();
        in.close();
        sk.close();
    }
    
    
}
