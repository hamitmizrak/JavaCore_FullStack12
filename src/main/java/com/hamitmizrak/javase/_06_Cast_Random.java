package com.hamitmizrak.javase;

import java.util.Random;

public class _06_Cast_Random {
    public static void main(String[] args) {
        long l1 = 15151551115L;
        float f1 = 1515.112f;

        int i2=1515;
        Integer i3=i2; // boxing

        int number1=10;
        int number2=5;
        String number3="20";
        int stringToNumber=Integer.valueOf(number3);
        int stringToNumber2=Integer.parseInt(number3);

        int data1=55;
        String str=String.valueOf(data1);
        String str2=Integer.toString(data1);
        System.out.println(20+data1);
        System.out.println(20+str2);

        System.out.println("toplam: "+ (number1+number2));
        System.out.println(number1+number3);
        System.out.println(number1+str);
        System.out.println(number1+str2);

        System.out.println("//////////////////////////");
        double random1=Math.floor(Math.random()*10+1) ;
        System.out.println(random1);

        Random random2=new Random();
        int rnd=random2.nextInt(10)+1;
        System.out.println(rnd);

    }
}
