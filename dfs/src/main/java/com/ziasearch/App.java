package com.ziasearch;

import java.net.URISyntaxException;
import java.util.Scanner;


/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("unused")
    public static void main( String[] args ) throws Exception
    {
        JavaGrid.println("WELCOME TO DISTRIBUTED FILE SYSTEM");
        System.out.println();

        //Getting the user id
        JavaGrid.print("Enter user id : ");
        Scanner scanner = new Scanner(System.in);
        int userId = scanner.nextInt();
        User user = new User(userId);

        //displaying the node
        NameNode nameNode = new NameNode();
        nameNode.getNodesList();

        //getting the type of operation that user need to perform in the node
        ServerOperation serverOperation = new ServerOperation();
        int operation = serverOperation.operationType();
        try {
            serverOperation.operationNavigation(operation);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
