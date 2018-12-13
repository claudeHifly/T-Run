/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author claud
 */

public class UserInterface extends JFrame { //Singleton

    public static int width = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()*0.8);    //dovrebbero essere final
    public static int height = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()*0.7);
    private static UserInterface instance = null;
    
    
    private UserInterface() {
        add(new Board());//con questo metodo inserisco una Board al centro del contenitore JFrame
        setTitle("T-Run");//setto il titolo
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//setto l'operazione di default da eseguire quando chiudo la finestra

        setSize(width, height);
        setLocationRelativeTo(null);//in questo modo posiziono il JFrame al centro dello schermo
        setResizable(false);//dimensioni JFrame non modificabili
    }
    
    public static UserInterface instance(){
        if (instance == null)
            instance = new UserInterface();
        return instance;
    }
    

//    public static void main(String[] args) {
//    
//        EventQueue.invokeLater(() -> {
//            UserInterface gameInterface = new UserInterface();
//            gameInterface.setVisible(true);
//        });
//        
//    }
}
