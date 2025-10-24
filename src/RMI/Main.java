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
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String qCode = "Egzqxd8e";
        String studentCode = "B22DCCN650";
        String host = "203.162.10.109";
        int port = 1099;
        Registry registry = LocateRegistry.getRegistry(host, port);
        ObjectService objectService = (ObjectService) registry.lookup("RMIObjectService");
        ProductX productX = (ProductX) objectService.requestObject(studentCode, qCode);
        System.out.println(productX);
        productX.discount = sumDigit(productX.discountCode);
        objectService.submitObject(studentCode, qCode, productX);
        System.out.println(productX);
        
    }
    public static int sumDigit(String s){
        int len = s.length();
        int sum = 0;
        for(int i=0;i<len;i++){
            try{
                int digit = Integer.valueOf(s.charAt(i) + "");
                sum+= digit;
            }
            catch(Exception e){
                continue;
            }
        }
        return sum;
    }
}
