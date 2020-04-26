/*
 * April 30, 2019
 */

/**
 *
 * @author Neha Bala
 *
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class DFA_Interpreter {
    public static void main(String [] args){
        int currState = 0;
        int currCharacter = 0;
        String startState = "S";
        String finalState = "F";
        
        Scanner input = new Scanner(System.in);
        
        //Read transition table file with the scanner
        System.out.println("Enter your transition table file path name: ");
        String transitionFileName = input.next();
        Scanner readFile = null;
        System.out.println("Processing File " + transitionFileName + "\n");
        try{
            readFile = new Scanner(new File(transitionFileName));
        }
        catch(FileNotFoundException e){
            System.out.println("Error: Can't open file " + transitionFileName);
            System.exit(0);
        }
        
        //Read each line in transition table into arraylist then into an array
        ArrayList<String> inputTransitionTable = new ArrayList<String>();
        while(readFile.hasNextLine()){
            String line = readFile.nextLine(); 
            inputTransitionTable.add(line); //Each line read will be added to the arraylist
        }
        String [] readTransitionInput = inputTransitionTable.toArray(new String [inputTransitionTable.size()]);

        //Calculate the number of states in your transition table and create new array to hold the states
        int inputStates = (inputTransitionTable.size()) - 1;
        String [] states = new String [inputStates]; //Use the number of states as the size of the states array
        
        //Retrieve the state labels from the transition table and add them into the states array
        for (int i = 0; i < inputStates; i++){
            //Start from index 1 and get the first character at each index which is the states
            char state = inputTransitionTable.get(i+1).charAt(0);
            String calculateStates = String.valueOf(state);
            states[i] = calculateStates;
        }
        
        //Calculate the number of characters in your transition table and create new array to hold the characters
        String terminals = inputTransitionTable.get(0);
        int inputCharacters = terminals.length();
        String [] characters = new String [inputCharacters]; //Use the number of characters as the size of the characters array
        
        //Retrieve the characters in the alphabet from the transition table and add them into the characters array
        for (int i = 0; i < inputCharacters; i++){
            //At index 0 get the characters in the alphabet
            char character = inputTransitionTable.get(0).charAt(i);
            String calculateCharacters = String.valueOf(character);
            characters[i] = calculateCharacters;
        }
        
        //Create double array for transition table and add the number of states and the number of characters
        String [][] transitionTable = new String [inputStates][inputCharacters];
        
        //Determine the start state and final state based on 'S' or 'F'
        int index = 1;
        while (index < inputTransitionTable.size()){
            //At index 1 and all indexes after that, if the length is more than the characters in alphabet then get the initial and final state
            if (inputTransitionTable.get(index).length() > (inputCharacters+1)){
                char determineStates = inputTransitionTable.get(index).charAt(inputTransitionTable.get(index).length() - 1);
                String sfstates = String.valueOf(determineStates);
                if (sfstates.equalsIgnoreCase("S")){
                    char start = inputTransitionTable.get(index).charAt(0); //Get the first character in the string from the index with 'S'
                    String initial = String.valueOf(start);
                    startState = initial; //The first character in index with 'S' is the start state
                }
                else if (sfstates.equalsIgnoreCase("F")){
                    char finish = inputTransitionTable.get(index).charAt(0); //Get the first character in the string from the index with 'F'
                    String accept = String.valueOf(finish);
                    finalState = accept; //The first character in index with 'F' is the final state
                }
            }
            index++;
        }
        
        //Transitions on the characters
        for(int i = 0; i < inputStates; i++){
            //Determine the transitions at each index in the input transition table array
            String transition = inputTransitionTable.get(i + 1);
            for(int j = 0; j < inputCharacters; j++){
                char currentinput = transition.charAt(j + 1);
                String transitions = String.valueOf(currentinput);
                transitionTable [i][j] = transitions; //Add the obtained transitions into the double array
            }
        }

        //Read string input file with the scanner
        System.out.println("Enter your string input file path name: ");
        String stringFileName = input.next();
        Scanner readString = null;
        System.out.println("Processing File " + stringFileName + "\n");
        try{
            readString = new Scanner(new File(stringFileName));
        }
        catch(FileNotFoundException e){
            System.out.println("Error: Can't open file " + stringFileName);
            System.exit(0);
        }
        
        //Processing string input file
        String enteredString = readString.nextLine();
        String [] stringInput = new String [enteredString.length()]; //Use the length of the read string input as the size of the new array
        for (int i = 0; i < enteredString.length(); i++){
            char process = enteredString.charAt(i); //Process each character in the input string
            String processString = String.valueOf(process);
            stringInput[i] = processString; //Add the input string into the array 
        }
        
        //Determine if DFA accepts the string or not
        int index2 = 0;
        while (index2 < stringInput.length){
            String determineString = stringInput[index2]; //Enter the string input into determine string
            int index3 = 0; 
            while (index3 < inputStates){
                //Determines the current state based on whether the character in the array states equals start state
                if (states[index3].equals(startState)){ 
                    currState = index3;
                }
                index3++;
            }
            int index4 = 0;
            while (index4 < inputCharacters){
                //Determines the current character based on whether the character in the array equals the string input
                if (characters[index4].equals(determineString)){
                    currCharacter = index4;
                }
                index4++;
            }
            //The determined current state and determined current characters are entered into the transition table and assigned to start state
            startState = transitionTable[currState][currCharacter];
            //Since index 2 starts at 0, therefore if it is equal to one less the string input array, then the string acceptance is determined
            if(index2==(stringInput.length-1)){
                //When the string is done transitioning through the DFA it is determined whether it should be accepted or rejected
                if (finalState.equals(startState)){
                    System.out.println("\n" + "Yes, the string is accepted.");
                }
                else{
                    System.out.println("\n" + "No, the string is not accepted.");
                }
            }
            index2++;
        }
    }
}
