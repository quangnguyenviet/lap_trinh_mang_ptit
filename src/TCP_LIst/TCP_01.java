/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nvqua
 */
public class TCP_01 {
    public static void main(String[] args) throws Exception{
        Socket sk = new Socket("203.162.10.109", 2208);
        InputStream in = sk.getInputStream();
        OutputStream out = sk.getOutputStream();
        String studentCode = "B22DCCN650";
        String qCode = "a4UY7rKu";
        String message = studentCode + ";" + qCode;        
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        writer.write(message);
        writer.newLine();
        writer.flush();
        
        String s = reader.readLine();
        System.out.println(s);
        
        List<String> domainsList = new ArrayList<>();
        String[] domains = s.split(",");
        for(String d : domains){
            d = d.trim();
            if(d.endsWith(".edu")){
                domainsList.add(d);
            }
        }
        String result = String.join(", ", domainsList);
        
//        System.out.println(result);
        writer.write(result);
        writer.newLine();
        writer.flush();
        
        writer.close();
        reader.close();
        sk.close();
        
    }
}
