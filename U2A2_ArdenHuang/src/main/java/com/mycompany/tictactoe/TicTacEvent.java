    package com.mycompany.tictactoe;


import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class TicTacEvent implements ItemListener, ActionListener, Runnable {
    File save = new File("save.txt");
    TicTac gui;
    Thread playing;
    ImageIcon a = new ImageIcon("x.png");
    ImageIcon b = new ImageIcon("o.png");
    int clicks = 0;
    int win = 0;
    int Xwin = 0;
    int Owin = 0;
    int[][] check = new int[4][4];

    public TicTacEvent (TicTac in){
        gui = in;
        for (int row=0; row<=3; row++){
           for (int col=0; col<=3; col++){
               check[row][col]=0;
           }
       }
    }
    public void actionPerformed (ActionEvent event) {
       String command = event.getActionCommand();

       if (command.equals("Play")) {
           startPlaying();
       }
       if (command.equals("Reset")) {
           resetGame();
       }
       if (command.equals("1")) {
           System.out.println("1");
           b1();
       }
       if (command.equals("2")) {
           System.out.println("2");
           b2();
       }
       if (command.equals("3")) {
           System.out.println("3");
           b3();
       }
       if (command.equals("4")) {
           System.out.println("4");
           b4();
       }
       if (command.equals("5")) {
           System.out.println("5");
           b5();
       }
       if (command.equals("6")) {
           System.out.println("6");
           b6();
       }
       if (command.equals("7")) {
           System.out.println("7");
           b7();
       }
       if (command.equals("8")) {
           System.out.println("8");
           b8();
       }
       if (command.equals("9")) {
           System.out.println("9");
           b9();
       }
       if (command.equals("10")) {
           System.out.println("10");
           b10();
       }
       if (command.equals("11")) {
           System.out.println("11");
           b11();
       }
       if (command.equals("12")) {
           System.out.println("12");
           b12();
       }
       if (command.equals("13")) {
           System.out.println("13");
           b13();
       }
       if (command.equals("14")) {
           System.out.println("14");
           b14();
       }
       if (command.equals("15")) {
           System.out.println("15");
           b15();
       }
       if (command.equals("16")) {
           System.out.println("16");
           b16();
       }
    }
     void b1() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][0].setIcon(a);
            check[0][0] = 1;
        } else {
            gui.boxes[0][0].setIcon(b);
            check[0][0] = 2;
        }
        winner();
    }
    void b2() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][1].setIcon(a);
            check[0][1] = 1;
        } else {
            gui.boxes[0][1].setIcon(b);
            check[0][1] = 2;
        }
        winner();
    }
    void b3() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][2].setIcon(a);
            check[0][2] = 1;
        } else {
            gui.boxes[0][2].setIcon(b);
            check[0][2] = 2;
        }
        winner();
    }
    void b4() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[0][3].setIcon(a);
            check[0][3] = 1;
        } else {
            gui.boxes[0][3].setIcon(b);
            check[0][3] = 2;
        }
        winner();
    }
    void b5() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][0].setIcon(a);
            check[1][0] = 1;
        } else {
            gui.boxes[1][0].setIcon(b);
            check[1][0] = 2;
        }
        winner();
    }
    void b6() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][1].setIcon(a);
            check[1][1] = 1;
        } else {
            gui.boxes[1][1].setIcon(b);
            check[1][1] = 2;
        }
        winner();
    }
    void b7() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][2].setIcon(a);
            check[1][2] = 1;
        } else {
            gui.boxes[1][2].setIcon(b);
            check[1][2] = 2;
        }
        winner();
    }
    void b8() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[1][3].setIcon(a);
            check[1][3] = 1;
        } else {
            gui.boxes[1][3].setIcon(b);
            check[1][3] = 2;
        }
        winner();
    }
    void b9() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][0].setIcon(a);
            check[2][0] = 1;
        } else {
            gui.boxes[2][0].setIcon(b);
            check[2][0] = 2;
        }
        winner();
    }
    void b10() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][1].setIcon(a);
            check[2][1] = 1;
        } else {
            gui.boxes[2][1].setIcon(b);
            check[2][1] = 2;
        }
        winner();
    }
    void b11() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][2].setIcon(a);
            check[2][2] = 1;
        } else {
            gui.boxes[2][2].setIcon(b);
            check[2][2] = 2;
        }
        winner();
    }
    void b12() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[2][3].setIcon(a);
            check[2][3] = 1;
        } else {
            gui.boxes[2][3].setIcon(b);
            check[2][3] = 2;
        }
        winner();
    }
    void b13() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][0].setIcon(a);
            check[3][0] = 1;
        } else {
            gui.boxes[3][0].setIcon(b);
            check[3][0] = 2;
        }
        winner();
    }
    void b14() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][1].setIcon(a);
            check[3][1] = 1;
        } else {
            gui.boxes[3][1].setIcon(b);
            check[3][1] = 2;
        }
        winner();
    }
    void b15() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][2].setIcon(a);
            check[3][1] = 1;
        } else {
            gui.boxes[3][2].setIcon(b);
            check[3][2] = 2;
        }
        winner();
    } 
    void b16() {
        clicks = clicks + 1;
        if ((clicks%2)==1){
            gui.boxes[3][3].setIcon(a);
            check[3][3] = 1;
        } else {
            gui.boxes[3][3].setIcon(b);
            check[3][3] = 2;
        }
        winner();
    }
     void startPlaying() {
        playing = new Thread(this);
        playing.start();
        gui.play.setEnabled(false);
    }
     void resetGame() {
         clicks=0;
         win=0;
         for(int i=0;i<4;i++){
             for(int j=0;j<4;j++){
                 check[i][j]=0;
                 gui.boxes[i][j].setIcon(gui.back);
             }
         }
     }
     void winner() {
        for (int x=0; x<=3; x++){
            if ((check[x][0]==check[x][1])&&(check[x][0]==check[x][2])&&(check[x][0]==check[x][3])) {
                if (check[x][0]==1) {
                    JOptionPane.showMessageDialog(null, "X is the winner");
                    win = 1;
                    Xwin++;
                } else if (check[x][0]==2){
                    JOptionPane.showMessageDialog(null, "O is the winner");
                    win = 1;
                    Owin++;
                }
                }
        }
        for (int x=0; x<=3; x++){
            if ((check[0][x]==check[1][x])&&(check[0][x]==check[2][x])&&(check[0][x]==check[3][x])) {
                if (check[0][x]==1) {
                    JOptionPane.showMessageDialog(null, "X is the winner");
                    win = 1;
                    Xwin++;
                } else if (check[0][x]==2) {
                    JOptionPane.showMessageDialog(null, "O is the winner");
                    win = 1;
                    Owin++;
                }
            }
        }
        if ((check[0][0]==check[1][1]&&check[0][0]==check[2][2]&&check[0][0]==check[3][3])){
            if (check[0][0]==1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                win = 1;
                Xwin++;
            } else if (check[0][0]==2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                win = 1;
                Owin++;
            }
        }
        if ((check[3][0]==check[2][1]&&check[3][0]==check[1][2]&&check[3][0]==check[0][3])){
            if (check[3][0]==1) {
                JOptionPane.showMessageDialog(null, "X is the winner");
                win = 1;
                Xwin++;
            } else if (check[3][0]==2) {
                JOptionPane.showMessageDialog(null, "O is the winner");
                win = 1;
                Owin++;
            }
        }
         if ((clicks==16) && (win==0)) {
            JOptionPane.showMessageDialog(null, "The game is a tie");
        }
    }
      
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
                
}
