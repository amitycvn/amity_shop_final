package org.example.backend.services.files;

import org.example.backend.utils.FileUtils;

import java.io.IOException;

public class FileService {
    public void readFile() {
        try {
            String content = FileUtils.readFileAsString("example.txt");
            System.out.println(content); // In ra nội dung file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}