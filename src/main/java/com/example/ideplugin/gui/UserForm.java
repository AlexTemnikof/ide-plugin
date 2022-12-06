package com.example.ideplugin.gui;

import com.example.ideplugin.project.AppService;
import com.intellij.openapi.application.ApplicationManager;

import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.* ;

import static javax.swing.BoxLayout.X_AXIS;

public class UserForm extends JFrame {

    JTextField typingArea;

    public UserForm() {
        super() ;
        setTitle( "Select File" );
        setContentPane(new MyPanel());
        typingArea = new JTextField();
        typingArea.setBounds(700, 500, 150, 27);
        JButton button = new JButton("Submit");
        button.setBounds(900, 500, 180, 27);
        button.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                // Execute when button is pressed
                typingArea.getText();
            }
        });
        this.add(typingArea);
        this.add(new JLabel());
        this.add(button);
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

    public static void main() {
        new UserForm();
    }
}
