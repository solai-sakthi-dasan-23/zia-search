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

    public int operationType() throws InterruptedException {
        JavaGrid.println("What is the operation you need to perform");
        JavaGrid.println("1. Add a File");
        JavaGrid.println("2. Retrieve a File");
        JavaGrid.println("3. Modify a File");
        JavaGrid.println("4. Remove a File");
        System.out.println();
        JavaGrid.print("Enter the number for your operation respectively : ", 20);
        int operation = scanner.nextInt();
        return operation;
    }

    public void operationNavigation(int operation) throws Exception {

        // get the file name, node that the file should be acted in the node
        System.out.println();
        JavaGrid.print("Enter the node name : ",20);
        scanner.nextLine();
        nodeName = scanner.nextLine();
        String nodeNav = dataNodes.enterNode(nodeName);

        System.out.println();

        JavaGrid.print("Enter the file name : ",20);
        fileName = scanner.nextLine();

        System.out.println();

        JavaGrid.print("Enter the file type : ", 20);
        fileType = scanner.nextLine();

        switch (operation) {

            case 1:
                scanner.nextLine();
                JavaGrid.print("Enter the file path where the mentioned file is present", 20);
                String filePath = scanner.nextLine();
                addFile(nodeNav, filePath, fileName, fileType, false);
                replicateAddedFile(filePath, fileName);
                break;

            case 2:
                retrieveFile(nodeNav, fileName);
                break;

            case 3:
                JavaGrid.print("Enter the content to the file : " , 20);
                String content = scanner.nextLine();
                modifyFile(nodeNav, fileName + "." + fileType, content);
                modifyReplicatedFile(fileName, content);
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
        while (replicationCount <= 3) {
            indexOfNode++;
            if (indexOfNode == 10) {
                indexOfNode = 0;
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
        while (replicationCount <= 3) {
            indexOfNode++;
            if (indexOfNode == 10) {
                indexOfNode = 0;
            }
            String nodeNavrep = dataNodes.enterNode(nodesListFiles.get(indexOfNode));
            modifyFile(nodeNavrep, filename + "_replicated" + "." + fileType, content);
            replicationCount++;
        }
    }

    private void removeFile(String nodeNav, String filename, String fileType) throws InterruptedException {
        System.out.println("You choose to remove the existing file : " + filename + "." + fileType);
        String filePath = nodeNav + File.separator + filename + "." + fileType;

        File file = new File(filePath);

        if (file.delete()) {
            JavaGrid.print("File deleted successfully.");
        } else {
            JavaGrid.print("Failed to delete the file.");
        }
    }

    private void modifyFile(String nodeNav, String filename, String content) throws InterruptedException {
        JavaGrid.print("You choose to modify a existing file : " + filename);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nodeNav + File.separator + filename))) {
            writer.write(content);
            JavaGrid.print("File has been rewritten successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file.");
            e.printStackTrace();
        }
    }

    private void retrieveFile(String nodeNav, String filename) throws InterruptedException {
        JavaGrid.println("You choose to retrieve a existing file : " + filename + "." + fileType, 10);
        File file = new File(nodeNav + File.separator + filename + "." + fileType);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JavaGrid.println(line, 10);
            }
        } catch (IOException e) {
            JavaGrid.println("Error reading the file.");
            e.printStackTrace();
        }
    }

    private void addFile(String nodeNav, String filePath, String filename, String fileType, boolean replication)
            throws Exception {

        Path sourcePath = Paths.get(filePath + File.separator + filename + "." + fileType);
        Path destinationPath;
        if (replication == true) {
            destinationPath = Paths.get(nodeNav + File.separator + filename + "_replicated" + "." + fileType);
        } else {
            JavaGrid.println("You choose to add a new file : " + filename + "." + fileType);
            destinationPath = Paths.get(nodeNav + File.separator + filename + "." + fileType);
        }

        try {
            // Copy the file to the new location
            Files.copy(sourcePath, destinationPath);
            JavaGrid.println("File copied successfully.");
        } catch (IOException e) {
            JavaGrid.println("Error copying file.");
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
        while (replicationCount <= 3) {
            indexOfNode++;
            if (indexOfNode == 10) {
                indexOfNode = 0;
            }
            String nodeNavrep = dataNodes.enterNode(nodesListFiles.get(indexOfNode));
            addFile(nodeNavrep, filePath, filename, fileType, true);
            replicationCount++;
        }
    }
}
