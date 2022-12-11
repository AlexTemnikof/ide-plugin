package com.example.ideplugin.project.Services;

import com.example.ideplugin.gui.form.UserForm;
import com.example.ideplugin.project.entities.DirectoryEntity;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public final class AppService {

    DirectoryEntity directory;
    Project project;

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

    public List<FileEntity> getDirectory(String dirPath) throws IOException {
        List<FileEntity> entities = new ArrayList<>();
        File dir = new File(dirPath);
        if (!dir.isDirectory()){
            // todo: display failure form
            return null;
        }
        DirectoryEntity directoryEntity = new DirectoryEntity(directory.getAbsolutePath(), dirPath);
        List<String> filesInFolder = Files.walk(Paths.get(dirPath))
                .filter(Files::isRegularFile)
                .map(Path:: toString)
                .collect(Collectors.toList());
        for (String s : filesInFolder){
            FileEntity file = new FileEntity(s);
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

    public void display(Project project){
        // todo: implement choice of the form
        this.project = project;
    }
}
