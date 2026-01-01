/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMI2;

/**
 *
 * @author nvqua
 */
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        String studentCode = "B22DCCN650";
        String qCode = "uSLe917b";
        String host = "203.162.10.109";
        int port = 1099;
        Registry rg = LocateRegistry.getRegistry(host, port);
        CharacterService characterService = (CharacterService) rg.lookup("RMICharacterService");
        String s = characterService.requestCharacter(studentCode, qCode);
        System.out.println(s);
        LinkedHashMap<String, Integer> hashMap = frequently(s);
        List<String> list = new ArrayList<>();
        
        hashMap.forEach((t, u) -> {
            String temp = "\"" + t + "\": " + u;
            list.add(temp);
        });
        String res = "{" + String.join(", ", list) + "}";
        System.out.println(res);
        characterService.submitCharacter(studentCode, qCode, res);
        
    }
    public static LinkedHashMap<String, Integer> frequently(String s){
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
        int len = s.length();
        for(int i=0;i<len;i++){
            String key = s.charAt(i) + "";
            if(hashMap.containsKey(key)){
                hashMap.replace(key, hashMap.get(key) +1);
            }
            else{
                hashMap.put(key, 1);
            }
        }
        return hashMap;
    }
}
