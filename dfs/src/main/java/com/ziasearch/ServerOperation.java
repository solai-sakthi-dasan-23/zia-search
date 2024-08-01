package com.ziasearch;

import java.net.URISyntaxException;
import java.util.Scanner;

public class ServerOperation {

    Scanner scanner = new Scanner(System.in);
    DataNodes dataNodes = new DataNodes();
    
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

    public void operationNavigation(int operation) throws URISyntaxException {

        
        //get the file name, node that the file should be acted in the node
        System.out.println();
        System.out.print("Enter the node name : ");
        scanner.nextLine();
        String nodeName = scanner.nextLine();
        String nodeNav = dataNodes.enterNode(nodeName);

        System.out.println();
        
        System.out.print("Enter the file : ");
        String filename = scanner.nextLine();
                
        switch (operation) {

            case 1:
                addFile(nodeNav, filename);
                break;

            case 2:
                retrieveFile(nodeNav, filename);
                break;

            case 3:
                modifyFile(nodeNav, filename);
                break;

            case 4:
                removeFile(nodeNav, filename);
                break;
        
            default:
                break;
        }
    }

    private boolean removeFile(String nodeNav, String filename) {
        System.out.println("You choose to remove the existing file : " + filename);
        return true;
    }

    private boolean modifyFile(String nodeNav, String filename)  {
        System.out.println("You choose to modify a existing file : " + filename);
        return true;
    }

    private boolean retrieveFile(String nodeNav, String filename) {
        System.out.println("You choose to retrieve a existing file : " + filename); 
        return true;
    }

    private boolean addFile(String nodeNav, String filename) {
        System.out.println("You choose to add a new file : " + filename);
        return true;
    }
}
