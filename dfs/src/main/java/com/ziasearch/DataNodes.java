package com.ziasearch;


import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class DataNodes {

    public File navigateToNodeMaster() throws Exception {
        // Get the class file path
        String classFilePath = App.class.getResource(App.class.getSimpleName() + ".class").toURI().getPath();
            
        // Convert to File object
        File classFile = new File(classFilePath);
        
        // Get the directory containing the class file
        File directory = classFile.getParentFile().getParentFile();
        File nodesFolder = new File(directory.getAbsolutePath(), "Nodes");
        return nodesFolder;
    }

    public void getNodesList() {
        try {
            File nodesFolder = navigateToNodeMaster();   
            File[] fileList = nodesFolder.listFiles();
            for (File file : fileList) {
                System.out.println(file.getName());
                System.out.print("  ");
                File fileN = new File(nodesFolder.getAbsolutePath(), file.getName());
                File[] nodeFilesList = fileN.listFiles();
                Arrays.sort(nodeFilesList);
                for (File fileS : nodeFilesList) {
                    System.out.println(fileS.getName());
                    System.out.print("  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String enterNode(String nodeName) throws Exception {

        File nodesFolder = navigateToNodeMaster(); 
        File node = new File(nodesFolder.getAbsolutePath(), nodeName);
        System.out.println(node.getAbsolutePath());
        return node.getAbsolutePath();
    }

    public void getMetadata(String file) {
        File fileStud = new File(file);
        System.out.println("File Name: " + fileStud.getName());
        System.out.println("File Size: " + fileStud.length() + " bytes");
        System.out.println("Last Modified: " + getLastModifiedTime(fileStud.lastModified()));
        System.out.println("Is Directory: " + fileStud.isDirectory());
        System.out.println();
    }

    public String getLastModifiedTime(long milliseconds) {

        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
