/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.awt.image.BufferedImage;
import utility.*;

/**
 *
 * @author Angela
 */
public class Resources { //singleton
    
    private static Resources instance = null;
    
    private final BufferedImage backgroundImage;
    private final BufferedImage homepageTitleImage;
    private final BufferedImage homepageBackgroundImage;
    private final BufferedImage jumpingImage;//immagine TRex stand colorato
    private final BufferedImage deadImage;//immagine TRex morto
    private final BufferedImage leftFootImage;//immagine TRex leftFoot
    private final BufferedImage rightFootImage;//immagine TRex rightFoot
    private final BufferedImage lowerHeadLeftFootImage;
    private final BufferedImage lowerHeadRightFootImage;

    private Resources() {
        
        backgroundImage = new Utility().create(getClass().getClassLoader().getResource("image/color/background3_small.jpg"));
        homepageTitleImage = new Utility().create(this.getClass().getClassLoader().getResource("image/HomePage/Title small.png"));
        homepageBackgroundImage = new Utility().create(this.getClass().getClassLoader().getResource("image/HomePage/sfondoHome.png"));
        jumpingImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand-colorato.png"));
        deadImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-big-eyes-colorato.png"));
        leftFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-left-up-colorato.png"));
        rightFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-right-up-colorato.png"));
        lowerHeadLeftFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-left-up-colorato.png"));
        lowerHeadRightFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-right-up-colorato.png"));
    
    }
    
    public static Resources instance(){
        if (instance == null)
            instance = new Resources();
        return instance;
    }

    public BufferedImage getJumpingImage() {
        return jumpingImage;
    }

    public BufferedImage getDeadImage() {
        return deadImage;
    }

    public BufferedImage getLeftFootImage() {
        return leftFootImage;
    }

    public BufferedImage getRightFootImage() {
        return rightFootImage;
    }

    public BufferedImage getLowerHeadLeftFootImage() {
        return lowerHeadLeftFootImage;
    }

    public BufferedImage getLowerHeadRightFootImage() {
        return lowerHeadRightFootImage;
    }  
    
    public BufferedImage getBackgroundImage(){
        return backgroundImage;
    }

    public BufferedImage getHomepageTitleImage() {
        return homepageTitleImage;
    }

    public BufferedImage getHomepageBackgroundImage() {
        return homepageBackgroundImage;
    }
}
