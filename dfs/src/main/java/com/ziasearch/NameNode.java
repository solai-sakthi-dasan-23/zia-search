package com.ziasearch;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

        Instant instant = Instant.ofEpochMilli(milliseconds);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
