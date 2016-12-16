package me.andrewyw.android79;

public class Move {
    /**
     * The beginning position of a piece
     */
    private int start;
    /**
     * The end position of a piece
     */
    private int end;
    /**
     * Boolean for enpassant-ability
     */
    private boolean enpassant;
    /**
     * Boolean for castling viability
     */
    private boolean castle;
    /**
     * Boolean to see if a pawn moved 2 squares or not.
     */
    private boolean twoStep;

    /**
     * Default constructor
     * @param s starting position
     * @param e ending position
     */
    public Move(int s, int e){
        start = s;
        end = e;
        enpassant = false;
        castle = false;
    }

    /**
     * Swap between enpassant boolean
     */
    public void setEnp(){
        enpassant = !enpassant;
    }

    /**
     * Swap between Castling boolean values
     */
    public void setCastle(){
        castle = !castle;
    }
    /**
     * Swap between two step boolean values
     */
    public void setTwoStep(){
        twoStep = !twoStep;
    }
    /**
     * Checks if a pawn is in position to be en passanted
     * @return true if it is viable
     */
    public boolean isEnp(){
        return enpassant;
    }
    /**
     * Checks to see if the king can perform Castling
     * @return true if it is viable
     */
    public boolean isCastle(){
        return castle;
    }

    /**
     * Setup for enpassant - checks if a pawn moved 2 squares
     * @return boolean value of 2 square movement
     */
    public boolean isTwoStep(){
        return twoStep;
    }

    /**
     * Finds starting position of piece
     * @return int starting value
     */
    public int getStart(){
        return start;
    }

    /**
     * Finds ending position of piece
     * @return int ending value
     */
    public int getEnd(){
        return end;
    }

    /**
     * Compares moves to see if they are equal
     * @return true if they are the same move
     */
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Move other_move = (Move)o;
        if(this.start == other_move.start && this.end == other_move.end)
            return true;
        else
            return false;
    }

    public String posToString(int pos){
        String st;
        switch(pos){
            case 0:
                st="a1";
                break;
            case 1:
                st="b1";
                break;
            case 2:
                st="c1";
                break;
            case 3:
                st="d1";
                break;
            case 4:
                st="e1";
                break;
            case 5:
                st="f1";
                break;
            case 6:
                st="g1";
                break;
            case 7:
                st="h1";
                break;
            case 8:
                st="a2";
                break;
            case 9:
                st="b2";
                break;
            case 10:
                st="c2";
                break;
            case 11:
                st="d2";
                break;
            case 12:
                st="e2";
                break;
            case 13:
                st="f2";
                break;
            case 14:
                st="g2";
                break;
            case 15:
                st="h2";
                break;
            case 16:
                st="a3";
                break;
            case 17:
                st="b3";
                break;
            case 18:
                st="c3";
                break;
            case 19:
                st="d3";
                break;
            case 20:
                st="e3";
                break;
            case 21:
                st="f3";
                break;
            case 22:
                st="g3";
                break;
            case 23:
                st="h3";
                break;
            case 24:
                st="a4";
                break;
            case 25:
                st="b4";
                break;
            case 26:
                st="c4";
                break;
            case 27:
                st="d4";
                break;
            case 28:
                st="e4";
                break;
            case 29:
                st="f4";
                break;
            case 30:
                st="g4";
                break;
            case 31:
                st="h4";
                break;
            case 32:
                st="a5";
                break;
            case 33:
                st="b5";
                break;
            case 34:
                st="c5";
                break;
            case 35:
                st="d5";
                break;
            case 36:
                st="e5";
                break;
            case 37:
                st="f5";
                break;
            case 38:
                st="g5";
                break;
            case 39:
                st="h5";
                break;
            case 40:
                st="a6";
                break;
            case 41:
                st="b6";
                break;
            case 42:
                st="c6";
                break;
            case 43:
                st="d6";
                break;
            case 44:
                st="e6";
                break;
            case 45:
                st="f6";
                break;
            case 46:
                st="g6";
                break;
            case 47:
                st="h6";
                break;
            case 48:
                st="a7";
                break;
            case 49:
                st="b7";
                break;
            case 50:
                st="c7";
                break;
            case 51:
                st="d7";
                break;
            case 52:
                st="e7";
                break;
            case 53:
                st="f7";
                break;
            case 54:
                st="g7";
                break;
            case 55:
                st="h7";
                break;
            case 56:
                st="a8";
                break;
            case 57:
                st="b8";
                break;
            case 58:
                st="c8";
                break;
            case 59:
                st="d8";
                break;
            case 60:
                st="e8";
                break;
            case 61:
                st="f8";
                break;
            case 62:
                st="g8";
                break;
            case 63:
                st="h8";
                break;
            default:
                st = "a1";
                break;
        }
        return st;
    }

    //for app
    public String toString(){
        String command;
        String start;
        String end;
        start = this.posToString(this.getStart());
        end = this.posToString(this.getEnd());
        command = start +" "+end;

        return command;
    }
}