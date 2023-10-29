package com.hamitmizrak.javase;

public enum _10_Enum {
    SMALL,MEDIUM,LARGE
}

class MainEnum{
    public static void main(String[] args) {
        System.out.println(_10_Enum.SMALL);
        System.out.println(_10_Enum.SMALL.hashCode());
        System.out.println(_10_Enum.SMALL.ordinal());
        System.out.println(_10_Enum.SMALL.toString());
        System.out.println(_10_Enum.SMALL.name());
    }
}
