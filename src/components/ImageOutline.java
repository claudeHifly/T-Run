/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

/**
 *
 * @author sivoc
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/* Gain the outline of an image for further processing. */
class ImageOutline {

    private BufferedImage image;

    private TwoToneImageFilter twoToneFilter;
    private BufferedImage imageTwoTone;
    private JLabel labelTwoTone;

    private BufferedImage imageOutline;
    private Area areaOutline = null;
    private JLabel labelOutline;

    private JLabel targetColor;
    private JSlider tolerance;

    private JProgressBar progress;
    private SwingWorker sw;

    public ImageOutline(BufferedImage image) {
        this.image = image;
        imageTwoTone = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB);
    }

    public void drawOutline() {
        if (areaOutline != null) {
            Graphics2D g = imageOutline.createGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, imageOutline.getWidth(), imageOutline.getHeight());

            g.setColor(Color.RED);
            g.setClip(areaOutline);
            g.fillRect(0, 0, imageOutline.getWidth(), imageOutline.getHeight());
            g.setColor(Color.BLACK);
            g.setClip(null);
            g.draw(areaOutline);

            g.dispose();
        }
    }

    public Area getOutline(Color target, BufferedImage bi) {
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        int targetRGB = target.getRGB();
        for (int xx = 0; xx < bi.getWidth(); xx++) {
            for (int yy = 0; yy < bi.getHeight(); yy++) {
                if (bi.getRGB(xx, yy) == targetRGB) {
                    if (cont) {
                        gp.lineTo(xx, yy);
                        gp.lineTo(xx, yy + 1);
                        gp.lineTo(xx + 1, yy + 1);
                        gp.lineTo(xx + 1, yy);
                        gp.lineTo(xx, yy);
                    } else {
                        gp.moveTo(xx, yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }
    
    public Area getOutline(BufferedImage bi) {
        // construct the GeneralPath
        GeneralPath gp = new GeneralPath();

        boolean cont = false;
        for (int xx = 0; xx < bi.getWidth(); xx++) {
            for (int yy = 0; yy < bi.getHeight(); yy++) {
                if (bi.getRGB(xx, yy) >> 24 != 0x00) {
                    if (cont) {
                        gp.lineTo(xx, yy);
                        gp.lineTo(xx, yy + 1);
                        gp.lineTo(xx + 1, yy + 1);
                        gp.lineTo(xx + 1, yy);
                        gp.lineTo(xx, yy);
                    } else {
                        gp.moveTo(xx, yy);
                    }
                    cont = true;
                } else {
                    cont = false;
                }
            }
            cont = false;
        }
        gp.closePath();

        // construct the Area from the GP & return it
        return new Area(gp);
    }

    public JPanel getGui() {
        JPanel images = new JPanel(new GridLayout(2, 2, 2, 2));
        JPanel gui = new JPanel(new BorderLayout(3, 3));

        JPanel originalImage = new JPanel(new BorderLayout(2, 2));
        final JLabel originalLabel = new JLabel(new ImageIcon(image));
        targetColor = new JLabel("Target Color");
        targetColor.setForeground(Color.RED);
        targetColor.setBackground(Color.WHITE);
        targetColor.setBorder(new LineBorder(Color.BLACK));
        targetColor.setOpaque(true);

        JPanel controls = new JPanel(new BorderLayout());
        controls.add(targetColor, BorderLayout.WEST);
        originalLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                originalLabel.setCursor(
                        Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                originalLabel.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent me) {
                int x = me.getX();
                int y = me.getY();

                Color c = new Color(image.getRGB(x, y));
                targetColor.setBackground(c);

                updateImages();
            }
        });
        originalImage.add(originalLabel);

        tolerance = new JSlider(
                JSlider.HORIZONTAL,
                0,
                255,
                104
        );
        tolerance.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent ce) {
                updateImages();
            }
        });
        controls.add(tolerance, BorderLayout.CENTER);
        gui.add(controls, BorderLayout.NORTH);

        images.add(originalImage);

        labelTwoTone = new JLabel(new ImageIcon(imageTwoTone));

        images.add(labelTwoTone);

        images.add(new JLabel("Smoothed Outline"));

        imageOutline = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_INT_RGB
        );

        labelOutline = new JLabel(new ImageIcon(imageOutline));
        images.add(labelOutline);

        updateImages();

        progress = new JProgressBar();

        gui.add(images, BorderLayout.CENTER);
        gui.add(progress, BorderLayout.SOUTH);

        return gui;
    }

    private void updateImages() {
        if (sw != null) {
            sw.cancel(true);
        }
        sw = new SwingWorker() {
            @Override
            public String doInBackground() {
                progress.setIndeterminate(true);
                adjustTwoToneImage();
                labelTwoTone.repaint();
                areaOutline = getOutline(Color.BLACK, imageTwoTone);

                drawOutline();

                return "";
            }

            @Override
            protected void done() {
                labelOutline.repaint();
                progress.setIndeterminate(false);
            }
        };
        sw.execute();
    }

    public void adjustTwoToneImage() {
        twoToneFilter = new TwoToneImageFilter(
                targetColor.getBackground(),
                tolerance.getValue());

        Graphics2D g = imageTwoTone.createGraphics();
        g.drawImage(image, twoToneFilter, 0, 0);

        g.dispose();
    }

    public static void main(String[] args) throws Exception {
        int size = 150;
        final BufferedImage outline
                = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = outline.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        g.setRenderingHint(
                RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Polygon p = new Polygon();
        p.addPoint(size / 2, size / 10);
        p.addPoint(size - 10, size - 10);
        p.addPoint(10, size - 10);
        Area a = new Area(p);

        Rectangle r = new Rectangle(size / 4, 8 * size / 10, size / 2, 2 * size / 10);
        a.subtract(new Area(r));

        int radius = size / 10;
        Ellipse2D.Double c = new Ellipse2D.Double(
                (size / 2) - radius,
                (size / 2) - radius,
                2 * radius,
                2 * radius
        );
        a.subtract(new Area(c));

        g.setColor(Color.BLACK);
        g.fill(a);

        ImageOutline io = new ImageOutline(outline);

        JFrame f = new JFrame("Image Outline");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(io.getGui());
        f.pack();
        f.setResizable(false);
        f.setLocationByPlatform(true);
        f.setVisible(true);
    }
}

