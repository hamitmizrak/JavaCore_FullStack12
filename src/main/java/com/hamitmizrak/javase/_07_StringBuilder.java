package com.hamitmizrak.javase;

public class _07_StringBuilder {
    public static void main(String[] args) {

        String str1="jsp";
        String str2="jsf";
        String str3="spring";

        String str4=str1+str2+str3;
        System.out.println(str4);

        String str5=str1.concat(str2).concat(str3);
        System.out.println(str5);

        StringBuilder str6=new StringBuilder();
        str6.append(str1).append(str2).append(str3);
        String change=str6.toString();
        System.out.println(change);

        StringBuffer str7=new StringBuffer();
        str7.append(str1).append(str2).append(str3);
        String change2=str7.toString();
        System.out.println(change2);
    }
}
