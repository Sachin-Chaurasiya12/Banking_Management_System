package com.example.SysDatabaseAndCaching;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.example.Model.Account;
import com.zaxxer.hikari.HikariDataSource;

public class accountAndTransferlog {
    static HikariDataSource ds;
    static HashMap<String,Account> map = new HashMap<>();
    public static void initAConnection() {
        if (ds != null) return;
        connectionCaching conn = new connectionCaching();
        ds = conn.conPooling();

        if (ds == null) {
            throw new RuntimeException("Failed to initialize DB connection");
        }
    }
public void Accountlogs() throws SQLException {
    initAConnection();
    String countSql = "SELECT COUNT(*) FROM accountdetails";
    String sql = "SELECT * FROM accountdetails";

    try (Connection conn = ds.getConnection()) {

        // 1. Get total row count
        try (PreparedStatement psCount = conn.prepareStatement(countSql);
             ResultSet rsCount = psCount.executeQuery()) {

            if (rsCount.next()) {
                int totalRows = rsCount.getInt(1);
                System.out.println("Total rows: " + totalRows);
            }
        }

        // 2. Fetch all accounts
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("+------------+----------------+----------------+-------------+");
            System.out.println("| Account #  | Name           | Amount         | Phone No    |");
            System.out.println("+------------+----------------+----------------+-------------+");

            while (rs.next()) {
                int accNo = rs.getInt("Accountnumber");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                String phone = rs.getString("phoneno");

                System.out.printf("| %-10d | %-15s | %-14.2f | %-10s |\n",accNo, name, amount, phone);
            }

            System.out.println("+------------+----------------+----------------+-------------+");
        }

    }
}

public void AccountlogsbyID(String Accountnumber) throws SQLException{
        initAConnection();
        String sql = "SELECT * FROM accountdetails where Accountnumber = ?";

        try (Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) { 

            ps.setString(1,Accountnumber);
            try(ResultSet rs = ps.executeQuery()){
            System.out.println("+------------+----------------+----------------+-------------+");
            System.out.println("| Account #  | Name           | Amount         | Phone No    |");
            System.out.println("+------------+----------------+----------------+-------------+");
            
            boolean found = false;

            while (rs.next()) {
                found = true;
                int accNo = rs.getInt("Accountnumber");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                String phone = rs.getString("phoneno");

                System.out.printf("| %-10d | %-15s | %-14.2f | %-10s |\n",accNo, name, amount, phone);
            }
            if (!found) {
                System.out.println("|        No account found for given ID        |");
            }
            System.out.println("+------------+----------------+----------------+-------------+");
        }
        }
    }
    public void Transactionlogs(){}
}
