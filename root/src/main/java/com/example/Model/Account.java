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
package com.example.Model;

public class Account {
    private int Accountnumber;
    private String name;
    private double amount;
    private String phoneno;

    public Account(){}
    public Account(int Accountnumber,String name,double amount, String phoneno){
        this.Accountnumber = Accountnumber;
        this.name = name;
        this.amount = amount;
        this.phoneno = phoneno;
    }
    public int getAccountnumber() {
        return Accountnumber;
    }
    public String getName() {
        return name;
    }
    public double getAmount() {
        return amount;
    }
    public String getPhoneno() {
        return phoneno;
    }
    public void setAccountnumber(int accountnumber) {
        Accountnumber = accountnumber;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
