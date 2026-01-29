package com.example.SysDatabase;

import java.sql.*;
import com.example.Model.login;
import com.zaxxer.hikari.HikariDataSource;

public class LoginDataImpl {

    private static HikariDataSource ds;

    // initialize connection pool ONCE
    public static void initConnection() {
        if (ds != null) return;
        connectionCaching conn = new connectionCaching();
        ds = conn.conPooling();

        if (ds == null) {
            throw new RuntimeException("Failed to initialize DB connection");
        }

        createTable();
    }

    // expose pooled connection
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    // create table
    private static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS loggeduser(
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(20) UNIQUE NOT NULL,
                password VARCHAR(255) NOT NULL
            )
        """;

        try (Connection con = ds.getConnection();
             Statement stmt = con.createStatement()) {

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // save user
    public void saveUser(login user) throws Exception {
        String sql = "INSERT INTO loggeduser(username, password) VALUES (?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword()); // hashed password
            ps.executeUpdate();
        }
    }
    public boolean validateUser(String username,String password) throws Exception {
        String sql = "select * from loggeduser where username = ? and password = ?";

        try (Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()){
                return rs.next();
            }
        }

    }
    public boolean userExists(String username) throws Exception {
    String sql = "SELECT 1 FROM loggeduser WHERE username = ?";
    try (Connection con = getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, username);
        try (ResultSet rs = ps.executeQuery()) {
            return rs.next(); // true if user exists
        }
    }
}
}
