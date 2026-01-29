package com.example.Security;

import java.io.Console;
import java.util.Scanner;

public class hidingPassword {
    public String passwordhide(){
        Console console = System.console();
        if (console != null) {
            char[] pwd = console.readPassword("Enter password: ");
            return new String(pwd);
        }else {
            System.out.print("Enter password: ");
            try (Scanner sc = new Scanner(System.in)) {
                return sc.nextLine();
            }
        }
    }
    public String passwordhiderenter(){
        Console console = System.console();
        if (console != null) {
            char[] pwd = console.readPassword("re Enter password: ");
            return new String(pwd);
        }else {
            System.out.print("Enter password: ");
            try (Scanner sc = new Scanner(System.in)) {
                return sc.nextLine();
            }
        }
    }
}
