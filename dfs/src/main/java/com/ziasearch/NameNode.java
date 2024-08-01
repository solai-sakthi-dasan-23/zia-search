package com.ziasearch;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.Arrays;

public class NameNode {
    
    public void getNodesList() {
        try {
            // Get the class file path
            String classFilePath = App.class.getResource(App.class.getSimpleName() + ".class").toURI().getPath();
            
            // Convert to File object
            File classFile = new File(classFilePath);
            
            // Get the directory containing the class file
            File directory = classFile.getParentFile().getParentFile();

            File nodesFolder = new File(directory.getAbsolutePath(), "Nodes");            
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
}
