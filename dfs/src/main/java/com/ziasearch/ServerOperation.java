package com.ziasearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerOperation {

    Scanner scanner = new Scanner(System.in);
    DataNodes dataNodes = new DataNodes();
    String nodeName, fileType, fileName;
    
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
        
        System.out.print("Enter the file name : ");
        fileName = scanner.nextLine();

        System.out.println();

        System.out.print("Enter the file type : ");
        fileType = scanner.nextLine();
                
        switch (operation) {

            case 1:
                scanner.nextLine();
                System.out.println("Enter the file path where the mentioned file is present");
                String filePath = scanner.nextLine();
                addFile(nodeNav, filePath, fileName, fileType, false);
                replicateAddedFile(filePath, fileName);
                break;

            case 2:
                retrieveFile(nodeNav, fileName);
                break;

            case 3:
                System.out.println("Enter the content to the file");
                String content = scanner.nextLine();
                modifyFile(nodeNav, fileName + "." + fileType , content);
                modifyReplicatedFile(fileName,content);
                break;

            case 4:
                removeFile(nodeNav, fileName, fileType);
                removeReplicatedFiles(nodeName, fileName + "_replicated");
                break;
        
            default:
                break;
        }
    }

    private void removeReplicatedFiles(String nodeName, String fileName) throws Exception {
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
            removeFile(nodeNavrep, fileName, fileType);
            replicationCount++;
        }
    }

    private void modifyReplicatedFile(String filename, String content) throws Exception {
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
            modifyFile(nodeNavrep, filename + "_replicated" + "." + fileType, content);
            replicationCount++;
        }
    }

    private void removeFile(String nodeNav, String filename, String fileType) {
        System.out.println("You choose to remove the existing file : " + filename + "." + fileType);
        String filePath = nodeNav + "\\" + filename + "." + fileType;

        File file = new File(filePath);

        if (file.delete()) {
            System.out.println("File deleted successfully.");
        } else {
            System.err.println("Failed to delete the file.");
        }
    }

    private void modifyFile(String nodeNav, String filename, String content)  {
        System.out.println("You choose to modify a existing file : " + filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nodeNav + "\\" + filename))) {
            writer.write(content);
            System.out.println("File has been rewritten successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file.");
            e.printStackTrace();
        }
    }

    private void retrieveFile(String nodeNav, String filename) {
        System.out.println("You choose to retrieve a existing file : " + filename + "." + fileType); 
        File file = new File(nodeNav + "\\" + filename + "." + fileType);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file.");
            e.printStackTrace();
        }
    }

    private void addFile(String nodeNav, String filePath, String filename, String fileType, boolean replication) throws Exception {
        

        Path sourcePath = Paths.get(filePath + "\\" + filename + "." + fileType);
        Path destinationPath;
        if (replication==true) {
            destinationPath = Paths.get(nodeNav + "\\" + filename + "_replicated" + "." + fileType);
        } else {    
            System.out.println("You choose to add a new file : " + filename + "." + fileType);
            destinationPath = Paths.get(nodeNav + "\\" + filename + "." + fileType);
        }

        try {
            // Copy the file to the new location
            Files.copy(sourcePath, destinationPath);
            System.out.println("File copied successfully.");
        } catch (IOException e) {
            System.err.println("Error copying file.");
            e.printStackTrace();
        }
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
            addFile(nodeNavrep, filePath, filename, fileType, true);
            replicationCount++;
        }
    }
}
