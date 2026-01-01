/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS3;

/**
 *
 * @author nvqua
 */
import java.util.*;
import java.io.*;
import WS3_D.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "jPxlBGps";
        ObjectService_Service sv = new ObjectService_Service();
        ObjectService os = sv.getObjectServicePort();
        List<Student> students = os.requestListStudent(studentCode, qCode);
        List<Student> res = new ArrayList<>();
        students.forEach((t) -> {
            float score = t.getScore();
            if(score>=8.0 || score<5.0){
                res.add(t);
            }
        });
        
        os.submitListStudent(studentCode, qCode, res);
        
    }
}
