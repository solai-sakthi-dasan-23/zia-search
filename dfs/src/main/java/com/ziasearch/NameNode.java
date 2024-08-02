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
                JavaGrid.println(file.getName(), 15);
                System.out.println();
                File fileN = new File(nodesFolder.getAbsolutePath(), file.getName());
                File[] nodeFilesList = fileN.listFiles();
                Arrays.sort(nodeFilesList);
                for (File fileS : nodeFilesList) {
                    dataNodes.getMetadata(nodesFolder.getAbsolutePath()+ File.separator + file.getName() + File.separator +  fileS.getName());
                }
                System.out.println();
                JavaGrid.wait(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
