package com.example.Model;

public class login {
    private String Name;
    private String password;

    public login() {}
    public login(String Name,String password){
        this.Name = Name;
        this.password = password;
    }

    public String getName() {
        return Name;
    }
    public String getPassword() {
        return password;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
