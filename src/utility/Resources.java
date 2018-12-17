/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.image.BufferedImage;

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
    private final BufferedImage scoreboardBackgroundImage;

    private Resources() {
        
        //IMMAGINI HOMESCREEN
        homepageTitleImage = Utility.instance().instance().create(this.getClass().getClassLoader().getResource("image/HomePage/Title small.png"));
        homepageBackgroundImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/HomePage/sfondoHome.png"));
        scoreboardBackgroundImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/altro/scoreScreen1.jpg"));
       
        
        //IMMAGINI BN
        /*
        backgroundImage = Utility.instance().create(getClass().getClassLoader().getResource("image/bn/background.png"));
        jumpingImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-stand.png"));
        deadImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-big-eyes.png"));
        leftFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-left-up.png"));
        rightFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-right-up.png"));
        lowerHeadLeftFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-below-left-up.png"));
        lowerHeadRightFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-below-right-up.png"));
        */
        
        
        //IMMAGINI COLORATE
        backgroundImage = Utility.instance().create(getClass().getClassLoader().getResource("image/color/background3_small.jpg"));
        jumpingImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand.png"));
        deadImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-big-eyes.png"));
        leftFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-left-up.png"));
        rightFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-right-up.png"));
        lowerHeadLeftFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-left-up.png"));
        lowerHeadRightFootImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-right-up.png"));
        auraImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/fire.png"));
        biggerAuraImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/biggerFire.png"));
        mulBanner1 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/mulBanner1.png"));
        mulBanner2 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/mulBanner2.png"));
        explosion = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/explosion.png"));
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

    public BufferedImage getMulBanner1Image() {
        return mulBanner1;
    }

    public BufferedImage getMulBanner2Image() {
        return mulBanner2;
    }

    public BufferedImage getExplosion() {
        return explosion;
    }

    public BufferedImage getScoreboardBackgroundImage() {
        return scoreboardBackgroundImage;
    }
}