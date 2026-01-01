/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WS;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.util.*;
import WS_D.*;
public class Main2 {
    public static void main(String[] args) throws Exception {
        DataService_Service sv = new DataService_Service();
        DataService ds = sv.getDataServicePort();
        String studentCode = "B22DCCN650";
        String qCode = "iUcMi2RQ";
        List<Integer> integers = ds.getData(studentCode, qCode);
        
        List<String> strings = new ArrayList<>();
        integers.forEach((t) -> {
           strings.add(Integer.toBinaryString(t));
        });
        ds.submitDataStringArray(studentCode, qCode, strings);
        
    }
}
