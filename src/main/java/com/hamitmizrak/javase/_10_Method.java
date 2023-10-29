package com.hamitmizrak.javase;

public class _10_Method {

    // 1
    public static void returnsuzParametresiz() {
        System.out.println("returnsuz Parametresiz");
    }

    // 2
    static public void returnsuzParametreli(String param1) {
        System.out.println("returnsuz Parametresiz " + param1);
    }

    // 3
    public static String returnluParametresiz() {
        return "returnluParametresiz";
    }

    // 4
    public static String returnluParametreli(double key) {
        return "returnlu Parametreli " + key;
    }

    public static void main(String[] args) {
        returnsuzParametresiz();
        returnsuzParametreli("Merhabalar Güzel insanlar");
        String str3 = returnluParametresiz();
        System.out.println(str3);
        String str4 = returnluParametreli(44.23);
        System.out.println(str4);
    }

}
