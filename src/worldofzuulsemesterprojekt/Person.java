package worldofzuulsemesterprojekt;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Person {
    private final int       ID;
    private final String    name; //Name of the person
    private final boolean   isMurder; //Is the person the murderer?
    private final String    response; //Response when talked to.
    private boolean         hasBeenAsked;
    private final String    keyWords;
    private final String    accusationResponse;
    private final String    askName;
    private HashMap<Integer, String> questions;
    private HashMap<Integer, String> anwsers;
    public int chosenAnswer;
    private String          welcome;
    private Scanner         input = new Scanner(System.in);
    private LogBook         LogConnection;

    public Person(int ID, String name, boolean isMurder, String response, String keyWords, String accusationResponse, String askName, LogBook Log) {
        this.ID = ID;
        this.name = name;
        this.isMurder = isMurder;
        this.response = response;
        this.keyWords = keyWords;
        this.accusationResponse = accusationResponse;
        this.askName = askName;
        this.hasBeenAsked = false;
        this.questions = new HashMap<>();
        this.anwsers = new HashMap<>();
        this.chosenAnswer = 0;
        this.welcome = "";
        this.LogConnection = Log;
    }
    
    public Person(int ID, String name, String response, String keyWords, String accusationResponse, String askName, LogBook Log) {
        this.ID = ID;
        this.name = name;
        this.response = response;
        this.keyWords = keyWords;
        this.accusationResponse = accusationResponse;
        this.askName = askName;
        this.isMurder = false;
        this.hasBeenAsked = false;
        this.questions = new HashMap<>();
        this.anwsers = new HashMap<>();
        this.chosenAnswer = 0;
        this.welcome = "";
        this.LogConnection = Log;
    }

    /*
    Getter methods for the attributes!   
     */
    public String getName() {
        return this.name;
    }
    
    public boolean isAsked(){
        return this.hasBeenAsked;
    }
    
    public void setHasBeenAsked(boolean condition){
        this.hasBeenAsked = condition;
    }
    
    public int getID(){
        return this.ID;
    }
    
    public String getAskName(){
        return this.askName;
    }

    public boolean isMurder() {
        return this.isMurder;
    }

    public String getResponse() {
        return this.response;
    }
    
    public String getKeyWords(){
        return this.keyWords;
    }
    
    public String getAccusationResponse(){
        return this.accusationResponse;
    }
    
    public void setQuestions(String q1, String q2, String q3) {
        this.questions.put(1, q1);
        this.questions.put(2, q2);
        this.questions.put(3, q3);
        this.questions.put(4, "Goodbye. \n");
        
    }
    
    public void setAnswers(String a1, String a2, String a3) {
        this.anwsers.put(-1, "This is not a valid command, please type a number\n");
        this.anwsers.put(1, a1);
        this.anwsers.put(2, a2);
        this.anwsers.put(3, a3);
        this.anwsers.put(4, "Farewell! \n");
    }
    
    public void returnQuestions() {
        this.chosenAnswer = -1;
        System.out.println("Please choose one of the following questions:");
        System.out.println("1: " + this.questions.get(1));
        System.out.println("2: " + this.questions.get(2));
        System.out.println("3: " + this.questions.get(3));
        System.out.println("4: " + this.questions.get(4));
    }
    
    public int chosenAnswers() {
        return this.chosenAnswer;
    }
    
    public String conversation() {
        int key;
        String value;
        System.out.print(">");
        
        try {
            key = this.input.nextInt();
            if(key >= 5 || key <= 0){
                throw new IllegalArgumentException();
            }
        } catch(InputMismatchException exception) {
            //Print "sets key to -1"
            //when user put other than integer in the input
            key = -1;
            this.input.next();
        } catch (IllegalArgumentException err){
            key = -1;
        }
        value = this.anwsers.get(key);
        this.chosenAnswer = key;
        return value;
    }
    
    public void setWelcome(String welcome) {
        this.welcome = welcome;
    }
    
    public String getWelcome() {
        return this.welcome;
    }
}