class TwoToneImageFilter implements BufferedImageOp {

    Color target;
    int tolerance;

    TwoToneImageFilter(Color target, int tolerance) {
        this.target = target;
        this.tolerance = tolerance;
    }

    private boolean isIncluded(Color pixel) {
        int rT = target.getRed();
        int gT = target.getGreen();
        int bT = target.getBlue();
        int rP = pixel.getRed();
        int gP = pixel.getGreen();
        int bP = pixel.getBlue();
        return ((rP - tolerance <= rT) && (rT <= rP + tolerance)
                && (gP - tolerance <= gT) && (gT <= gP + tolerance)
                && (bP - tolerance <= bT) && (bT <= bP + tolerance));
    }

    public BufferedImage createCompatibleDestImage(
            BufferedImage src,
            ColorModel destCM) {
        BufferedImage bi = new BufferedImage(
                src.getWidth(),
                src.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        return bi;
    }

    public BufferedImage filter(
            BufferedImage src,
            BufferedImage dest) {

        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                Color pixel = new Color(src.getRGB(x, y));
                Color write = Color.BLACK;
                if (isIncluded(pixel)) {
                    write = Color.WHITE;
                }
                dest.setRGB(x, y, write.getRGB());
            }
        }

        return dest;
    }

    public Rectangle2D getBounds2D(BufferedImage src) {
        return new Rectangle2D.Double(0, 0, src.getWidth(), src.getHeight());
    }

    public Point2D getPoint2D(
            Point2D srcPt,
            Point2D dstPt) {
        // no co-ord translation
        return srcPt;
    }

    public RenderingHints getRenderingHints() {
        return null;
    }
}
