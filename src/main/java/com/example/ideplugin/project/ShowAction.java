package com.example.ideplugin.project;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;

public class ShowAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        if (ApplicationManager.getApplication().isUnitTestMode()){
            return;
        }
        AppService service = ApplicationManager.getApplication().getService(AppService.class);
        service.display();
    }

    @Override
    public boolean isDumbAware(){
        return false;
    }
}
