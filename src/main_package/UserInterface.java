/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_package;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author claud
 */
public class UserInterface extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int width = (int)(screenSize.getWidth()*0.9);
    public final int height = (int)(screenSize.getHeight()*0.7);
    
    /*public double width = screenSize.getWidth();
    public double height = screenSize.getHeight();*/
    
    @Override
    public int getWidth(){
        return (this.width);
    }
    
    @Override
    public int getHeight(){
        return (this.height);
    }
    
    public UserInterface() {

        /*
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        width = (int)(screenSize.getWidth()*0.9);
        height = (int)(screenSize.getHeight()*0.7);*/
        
        add(new Board());//con questo metodo inserisco una Board al centro del contenitore JFrame
        setTitle("T-Run");//setto il titolo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setto l'operazione di default da eseguire quando chiudo la finestra

        setSize(width, height);
        setLocationRelativeTo(null);//in questo modo posiziono il JFrame al centro dello schermo
        setVisible(true);//setto JFrame visibile
        setResizable(false);//dimensioni JFrame non modificabili
    }

    public static void main(String[] args) {
   
        /*
        javax.swing.SwingUtilities.invokeLater(() -> {
            UserInterface gameInterface = new UserInterface();
        });*/
        
        UserInterface gameInterface = new UserInterface();
        
        System.out.println(gameInterface.width);
        System.out.println(gameInterface.height);
    }

}
