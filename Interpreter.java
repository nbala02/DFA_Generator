/*
 * DFA Interpreter
 * April 30, 2019
 */

/**
 *
 * @author Neha Bala
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {
	// Variables
	int currState = 0;
	int currCharacter = 0;
	String startState = "S";
   	String finalState = "F";
    	String readString;
    
    	ArrayList<String> inputTransitionTable = new ArrayList<String>();
    
	// Method #1: Read the Transition Table file 
	public String transitionTable(String tablePath) {
		// Determine whether the file path is valid
		Scanner readTableFile = null;
    		try {
    			readTableFile = new Scanner(new File(tablePath));
    		}
    		catch (FileNotFoundException e){
    			System.out.println("File Not Found!");
    			System.exit(0);
    		}
		
		// Iterate through the text file and add each line into an ArrayList
		String readTable;
		while (readTableFile.hasNext()) {
			readTable = readTableFile.nextLine();
			inputTransitionTable.add(readTable);
		}
		
		return tablePath;
	}
	
	
	// Method #2: Read the String file
	public String stringInput(String stringPath) {
		// Determine whether the file path is valid
    		Scanner readStringFile = null;
    		try {
    			readStringFile = new Scanner(new File(stringPath));
    		}
    		catch (FileNotFoundException e){
    			System.out.println("File Not Found!");
    			System.exit(0);
    		}
		
		// Iterate through the file and store the string in the "readString" variable
		readString = readStringFile.nextLine();
		
		return stringPath;
	}
	
	
	// Method #3: Interprete whether the string is valid or invalid for the DFA
	public void dfaInterpreter() {
		//Calculate the number of states in your transition table and create new array to hold the states
        	int inputStates = (inputTransitionTable.size()) - 1;
        	String [] states = new String [inputStates];
		
        	//Retrieve the state labels from the transition table and add them into the states array
        	for (int i = 0; i < inputStates; i++){
            		char state = inputTransitionTable.get(i+1).charAt(0);
            		String calculateStates = String.valueOf(state);
            		states[i] = calculateStates;
        	}
        
        	//Calculate the number of characters in your transition table and create new array to hold the characters
        	String terminals = inputTransitionTable.get(0);
        	int inputCharacters = terminals.length();
        	String [] characters = new String [inputCharacters];
        
        	//Retrieve the characters in the alphabet from the transition table and add them into the characters array
        	for (int i = 0; i < inputCharacters; i++){
            		char character = inputTransitionTable.get(0).charAt(i);
            		String calculateCharacters = String.valueOf(character);
            		characters[i] = calculateCharacters;
        	}
        
        	//Create double array for transition table and add the number of states and the number of characters
        	String [][] transitionTable = new String [inputStates][inputCharacters];
        
        	//Determine the start state and final state based on 'S' or 'F'
        	int index = 1;
        	while (index < inputTransitionTable.size()){
            		if (inputTransitionTable.get(index).length() > (inputCharacters+1)){
                		char determineStates = inputTransitionTable.get(index).charAt(inputTransitionTable.get(index).length() - 1);
                		String dfaState = String.valueOf(determineStates);
				
				// The first character in index with 'S' is the start state
                		if (dfaState.equalsIgnoreCase("S")){
                    			char start = inputTransitionTable.get(index).charAt(0);
                    			String initial = String.valueOf(start);
                    			startState = initial; 
                		}
				// The first character in index with 'F' is the final state
                		else if (dfaState.equalsIgnoreCase("F")){
                    			char finish = inputTransitionTable.get(index).charAt(0);
                    			String accept = String.valueOf(finish);
                    			finalState = accept;
                		}
            		}
            		index++;
        	}
        
        	// Transitions on the characters
        	for(int i = 0; i < inputStates; i++){
            		String transition = inputTransitionTable.get(i + 1);
            		// Determine the transitions at each index in the input transition table array
            		for(int j = 0; j < inputCharacters; j++){
                		char currentinput = transition.charAt(j + 1);
                		String transitions = String.valueOf(currentinput);
                		transitionTable [i][j] = transitions;
            		}	
        	}
        
		// Processing string input file
        	String [] stringInput = new String [readString.length()];
		//Process each character in the input string and add the input string into the array 
        	for (int i = 0; i < readString.length(); i++){
            		char process = readString.charAt(i);
            		String processString = String.valueOf(process);
            		stringInput[i] = processString;
        	}
        
        	//Determine if DFA accepts the string or not
        	int i = 0;
        	while (i < stringInput.length){
            		String determineString = stringInput[i]; 
            
            		int j = 0; 
            		while (j < inputStates){
				// Determine the current state based on whether the character in the array states equals start state
                		if (states[j].equals(startState)){ 
                    			currState = j;
                		}
                		j++;
            		}
            
		    	int k = 0;
		    	while (k < inputCharacters){
				// Determines the current character based on whether the character in the array equals the string input
				if (characters[k].equals(determineString)){
			    		currCharacter = k;
				}
				k++;
		    	}
            
			// The determined current state and determined current characters are entered into the transition table and assigned to start state
            		startState = transitionTable[currState][currCharacter];
            		if(i == (stringInput.length-1)){
				// When the string is done transitioning through the DFA it is determined whether it should be accepted or rejected
                		if (finalState.equals(startState)){
                    			System.out.println("\n" + "Yes, the string is accepted.");
                		}
                		else{
                    			System.out.println("\n" + "No, the string is not accepted.");
                		}
            		}
            		i++;
        	}
	}
}
