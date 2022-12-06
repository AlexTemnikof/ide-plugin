package com.example.ideplugin.project;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class ShowAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (ApplicationManager.getApplication().isUnitTestMode()){
            return;
        }
        AppService service = ApplicationManager.getApplication().getService(AppService.class);
        Project project = e.getProject();
        service.display(project);
    }

    @Override
    public boolean isDumbAware(){
        return false;
    }
}
