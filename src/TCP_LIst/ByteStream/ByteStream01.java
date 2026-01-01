/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_LIst.ByteStream;

/**
 *
 * @author nvqua
 */
//[Mã câu hỏi (qCode): 0XMVDbtP].  Một chương trình server cho phép kết nối qua giao thức TCP tại cổng 2206 (thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s).
//Yêu cầu là xây dựng một chương trình client tương tác tới server ở trên sử dụng các luồng byte (InputStream/OutputStream) để trao đổi thông tin theo thứ tự: 
//a.	Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode". Ví dụ: "B16DCCN999;2B3A6510"
//b.	Nhận dữ liệu từ server là một số nguyên n nhỏ hơn 400. Ví dụ: 7
//c.	Thực hiện các bước sau đây để sinh ra chuỗi từ số nguyên n ban đầu và gửi lên server.
//        Bắt đầu với số nguyên nn:
//            Nếu n là số chẵn, chia nn cho 2 để tạo ra số tiếp theo trong dãy.
//            Nếu n là số lẻ và khác 1, thực hiện phép toán n=3*n+1 để tạo ra số tiếp theo.
//        Lặp lại quá trình trên cho đến khi n=1, tại đó dừng thuật toán.
//Kết quả là một dãy số liên tiếp, bắt đầu từ n ban đầu, kết thúc tại 1 và độ dài của chuỗi theo format "chuỗi kết quả; độ dài"  Ví dụ: kết quả với n = 7 thì dãy: 7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1; 17;  
//d.	Đóng kết nối và kết thúc chương trình.
import java.util.*;
import java.net.*;
import java.io.*;
public class ByteStream01 {
    public static void main(String[] args) {
        try {
            String host = "203.162.10.109";
            int port = 2206;
            Socket sk = new Socket(host, port);
            String s = "B22DCCN650;0XMVDbtP";
            InputStream in = sk.getInputStream();
            OutputStream out = sk.getOutputStream();
            out.write(s.getBytes());
            out.flush();
            byte[] bs = new byte[1024];
            int len = in.read(bs);
            String s1 = new String(bs, 0, len);
            System.out.println(s1);
            String res = solve(s1);
            out.write(res.getBytes());
            out.flush();
            
            in.close();
            out.close();
            sk.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static String solve(String s){
        int n = Integer.valueOf(s);
        int count = 1;
        List<String> l = new ArrayList<>();
        l.add(s);
        while(n!=1){
            if(n%2==0){
                n/=2;
       
            }
            else{
                n= n*3 + 1;
            }
            count ++;
            l.add(n + "");
        }
        return String.join(" ", l) + "; " + count;
    }
}
