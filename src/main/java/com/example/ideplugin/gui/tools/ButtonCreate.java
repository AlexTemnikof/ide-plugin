package com.example.ideplugin.gui.tools;

import com.example.ideplugin.project.Services.AppService;
import com.example.ideplugin.project.entities.Entity;
import com.example.ideplugin.project.entities.FileEntity;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ButtonCreate {

    public static List<JButton> createButton(List<Entity> list){
        ImageIcon fileIcon = new ImageIcon("icons/fileIcon.jpg");
        Icon folderIcon = new ImageIcon("icons/folderIcon.jpg");
        List<JButton> buttons = new ArrayList<>();
        for (Entity f : list){
            JButton but = new JButton(f.getName(), fileIcon);
            f.setButton(but);
            buttons.add(but);
        }
        return buttons;
    }
}
