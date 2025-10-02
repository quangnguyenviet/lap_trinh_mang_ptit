/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//[Mã câu hỏi (qCode): d2IzZct3].  Một chương trình server cho phép kết nối qua giao thức UDP tại cổng 2208. Yêu cầu là xây dựng một chương trình client trao đổi thông tin với server theo kịch bản dưới đây:
//a.	Gửi thông điệp là một chuỗi chứa mã sinh viên và mã câu hỏi theo định dạng “;studentCode;qCode”. Ví dụ: “;B15DCCN001;5B35BCC1”
//b.	Nhận thông điệp từ server theo định dạng “requestId;data” 
//-	requestId là một chuỗi ngẫu nhiên duy nhất
//-	data là chuỗi dữ liệu cần xử lý
//c.	Xử lý chuẩn hóa chuỗi đã nhận thành theo nguyên tắc 
//i.	Ký tự đầu tiên của từng từ trong chuỗi là in hoa
//ii.	Các ký tự còn lại của chuỗi là in thường
//Gửi thông điệp chứa chuỗi đã được chuẩn hóa lên server theo định dạng “requestId;data”
//d.	Đóng socket và kết thúc chương trình
package UDP_LIST;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import java.net.*;
public class UDP2 {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "d2IzZct3";
        String host = "203.162.10.109";
        int port = 2208;
        String message = ";" + studentCode + ";" + qCode;
        DatagramSocket dsk = new DatagramSocket();
        InetAddress sA = InetAddress.getByName(host);
        DatagramPacket packet = new DatagramPacket(message.getBytes(), message.length(), sA, port);
        dsk.send(packet);
        
        byte[] bs = new byte[1024];
        DatagramPacket packetIn = new DatagramPacket(bs, bs.length);
        dsk.receive(packetIn);
        String s = new String(packetIn.getData());
        System.out.println(s);
        String data = getData(s);
        String requestId = getRequestId(s);
        String formattedData = formatData(data);
        StringBuilder response = new StringBuilder();
        response.append(requestId)
                .append(";")
                .append(formattedData);
        
        System.out.println(response.toString());
        packet = new DatagramPacket(response.toString().getBytes(), response.length(), sA, port);
        dsk.send(packet);
        
    }
    public static String getData(String s){
        String temp = ";";
        int start = s.indexOf(temp) + 1;
        return s.substring(start);
    }
    public static String getRequestId(String s){
        String temp = ";";
        int end = s.indexOf(temp);
        return s.substring(0, end);
    }
    public static String formatData(String s){
        String[] words = s.split(" ");
        List<String> res = new ArrayList<>();
        for(String w : words){
            StringBuilder sb = new StringBuilder(w.toLowerCase());
            char firstChar = sb.charAt(0);
            sb.replace(0, 1, Character.toUpperCase(firstChar) + "");
            res.add(sb.toString());
        }
        return String.join(" ", res);
    }
}
