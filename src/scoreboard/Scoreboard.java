/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author Angela
 */
public class Scoreboard {

    private static TreeSet<Record> scoreboard = new TreeSet<>();
    private static File file;
    private static String name = null;

    private static TreeSet<Record> readFromScoreFile(){
        file = new File("score.txt");
        Scanner sc;
        String[] data = new String[2];
        
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                data = sc.nextLine().split(",");
                scoreboard.add(new Record(data[0],Integer.parseInt(data[1])));
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return scoreboard;
    }
    
    private static void saveOnScoreFile(){
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file, "UTF-8");
            int size = scoreboard.size();
            
            if(size > 0){
                int i = 0;
                Iterator it = scoreboard.iterator();
                writer.write("");                           //sovrascrive il file
                while(it.hasNext() && i < 10){
                    Record tmp = (Record) it.next();
                    writer.println(tmp.name+","+tmp.score); //fa append() al file
                    i++;
                }
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            writer.close();
        }
}
    
    public static void loadScores(String name, int score, Graphics g){
        
        scoreboard.add(new Record(name, score));
        scoreboard = readFromScoreFile();

        int xPosFirstColumn = (int) (ScoreUserInterface.width*0.20);
        int xPosSecondColumn = (int) (ScoreUserInterface.width*0.65);
        int yPos = (int) (ScoreUserInterface.height*0.28);
        int yPosOffset = (int) (ScoreUserInterface.height*0.075);

        g.drawString("Name:", xPosFirstColumn, yPos);
        g.drawString("Score:", xPosSecondColumn, yPos);
        yPos += yPosOffset;
        
        
        yPosOffset = (int) (ScoreUserInterface.height*0.05);
        int size = scoreboard.size();
        int i=0;
        Iterator it = scoreboard.iterator();
        while(it.hasNext() && i<10){
            Record tmp = (Record) it.next();
            g.drawString(""+tmp.name, xPosFirstColumn, yPos);
            g.drawString(""+tmp.score, xPosSecondColumn, yPos);
            yPos += yPosOffset;
            i++;
        }
        
        saveOnScoreFile();
    }
    
    private static class Record implements Comparable<Record>{
        
        private String name;
        private int score;

        public Record(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Record o) { //Il return è fatto al contrario per ordinare in senso discendente
            if(this.score > o.score)
                return -1;
            else 
                if(this.score < o.score)
                    return +1;
            else
                return this.name.compareTo(o.name) * -1; 
        }
    }
}


