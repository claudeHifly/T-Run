/*
 * The purpose of this class is to load the old scores from a file, add the new score, show the scoreboard and save all in the file again.
 */
package scoreboard;

import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author G8
 */
public class Scoreboard {

    private static TreeSet<Record> scoreboard = new TreeSet<>();
    private static File file;
    private static final String name = null;

    /**
     * This method is used to read the file that containes the scores. The
     * scores read are added into a TreeSet structure.
     */
    public static TreeSet<Record> readFromScoreFile() {
        file = new File("score.txt");
        Scanner sc;
        String[] data = new String[2];
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                data = sc.nextLine().split(",");
                scoreboard.add(new Record(data[0], Integer.parseInt(data[1])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return scoreboard;
    }

    /**
     * This method is used to save the scores on the file.
     */
    private static void saveOnScoreFile() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file, "UTF-8");
            int size = scoreboard.size();
            if (size > 0) {
                int i = 0;
                Iterator it = scoreboard.iterator();
                writer.write("");                                   //overwrite the file
                while (it.hasNext() && i < 10) {
                    Record tmp = (Record) it.next();
                    writer.println(tmp.name + "," + tmp.score);     //append on the file
                    i++;
                }
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        } finally {
            writer.close();
        }
    }

    /**
     * This method is used to add to the TreeSet the new score and to show the
     * scoreboard in the ScorePanel JPanel.
     */
    public static void loadScores(String name, int score, Graphics g) {
        scoreboard.add(new Record(name, score));
        scoreboard = readFromScoreFile();
        int xPosFirstColumn = (int) (ScoreUserInterface.width * 0.20);
        int xPosSecondColumn = (int) (ScoreUserInterface.width * 0.65);
        int yPos = (int) (ScoreUserInterface.height * 0.30);
        int yPosOffset = (int) (ScoreUserInterface.height * 0.06);
        g.drawString("Name:", xPosFirstColumn, yPos);
        g.drawString("Score:", xPosSecondColumn, yPos);
        yPos += yPosOffset;
        yPosOffset = (int) (ScoreUserInterface.height * 0.05);
        int size = scoreboard.size();
        int i = 0;
        Iterator it = scoreboard.iterator();
        while (it.hasNext() && i < 10) {
            Record tmp = (Record) it.next();
            g.drawString("" + tmp.name, xPosFirstColumn, yPos);
            g.drawString("" + tmp.score, xPosSecondColumn, yPos);
            yPos += yPosOffset;
            i++;
        }
        saveOnScoreFile();
    }

    /**
     * This method returns the lowest score of the scoreboard, that is the last
     * entry of the TreeSet.
     */
    public static int getLowest() {
        if (scoreboard.size() > 0) {
            if (scoreboard.size() <= 10) {
                return scoreboard.last().score;
            } else {
                int i = 0;
                Iterator it = scoreboard.iterator();
                while (it.hasNext() && i < 10) {
                    Record tmp = (Record) it.next();
                    i++;
                }
                return ((Record) it.next()).score;
            }
        } else {
            return 0;
        }
    }

    /**
     * This private nested class is used to save the name and the score of the
     * player in order to build the scoreboard.
     */
    private static class Record implements Comparable<Record> {

        private String name;
        private int score;

        Record(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Record o) {                //Descending order
            if (this.score > o.score) {
                return -1;
            } else if (this.score < o.score) {
                return +1;
            } else {
                return this.name.compareTo(o.name) * -1;
            }
        }
    }

    public static TreeSet<Record> getScoreboard() {
        return scoreboard;
    }

    private Scoreboard() {
    }

}
