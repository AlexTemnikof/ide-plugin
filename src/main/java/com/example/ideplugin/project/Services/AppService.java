package com.example.ideplugin.project.Services;

import com.example.ideplugin.gui.form.UserForm;
import com.example.ideplugin.project.entities.DirectoryEntity;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public final class AppService {

    DirectoryEntity directory;

    public AppService(){
        directory = new DirectoryEntity(null, System.getProperty("user.home"));
    }
    public void findAndAddFile(String path, Project project) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            return;
        }
        String name = file.getName();
        String d = project.getBasePath();
        File theDir = new File(d + "\\resources");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        String s = d + "\\resources\\" + name;
        Files.move(Paths.get(path), Paths.get(s));
    }

    public List<String> getDirectory(String directory){
        // todo: implement
        List<String> files = new ArrayList<>();
        return files;
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
