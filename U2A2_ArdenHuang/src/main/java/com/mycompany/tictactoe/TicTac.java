/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.tictactoe;

/**
 *
 * @author 342513926
 */
import java.awt.*;
import javax.swing.*;

public class TicTac extends JFrame {
    Font f1 = new Font(Font.SERIF, Font.PLAIN,0); //invis font
    TicTacEvent tictac = new TicTacEvent(this);
    JPanel row1 = new JPanel();
    JButton[][] boxes = new JButton[4][4];
    JButton play = new JButton("Play");
    JLabel blank1 = new JLabel("",SwingConstants.CENTER);
    JButton reset = new JButton("Reset");
    JLabel blank3 = new JLabel("",SwingConstants.CENTER);
    ImageIcon back = new ImageIcon("cardback.jpg ");
    

    public TicTac() {
        super("Tic Tac Toe");
        setLocationRelativeTo(null);
        setSize(750, 700);
        setLocationRelativeTo(null); //centers window on launch
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout layout = new FlowLayout();
        setLayout(layout);
        int name = 0;
        String newname;

        GridLayout layout1 = new GridLayout(5, 4, 10, 10); //sets grid layout
        row1.setLayout(layout1);
        for (int x = 0; x <= 3; x++) {
            for (int y = 0; y <= 3; y++) {
                name = name + 1;
                newname = Integer.toString(name); //names the buttons
                boxes[x][y] = new JButton(newname);
                boxes[x][y].setFont(f1); //sets button names invisible
                boxes[x][y].setEnabled(false); //disables buttons
                boxes[x][y].setIcon(back);
                row1.add(boxes[x][y]);
            }
        }
        row1.add(blank1);
        row1.add(play);
        row1.add(reset);
        row1.add(blank3);
        add(row1);
         play.addActionListener(tictac); //adding listeners
         reset.addActionListener(tictac);
        for (int x=0; x<=3; x++){
            for (int y=0; y<=3; y++){
                boxes[x][y].addActionListener(tictac);
            }
        }
        setVisible(true);
    }

    public static void main(String[] arguments) {
        TicTac frame = new TicTac(); //frame for the event
    }
}
