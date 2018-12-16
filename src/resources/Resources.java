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
    private final BufferedImage auraImage;
    private final BufferedImage biggerAuraImage;
    private final BufferedImage mulBanner1;
    private final BufferedImage mulBanner2;
    private final BufferedImage explosion;

    private Resources() {
        
        //IMMAGINI HOMESCREEN
        homepageTitleImage = new Utility().create(this.getClass().getClassLoader().getResource("image/HomePage/Title small.png"));
        homepageBackgroundImage = new Utility().create(this.getClass().getClassLoader().getResource("image/HomePage/sfondoHome.png"));
       
        
        //IMMAGINI BN
        /*
        backgroundImage = new Utility().create(getClass().getClassLoader().getResource("image/bn/background.png"));
        jumpingImage = new Utility().create(this.getClass().getClassLoader().getResource("image/bn/Dino-stand.png"));
        deadImage = new Utility().create(this.getClass().getClassLoader().getResource("image/bn/Dino-big-eyes.png"));
        leftFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/bn/Dino-left-up.png"));
        rightFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/bn/Dino-right-up.png"));
        lowerHeadLeftFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/bn/Dino-below-left-up.png"));
        lowerHeadRightFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/bn/Dino-below-right-up.png"));
        */
        
        
        //IMMAGINI COLORATE
        backgroundImage = new Utility().create(getClass().getClassLoader().getResource("image/color/background3_small.jpg"));
        jumpingImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand.png"));
        deadImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-big-eyes.png"));
        leftFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-left-up.png"));
        rightFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-right-up.png"));
        lowerHeadLeftFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-left-up.png"));
        lowerHeadRightFootImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-right-up.png"));
        auraImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/fire.png"));
        biggerAuraImage = new Utility().create(this.getClass().getClassLoader().getResource("image/color/biggerFire.png"));
        mulBanner1 = new Utility().create(this.getClass().getClassLoader().getResource("image/color/mulBanner1.png"));
        mulBanner2 = new Utility().create(this.getClass().getClassLoader().getResource("image/color/mulBanner2.png"));
        explosion = new Utility().create(this.getClass().getClassLoader().getResource("image/color/explosion.png"));
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

    public BufferedImage getAuraImage() {
        return auraImage;
    }

    public BufferedImage getBiggerAuraImage() {
        return biggerAuraImage;
    }

    public BufferedImage getMulBanner1() {
        return mulBanner1;
    }

    public BufferedImage getMulBanner2() {
        return mulBanner2;
    }

    public BufferedImage getExplosion() {
        return explosion;
    }
    
    
    
    
    
}
