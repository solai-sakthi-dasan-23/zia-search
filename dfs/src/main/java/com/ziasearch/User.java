package com.ziasearch;

public class User {
    
    @SuppressWarnings("unused")
    private int userId;

    public User(int userId) throws InterruptedException{
        this.userId = userId;
        System.out.println("Hello, user " + userId);
        Thread.sleep(1000);
    }
}
