package com.example.ideplugin.project.core;

import com.example.ideplugin.project.Services.AppService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ShowAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (ApplicationManager.getApplication().isUnitTestMode()){
            return;
        }
        AppService service = ApplicationManager.getApplication().getService(AppService.class);
        Project project = e.getProject();
        try {
            service.display(project);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isDumbAware(){
        return false;
    }
}
