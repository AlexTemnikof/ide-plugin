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

    private String parseName(String path){
        // todo: implement
        return "";
    }
}
