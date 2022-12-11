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

    public String getName(){
        return name;
    }

    private String parseName(String absolutePath){
        String[] parts = absolutePath.split("\\\\");
        String temp = parts[parts.length - 1];
        return temp.substring(0, temp.indexOf("."));
    }
}
