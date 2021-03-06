/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.Math.random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kristian
 */
public class Riddle {

    private static final ArrayList<Riddle> RIDDLES = new ArrayList(); // A database filled with all of the riddles. !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static String path;                                         // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private String question; //one question per riddle
    private String correctAnswer; //one answer per riddle
    private String[] wrongAnswers = new String[2]; //2 wrong answers pr riddle.

    private static void load() {

        File file = new File(path+"/riddles.txt"); //Hold file of the riddles. riddles.txt should be placed in the root folder.
        Scanner scanner = null; //if the scanner can't load the file.

        try {
            scanner = new Scanner(file); // scanner for the file
        } catch (FileNotFoundException ex) {
            try {
                //if not such file exists create it.
                file.createNewFile();
            } catch (IOException ex1) {
                Logger.getLogger(Riddle.class.getName()).log(Level.SEVERE, null, ex1);
                return;
            }
        }

        while (scanner.hasNext()) {
            Riddle temp = new Riddle(); //temporary object to hold the riddle. Will be put the in the RIDDLES database
            temp.question = scanner.nextLine(); //reads question
            temp.correctAnswer = scanner.nextLine(); //reads the one correct answer
            temp.wrongAnswers[0] = scanner.nextLine(); //wrong answers:
            temp.wrongAnswers[1] = scanner.nextLine();
            RIDDLES.add(temp); //put reference in RIDDLE database.
        }
    }

    /**
     *
     * @return
     */
    public static Riddle getRandomRiddle() {
        if (RIDDLES.isEmpty()) { //loads all the riddles, if not used before.
            load();
        }
        if (RIDDLES.isEmpty()) { //checks if there is placed any riddles in the riddles.txt file.
            throw new NullPointerException("No riddles are placed in the riddles.txt file...");
        }
        
        int random_index = (int) (random() * RIDDLES.size()); //get random index
        Riddle temp = RIDDLES.get(random_index); //get riddle from index.
        RIDDLES.remove(random_index); //removes the riddle from the list, so it won't be drawn twice on the same playthrough.
        
        return temp;  //returns a random riddle object
    }
    
    public static void setPath(String path) {
        Riddle.path = path;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @return the correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @return the wrongAnswers
     */
    public String[] getWrongAnswers() {
        return wrongAnswers;
    }
}
