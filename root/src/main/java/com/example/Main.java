package com.example;

import java.util.Scanner;

import com.utils.programTermination;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static programTermination termination = new programTermination();
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("ENter value : ");
        int input = sc.nextInt();
        if(input == 1){
            termination.exitAndClose();
        }
    }
}