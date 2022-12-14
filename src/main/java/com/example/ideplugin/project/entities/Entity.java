package com.example.ideplugin.project.entities;

import javax.swing.*;

public abstract class Entity {
    public abstract String getName();

    public abstract String getAbsolutePath();

    public abstract JButton getButton();

    public abstract void setButton(JButton but);
}
