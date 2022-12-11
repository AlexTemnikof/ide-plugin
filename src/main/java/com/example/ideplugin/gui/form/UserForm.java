package com.example.ideplugin.gui.form;

import com.example.ideplugin.gui.tools.ButtonCreate;
import com.example.ideplugin.project.Services.AppService;
import com.example.ideplugin.project.entities.FileEntity;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;

import java.awt.* ;
import javax.swing.* ;
import java.util.List;

public class UserForm extends JFrame {

    //todo: implement
    public UserForm() {
        super();
        setTitle( "Select File" );
        setContentPane(new MyPanel());

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

    public static void main(Project project, List<FileEntity> list) {
        List<JButton> buttons = ButtonCreate.createButton(list);
        new UserForm();
    }
}
