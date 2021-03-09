package com.kodillapro.ex1_3;

public class FileTransformer {

    public String returnFileName(String fileName) {
        String[] pathParts = fileName.split("\\\\");
        return pathParts[pathParts.length-1];
    }
}
