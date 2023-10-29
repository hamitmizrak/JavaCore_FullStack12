package com.hamitmizrak.javase;

public class _07_String {
    public static void main(String[] args) {

        String value=" java ÖgReniyorum java ";
        System.out.println(value);
        System.out.println(value.length());
        System.out.println(value.trim().length());

        System.out.println(value.startsWith(" "));
        System.out.println(value.endsWith(" "));

        System.out.println(value.toLowerCase());
        System.out.println(value.toUpperCase());

        System.out.println("Dolu mu bos mu?"+value.isEmpty());
        System.out.println(value.replace(value," degistir"));

        System.out.println(value.substring(2));
        System.out.println(value.substring(0,5));

        System.out.println(value.charAt(1));
        System.out.println(value.indexOf("java"));
        System.out.println(value.lastIndexOf("java"));

    }
}
