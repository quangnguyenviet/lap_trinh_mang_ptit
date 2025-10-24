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

public class TCP_08 {

    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "OoeBsXOw";
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
        String s = new String(bs, 0, len);
        List<String> words = new ArrayList<>(Arrays.asList(s.trim().split(",")));
        List<Integer> nums = new ArrayList<>();
        Double mean = 0d;
        for (String w : words) {
            Integer temp = Integer.parseInt(w);
            nums.add(temp);
            mean += temp * 1.0;
        }
        mean /= nums.size();
        mean*=2;
        Collections.sort(nums);
        Double minv = 1e8;
        int si = nums.size();
        Integer n1 = null, n2 = null;
        for (int i = 0; i < si; i++) {
            {
                for (int j = i + 1; j < si; j++) {
                    int sum = nums.get(i) + nums.get(j);
                    if (Math.abs(mean - sum) < minv) {
                        n1 = nums.get(i);
                        n2 = nums.get(j);
                        minv = Math.abs(mean - sum);
                    }
                }
            }

        }
        System.out.println(n1);
        System.out.println(n2);
        
        String response = String.join(",", Arrays.asList(n1.toString(), n2.toString()));
        System.out.println(response);
        out.write(response.getBytes());
        out.flush();
        in.close();
        out.close();
        sk.close();
    }
}
