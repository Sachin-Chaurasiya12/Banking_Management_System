package com.example.interfaceModel.LoginModel;

import java.util.Scanner;

import com.example.Exception.loginException;
import com.example.Model.login;
import com.example.Security.hidingPassword;
import com.example.SysDatabase.LoginDataImpl;
import com.example.utils.printInsider;
import com.example.utils.programTermination;

public class loginstrutImpl {

    static final printInsider in = new printInsider();
    static final Scanner sc = new Scanner(System.in);
    static final programTermination t = new programTermination();
    static final hidingPassword hide = new hidingPassword();

    public void Login() throws Exception {
        LoginDataImpl.initConnection();
        int input;

        while (true) {
            in.println("\n+-------------------------+");
            in.println("1) Login");
            in.println("2) New User");
            in.println("0) Quit");
            in.println("+-------------------------+");

            in.print("Enter Value: ");
            if (sc.hasNextInt()) {
                input = sc.nextInt();
                sc.nextLine(); // consume newline
            } else {
                in.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (input) {
                case 1:
                    // Loop until login is successful
                    while (!loginuser()) {
                        // retry until success
                    }
                    return; // exit after successful login

                case 2:
                    // Register new user, then loop back to login
                    newUser();
                    in.println("Please login with your new credentials.\n");
                    break;

                case 0:
                    Back();
                    return;

                default:
                    in.println("Invalid input! Please try again.");
                    break;
            }
        }
    }

    public boolean loginuser() throws Exception {
        in.print("Enter Username: ");
        String username = sc.nextLine();
        String password = hide.passwordhide();

        LoginDataImpl dao = new LoginDataImpl();
        boolean success = dao.validateUser(username, password);

        if (success) {
            in.println("Login Successful!\n");
            return true;
        } else {
            in.println("Invalid username or password. Please try again.\n");
            return false; // retry will happen in the Login() loop
        }
    }

    public void newUser() throws Exception {
        LoginDataImpl dao = new LoginDataImpl();
        String name;

        // Loop until username is available
        while (true) {
            in.print("Enter Username: ");
            name = sc.nextLine();

            if (!dao.userExists(name)) {
                break;
            }
            in.println("Username already exists! Try another.");
        }

        String password, conpass;

        // Loop until passwords match
        while (true) {
            password = hide.passwordhide();
            conpass = hide.passwordhiderenter();

            if (!password.equals(conpass)) {
                in.println("Passwords do not match! Try again.");
            } else {
                break;
            }
        }

        login user = new login(name, password);
        dao.saveUser(user);

        in.println("User Registered Successfully!");
    }

    public void Back() {
        t.exitAndClose();
    }
}
