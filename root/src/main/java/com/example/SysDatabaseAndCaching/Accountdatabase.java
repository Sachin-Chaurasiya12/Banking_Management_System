package com.example.SysDatabaseAndCaching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.example.Model.Account;
import com.zaxxer.hikari.HikariDataSource;

public class Accountdatabase {
    static HikariDataSource ds;
    public static void initAConnection() {
        if (ds != null) return;
        connectionCaching conn = new connectionCaching();
        ds = conn.conPooling();

        if (ds == null) {
            throw new RuntimeException("Failed to initialize DB connection");
        }

        createATable();
    }
    public static void createATable(){
        String sql = """
                CREATE TABLE IF NOT EXISTS AccountDetails (
                Accountnumber INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                amount DOUBLE NOT NULL,
                phoneno VARCHAR(10) NOT NULL
                )AUTO_INCREMENT=1000001;
                """;
        
            try (Connection conn = ds.getConnection();
                Statement stmt = conn.createStatement()){
                stmt.execute(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public static int insertAccount(Account account) throws Exception {
    String sql = "INSERT INTO AccountDetails(name, amount, phoneno) VALUES (?, ?, ?)";

    try (Connection conn = ds.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setString(1, account.getName());
        ps.setDouble(2, account.getAmount());
        ps.setString(3, account.getPhoneno());

        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1); // ✅ generated Accountnumber
            }
        }
    }
    throw new RuntimeException("Failed to generate account number");
}
public static String CheckABalance(Account account) throws Exception {
    String sql = "select amount from accountdetails where Accountnumber = ?";

    try (Connection conn = ds.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, account.getAccountnumber());
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            double amount = rs.getDouble("amount");
            return "Account Balance : " + amount;
        }
    }
    throw new RuntimeException("Account does'nt Exist!");
}
public static double DepositABalance(Account account, double add) throws Exception {
    String balance = CheckABalance(account);
    double currentBalance = Double.parseDouble(balance.replace("Account Balance : ", ""));

    double newbalance = currentBalance + add;

    String sql = "update accountdetails set amount = ? where accountnumber = ? ";

    try (Connection conn = ds.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDouble(1, newbalance);
        ps.setString(2, account.getAccountnumber());
        
        int rowupdate = ps.executeUpdate();
        if(rowupdate == 0){
            throw new RuntimeException("Account does'nt Exist!");
        }
    }
    return newbalance;
}
public static double WithdrawABalance(Account account, double Withdraw) throws Exception {
    String balance = CheckABalance(account);
    double currentBalance = Double.parseDouble(balance.replace("Account Balance : ", ""));

    double newbalance = currentBalance - Withdraw;

    String sql = "update accountdetails set amount = ? where accountnumber = ? ";

    try (Connection conn = ds.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setDouble(1, newbalance);
        ps.setString(2, account.getAccountnumber());
        
        int rowupdate = ps.executeUpdate();
        if(rowupdate == 0){
            throw new RuntimeException("Account does'nt Exist!");
        }
    }
    return newbalance;
}
}
