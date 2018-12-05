/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author claud
 */
public class Cactus extends Obstacle{
    

    public Cactus(int x, int y) {
        super(x, y, "src/image/old/Cactus-" + (int) (Math.random() * 4 + 1) + "-colorato.png");

        //this.setImage(new Utility().create(path));
        //this.setY(y-this.getImage().getHeight());
        
        //outline = new ImageOutline(super.getImage());
        //this.setCollider(new Area(outline.getOutline(super.getImage())));
        //collider.add(new Area(new Rectangle(super.getX(), super.getY(), super.getImage().getWidth(), super.getImage().getHeight())));
    }
            
    
//    public static void main(String[] args) throws Exception {
//        Cactus cactus = new Cactus(0, 0);
//        
//        JFrame f = new JFrame("Image Outline");
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.add(cactus.outline.getGui());
//        f.pack();
//        f.setResizable(false);
//        f.setLocationByPlatform(true);
//        f.setVisible(true);
//    }
}
