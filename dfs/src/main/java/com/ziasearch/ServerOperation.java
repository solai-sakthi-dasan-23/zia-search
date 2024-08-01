package com.ziasearch;

import java.util.Scanner;

public class ServerOperation {

    Scanner scanner = new Scanner(System.in);
    
    public int operationType () {
        System.out.println("What is the operation you need to perform");
        System.out.println("1. Add a File");
        System.out.println("2. Retrieve a File");
        System.out.println("3. Modify a File");
        System.out.println("4. Remove a File");
        System.out.println();
        System.out.print("Enter the number for your operation respectively : ");
        int operation = scanner.nextInt();
        return operation;
    }

    public void operationNavigation(int operation) {

        
        //get the file name, node that the file should be acted in the node
        System.out.println();
        System.out.print("Enter the node : ");
        String nodeName = scanner.next();
        System.out.println();
        System.out.print("Enter the file : ");
        String filename = scanner.next();
        
        switch (operation) {

            case 1:
                addFile(nodeName, filename);
                break;

            case 2:
                retrieveFile(nodeName, filename);
                break;

            case 3:
                modifyFile(nodeName, filename);
                break;

            case 4:
                removeFile(nodeName, filename);
                break;
        
            default:
                break;
        }
    }

    private boolean removeFile(String nodeName, String filename) {
        System.out.println("You choose to remove the existing file");
        return true;
    }

    private boolean modifyFile(String nodeName, String filename) {
        System.out.println("You choose to modify a existing file");
        return true;
    }

    private boolean retrieveFile(String nodeName, String filename) {
        System.out.println("You choose to retrieve a existing file"); 
        return true;
    }

    private boolean addFile(String nodeName, String filename) {
        System.out.println("You choose to add a new file");
        return true;
    }
}
