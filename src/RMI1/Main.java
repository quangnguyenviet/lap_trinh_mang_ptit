/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI1;

/**
 *
 * @author nvqua
 */
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception{
        String key = "PTIT";
        String host = "203.162.10.109";
        int port = 1099;
        String stuCode = "B22DCCN650";
        String qCode = "1aYah8GD";
        Registry rg = LocateRegistry.getRegistry(host, port);
        ByteService bs = (ByteService) rg.lookup("RMIByteService");
        byte[] bs1 = bs.requestData(stuCode, qCode);
        String s = new String(bs1);
        System.out.println(s);
        int idx = 0;
        List<String> res = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            char d = key.charAt(idx%4);
            char e = (char) (c ^ d);
            res.add(e + "");
            idx++;
        }
        String s1 = String.join("", res);
        bs.submitData(stuCode, qCode, s1.getBytes());
                
    }
    
     
    
}
