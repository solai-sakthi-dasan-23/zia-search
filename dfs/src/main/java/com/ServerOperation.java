package com;

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
        
        switch (operation) {
            case 1:
                addFile();
                break;

            case 2:
                retrieveFile();
                break;

            case 3:
                modifyFile();
                break;

            case 4:
                removeFile();
                break;
        
            default:
                break;
        }
    }

    private void removeFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFile'");
    }

    private void modifyFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyFile'");
    }

    private void retrieveFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'retrieveFile'");
    }

    private void addFile() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFile'");
    }
}
