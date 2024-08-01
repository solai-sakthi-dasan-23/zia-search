package com.ziasearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ServerOperation {

    Scanner scanner = new Scanner(System.in);
    DataNodes dataNodes = new DataNodes();
    String nodeName;
    
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

    public void operationNavigation(int operation) throws Exception {

        
        //get the file name, node that the file should be acted in the node
        System.out.println();
        System.out.print("Enter the node name : ");
        scanner.nextLine();
        nodeName = scanner.nextLine();
        String nodeNav = dataNodes.enterNode(nodeName);

        System.out.println();
        
        System.out.print("Enter the file : ");
        String filename = scanner.nextLine();
                
        switch (operation) {

            case 1:
                scanner.nextLine();
                System.out.println("Enter the file path where the mentioned file is present");
                String filePath = scanner.nextLine();
                addFile(nodeNav, filePath, filename, false);
                replicateAddedFile(filePath, filename);
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

    private boolean addFile(String nodeNav, String filePath, String filename, boolean replication) throws Exception {
        System.out.println("You choose to add a new file : " + filename);

        Path sourcePath = Paths.get(filePath + "\\" + filename);
        Path destinationPath;
        if (replication==true) {
            destinationPath = Paths.get(nodeNav + "\\" + filename + "_replicated");
        } else {
            destinationPath = Paths.get(nodeNav + "\\" + filename);
        }
        
        // Get the destination directory
        Path destinationDir = destinationPath.getParent();

        try {
            // Create destination directory if it does not exist
            if (Files.notExists(destinationDir)) {
                Files.createDirectories(destinationDir);
            }

            // Copy the file to the new location
            Files.copy(sourcePath, destinationPath);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error copying file.");
            e.printStackTrace();
        }
        return true;
    }

    public void replicateAddedFile(String filePath, String filename) throws Exception {
        DataNodes dataNodes = new DataNodes();
        File nodesFolder = dataNodes.navigateToNodeMaster();
        File[] nodesList = nodesFolder.listFiles();
        List<String> nodesListFiles = new ArrayList<>();
        for (File file : nodesList) {
            nodesListFiles.add(file.getName());
        }
        int indexOfNode = nodesListFiles.indexOf(nodeName);
        int replicationCount = 0;
        while (replicationCount<=3) {
            indexOfNode++;
            if (indexOfNode==10) {
                indexOfNode=0;
            }
            String nodeNavrep = dataNodes.enterNode(nodesListFiles.get(indexOfNode));
            addFile(nodeNavrep, filePath, filename, true);
            replicationCount++;
        }
    }
}
