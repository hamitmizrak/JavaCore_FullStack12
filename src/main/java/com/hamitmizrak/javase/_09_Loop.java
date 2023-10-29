package com.hamitmizrak.javase;

public class _09_Loop {
    public static void main(String[] args) {

        for (int i = 0; i <=9 ; i++) {
            System.out.print(i+" ");
        }
        System.out.println("\n");
        int k = 0;
        while(k<=9){
            System.out.print(k+" ");
            k=k+1;
        }
        System.out.println("\n");

        int m=0;
        do{
            System.out.print(m+" ");
            m+=1;
        }while(m<=9);
    }
}
