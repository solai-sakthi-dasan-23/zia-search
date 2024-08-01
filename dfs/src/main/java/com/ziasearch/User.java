package com.ziasearch;

public class User {
    
    @SuppressWarnings("unused")
    private int userId;

    public User(int userId){
        this.userId = userId;
        System.out.println("Hello, user " + userId);
    }
}
