/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package usedice;

/**
 *
 * @author Mogamat Tariq Patel
 */
public class Dice {

    //initialising variables
    private int sides;
    private int colour;
    private int value;
    private String colourString;
    private String valueString;
    static final int WHITE = 4;
    static final int RED = 6;
    static final int BLUE = 10;

    //Constructor that recieves sides in argument
    public Dice(int aSides) {
        sides = aSides;
        rollDice(sides);
    }

    //Constructor with no arguments
    public Dice() {
        sides = 6;
    }

    //get colour method
    public int getColour() {

        return colour;
    }

    //get color as string method
    public String getColourAsString(String colourS) {
        if ("4 :Double money".equals(colourS)) {
            colourString = "White";
        } else if ("6 :Triple money".equals(colourS)) {
            colourString = "Red";
        } else if ("10 :Quadruple money".equals(colourS)) {
            colourString = "Blue";
        }

        return colourString;

    }

    //get the number of sides
    public int getSides(String values) {
        if ("4 :Double money".equals(values)) {
            sides = WHITE;
        } else if ("6 :Triple money".equals(values)) {
            sides = RED;
        } else if ("10 :Quadruple money".equals(values)) {
            sides = BLUE;
        }

        return sides;
    }

    //get value of dice as string
    public int getValue(String valueString) {
        if ("4 :Double money".equals(valueString)) {
            value = 2;
        } else if ("6 :Triple money".equals(valueString)) {
            value = 3;
        } else if ("10 :Quadruple money".equals(valueString)) {
            value = 4;
        }
        return value;
    }

    public String getValueAsString() {

        return valueString;


    }

    //rolling dice method
    public int rollDice(int sides) {

        int random = 0;
        if (sides == 4) {
            random = (int) ((int) 1 + Math.random() * 4);
        } else if (sides == 6) {
            random = (int) ((int) 1 + Math.random() * 6);

        } else if (sides == 10) {
            random = (int) ((int) 1 + Math.random() * 10);

        }
        return random;

    }
}