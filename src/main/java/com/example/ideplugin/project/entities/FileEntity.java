package com.example.ideplugin.project.entities;

import javax.swing.*;
import java.io.File;

public class FileEntity extends Entity {
    String absolutePath;
    String name;

    JButton button;

    public FileEntity(String absolutePath){
        if (absolutePath == null){
            // todo: implement new form
            return;
        }
        File file = new File(absolutePath);
        name = parseName(absolutePath);
        this.absolutePath = absolutePath;
    }

    public String getName(){
        return name;
    }

    public String getAbsolutePath(){
        return absolutePath;
    }

    public JButton getButton(){
        return button;
    }

    public void setButton(JButton but){
        button = but;
    }

    private String parseName(String absolutePath){
        String[] parts = absolutePath.split("\\\\");
        return parts[parts.length - 1];
    }
}
