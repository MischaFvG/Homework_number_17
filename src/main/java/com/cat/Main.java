package com.cat;

import org.apache.ant.compress.taskdefs.Unzip;
import org.apache.commons.io.FileUtils;

import java.io.*;

public class Main {
    private static int countJavaFiles = 0;
    private static int countFilesWithAnnotation = 0;
    private static final String ZIP_URL = "javaFiles.zip";
    private static final String OUTPUT_DIRECTORY = "javaFiles";

    public static void main(String[] args) {
        File inputZIP = new File(ZIP_URL);
        File outputDirectory = new File(OUTPUT_DIRECTORY);
        Unzip unzip = new Unzip();
        unzip.setSrc(inputZIP);
        unzip.setDest(outputDirectory);
        unzip.execute();
        System.out.println("All files of input ZIP directory");
        walk(OUTPUT_DIRECTORY);
        System.out.println("Quantity of java files is " + countJavaFiles);
        System.out.println("Quantity of java files with annotation @FunctionalInterface is " + countFilesWithAnnotation);
    }

    public static void walk(String fileName) {
        File file = new File(fileName);
        File[] fileList = file.listFiles();
        if (fileList == null) {
            return;
        }
        for (File f : fileList) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
            } else {
                System.out.println(f.getName());
                String filePath = f.getAbsolutePath();
                if (filePath.contains(".java")) {
                    countJavaFiles++;
                }
                try {
                    String fileString = FileUtils.readFileToString(new File(f.getAbsolutePath()), "UTF-8");
                    if (fileString.contains("@FunctionalInterface")) {
                        countFilesWithAnnotation++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
