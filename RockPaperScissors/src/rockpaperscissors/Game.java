/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpaperscissors;

import java.util.ArrayList;

/**
 *
 * @author Danie
 */
public class Game {
    
    private int compMove;
    private int userMove;
    private String userMove2;
    private int result;
    private String compare1 = "";
    private String compare2 = "";
    private boolean findMode = false;
    private String modeFinder = "";
    
    //Constructor initializes the usermove, and the initalize win/loss/ties condition
    //Result: wins = 1, losses = 2, ties = 3
    
    public Game(int move){
        userMove = move;
        userMove2 = Integer.toString(move);
        result = 0;
    }

    //Pre-Condition: 2 String Arrays
    //Post-Condition: The index of the next move the computer could use
    //Parameters: previous guesses array, and current guesses array
    public void compareMoves(ArrayList<String> previousGuesses,ArrayList<String> currentGuesses){
        //use 11213
        
        //This array is used to store all the possible next move that could be used depending on the patter
        ArrayList<String> possibleMoves = new ArrayList<>();
        //Index is used to find the index of the next move that will be used
        int index = 0;
        //Counter keeps track of the number of times the game has been played. 8 moves must be used before any comparing
      
        int counter = 0;
        //CheckNum is the INITIAL amount of moves that will be checked. Decreases if a pattern of 8 can't be found
        int checkNum = 8;
        //Boolean that checks if a match is found or not
        boolean foundMatch = false;
        //Before 8 moves are inputted, the computer should use random moves
        if(currentGuesses.size() < checkNum){
            compMove = 1 + (int)(Math.random()*3);
            checkGame();
        }
        //Once the user has 8 moves inputted
        else if(currentGuesses.size() >= checkNum){
            checkNum = 8;
            //This while loop will run until a match is found, or if it cannot find a pattern of 4
            while(!foundMatch && checkNum >=4){
                compare1 = "";
                compare2 = "";
                //This primitive integer array is used to store the last 8 moves that the user put before their current input.
                //This array size decreases if a certain size pattern can't be found
                int[]x = new int[checkNum];
//                if(checkNum == 4){
//                    foundMatch = true;
//                }
               
                //This next method is a copy of what I did for my MINI ARRAY PROJECT
                //I get the last X amount of moves behind the current array, and reverse it so that it is in the order that I want
                for(int i = 0; i < checkNum;i++){
                    x[i] = Integer.parseInt(currentGuesses.get((currentGuesses.size()-1)-i));
                }
                int temp = 0;
                for(int q = 0; q < x.length/2; q++){
                    temp = x[q];
                    x[q] = x[(x.length-1) - q];
                    x[(x.length-1) - q] = temp;
                }
                for(int r= 0; r < x.length; r++){
                    //get each move in the array and input it into a string
                    compare1 = compare1 + x[r];
                }
    
                /////////////////////////////////////////////////////////
                //This for loop will go through every pattern of 8 (except for the last couple of moves)
                //If a pattern is found, the loop will store that pattern in the possible moves array
                //Will store every possible move in the possible moves array
                for (int y = 0; y < (previousGuesses.size()-(checkNum+1)); y++){
                    for(int a = 0; a < checkNum; a++){
                        compare2 = compare2 + previousGuesses.get(a+y);
                        counter = a;
                    }
                    System.out.println("Compare2: " + compare2);
                    if(compare2.equals(compare1)){
                        //Get the index of the NEXT move in the pattern
                        index = counter+y+1;
                        System.out.println("//////////////////////////////");
                        System.out.println("Match: " + compare1);
                        System.out.println("//////////////////////////////");
                        possibleMoves.add(previousGuesses.get(index));
                        foundMatch = true;
                    }
                    compare2 = "";
                    counter = 0;
                    
                }
                //If a pattern cannot be found for 8, then it will decrease to 7, and 6, etc.
                checkNum--;
          
            }
            //Once this while loop is over, if there wasn't a pattern, the computer will use a random move
            if(!foundMatch){
                System.out.println("No match!");
                if((possibleMoves.size())==0){
                    compMove = 1 + (int)(Math.random()*3);
                    checkGame();
                }
            }
            else{
                //If there was a pattern found, the computer will first look for the mode of that array
                //This method looks for the mode of the possible moves array
                randomNums(possibleMoves);
                System.out.println("There's a match!");
                
                //Will counter whatever move appears the most
                if(modeFinder.equals("1")){
                    compMove = 2;
                    System.out.println("Computer should have used paper");
                }
                if (modeFinder.equals("2")){
                    compMove = 3;
                    System.out.println("Computer should have used scissor");
                }
                if (modeFinder.equals("3")){
                    compMove = 1;
                    System.out.println("Computer should have used Rock");
                          
                }
                //This method checks to compare the moves of the computer and user
                checkGame();
            }
//            for(int i = 0; i < 5;i++){
//                x[i] = Integer.parseInt(currentGuesses.get((currentGuesses.size()-1)-i));
//            }
//            int temp = 0;
//            for(int q = 0; q < x.length/2; q++){
//                temp = x[q];
//                x[q] = x[(x.length-1) - q];
//                x[(x.length-1) - q] = temp;
//            }
//            for(int r= 0; r < x.length; r++){
//                compare1 = compare1 + x[r];
//            }
//            System.out.println("Compare1: " + compare1);     
//            /////////////////////////////////////////////////////////
//            for (int y = 0; y < previousGuesses.size()-6; y++){
//                for(int a = 0; a < 5; a++){
//                    compare2 = compare2 + previousGuesses.get(a+y);
//                    counter = a;
//                }
//                if(compare2.equals(compare1)){
//                    index = counter+y+1;
//                    possibleMoves.add(previousGuesses.get(index));
//                }
//                compare2 = "";
//                counter = 0;
//            }
//            if((possibleMoves.size())==0){
//               compMove = 1 + (int)(Math.random()*3);
//               checkGame();
//            }
//            else{
//                randomNums(possibleMoves);
//                System.out.println("There's a match!");
//                System.out.println("Compare2: " + compare2);
//                if(modeFinder.equals("1")){
//                        compMove = 2;
//                        System.out.println("Computer should have used paper");
//                }
//                if (modeFinder.equals("2")){
//                        compMove = 3;
//                        System.out.println("Computer should have used scissor");
//                              
//                }
//                if (modeFinder.equals("3")){
//                        compMove = 1;
//                        System.out.println("Computer should have used Rock");
//                          
//                }
//                checkGame();
//                
//
//                    
//            }
            
        }
    }
    //THIS METHOD IS THE SAME METHOD I USED IN THE MINI ARRAY PROJECT
    //Used to check the mode of an array
    public void randomNums(ArrayList<String> previousGuesses){
        //This method is used to calculate average and mode. The parameter is how big the array will be
        //This array is used to first contain a certain amount of values
        //This array contains the counter of each value in the array. Ex: If 12 appears 2 times, 2 will be stored
        ArrayList<Integer> counterArray = new ArrayList<>();
//        ArrayList<Integer> positionsArray = new ArrayList<>();

        //This array is used to store all the possible modes
        ArrayList<String> modes = new ArrayList<>();
        
        for (int x= 0; x < previousGuesses.size();x++){
            int counter = 0;
            for (int k = 0; k < previousGuesses.size(); k++){
                if ((previousGuesses.get(x)).equals(previousGuesses.get(k))){
                    counter++;
                }
            }
            //Add each counter value to a new array
            counterArray.add(counter);
        }
//        System.out.println(counterArray);
        int max = counterArray.get(0);
        for (int q= 0; q < counterArray.size();q++){
            if (counterArray.get(q) >= max){
                //If the next value in the array is greater, than you know the original value is NOT the max
                max = counterArray.get(q);
            }
        }
//        System.out.println(max);
        //This loop find the position of the max in the counter array, and uses that position to find the orignal value from the startArray
        for(int e = 0; e < counterArray.size();e++){
            if (counterArray.get(e) == max){
                modes.add(previousGuesses.get(e));
            }
        }
        //This loop is used to rid of any duplicates in the array. 
        for(int a = 0; a < modes.size(); a++){
            for(int a1 = a + 1; a1 < modes.size(); a1++){
                if(modes.get(a).equals(modes.get(a1))){
                    modes.remove(a1);
                    a1--;
                }
            }
        }
        //No mode exists if the mode array is the same size as the orignal array
        if(modes.size() == previousGuesses.size()){
            findMode = false;
            System.out.println("Same size!");
            modeFinder = modes.get(0);
            
        }
        else{
            findMode = true;
            if(modes.size() > 1){
                System.out.println("Theres more than one mode!!");
                int randomer = 1 + (int)(Math.random()*2);
                if(randomer == 1){
                    modeFinder = modes.get(0);
                }
                else{
                    modeFinder = modes.get(1);
                }
                
            }
            else{
                modeFinder = modes.get(0);
            }
            
        }


        
        

    }
    public void checkGame(){
        if((compMove%3)+1 == userMove){
            result = 1;
        }
        else if((userMove%3)+1 == compMove){
            result = 2;
        }
        else{
            result = 3;
        }
    }
    
    public int showResult(){
        return result;
    }
    
    public int showComputerMove(){
        return compMove;
    }
}
