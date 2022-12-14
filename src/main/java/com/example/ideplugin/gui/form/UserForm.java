package com.example.ideplugin.gui.form;

import com.example.ideplugin.gui.tools.ButtonCreate;
import com.example.ideplugin.project.Services.AppService;
import com.example.ideplugin.project.entities.Entity;
import com.intellij.openapi.application.ApplicationManager;

import java.awt.* ;
import javax.swing.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserForm extends JFrame {

    //todo: implement
    public UserForm(List<JButton> buttons) {
        super();
        setTitle( "Select File" );
        setContentPane(new MyPanel());

        JButton backButton = new JButton("Назад");
        backButton.setBounds(100, 300, 300, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationManager.getApplication().getService(AppService.class).clickEvent();
                dispose();
            }
        });
        for (JButton but : buttons){
            but.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ApplicationManager.getApplication().getService(AppService.class).clickEvent(e.getSource());
                    dispose();
                }
            } );
        }
        int i = 100;
        for (JButton b : buttons){
            b.setBounds(600, i, 300, 50);
            this.add(b);
            i+= 50;
        }
        this.add(new JLabel());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible( true ) ;
    }

    public class MyPanel extends JComponent {
        private Image img ;
        public MyPanel() {
            img = ApplicationManager.getApplication().getService(AppService.class).getRandomBackground();
            if( img == null ) {
                System.out.println( "Image is null" );
                System.exit( 1 ) ;
            }
            if( img.getHeight(this) <= 0 || img.getWidth( this ) <= 0 ) {
                System.out.println( "Image width or height must be +ve" );
                System.exit( 2 ) ;
            }
        }
        public void drawBackground( Graphics g ) {
            int w = getWidth() ;
            int h = getHeight() ;
            int iw = img.getWidth( this ) ;
            int ih = img.getHeight( this ) ;
            for( int i = 0 ; i < w ; i+=iw ) {
                for( int j = 0 ; j < h ; j+= ih ) {
                    g.drawImage( img , i , j , this ) ;
                }
            }
        }
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBackground( g ) ;
        }
    }

    public static void main(List<JButton> buttons) {
        new UserForm(buttons);
    }
}
