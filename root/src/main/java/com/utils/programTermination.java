package com.utils;

import java.io.IOException;

public class programTermination {
    public void exitAndClose() {
    try {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                new ProcessBuilder("cmd", "/c", "taskkill /f /im cmd.exe").start();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }));
        
        // Standard Java exit
        System.exit(0);
    } catch (Exception e) {
        System.exit(0);
    }
}
}
