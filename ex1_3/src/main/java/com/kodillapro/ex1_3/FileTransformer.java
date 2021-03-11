package com.kodillapro.ex1_3;

public class FileTransformer {

    private static final String PATH_DELIMITER_REGEX = "\\\\";

    public String returnFileName(String fileName) {
        String[] pathParts = fileName.split(PATH_DELIMITER_REGEX);
        return pathParts[pathParts.length-1];
    }
}
