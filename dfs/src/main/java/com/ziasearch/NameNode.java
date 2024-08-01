package com.ziasearch;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class NameNode {
    
    public void getNodesList() {
        try {
             
            DataNodes dataNodes = new DataNodes();
            File nodesFolder = dataNodes.navigateToNodeMaster();
            File[] fileList = nodesFolder.listFiles();
            for (File file : fileList) {
                System.out.println(file.getName());
                System.out.print("  ");
                File fileN = new File(nodesFolder.getAbsolutePath(), file.getName());
                File[] nodeFilesList = fileN.listFiles();
                Arrays.sort(nodeFilesList);
                for (File fileS : nodeFilesList) {
                    System.out.println(fileS.getName() + "  " + fileS.length() + "-bytes" + "   " + getLastModifiedTime(fileS.lastModified()));
                    System.out.print("  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastModifiedTime(long milliseconds) {

        // Convert epoch milliseconds to Instant
        Instant instant = Instant.ofEpochMilli(milliseconds);

        // Convert Instant to LocalDateTime using system default time zone
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        // Define the formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format LocalDateTime to string
        return localDateTime.format(formatter);
    }
}
