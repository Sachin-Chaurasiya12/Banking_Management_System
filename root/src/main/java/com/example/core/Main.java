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
package com.example.core;

import java.util.Scanner;
import java.util.Stack;

import com.example.Exception.InterfaceException;
import com.example.interfaceModel.modelUI;
import com.example.interfaceModel.LoginModel.loginstrutImpl;
import com.example.utils.loadingAnimation;
import com.example.utils.printInsider;
import com.example.utils.programTermination;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static modelUI console = new modelUI();
    static printInsider in = new printInsider();
    static loadingAnimation l = new loadingAnimation();
    static programTermination termination = new programTermination();
    static InterfaceException ex = new InterfaceException();
    static Stack<Runnable> menustack = new Stack<>();

    public static void main(String[] args) throws Exception {
        //Contains the heading of the Banking System.
        console.version();
        //Call the Main function.
        loginstrutImpl log = new loginstrutImpl();
        menustack.push(() -> {
            try {
                log.Login();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                menustack.push(() -> {
                    try {
                        MainMenu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        runMenuLoop();
    }
    public static void MainMenu() throws Exception{
        int input = 0;
            console.interfaceModel();
        in.print("\n");
        while (true) {
            in.print("Enter value: ");
            if (sc.hasNextInt()) {
            input = sc.nextInt();
            sc.nextLine(); // consume the newline
            break;
        } else {
            in.println("Invalid input! Please enter a number.");
            sc.nextLine(); // consume the invalid token
        }
}
        switch (input) {
            case 6:
                menustack.push(Main::otherOperation);
                break;
            case 7:
            try {
                //Contain loading + termination 
                l.loading();
                menustack.clear();
                termination.exitAndClose();
            } catch (Exception e) {
                ex.valueError();
            }       
                break;
            default:
                in.println("Invalid Input! please Try again\n");
                break;
        }
    }
    public static void otherOperation(){
        int options;
        console.otherOperation();
        in.print("Enter Value : ");
        options = sc.nextInt();
        sc.nextLine();
        switch (options) {
            case 0:
                menustack.pop();
                break;
        
            default:
                in.println("Invalid Input! please Try again\n");
                break;
        }
    }
    static void runMenuLoop(){
        while(!menustack.isEmpty()){
            Runnable CurrentOperation = menustack.pop();
            CurrentOperation.run();
        }
    }
}