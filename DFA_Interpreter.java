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
    	
    	System.out.println("Enter the transition table file path: "); // /Users/nehabala/Desktop/transition_table.txt
    	String tablePath = tableFile.next();
    	
    	System.out.println("Enter the string file path: "); // /Users/nehabala/Desktop/valid_string.txt
    	String stringPath = stringFile.next();
    	
    	Interpreter interpreter = new Interpreter();
    	interpreter.transitionTable(tablePath);
    	interpreter.stringInput(stringPath);
    	interpreter.dfaInterpreter();
    }
}