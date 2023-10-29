package com.hamitmizrak.javase;

import javax.swing.*;
import java.util.Scanner;

public class _04_Scanner_JOptionalPane_Escape_System {
    public static void main(String[] args) {

        // Scanner
        Scanner scanner=new Scanner(System.in);
        System.out.println("Adınız ve soyadınız");
        String value=scanner.nextLine();
        System.out.println(value);
        System.err.println(value);

        // JOptionPane
        String value2= JOptionPane.showInputDialog("Adınız ve soyadınız");
        System.out.println(value2);

        // System
        /*
        System.in();
        System.out();
        System.gc();
        System.currentTimeMillis();
        */

    }
}
