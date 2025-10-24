/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI3;

/**
 *
 * @author nvqua
 */
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String host = "203.162.10.109";
        int port = 1099;
        String studentCode = "B22DCCN650";
        String qCode = "1dFl0UUr";
        Registry registry = LocateRegistry.getRegistry(host, port);
        DataService dataService = (DataService) registry.lookup("RMIDataService");
        Integer amount = (Integer) dataService.requestData(studentCode, qCode);
        System.out.println(amount);
        String s = solve(amount);
        dataService.submitData(studentCode, qCode, s);
    }
    public static String solve(int n){
        int[] is = new int[4];
        is[0] = 10;
        is[1] = 5;
        is[2] = 2;
        is[3] = 1;
        int[] count = new int[4];
        int total = 0;
        List<String> list = new ArrayList<>();
        for(int i=0;i<4;i++){
            count[i] = (int) n/is[i];
            if(count[i] !=0){
                for(int j=0;j<count[i];j++){
                    list.add(is[i] + "");
                }
            }
            total += count[i];
            n%=is[i];
        }
        System.out.println(total);
        
        return total + "; " + String.join(",", list);
    }
}
