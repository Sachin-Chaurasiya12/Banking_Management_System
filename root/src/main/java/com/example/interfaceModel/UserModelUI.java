package com.example.interfaceModel;

import com.example.interfaceModel.LoginModel.loginstrutImpl;
import com.example.utils.printInsider;

public class UserModelUI {
    static final printInsider in = new printInsider();
    static final String Version = "v1.0.0";
    public void version(){
        in.println("+-----------------------------------------------------------------+");
        in.println("            Banking Management System Version : " + Version + "   ");
        in.println("+-----------------------------------------------------------------+");
    }
    public static void interfaceModel(){
        in.println(CurrentUser() + " (" + CurrentRole() + ")");
        in.println("+--------------------------------+--------------------------------+");
        in.print("1) Check Balance");
        in.print("        2) Withdraw");
        in.println("        3) Transcation");
        in.print("4) Other Operation");
        in.println("       5) Exit");
        in.println("+--------------------------------+--------------------------------+");
    }
    public static void otherOperation(){
        in.println("+---------------------------+");
        in.println("2) Transaction logs");
        in.println("0) <-- Back");
        in.println("+---------------------------+");
    }
    public static String CurrentUser(){
        String CurrentUser = loginstrutImpl.getloggedinusername();
        return "logged in User : " + CurrentUser;
    } 
    public static String CurrentRole(){
        String CurrentRole = loginstrutImpl.getUserRole();
        return CurrentRole;
    }
}
