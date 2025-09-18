/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmangptit;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_10 {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "mSxj2jJk";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2207;
        Socket sk = new Socket(host, port);
        DataInputStream in = new DataInputStream(sk.getInputStream());
        DataOutputStream out = new DataOutputStream(sk.getOutputStream());
        out.writeUTF(message);
        out.flush();
        String s = in.readUTF();
        System.out.println(s);
        String[] words = s.trim().split(",");
        List<Integer> nums = new ArrayList<>();
        for(String w : words){
            nums.add(Integer.parseInt(w));
        }
        int count1 = 0, count2 = 0;
        for(int i=1;i<nums.size()-1;i++){
            count1 += Math.abs(nums.get(i) - nums.get(i-1));
            if((nums.get(i) > nums.get(i-1) && nums.get(i) > nums.get(i+1))
                    ||
                    (nums.get(i) < nums.get(i-1) && nums.get(i) < nums.get(i+1))
                    ){
                count2 ++;
            }
        }
        count1 += Math.abs(nums.getLast() - nums.get(nums.size()-2));
        out.writeInt(count2);
        out.flush();
        out.writeInt(count1);
        out.flush();
        in.close();
        out.close();
        sk.close();
    }
}
