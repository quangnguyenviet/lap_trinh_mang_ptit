/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package laptrinhmangptit;

import java.net.*;
import java.io.*;
import java.util.*;

public class test {
    public static void main(String[] args) throws Exception {
        // Thông tin kết nối
        String studentCode = "B22DCCN650";
        String qCode = "N3M9lGvZ";
        String message = studentCode + ";" + qCode;
        String host = "203.162.10.109";
        int port = 2207;

        Socket sk = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            // 1. Mở kết nối tới server
            sk = new Socket(host, port);
//            sk.setSoTimeout(5000); // Đặt thời gian timeout là 5s

            // 2. Khởi tạo luồng dữ liệu
            in = new DataInputStream(sk.getInputStream());
            out = new DataOutputStream(sk.getOutputStream());

            // a. Gửi chuỗi là mã sinh viên và mã câu hỏi
            out.writeBytes(message);
            out.flush();

            // b. Nhận lần lượt hai số nguyên a và b từ server
            int a = in.readInt();
            int b = in.readInt();

            // c. Thực hiện tính toán tổng, tích
            long sum = (long) a + b;
            long product = (long) a * b;

            // Gửi lần lượt từng giá trị lên server
            out.writeLong(sum);
            out.writeLong(product);
            out.flush();

            // Hiển thị kết quả để kiểm tra
            System.out.println("Đã gửi: Tổng = " + sum + ", Tích = " + product);

        } catch (IOException e) {
            System.err.println("Lỗi kết nối hoặc IO: " + e.getMessage());
        } finally {
            // d. Đóng kết nối
            if (in != null) in.close();
            if (out != null) out.close();
            if (sk != null) sk.close();
            System.out.println("Kết nối đã đóng.");
        }
    }
}