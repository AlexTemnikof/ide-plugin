package com.example.ideplugin.project;

import com.example.ideplugin.gui.UserForm;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public final class AppService {

    public void findAndAddFile(String path, Project project){
        File file = new File(path);
        if (!file.exists()){
            return;
        }
        String name = file.getName();
        String d = project.getBasePath();
        String s = d + "\\resources\\" + name;
        file.renameTo(new File(d + "/resources/" + name));
    }

    public BufferedImage getRandomBackground(){
        List<BufferedImage> lst = new ArrayList<>();
        for (int i = 1; i < 12; i++){
            try {
                lst.add(ImageIO.read(Objects.requireNonNull(AppService.class
                        .getResource("/images/image" + i + ".jpg"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Random random = new Random();
        int randNumber = random.nextInt(lst.size());
        return lst.get(randNumber);
    }

    public void getRandomGif(){

    }

    public void display(Project project){
        UserForm.main(project);
    }
}
