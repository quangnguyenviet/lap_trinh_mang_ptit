
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nvqua
 */
import java.util.*;

public class Test {
    public static void main(String[] args) {
        int n = 4;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
        trys(a, n, 0);
    }

    public static void myPrint(int[] x, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(x[i] + " ");
        }
        System.out.println();
    }

    public static void trys(int[] a, int n, int start) {
        if(start == n-1){
            myPrint(a, n);
            return;
        }
        for(int i=start;i<n;i++){
            
        }
    }
}

