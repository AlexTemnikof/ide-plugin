package com.example.ideplugin.gui.tools;

import com.example.ideplugin.project.Services.AppService;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.Service;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ButtonCreate {

    public List<JButton> createButton(){
        List<JButton> buttons = new ArrayList<>();
        List<String> names = ApplicationManager.getApplication().getService(AppService.class).getDirectory(System.getProperty("user.home"));
        for (String str : names){
            JButton but = new JButton(str);
            buttons.add(but);
        }
        return buttons;
    }
}
