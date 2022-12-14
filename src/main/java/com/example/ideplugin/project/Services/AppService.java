package com.example.ideplugin.project.Services;

import com.example.ideplugin.gui.form.UserForm;
import com.example.ideplugin.gui.tools.ButtonCreate;
import com.example.ideplugin.project.entities.DirectoryEntity;
import com.example.ideplugin.project.entities.Entity;
import com.example.ideplugin.project.entities.FileEntity;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.project.Project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public final class AppService {

    private DirectoryEntity directory;
    private Project project;

    private List<Entity> entities;

    public AppService(){
        directory = new DirectoryEntity(null, System.getProperty("user.home"));
    }

    public void clickEvent(Object object){
        if (object instanceof JButton){
            JButton target = (JButton) object;
            Entity tmp = null;
            for (Entity e : entities){
                if (e.getButton().equals(target)) {
                    tmp = e;
                    break;
                }
            }
            if (tmp == null){
                this.displayFailureForm();
                return;
            }

            if (tmp.getClass() == FileEntity.class){
                try {
                    this.findAndAddFile(tmp.getAbsolutePath(), project);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                this.displaySuccessForm();
                return;
            }

            try {
                this.display(tmp.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            this.displayFailureForm();
            //todo: show failure form
        }
    }

    public void clickEvent(){
        try {
            this.display(directory.getParentDirPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        Files.copy(Paths.get(path), Paths.get(s));
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
            Entity file;
            if (Files.isDirectory(Paths.get(dirPath + "\\" + s))){
                file = new DirectoryEntity(dirPath + "\\" + s);
            }
            else {
                file = new FileEntity(dirPath + "\\" + s);
            }
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
        if (entities != null) {
            UserForm.main(ButtonCreate.createButton(entities));
        }
        else{
            this.displayFailureForm();
        }
    }

    public void display(String path) throws IOException {
        DirectoryEntity dir = new DirectoryEntity(directory.getAbsolutePath(), path);
        this.directory = dir;
        entities = this.getDirectory(directory.getAbsolutePath());
        if (entities != null) {
            UserForm.main(ButtonCreate.createButton(entities));
        }
        else{
            this.displayFailureForm();
        }
    }

    private void displayFailureForm(){
        //todo: implement
    }

    private void displaySuccessForm(){
        //todo: implement
    }
}
