/*----------------------------------------------------------------
 *  Author:   Caroline Frey
 *  Email:    crfrey20@g.holycross.edu
 *  Written:  12/1/16
 *  
 *  Each Cell object manages information about and draws a
 *  single "cell" of the game grid. 
 *----------------------------------------------------------------*/

import GUI.*;
import java.awt.Color;

/**
 * A <i>Cell</i> object holds all information about the state of a single cell
 * of the minesweeper game board. This includes:
 *   - whether a mine is hidden in this cell or not
 *   - how many of its neighboring cells contain mines
 *   - whether it has been revealed yet or is still hidden
 * Each Cell object knows how to draw itself in a graphical window, and it will
 * draw itself in different styles depending on all the above state information.
 */
public class Cell extends Widget {

    /**
     * Size of one cell when it is drawn on the screen, in pixels.
     */
    public static final int SIZE = 20;

    /**
     * Whether a mine is hidden in this cell or not.
     */
    protected boolean isMine;

    /**
     * Whether this cell is "revealed" or not.
     */
    protected boolean isRevealed;

    /**
     * Count of how many neighboring cells have mines.
     */
    protected int neighborMineCount;

    protected boolean isFlagged; //EC

    /**
     * Constructor: Initialize a cell to be drawn at the given x, y coordinates
     * on the screen. The cell will be blank. That is, it will not be a mine,
     * and it will have no neighboring mines so a neighbor mine count of zero.
     */
    public Cell(int x, int y) {
        super(x, y, SIZE, SIZE);
        this.isMine = false;
        this.isRevealed = false;
        this.neighborMineCount = 0;
    }

    /**
     * Hide a mine in this cell by changing the isMine variable to true.
     */
    public void plantMine() {
        isMine = true;
    }

    /**
     * Returns true if a mine is hidden in this cell, otherwise returns false.
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * Increment the neighbor mine count variable by one. 
     */
    public void incrementNeighborMineCount() {
        neighborMineCount++;
    }

    /**
     * Set the neighbor mine count variable to a given value.
     */
    public void setNeighborMineCount(int count) {
        neighborMineCount = count;
    }

    /**
     * Returns the value of the neighbor mine count variable.
     */
    public int getNeighborMineCount() {
        return neighborMineCount;
    }

    /**
     * Change this cell so that it is "revealed" by setting isRevealed to true.
     */
    public void reveal() {
        isRevealed = true;
    }

    /**
     * Returns true if this cell is "revealed", otherwise returns false.
     */
    public boolean isRevealed() {
        return isRevealed;
    }

    /**
     * Hide a mine in this cell by changing the isMine variable to true.
     */
    public void makeMine() {
        isMine = true;
    }

    /**
     * Change this cell so that it shows the mine that is hiding in it.
     */
    public void showMine() {
        if (isMine)
            isRevealed = true;
    }

    /**
     * Check whether there are neighboring mines.
     */
    public boolean coastIsClear() {
        return (neighborMineCount == 0);
    }

    public boolean isFlagged() {   //EC
        return isFlagged = true;
    }


    /**
     * Paint this cell on the canvas. Don't call this directly, it is called by
     * the GUI system automatically. This function should draw something on the
     * canvas. Usually the drawing should stay within the bounds (x, y, width,
     * height) which are protected member variables of GUI.Widget, which this
     * class extends.
     * @param canvas the canvas on which to draw.
     */
    public void repaint(GUI.Canvas canvas) {
        //Draw the cell
        int width = SIZE;
        int height = SIZE;
        canvas.setPenColor(Canvas.GRAY);
        canvas.setPenRadius(1.0);
        canvas.raisedBevelRectangle(x, y, width, height, 4.0); 

        if (isRevealed) {                                     //different style once cell is clicked (revealed)
            canvas.setPenColor(Canvas.GRAY);
            canvas.setPenRadius(1.0);
            canvas.filledRectangle(x, y, width, height);
        }

        if (isRevealed && isMine) {                           //style if clicked cell is a mine
            canvas.setPenColor(Canvas.RED);
            canvas.filledRectangle(x, y, width, height);
            canvas.picture(x, y, "bomb.png", width, height);
        }

        if (isRevealed && !isMine) {                 //style for cells that have mines nearby

            int color = getNeighborMineCount();      //store number of neighbor bombs as integer
            String reveal = Integer.toString(color); //print number of bombs by converting int to String
           
            if (color == 0) {
                    canvas.setPenColor(Color.GRAY);
                    canvas.filledRectangle(x, y, width, height);
                }
            if (color == 1) {                       
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.BLUE);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 2) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.GREEN);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 3) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.RED);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 4) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.MAGENTA);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 5) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.CYAN);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 6) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.YELLOW);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 7) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.BLACK);
                canvas.text(x + width/2, y + height/2, reveal);
            }
            if (color == 8) {
                canvas.setFont("Serif", 16);
                canvas.setPenColor(Color.ORANGE);
                canvas.text(x + width/2, y + height/2, reveal);
            }

        } //end if

        if (isFlagged) {
            canvas.picture(x, y, "flag.png", width, height);
        }

    } //end repaint

} //end class
