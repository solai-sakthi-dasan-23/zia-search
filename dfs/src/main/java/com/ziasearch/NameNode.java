package com.ziasearch;

import java.io.File;
import java.util.Arrays;

public class NameNode {
    
    public void getNodesList() {
        try {
             
            DataNodes dataNodes = new DataNodes();
            File nodesFolder = dataNodes.navigateToNodeMaster();
            File[] fileList = nodesFolder.listFiles();
            for (File file : fileList) {
                System.out.println(file.getName());
                System.out.println("");
                File fileN = new File(nodesFolder.getAbsolutePath(), file.getName());
                File[] nodeFilesList = fileN.listFiles();
                Arrays.sort(nodeFilesList);
                for (File fileS : nodeFilesList) {
                    //System.out.println(fileS.getName() + "  " + fileS.length() + "-bytes" + "   " + dataNodes.getLastModifiedTime(fileS.lastModified()));
                    //System.out.print("  ");
                    dataNodes.getMetadata(nodesFolder.getAbsolutePath()+ File.separator + file.getName() + File.separator +  fileS.getName());
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
