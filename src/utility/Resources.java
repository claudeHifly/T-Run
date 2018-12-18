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
    
    // IMMAGINI HOMESCREEN
    private final BufferedImage homepageTitleImage;
    private final BufferedImage homepageBackgroundImage;
    private final BufferedImage homepageFootprintImage;
    // SCOREBOARD
    private final BufferedImage scoreboardBackgroundImage;    
    
    // IMMAGINI B/N
    // OSTACOLI
    private final BufferedImage cactus1;
    private final BufferedImage cactus2;
    private final BufferedImage cactus3;
    private final BufferedImage cactus4;
    private final BufferedImage cactus5;
    private final BufferedImage empty;
    // DINO
    private final BufferedImage dinoBelowLeftUp;
    private final BufferedImage dinoBelowRightUp;
    private final BufferedImage dinoBigEyes;//immagine TRex stand colorato
    private final BufferedImage dinoLeftUp;//immagine TRex morto
    private final BufferedImage dinoRightUp;
    private final BufferedImage dinoStand;
    // GROUND
    private final BufferedImage ground1;
    private final BufferedImage ground2;
    private final BufferedImage ground3;
    private final BufferedImage groundCanyon;
    // BACKGROUND
    private final BufferedImage background;
    // UCCELLO
    private final BufferedImage bird1;
    private final BufferedImage bird2;
    // OSSA
    private final BufferedImage bone;
    private final BufferedImage boneSpecial;
    // POWER-UP
    private final BufferedImage explosion;
    private final BufferedImage aura;
    private final BufferedImage biggerAura;
    private final BufferedImage ham;
    private final BufferedImage mulBanner1;
    private final BufferedImage mulBanner2;
    private final BufferedImage mulScore;
    private final BufferedImage pepper;
    
    // IMMAGINI COLORATE
    // OSTACOLI
    private final BufferedImage cactus1Col;
    private final BufferedImage cactus2Col;
    private final BufferedImage cactus3Col;
    private final BufferedImage cactus4Col;
    private final BufferedImage cactus5Col;
    // DINO
    private final BufferedImage dinoBelowLeftUpCol;
    private final BufferedImage dinoBelowRightUpCol;
    private final BufferedImage dinoBigEyesCol;//immagine TRex stand colorato
    private final BufferedImage dinoLeftUpCol;//immagine TRex morto
    private final BufferedImage dinoRightUpCol;
    private final BufferedImage dinoStandCol;
    // GROUND
    private final BufferedImage ground1Col;
    private final BufferedImage ground2Col;
    private final BufferedImage ground3Col;
    private final BufferedImage groundCanyonCol;
    // BACKGROUND
    private final BufferedImage backgroundCol;
    // UCCELLO
    private final BufferedImage bird1Col;
    private final BufferedImage bird2Col;
    // OSSA
    private final BufferedImage boneCol;
    private final BufferedImage boneSpecialCol;
    // POWER-UP
    private final BufferedImage explosionCol;
    private final BufferedImage auraCol;
    private final BufferedImage biggerAuraCol;
    private final BufferedImage hamCol;
    private final BufferedImage mulBanner1Col;
    private final BufferedImage mulBanner2Col;
    private final BufferedImage mulScoreCol;
    private final BufferedImage pepperCol;
    
    private final BufferedImage score1Col;
    private final BufferedImage score2Col;
    private final BufferedImage score5Col;
    private final BufferedImage score10Col;
    private final BufferedImage score20Col;
    private final BufferedImage score50Col;
    private final BufferedImage score100Col;
    
    private Resources() {
        
        //IMMAGINI HOMESCREEN
        homepageTitleImage = Utility.instance().instance().create(this.getClass().getClassLoader().getResource("image/HomePage/Title small.png"));
        homepageBackgroundImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/HomePage/sfondoHome.png"));
        homepageFootprintImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/HomePage/Footprint small.png"));
        
        // SCOREBOARD
        scoreboardBackgroundImage = Utility.instance().create(this.getClass().getClassLoader().getResource("image/scoreboard/scoreScreen1.jpg"));
       
        
        // IMMAGINI BN
        cactus1 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Cactus-1.png"));
        cactus2 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Cactus-2.png"));
        cactus3 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Cactus-3.png"));
        cactus4 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Cactus-4.png"));
        cactus5 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Cactus-5.png"));
        empty = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/empty.png"));
        // DINO
        dinoBelowLeftUp = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-below-left-up.png"));
        dinoBelowRightUp = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-below-right-up.png"));
        dinoBigEyes = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-big-eyes.png"));;//immagine TRex stand colorato
        dinoLeftUp = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-left-up.png"));//immagine TRex morto
        dinoRightUp = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-right-up.png"));
        dinoStand = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Dino-stand.png"));
        // GROUND
        ground1 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Ground-1.png"));
        ground2 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Ground-2.png"));
        ground3 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/Ground-3.png"));
        groundCanyon = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/GroundCanyon.png"));
        // BACKGROUND
        background = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/background.png"));
        // UCCELLO
        bird1 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/bird1.png"));
        bird2 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/bird2.png"));
        // OSSA
        bone = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/bone.png"));
        boneSpecial = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/boneSpecial.png"));
        // POWER-UP
        explosion = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/explosion.png"));
        aura = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/aura.png"));
        biggerAura = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/biggerAura.png"));        
        ham = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/ham.png"));   
        mulBanner1 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/mulBanner1.png"));
        mulBanner2 = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/mulBanner2.png"));        
        mulScore = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/mulScore.png"));
        pepper = Utility.instance().create(this.getClass().getClassLoader().getResource("image/bn/pepper.png"));
        
        //IMMAGINI COLORATE
        cactus1Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Cactus-1.png"));
        cactus2Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Cactus-2.png"));
        cactus3Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Cactus-3.png"));
        cactus4Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Cactus-4.png"));
        cactus5Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Cactus-5.png"));
        // DINO
        dinoBelowLeftUpCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-left-up.png"));
        dinoBelowRightUpCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-below-right-up.png"));
        dinoBigEyesCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-big-eyes.png"));;//immagine TRex stand colorato
        dinoLeftUpCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-left-up.png"));//immagine TRex morto
        dinoRightUpCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-right-up.png"));
        dinoStandCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Dino-stand.png"));
        // GROUND
        ground1Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Ground-1.png"));
        ground2Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Ground-2.png"));
        ground3Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/Ground-3.png"));
        groundCanyonCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/GroundCanyon.png"));
        // BACKGROUND
        backgroundCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/background.jpg"));
        // UCCELLO
        bird1Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/bird1.png"));
        bird2Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/bird2.png"));
        // OSSA
        boneCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/bone.png"));
        boneSpecialCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/boneSpecial.png"));
        // POWER-UP
        explosionCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/explosion.png"));
        auraCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/aura.png"));
        biggerAuraCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/biggerAura.png"));        
        hamCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/ham.png"));   
        mulBanner1Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/mulBanner1.png"));
        mulBanner2Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/mulBanner2.png"));        
        mulScoreCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/mulScore.png"));
        pepperCol = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/pepper.png"));
        
        score1Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score1.png"));
        score2Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score2.png"));
        score5Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score5.png"));
        score10Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score10.png"));
        score20Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score20.png"));
        score50Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score50.png"));
        score100Col = Utility.instance().create(this.getClass().getClassLoader().getResource("image/color/score100.png"));
        
    }
    
    public static Resources instance(){
        if (instance == null)
            instance = new Resources();
        return instance;
    }

    public static Resources getInstance() {
        return instance;
    }

    public BufferedImage getHomepageTitleImage() {
        return homepageTitleImage;
    }

    public BufferedImage getHomepageBackgroundImage() {
        return homepageBackgroundImage;
    }
    
    public BufferedImage getHomepageFootprintImage(){
        return homepageFootprintImage;
    }

    public BufferedImage getScoreboardBackgroundImage() {
        return scoreboardBackgroundImage;
    }

    public BufferedImage getCactus() {
        int picked = (int) (Math.random() * 4 + 1);
        switch(picked){
            case 1:
                return this.cactus1;
            case 2:
                return this.cactus2;
            case 3:
                return this.cactus3;
            case 4:
                return this.cactus4;
            case 5:
                return this.cactus5;
        }
        return this.cactus2;
    }

    public BufferedImage getDinoBelowLeftUp() {
        return dinoBelowLeftUp;
    }

    public BufferedImage getDinoBelowRightUp() {
        return dinoBelowRightUp;
    }

    public BufferedImage getDinoBigEyes() {
        return dinoBigEyes;
    }

    public BufferedImage getDinoLeftUp() {
        return dinoLeftUp;
    }

    public BufferedImage getDinoRightUp() {
        return dinoRightUp;
    }

    public BufferedImage getDinoStand() {
        return dinoStand;
    }

    public BufferedImage getGround() {
        int picked = (int) (Math.random() * 2 + 1);
        switch(picked){
            case 1:
                return this.ground1;
            case 2:
                return this.ground2;
            case 3:
                return this.ground3;
        }
        return this.ground1;
    }

    public BufferedImage getGroundCanyon() {
        return groundCanyon;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public BufferedImage getBird1() {
        return bird1;
    }

    public BufferedImage getBird2() {
        return bird2;
    }

    public BufferedImage getBone() {
        return bone;
    }

    public BufferedImage getBoneSpecial() {
        return boneSpecial;
    }

    public BufferedImage getExplosion() {
        return explosion;
    }

    public BufferedImage getAura() {
        return aura;
    }

    public BufferedImage getBiggerAura() {
        return biggerAura;
    }

    public BufferedImage getHam() {
        return ham;
    }

    public BufferedImage getMulBanner1() {
        return mulBanner1;
    }

    public BufferedImage getMulBanner2() {
        return mulBanner2;
    }

    public BufferedImage getMulScore() {
        return mulScore;
    }

    public BufferedImage getPepper() {
        return pepper;
    }

    public BufferedImage getCactusCol() {
        int picked = (int) (Math.random() * 4 + 1);
        switch(picked){
            case 1:
                return this.cactus1Col;
            case 2:
                return this.cactus2Col;
            case 3:
                return this.cactus3Col;
            case 4:
                return this.cactus4Col;
            case 5:
                return this.cactus5Col;
        }
        return this.cactus2Col;
    }

    public BufferedImage getDinoBelowLeftUpCol() {
        return dinoBelowLeftUpCol;
    }

    public BufferedImage getDinoBelowRightUpCol() {
        return dinoBelowRightUpCol;
    }

    public BufferedImage getDinoBigEyesCol() {
        return dinoBigEyesCol;
    }

    public BufferedImage getDinoLeftUpCol() {
        return dinoLeftUpCol;
    }

    public BufferedImage getDinoRightUpCol() {
        return dinoRightUpCol;
    }

    public BufferedImage getDinoStandCol() {
        return dinoStandCol;
    }

    public BufferedImage getGroundCol() {
        int picked = (int) (Math.random() * 2 + 1);
        switch(picked){
            case 1:
                return this.ground1Col;
            case 2:
                return this.ground2Col;
            case 3:
                return this.ground3Col;
        }
        return this.ground1Col;
    }

    public BufferedImage getGroundCanyonCol() {
        return groundCanyonCol;
    }

    public BufferedImage getBackgroundCol() {
        return backgroundCol;
    }

    public BufferedImage getBird1Col() {
        return bird1Col;
    }

    public BufferedImage getBird2Col() {
        return bird2Col;
    }

    public BufferedImage getBoneCol() {
        return boneCol;
    }

    public BufferedImage getBoneSpecialCol() {
        return boneSpecialCol;
    }

    public BufferedImage getExplosionCol() {
        return explosionCol;
    }

    public BufferedImage getAuraCol() {
        return auraCol;
    }

    public BufferedImage getBiggerAuraCol() {
        return biggerAuraCol;
    }

    public BufferedImage getHamCol() {
        return hamCol;
    }

    public BufferedImage getMulBanner1Col() {
        return mulBanner1Col;
    }

    public BufferedImage getMulBanner2Col() {
        return mulBanner2Col;
    }

    public BufferedImage getMulScoreCol() {
        return mulScoreCol;
    }

    public BufferedImage getPepperCol() {
        return pepperCol;
    }

    public BufferedImage getEmpty() {
        return empty;
    }

    public BufferedImage getScore1Col() {
        return score1Col;
    }

    public BufferedImage getScore2Col() {
        return score2Col;
    }

    public BufferedImage getScore5Col() {
        return score5Col;
    }

    public BufferedImage getScore10Col() {
        return score10Col;
    }

    public BufferedImage getScore20Col() {
        return score20Col;
    }

    public BufferedImage getScore50Col() {
        return score50Col;
    }

    public BufferedImage getScore100Col() {
        return score100Col;
    }
    
    
    
}
