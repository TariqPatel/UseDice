/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usedice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import javax.swing.*;

/**
 *
 * @author Mogamat Tariq Patel
 */
public class UseDice extends JFrame implements ActionListener {

    //initilising variables
    JLabel lblLastRoll = new JLabel("Last roll outcome:");
    JLabel lblBalance = new JLabel("Your balance is:");
    JLabel lblName = new JLabel("Enter your name:");
    JLabel lblDiceType = new JLabel("Choose the dice type:");
    JLabel lblBetValue = new JLabel("Bet Value:");
    JLabel lblBetNumber = new JLabel("Bet number:");
    JLabel lblBalanceValue = new JLabel("100");
    JTextField fldName = new JTextField(15);
    JTextField fldBetValue = new JTextField(5);
    JTextField fldBetNumber = new JTextField(5);
    JTextArea area = new JTextArea(13, 15);
    String[] numbDiceSides = {"4 :Double money", "6 :Triple money", "10 :Quadruple money"};
    JComboBox combo = new JComboBox(numbDiceSides);
    JButton btnRollDice = new JButton("Roll the dice");
    JButton btnResetGame = new JButton("Reset Game");
    JButton btnLoadGame = new JButton("Load Game");
    JButton btnSaveGame = new JButton("Save Game");
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenuItem menuItemSaveGame = new JMenuItem("Save game");
    JMenuItem menuItemLoadGame = new JMenuItem("Load game");
    JMenuItem menuItemResetGame = new JMenuItem("Reset game");
    JMenuItem menuItemExit = new JMenuItem("Exit");
    JSeparator sep = new JSeparator();
    JPanel pan = new JPanel();
    JPanel pan2 = new JPanel();
    JPanel pan3 = new JPanel();
    Dice dice1;
    String comboSelect;
    int roll;
    String scoreType = "";

    //UseDice constructor
    public UseDice() {
        super("Playing dice");
        this.setSize(400, 400);
        Container con = getContentPane();
        con.setLayout(new BorderLayout());

        pan.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //Adding MenuBar and Menus
        setJMenuBar(menuBar);
        menuBar.add(menuFile);
        menuFile.add(menuItemSaveGame);
        menuItemSaveGame.addActionListener(this);

        menuFile.add(menuItemLoadGame);
        menuItemLoadGame.addActionListener(this);

        menuFile.add(menuItemResetGame);
        menuItemResetGame.addActionListener(this);

        menuFile.add(sep);
        menuFile.add(menuItemExit);
        menuItemExit.addActionListener(this);



        //Adding labels and text fields and buttons 
        gbc.gridx = 0;
        gbc.gridy = 0;
        pan.add(lblLastRoll, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        pan.add(lblBalance, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        pan.add(lblName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        pan.add(lblDiceType, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        pan.add(lblBetValue, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        pan.add(lblBetNumber, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1;
        gbc.gridy = 1;
        pan.add(lblBalanceValue, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        pan.add(fldName, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        pan.add(fldBetValue, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        pan.add(fldBetNumber, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        pan.add(area, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        pan.add(combo, gbc);
        combo.addActionListener(this);


        pan2.setLayout(new FlowLayout());


        pan2.add(btnRollDice);
        btnRollDice.addActionListener(this);

        pan2.add(btnResetGame);
        btnResetGame.addActionListener(this);

        pan2.add(btnLoadGame);
        btnLoadGame.addActionListener(this);

        pan2.add(btnSaveGame);
        btnSaveGame.addActionListener(this);


        con.add(pan, BorderLayout.WEST);
        con.add(pan2, BorderLayout.SOUTH);
        con.add(pan3, BorderLayout.CENTER);

        pan.setBackground(Color.GREEN);
        pan2.setBackground(Color.BLUE);
        pan3.setBackground(Color.GREEN);


        pack();


    }

    public void actionPerformed(ActionEvent ae) {



        if (ae.getSource() == btnRollDice ) {
            rollTheDice();
        }

        if (ae.getSource() == btnSaveGame||ae.getSource() ==menuItemSaveGame) {
            writeToFile();
        }

        if (ae.getSource() == btnLoadGame||ae.getSource() ==menuItemLoadGame) {
            readFromFile();
        }

        if (ae.getSource() == btnResetGame||ae.getSource() ==menuItemResetGame) {
            resetButton();
        }
        
        if(ae.getSource() == menuItemExit){
            exitGame();
        }
    }

    public void createDice() {

        dice1 = new Dice();

    }

    //Method to write to file
    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("playDice.txt");
            PrintWriter pw = new PrintWriter(fw, true);
            pw.println(fldName.getText() + "," + lblBalanceValue.getText() + ",Dice has: " + dice1.getSides(comboSelect) + " sides,");

        } catch (Exception io) {
            System.out.println("Error" + io.getMessage());

        }

    }

    //method to read from file
    public void readFromFile() {
        try {
            String names;
            String balance;
            String sides;

            String line;
            RandomAccessFile raf = new RandomAccessFile("playDice.txt", "rw");

            line = raf.readLine();

            String[] fields;
            fields = line.split(",");
            names = fields[0];
            balance = fields[1];
            sides = fields[2];


            area.setText(sides);
            fldName.setText(names);
            lblBalanceValue.setText(balance);

        } catch (IOException io) {
            System.out.println("Error" + io.getMessage());
        }
    }

    //Method for roll dice button
    public void rollTheDice() {
        createDice();
        comboSelect = combo.getSelectedItem().toString();

        int betNumb = Integer.parseInt(fldBetNumber.getText());
        int betValue = Integer.parseInt(fldBetValue.getText());
        int balance1 = Integer.parseInt(lblBalanceValue.getText());

        roll = dice1.rollDice(dice1.getSides(comboSelect));

        int ran = (int) ((int) 1 + Math.random() * 3);

        int scoreWin = 0;
        int scoreLose = 0;
        int totalScore = 0;

        if (roll == betNumb) {
            scoreWin = betValue * dice1.getValue(comboSelect);

            totalScore = balance1 + scoreWin;

            scoreType = "You won!: " + scoreWin;
        } else if (roll != betNumb) {
            scoreLose = betValue;


            totalScore = balance1 - scoreLose;
            scoreType = "You lost: " + scoreLose;

        }

        String balance2 = Integer.toString(totalScore);

        area.setText(dice1.getColourAsString(comboSelect) + " rolled" + "\nDice has " + dice1.getSides(comboSelect) + " sides \n" + "Fell on: " + roll + "\n" + scoreType);

        lblBalanceValue.setText(balance2);
    }

    //Method for reset button
    public void resetButton() {
        //Reseting balance to 100
        lblBalanceValue.setText("100");

        //reseting combo box to default
        combo.setSelectedIndex(0);

        //Reseting text area to blank
        area.setText("");

        //Reseting text field to blank
        fldBetNumber.setText("");

        //Reseting text field to blank
        fldBetValue.setText("");

        //Reseting text field to blank
        fldName.setText("");
    }
    
    //method for exiting the game
    public void exitGame(){
        
        int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit? ", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (exit == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
