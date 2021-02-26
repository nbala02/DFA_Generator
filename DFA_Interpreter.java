/*
 * DFA Interpreter
 * April 30, 2019
 */

/**
 *
 * @author Neha Bala
 *
 */

import java.util.Scanner;

public class DFA_Interpreter {
    public static void main(String [] args){
    	Scanner tableFile = new Scanner(System.in);
    	Scanner stringFile = new Scanner(System.in);
    	
        // Enter the transition table file path 
    	System.out.println("Enter the transition table file path: ");
    	String tablePath = tableFile.next();
    	
        // Enter the input string file path
    	System.out.println("Enter the input string file path: ");
    	String stringPath = stringFile.next();
    	
        // Create an instance of the Interpreter class
    	Interpreter interpreter = new Interpreter();
        
        // Call the methods of the Interpreter class
    	interpreter.transitionTable(tablePath);
    	interpreter.stringInput(stringPath);
    	interpreter.dfaInterpreter();
    }
}
