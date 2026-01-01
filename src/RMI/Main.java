/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        String host = "203.162.10.109";
        int port = 1099;
        String studentCode = "B22DCCN650";
        String qCode = "1aYah8GD";
        Registry rg = LocateRegistry.getRegistry(host, port);
        ByteService bs = (ByteService)rg.lookup("RMIByteService");
        String s = new String(bs.requestData(studentCode, qCode));
        String res = solve(s);
        bs.submitData(studentCode, qCode, res.getBytes());
    }
    public static String solve(String s){
        String key = "PTIT";
        int idx = 0;
        int len = s.length();
        String res = "";
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            char d = key.charAt(idx%4);
            char e = (char) (c ^ d );
            res += e + "";
            idx++;
        }
        return res;
    }
}
