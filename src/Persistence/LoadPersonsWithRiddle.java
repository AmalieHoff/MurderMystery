/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import Business.LogBook;
import Business.Person;
import Business.PersonWithRiddle;
import Business.TextHandler;
import Business.Room;
import Business.Time;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kristian
 */
public class LoadPersonsWithRiddle extends ScenarioLoader {

    /**
     *
     * @param path
     * @param log
     * @param rooms_list
     * @param persons_list
     * @param printer
     * @param time
     */
    public LoadPersonsWithRiddle(String path, LogBook log, ArrayList<Room> rooms_list, ArrayList<Person> persons_list, TextHandler printer, Time time) {
        super(path, log, rooms_list, persons_list, printer, time);
        this.load();
    }

    /**
     *
     */
    @Override
    public void load() {
        File file = new File(path + "/" + "riddle persons.txt"); //Hold file of the riddles. riddles.txt should be placed in the root folder.
        Scanner scanner = null; //if the scanner can't load the file.

        try {
            scanner = new Scanner(file); // scanner for the file
        } catch (FileNotFoundException ex) {
            try {
                //if not such file exists create it.
                file.createNewFile();
            } catch (IOException ex1) {
                Logger.getLogger(LoadRooms.class.getName()).log(Level.SEVERE, null, ex1);
                return;
            }
        }
        while (scanner.hasNextLine()) {
            switch (scanner.nextLine()) {
                case "[Person]:":
                    Room room = this.getRoomByName(scanner.nextLine());
                    String name = scanner.nextLine();
                    String introMessage = Util.stringConvertSmaller(scanner.nextLine());
                    String correctAnswer = scanner.nextLine();
                    String wrongAnswer = scanner.nextLine();
                    int timeWin = Integer.parseInt(scanner.nextLine());
                    int timeLoss = Integer.parseInt(scanner.nextLine());
                    PersonWithRiddle riddlePerson = new PersonWithRiddle(name, correctAnswer, wrongAnswer, timeWin, timeLoss, time);
                    riddlePerson.setIntroMessage(introMessage);
                    room.addPersonWithRiddle(riddlePerson);
                    break;

                default:
                    break;
            }
        }
    }
}
