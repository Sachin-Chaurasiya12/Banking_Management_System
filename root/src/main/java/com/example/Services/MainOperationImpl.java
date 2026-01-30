package com.example.Services;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.Stack;

import com.example.Model.Account;
import com.example.SysDatabaseAndCaching.Accountdatabase;
import com.example.utils.loadingAnimation;
import com.example.utils.printInsider;

public class MainOperationImpl implements MainOperation{
    static printInsider in =  new printInsider();
    static Scanner sc = new Scanner(System.in);
    static final String filename = "application.properties";
    static HashMap<String, Account> accounts = new HashMap<>();
    static loadingAnimation l = new loadingAnimation();
    static Stack<Runnable> stack = new Stack<>();

    @Override
    public void Account() {
        Accountdatabase.initAConnection();
        Properties props = new Properties();
        InputStream file = getClass()
                .getClassLoader()
                .getResourceAsStream(filename);
        
        if (file == null) {
            throw new RuntimeException("application.properties file not found");
        }
        try {
            props.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String miniBalance = props.getProperty("account.minimumBalance");
        Double balance = Double.valueOf(miniBalance);

        in.print("Enter Account holder Name : ");
        String name = sc.nextLine();

        double amount;
        while (true) {
        in.print("Enter Minimum Amount : ");
        amount = sc.nextDouble();
        sc.nextLine();
        if(amount >= balance){
            break;
        }
        in.println("Amount does'nt match minimum amount");
        }

        String phoneno;
        while(true){
        in.print("Enter Phone number : ");
        phoneno = sc.nextLine();   
        if(phoneno.matches("\\d{10}")){
            break;
        }
        in.println("Phone number doesnt match the expected length");
        }
        Account acc = new Account(null, name, amount, phoneno);
        try {
        l.AccountCreation();

        int generatedAccNo = Accountdatabase.insertAccount(acc);

        acc.setAccountnumber(String.valueOf(generatedAccNo)); 
        accounts.put(acc.getAccountnumber(), acc);           

        in.println("Account created Successfully!");
        in.println("Your Account Number is: " + generatedAccNo);

        } catch (Exception e) {
        in.println("Failed to create account. Please try again.");
        }
        System.out.println();
    }

    @Override
    public void CheckBalance() {
        Accountdatabase.initAConnection();
        in.print("Enter the Account Number : ");
        String accNo = sc.nextLine();

        Account acc = new Account();
        acc.setAccountnumber(accNo);

        try {
            String balanceinfo = Accountdatabase.CheckABalance(acc);
            in.println(balanceinfo);
        } catch (Exception e) {
            in.println("Failed to fetch balance: " + e.getMessage());
        }
    }

    @Override
    public void Deposit() {
        Accountdatabase.initAConnection();
        in.print("Enter the Account Number : ");
        String accNo = sc.nextLine();

        Account acc = new Account();
        acc.setAccountnumber(accNo);

        in.print("Enter the amount you want to deposit : ");
        double amount = sc.nextDouble();
        sc.nextLine();

        try {
            Accountdatabase.DepositABalance(acc, amount);
            Thread.sleep(1000);
            in.println("Amount Deposited Successfully!");
        } catch (Exception e) {
            in.println("Failed to fetch balance: " + e.getMessage());
        }

    }

    @Override
    public void Withdraw() {
        Accountdatabase.initAConnection();
        in.print("Enter the Account Number : ");
        String accNo = sc.nextLine();

        Account acc = new Account();
        acc.setAccountnumber(accNo);

        in.print("Enter the amount you want to Withdraw : ");
        double amount = sc.nextDouble();
        sc.nextLine();

        try {
            Accountdatabase.WithdrawABalance(acc, amount);
            Thread.sleep(1000);
            in.println("Amount Withdrawn Successfully!");
        } catch (Exception e) {
            in.println("Failed to fetch balance: " + e.getMessage());
        }
    }
    static void runloop(){
        while (!stack.isEmpty()) {
            Runnable currOperation = stack.pop();
            currOperation.run();
        }
    }
}
