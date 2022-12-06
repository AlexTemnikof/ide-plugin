package com.example.ideplugin.project;

import com.example.ideplugin.gui.UserForm;
import com.intellij.openapi.components.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public final class AppService {

    public void findAndAddFile(String path){
        File file = new File(path);

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

    public void display(){
        UserForm.main();
    }
}
