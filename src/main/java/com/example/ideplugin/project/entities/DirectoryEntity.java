package com.example.ideplugin.project.entities;

public class DirectoryEntity {
    String parentDirPath;
    String absolutePath;
    String name;

    public DirectoryEntity(String parentPath, String path){
        parentDirPath = parentPath;
        absolutePath = path;
        name = parseName(path);
    }

    public String getName(){
        return name;
    }

    public String getAbsolutePath(){
        return absolutePath;
    }
    private String parseName(String path){
        String[] parts = path.split("\\\\");
        String temp = parts[parts.length - 1];
        return temp.substring(0, temp.indexOf("."));
    }
}
