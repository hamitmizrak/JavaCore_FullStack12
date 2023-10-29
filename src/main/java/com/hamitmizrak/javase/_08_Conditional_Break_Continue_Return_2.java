package com.hamitmizrak.javase;

public class _08_Conditional_Break_Continue_Return_2 {
    public static void main(String[] args) {

        int number = 5;
        if (number == 1) {
            System.out.println("1");
        } else if (number == 2) {
            System.out.println("2");
        } else if (number == 3) {
            System.out.println("3");
        } else if (number == 4) {
            System.out.println("4");
        } else if (number == 5) {
            System.out.println("5");
        } else {
            System.out.println("1<=X<=5 dışındadır");
        }
        System.out.println("///////////////////////////");
        switch (number) {
            case 4:
                System.out.println("4");
                break;
            case 1:
                System.out.println("1");
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("3");
                break;
            case 5:
                System.out.println("5");
                break;
            default:
                System.out.println("1<=X<=5 dışındadır");
                break;
        }

    }
}
