package com.hamitmizrak.javase;

public class _08_Conditional_Break_Continue_Return {
    public static void main(String[] args) {

        int number=5;
        if(number>0){
            System.out.println("pozitif");
        }else{
            System.out.println("negatif");
        }

        String result= (number>0) ? "pozitif": "negatif";
        System.out.println(result);

    }
}
