package com.example.core;

import java.util.Scanner;
import java.util.Stack;

import com.example.Exception.InterfaceException;
import com.example.Services.MainOperation;
import com.example.Services.MainOperationImpl;
import com.example.Services.logs;
import com.example.Services.logsImplementation;
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
    static MainOperation main = new MainOperationImpl();
    static logs log = new logsImplementation();
    static Stack<Runnable> menustack = new Stack<>();

    public static void main(String[] args) throws Exception {
        // Display heading/version
        console.version();

        // Push login as first operation
        loginstrutImpl log = new loginstrutImpl();
        menustack.push(() -> {
            try {
                log.Login();
                menustack.push(() -> {
                    try {
                        MainMenu();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }); // After login, show main menu
            } catch (Exception e) {
                in.println("Login failed: " + e.getMessage());
                e.printStackTrace();
            }
        });

        // Run menu loop
        runMenuLoop();
    }

    /** Main menu logic 
     * @throws Exception **/
    public static void MainMenu() throws Exception {
        while (true) {
            console.interfaceModel();
            int input = readInt("Enter value: ");

            switch (input) {
                case 1 -> {
                    try {
                        main.Account();
                        System.out.println();
                    } catch (Exception e) {
                        in.println("Something went wrong! Please try again.");
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    try {
                        main.CheckBalance();
                        System.out.println();
                    } catch (Exception e) {
                        in.println("Something went wrong! Please try again.");
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    try {
                        main.Deposit();
                        System.out.println();
                    } catch (Exception e) {
                        in.println("Something went wrong! Please try again.");
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    try {
                        main.Withdraw();
                        System.out.println();
                    } catch (Exception e) {
                        in.println("Something went wrong! Please try again.");
                        e.printStackTrace();
                    }
                }
                case 6 -> {
                    menustack.push(Main::otherOperation);
                    return;
                }
                case 7 -> {
                    try {
                        l.loading();
                        menustack.clear();
                        termination.exitAndClose();
                        return; // exit loop
                    } catch (Exception e) {
                        ex.valueError();
                        e.printStackTrace();
                    }
                }
                default -> in.println("Invalid input! Please try again.\n");
            }
        }
    }

    /** Other operations menu **/
    public static void otherOperation() {
        while (true) {
            console.otherOperation();
            int option = readInt("Enter value: ");

            switch (option) {
                case 1 -> {
                    in.println("+----------------------+");
                    in.println("1) All Account logs");
                    in.println("2) Account logs by id");
                    in.println("+----------------------+");
                    in.print("Enter Value : ");
                    int input = sc.nextInt();
                    sc.nextLine();
                    if(input == 1){
                        menustack.push(() -> {
                        try {
                            log.Accountlogs();
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        otherOperation();
                        });
                        return;
                    }else if(input == 2){
                        menustack.push(() -> {
                        try {
                            in.print("Enter Account Number : ");
                            String AccountNumber = sc.nextLine();
                            log.AccountlogsbyID(AccountNumber);
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        otherOperation();
                        });
                        return;
                    }
                }
                case 0 -> {
                    // Go back to main menu
                    menustack.push(() -> {
                        try {
                            MainMenu();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    return;
                }
                default -> in.println("Invalid input! Please try again.\n");
            }
        }
    }

    /** Stack-driven loop for menus **/
    static void runMenuLoop() {
        while (!menustack.isEmpty()) {
            Runnable current = menustack.pop();
            current.run();
        }
    }

    /** Helper method to read integer input safely **/
    private static int readInt(String prompt) {
        int value;
        while (true) {
            in.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                sc.nextLine(); // consume newline
                return value;
            } else {
                in.println("Invalid input! Please enter a number.");
                sc.nextLine(); // consume invalid input
            }
        }
    }
}
