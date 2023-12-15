    package com.mycompany.tictactoe;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class TicTacEvent implements ItemListener, ActionListener, Runnable {
    TicTac gui;
    Thread playing;
    ImageIcon a = new ImageIcon("x.png"); //icons
    ImageIcon b = new ImageIcon("o.png");
    int clicks = 0;
    int win = 0;
    int[][] check = new int[4][4]; //for checking for winners
    int[] stats = new int[3];
    public TicTacEvent (TicTac in){
        gui = in;
        for (int row=0; row<=3; row++){ //setting all values in check array to 0
           for (int col=0; col<=3; col++){
               check[row][col]=0;
           }
       }
    }
    public void actionPerformed (ActionEvent event) {
       String command = event.getActionCommand();

       if (command.equals("Play")) {
           startPlaying();
           readFromFile(); //reads file and displays
       }
       if (command.equals("Reset")) {
           resetGame();
           updateFile(); //update file info 
           readFromFile(); //read and display
       }
       if (command.equals("1")) {
           b1();
           updateFile();
           readFromFile();
       }
       if (command.equals("2")) {
           b2();
           updateFile();
           readFromFile();
       }
       if (command.equals("3")) {
           b3();
           updateFile();
           readFromFile();
       }
       if (command.equals("4")) {
           b4();
           updateFile();
           readFromFile();
       }
       if (command.equals("5")) {
           b5();
           updateFile();
           readFromFile();
       }
       if (command.equals("6")) {
           b6();
           updateFile();
           readFromFile();
       }
       if (command.equals("7")) {
           b7();
           updateFile();
           readFromFile();
       }
       if (command.equals("8")) {
           b8();
           updateFile();
           readFromFile();
       }
       if (command.equals("9")) {
           b9();
           updateFile();
           readFromFile();
       }
       if (command.equals("10")) {
           b10();
           updateFile();
           readFromFile();
       }
       if (command.equals("11")) {
           b11();
           updateFile();
           readFromFile();
       }
       if (command.equals("12")) {
           b12();
           updateFile();
           readFromFile();
       }
       if (command.equals("13")) {
           b13();
           updateFile();
           readFromFile();
       }
       if (command.equals("14")) {
           b14();
           updateFile();
           readFromFile();
       }
       if (command.equals("15")) {
           b15();
           updateFile();
           readFromFile();
       }
       if (command.equals("16")) {
           b16();
           updateFile();
           readFromFile();
       }
    }
     void updateFile(){ //updates file with game info
         try {
        FileWriter pw = new FileWriter("winsTracker",false);
        pw.write(String.valueOf(stats[0])+" "+String.valueOf(stats[1])+" "+String.valueOf(stats[2]));
        pw.close();
    } catch (IOException e) {
        }
     }
     void readFromFile(){ //reads from the file, puts info into array, and displays info
         try{
        File file = new File("winsTracker.txt");
        Scanner input = new Scanner(file);
        String[] parts;
        String line = "";
            while (input.hasNextLine()) {
                line += input.nextLine()+" ";
            }
        input.close();
        parts = line.split(" ");
        for (int i=0;i<parts.length;i++){
            try{
                stats[i]=Integer.parseInt(parts[i]);
            }
            catch(NumberFormatException e){
            }
            }
        }
        catch(FileNotFoundException e){
        }
        gui.blank1.setText("<html>X wins: "+String.valueOf(stats[0])+"<br/> O wins: "+String.valueOf(stats[1])+"<br/>Ties: "+String.valueOf(stats[2])+"</html>");
        gui.blank3.setText("Press reset to restart");
     }
     void b1() { //methods for all the buttons
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][0].setIcon(a);
            gui.boxes[0][0].setEnabled(false); //buttons disabled so they can't be pressed again
            check[0][0] = 1;
        } else {
            gui.boxes[0][0].setIcon(b);
            gui.boxes[0][0].setEnabled(false);
            check[0][0] = 2;
        }
        winner();
    }
    void b2() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][1].setIcon(a);
            gui.boxes[0][1].setEnabled(false);
            check[0][1] = 1;
        } else {
            gui.boxes[0][1].setIcon(b);
            gui.boxes[0][1].setEnabled(false);
            check[0][1] = 2;
        }
        winner();
    }
    void b3() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][2].setIcon(a);
            gui.boxes[0][2].setEnabled(false);
            check[0][2] = 1;
        } else {
            gui.boxes[0][2].setIcon(b);
            gui.boxes[0][2].setEnabled(false);
            check[0][2] = 2;
        }
        winner();
    }
    void b4() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][3].setIcon(a);
            gui.boxes[0][3].setEnabled(false);
            check[0][3] = 1;
        } else {
            gui.boxes[0][3].setIcon(b);
            gui.boxes[0][3].setEnabled(false);
            check[0][3] = 2;
        }
        winner();
    }
    void b5() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][0].setIcon(a);
            gui.boxes[1][0].setEnabled(false);
            check[1][0] = 1;
        } else {
            gui.boxes[1][0].setIcon(b);
            gui.boxes[1][0].setEnabled(false);
            check[1][0] = 2;
        }
        winner();
    }
    void b6() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][1].setIcon(a);
            gui.boxes[1][1].setEnabled(false);
            check[1][1] = 1;
        } else {
            gui.boxes[1][1].setIcon(b);
            gui.boxes[1][1].setEnabled(false);
            check[1][1] = 2;
        }
        winner();
    }
    void b7() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][2].setIcon(a);
            gui.boxes[1][2].setEnabled(false);
            check[1][2] = 1;
        } else {
            gui.boxes[1][2].setIcon(b);
            gui.boxes[1][2].setEnabled(false);
            check[1][2] = 2;
        }
        winner();
    }
    void b8() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][3].setIcon(a);
            gui.boxes[1][3].setEnabled(false);
            check[1][3] = 1;
        } else {
            gui.boxes[1][3].setIcon(b);
            gui.boxes[1][3].setEnabled(false);
            check[1][3] = 2;
        }
        winner();
    }
    void b9() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][0].setIcon(a);
            gui.boxes[2][0].setEnabled(false);
            check[2][0] = 1;
        } else {
            gui.boxes[2][0].setIcon(b);
            gui.boxes[2][0].setEnabled(false);
            check[2][0] = 2;
        }
        winner();
    }
    void b10() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][1].setIcon(a);
            check[2][1] = 1;
            gui.boxes[2][1].setEnabled(false);
        } else {
            gui.boxes[2][1].setIcon(b);
            check[2][1] = 2;
            gui.boxes[2][1].setEnabled(false);
        }
        winner();
    }
    void b11() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][2].setIcon(a);
            check[2][2] = 1;
            gui.boxes[2][2].setEnabled(false);
        } else {
            gui.boxes[2][2].setIcon(b);
            check[2][2] = 2;
            gui.boxes[2][2].setEnabled(false);
        }
        winner();
    }
    void b12() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][3].setIcon(a);
            check[2][3] = 1;
            gui.boxes[2][3].setEnabled(false);            
        } else {
            gui.boxes[2][3].setIcon(b);
            check[2][3] = 2;
            gui.boxes[2][3].setEnabled(false);
        }
        winner();
    }
    void b13() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][0].setIcon(a);
            check[3][0] = 1;
            gui.boxes[3][0].setEnabled(false);
        } else {
            gui.boxes[3][0].setIcon(b);
            check[3][0] = 2;
            gui.boxes[3][0].setEnabled(false);
        }
        winner();
    }
    void b14() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][1].setIcon(a);
            check[3][1] = 1;
            gui.boxes[3][1].setEnabled(false);
        } else {
            gui.boxes[3][1].setIcon(b);
            check[3][1] = 2;
            gui.boxes[3][1].setEnabled(false);
        }
        winner();
    }
    void b15() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][2].setIcon(a);
            check[3][2] = 1;
            gui.boxes[3][2].setEnabled(false);
        } else {
            gui.boxes[3][2].setIcon(b);
            check[3][2] = 2;
            gui.boxes[3][2].setEnabled(false);
        }
        winner();
    } 
    void b16() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][3].setIcon(a);
            check[3][3] = 1;
            gui.boxes[3][3].setEnabled(false);
        } else {
            gui.boxes[3][3].setIcon(b);
            check[3][3] = 2;
            gui.boxes[3][3].setEnabled(false);
        }
        winner();
    }
    void disableButtons(){ //disables buttons
        for (int a = 0;a<4;a++){
            for(int b = 0;b<4;b++){
                gui.boxes[a][b].setEnabled(false);
                gui.boxes[a][b].setContentAreaFilled(true);
            }
        }
    }
    void enableButtons(){ //enables buttons
        for (int a = 0;a<4;a++){
            for(int b = 0;b<4;b++){
                gui.boxes[a][b].setEnabled(true);
            }
        }
    }
     void startPlaying() { //starts the playing
        playing = new Thread(this);
        playing.start();
        gui.play.setEnabled(false); //enables all buttons
        enableButtons();
    }
     void resetGame() { //resets game by reseting click number and puts all icons back to normal
         enableButtons(); //enables buttons again
         clicks=0;
         win=0;
         for(int i=0;i<4;i++){
             for(int j=0;j<4;j++){
                 check[i][j]=0;
                 gui.boxes[i][j].setIcon(gui.back);
             }
         }
     }
     void winner() { //checks horizontal, vertical, and both diagonal possibilities for wins
        for (int x=0; x<=3; x++){
            if ((check[x][0]==check[x][1])&&(check[x][0]==check[x][2])&&(check[x][0]==check[x][3])) {
                if (check[x][0]==1) {
                    JOptionPane.showMessageDialog(null, "X is the winner");
                    win = 1;
                    stats[0]++;
                    disableButtons();
                } else if (check[x][0]==2){
                    JOptionPane.showMessageDialog(null, "O is the winner");
                    win = 1;
                    stats[1]++;
                    disableButtons();
                }
            }
        }
        for (int x=0; x<=3; x++){
            if ((check[0][x]==check[1][x])&&(check[0][x]==check[2][x])&&(check[0][x]==check[3][x])) {
                if (check[0][x]==1) {
                    JOptionPane.showMessageDialog(null, "X is the winner");
                    win = 1;
                    stats[0]++;
                    disableButtons();
                } else if (check[0][x]==2) {
                    JOptionPane.showMessageDialog(null, "O is the winner");
                    win = 1;
                    stats[1]++;
                    disableButtons();
                }
            }
        }
        if ((check[0][0]==check[1][1]&&check[0][0]==check[2][2]&&check[0][0]==check[3][3])){
            if (check[0][0]==1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                win = 1;
                stats[0]++;
            } else if (check[0][0]==2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                win = 1;
                stats[1]++;
                disableButtons();
            }
        }
        if ((check[3][0]==check[2][1]&&check[3][0]==check[1][2]&&check[3][0]==check[0][3])){
            if (check[3][0]==1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                win = 1;
                stats[0]++;
                disableButtons();
            } else if (check[3][0]==2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                win = 1;
                stats[1]++;
                disableButtons();
            }
        }
         if ((clicks==16) && (win==0)) {
            JOptionPane.showMessageDialog(null, "The game is a tie");
            stats[2]++;
            disableButtons();
        }
    }
      
    
    @Override
    public void itemStateChanged(ItemEvent e) {
    }

    @Override
    public void run() {
    }
                
}
