package com.ziasearch;

public class User {
    
    @SuppressWarnings("unused")
    private int userId;

    public User(int userId) throws InterruptedException{
        this.userId = userId;
        JavaGrid.println("Hello, user " + userId);
        JavaGrid.wait(1000);
    }
}
