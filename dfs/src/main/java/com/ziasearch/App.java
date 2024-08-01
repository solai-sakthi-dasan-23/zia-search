package com.ziasearch;

import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unused")
    public static void main( String[] args )
    {
        System.out.println("WELCOME TO DISTRIBUTED FILE SYSTEM");
        System.out.println();

        //Getting the user id
        System.out.print("Enter user id : ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();
        User user = new User(userId);

        //displaying the node

        //getting the type of operation that user need to perform in the node
        ServerOperation serverOperation = new ServerOperation();
        int operation = serverOperation.operationType();
        serverOperation.operationNavigation(operation);

        //get the file name that the file should be acted in the node

        scanner.close();
    }
}
