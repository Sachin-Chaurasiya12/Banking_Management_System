package com.example.Services;

import java.sql.SQLException;

import com.example.SysDatabaseAndCaching.accountAndTransferlog;

public class logsImplementation implements logs{

    static accountAndTransferlog at = new accountAndTransferlog();
    @Override
    public void Accountlogs() {
        try {
            at.Accountlogs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void AccountlogsbyID(String AccountNumber) {
        try {
            at.AccountlogsbyID(AccountNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void Transactionlogs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Transactionlogs'");
    }

    @Override
    public void TransactionlogsbyID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'TransactionlogsbyID'");
    }
}
