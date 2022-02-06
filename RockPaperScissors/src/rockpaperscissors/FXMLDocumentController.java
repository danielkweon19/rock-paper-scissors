/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rockpaperscissors;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Danie
 */
//Rock Paper Scissors Project by Daniel Kweon
//Things to remember:
//1.)Only use the items in the GUI simply for graphics
//2.)Determine what your classes will be used for
//3.)Have a Pre/Post Condition for every method
//4.)Remember that to get an A, you must txtfile
//5.)Mr.Cortez will test your code by emptying your text file. Make sure that works


public class FXMLDocumentController implements Initializable {
    //This array will store every move that has been inputted
    ArrayList<String> previousGuesses = new ArrayList<>();
    
    //This array is to keep track of the moves for the current game
    ArrayList<String> currentGuesses = new ArrayList<>();
    
    //Observable lists for my listViews.I have a list views for my current guesses and previous guesses
    private ObservableList displayList = FXCollections.observableArrayList();
    private ObservableList displayList2 = FXCollections.observableArrayList();
    
    //Moves in this game are as follows
    //1-Rock
    //2-Paper
    //3-Scissors
    
    //whichMove stores whatever move the computer selected from the Game class
    private int whichMove;
    private int wins;
    private int losses;
    private int ties;
    
    //Chosen is the move that the user selects
    private int chosen;
    
    @FXML
    private ListView listView,listView2;
    
    @FXML
    private ImageView imgPic,imgPic2;
    
    @FXML
    private Label label,lossLabel,tiesLabel;
    
    //This method adds to the previous Guesses array
    //This method keeps increasing everything you adda move
    @FXML
    private void addList(){
        String adder = Integer.toString(chosen);
        previousGuesses.add(adder);
        displayList.add(adder);
        listView.setItems(displayList);
        saveList();
    }
    //This method adds to the current guesses array
    @FXML
    private void addList2(){
        String adder = Integer.toString(chosen);
        currentGuesses.add(adder);
        displayList2.add(adder);
        listView2.setItems(displayList2);
    }
    //This method saves the txtfile as new items are added everytime
    @FXML
    private void saveList(){
        String outFile = "src/Other Resources/file.txt.txt";
        try {
                PrintWriter out = new PrintWriter(outFile);
                for(int i = 0; i < previousGuesses.size(); i++)
                {
                    out.println(previousGuesses.get(i));
                }
                out.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Something went wrong!");
        }
    }
    //This method allows the user to load the previous guesses array in a list view.
    //MUST BE CALLED FOR GAME TO WORK CORRECTLY
    @FXML
    private void loadList(){
        try{
            FileReader reader = new FileReader("src/Other Resources/file.txt.txt");
            Scanner in = new Scanner(reader);
            while(in.hasNextLine())
            {
                String temp = in.nextLine();
                previousGuesses.add(temp);
                displayList.add(temp);
                listView.setItems(displayList);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("SOMETHING HAS GONE HORRIBLY WRONG WE'RE ALL GONNA DIE!");
        }
    }
    //This method calls the button for "Rock"
    @FXML
    private void handleButtonAction(ActionEvent event) {
        chosen = 1;
        imgPic.setImage(new Image("Resources/Rock.png"));
        computer();
        computerDisplay();
        showWinner();
        addList();
        addList2();
    }
    
    //This method calls the button for "Paper"
    @FXML
    private void paperButton(){
        chosen = 2;
        imgPic.setImage(new Image("Resources/Paper.png"));
        computer();
        computerDisplay();
        showWinner();
        addList();
        addList2();
    }
    //This method calls the button for "Scissors"
    @FXML
    private void scissorButton(){
        chosen = 3;
        imgPic.setImage(new Image("Resources/Scissors.png"));
        computer();
        computerDisplay();
        showWinner();
        addList();
        addList2();
    }
    
    //This method is used to determine the moves that the Computer will use
    //WhichMove changes based on the changes from the Game class
    @FXML
    private void computerDisplay(){
        if(whichMove == 1 ){
            imgPic2.setImage(new Image("Resources/Rock2.jpg"));
        }
        if(whichMove == 2){
            imgPic2.setImage(new Image("Resources/Paper2.jpg"));
        }
        if(whichMove == 3){
            imgPic2.setImage(new Image("Resources/Scissors2.jpg"));
        }
    }
    
    //This methdo displays the numeber of wins/losses/ties that will be displayed
    @FXML
    private void showWinner(){
        label.setText("Wins: " + wins);
        lossLabel.setText("Loss: " + losses);
        tiesLabel.setText("Ties: " + ties);
    }
    
    //This method creates an instance of the game class, and collects the data that changes from the game class
    public void computer(){
        //Object of game class
        //Will determine the computer moves, and will determine patterns
        Game x = new Game(chosen);
        
        //This method compares the last x user moves with the last x computer moves
        //Pass the arrays as parameters to the game class
        x.compareMoves(previousGuesses,currentGuesses);
        
        //Depending on the computer move, a global varaible is set that will determine the computer move
        switch (x.showComputerMove()){
            case 1: whichMove = 1;
                    break;
            case 2: whichMove = 2;
                    break;
            case 3: whichMove = 3;
                    break;
        }
//        System.out.println("ComputerMove in GUI: " + whichMove);
        //Depending on who wins in the game class, increase wins,losses, or ties
        switch(x.showResult()){
            case 1: wins = wins +1;
                    break;
            case 2: losses = losses + 1;
                    break;
            case 3: ties = ties + 1;
                    break;
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
