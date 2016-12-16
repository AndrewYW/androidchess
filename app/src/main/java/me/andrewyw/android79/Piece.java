package me.andrewyw.android79;

/**
 * Created by Andrew on 12/15/2016.
 */
public abstract class Piece {
    /**
     * Piece's position on board (integer id)
     */
    protected int space;
    /**
     * Player, the owner of the piece
     */
    protected Player player;
    /**
     * Boolean, refers to if a piece has moved or not.
     */
    protected boolean moved;
    /**
     * Int, refers to where player stores the piece
     */
    protected int uniqueID;

    /**
     * Constructor for a Piece object
     * @param p The owner of the piece.
     * @param s The position of the piece.
     * @param i The ID of the piece.
     */
    public Piece(Player p, int s, int i){
        space = s;
        player = p;
        uniqueID = i;
        moved = false;
    }

    /**
     * Abstract method (implemented per piece) to generate the valid moves the piece can make.
     * @param b The board that the piece is on.
     * @return Array of Move objects
     */
    public abstract Move[] validMoves(Board b); //Implemented per piece

    /**
     * Method to see if a piece has been moved. Used for Castling, Pawn 2 step.
     * @return True if moved, false if not.
     */
    public boolean hasMoved(){
        return moved;
    }

    /**
     * Method to set the moved boolean of a piece to true
     */
    public void pieceMoved(){
        this.moved = true;
    }
    /**
     * Method to find get the owner of the piece (and his color)
     * @return Boolean color
     */
    public boolean getColor(){
        return player.getColor();
    }

    /**
     * Method to get the ID of a piece
     * @return int for ID
     */
    public int getID(){
        return uniqueID;
    }

    /**
     * Method to find the position of a piece
     * @return int The space number
     */
    public int getSpace(){
        return space;
    }

    /**
     * Changes position of the piece
     * @param s The space number for the piece to move to
     */
    public void occupy(int s){
        space = s;
    }

    /**
     * Method to make sure the input is legal (actually goes to a board space)
     * @param space The space to check legality
     * @return Boolean - true for on board, false if not
     */
    public static boolean onBoard(int space){
        return (space>=0 && space<=63);
    }
    /**
     * Method to check if a space is on the left border of the board.
     * @param space The space to check
     * @return Boolean - true if space is on left border
     */
    public static boolean isLeftBorder(int space){
        switch(space){
            case 56:
            case 48:
            case 40:
            case 32:
            case 24:
            case 16:
            case 8:
            case 0:
                return true;
            default:
                return false;
        }
    }
    /**
     * Method to check if a space is on the right border of the board.
     * @param space The space to check
     * @return Boolean - true if space is on right border
     */
    public static boolean isRightBorder(int space){
        switch(space){
            case 7:
            case 15:
            case 23:
            case 31:
            case 39:
            case 47:
            case 55:
            case 63:
                return true;
            default:
                return false;
        }
    }

    /**
     * Method to convert the piece into the String value to print.
     * Overriden in individual piece classes to add type
     * @return String title of piece (e.g: bR)
     */
    public String toString(){
        //Note, an abstract piece would only have a color and not a type.
        //In the class of an actual piece it can override toString calling super and adding the type.
        return (player.getColor()) ? "w" : "b";
    }
}