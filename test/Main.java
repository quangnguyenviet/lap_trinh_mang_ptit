
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nvqua
 */
public class Main {
    public static void main(String[] args) {
        int[] x = {0, 0, 0, 0, 0};
        int n = 5;
        myPrint(x, n);
        while(true){
            int i = n-1;
            while(x[i] == 1){
                x[i] = 0;
                i--;
                if(i<0) break;
            }
            if(i<0) break;
            x[i] = 1;
        }
        myPrint(x, n);
        
    }
    public static void myPrint(int[] x, int n){
        for(int i=0;i<n;i++){
            System.out.print(x[i] + " ");
            
        }
        System.out.println("");
    }
}
