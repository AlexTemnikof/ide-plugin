package com.example.ideplugin.project.entities;

public class DirectoryEntity extends Entity {
    String parentDirPath;
    String absolutePath;
    String name;

    public DirectoryEntity(String parentPath, String path){
        parentDirPath = parentPath;
        absolutePath = path;
        name = parseName(path);
    }

    public DirectoryEntity(String path){
        parentDirPath = null;
        absolutePath = path;
        name = parseName(path);
    }

    public String getName(){
        return name;
    }

    public String getAbsolutePath(){
        return absolutePath;
    }

    public String getParentDirPath(){
        return parentDirPath;
    }
    private String parseName(String path){
        String[] parts = path.split("\\\\");
        return parts[parts.length - 1];
    }
}
