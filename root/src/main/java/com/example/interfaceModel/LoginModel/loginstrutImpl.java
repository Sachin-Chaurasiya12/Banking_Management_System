/*

   Copyright 2026 Sachin Chaurasiya

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

*/
package com.example.interfaceModel.LoginModel;

import java.util.Scanner;

import com.example.Model.login;
import com.example.Security.hidingPassword;
import com.example.SysDatabaseAndCaching.LoginDataImpl;
import com.example.utils.printInsider;
import com.example.utils.programTermination;

public class loginstrutImpl {

    static final printInsider in = new printInsider();
    static final Scanner sc = new Scanner(System.in);
    static final programTermination t = new programTermination();
    static final hidingPassword hide = new hidingPassword();
    private static String loggedinusername = null;

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
                    int attempt = 0;
                    // Loop until login is successful
                    while (attempt < 3) {
                        if(loginuser()){
                            return;
                        }
                        // retry until success
                        attempt++;
                        in.println("Attempt : " + attempt);
                    }
                    in.println("Attempts reached it's maximum limit!");
                    Thread.sleep(800);
                    Back();
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
    //function which help to login the user.
    public boolean loginuser() throws Exception {
        in.print("Enter Username: ");
        String username = sc.nextLine();
        String password = hide.passwordhide();

        LoginDataImpl dao = new LoginDataImpl();
        boolean success = dao.validateUser(username, password);

        if (success) {
            loggedinusername = username;
            in.println("Login Successful!\n");
            return true;
        } else {
            in.println("Invalid username or password. Please try again.\n");
            return false; // retry will happen in the Login() loop
        }
    }
    //function to add a new user.
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
    //Close the whole program and quit before login.
    public void Back() {
        t.exitAndClose();
    }
    public static String getloggedinusername(){
        return loggedinusername;
    }
}
