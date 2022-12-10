package com.example.ideplugin.project.entities;

import java.io.File;

public class FileEntity {
    boolean isDirectory;
    String absolutePath;
    String name;

    public FileEntity(String absolutePath){
        if (absolutePath == null){
            // todo: implement new form
            return;
        }
        File file = new File(absolutePath);
        isDirectory = file.isDirectory();
        name = parseName(absolutePath);
        this.absolutePath = absolutePath;
    }

    private String parseName(String absolutePath){
        // todo: implement
        return " ";
    }
}
