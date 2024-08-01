package com.ziasearch;

import java.util.Scanner;

import javax.sound.midi.Soundbank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("WELCOME TO DISTRIBUTED FILE SYSTEM");
        System.out.println();

        System.out.print("Enter user id : ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();
        User user = new User(userId);

        ServerOperation serverOperation = new ServerOperation();
        int operation = serverOperation.operationType();
        serverOperation.operationNavigation(operation);

        scanner.close();
    }
}
