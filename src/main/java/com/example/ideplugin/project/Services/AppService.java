package com.example.ideplugin.project.Services;

import com.example.ideplugin.gui.form.UserForm;
import com.example.ideplugin.project.entities.DirectoryEntity;
import com.example.ideplugin.project.entities.Entity;
import com.example.ideplugin.project.entities.FileEntity;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public final class AppService {

    DirectoryEntity directory;
    Project project;

    List<Entity> entities;

    public AppService(){
        directory = new DirectoryEntity(null, System.getProperty("user.home"));
    }

    public void clickEvent(){

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

    public List<Entity> getDirectory(String dirPath) throws IOException {
        List<Entity> entities = new ArrayList<>();
        File dir = new File(dirPath);
        if (!dir.isDirectory()){
            // todo: display failure form
            return null;
        }
        List<String> arrFiles = Arrays.asList(Objects.requireNonNull(dir.list()));
        for (String s : arrFiles){
            if (Files.isDirectory(Paths.get(s))){
                DirectoryEntity file = new DirectoryEntity(s);
            }
            FileEntity file = new FileEntity(dirPath + "\\" + s);
            entities.add(file);
        }

        return entities;
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

    public void display(Project project) throws IOException {
        // todo: implement choice of the form
        this.project = project;
        entities = this.getDirectory(directory.getAbsolutePath());

        UserForm.main(entities);
    }

    public void displayUserForm(String path) throws IOException {
        DirectoryEntity dir = new DirectoryEntity(directory.getAbsolutePath(), path);
        this.directory = dir;
        entities = this.getDirectory(directory.getAbsolutePath());
        UserForm.main(entities);
    }

    private void displayFailureForm(){
        //todo: implement
    }
}
