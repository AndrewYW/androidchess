package me.andrewyw.android79;

public class Board {
    private Piece[] space;
    private Player white, black;
    private int enpassantable;
    /**
     * Contructor for board.
     *
     * @param w the White player
     * @param b the Black player
     */
    public Board(Player w, Player b){
        space = new Piece[64];
        white =w;
        black =b;
        loadBoard();
        enpassantable = -1;
    }
    /**
     * Method to get the en passantability value
     * @return integer value for parsing en passant.
     */
    public int get_enpassantable(){
        return enpassantable;
    }
    /**
     * Sets the en passant value.
     * @param ep The new en passant value.
     */
    public void set_enpassantable(int ep){
        enpassantable = ep;
    }

    /**
     * Finds the piece (if there is one) at the ID'd space on the board.
     * @param i The ID value of the space
     * @return Piece on the space, if it exists
     */
    public Piece getPiece_atSpace(int i){
        if(i<0 || i>63){
            return null;
        }
        return space[i];
    }
    /**
     * Initializes the chess pieces onto the board.
     */
    public void loadBoard(){
        space = new Piece[64];
        for(int i=0; i<16; i++){
            if(white.getPiece_byID(i) != null){
                space[white.getPiece_byID(i).getSpace()] = white.getPiece_byID(i);
            }
            if(black.getPiece_byID(i) != null){
                space[black.getPiece_byID(i).getSpace()] = black.getPiece_byID(i);
            }
        }
    }
    /**
     * Prints board layout to console.
     */
    public void print(){
        String block = "   ";
        for(int j =0; j<8; j++){
            for(int i=64-((j+1)*8); i<64-(j*8); i++){
                if(space[i] == null){
                    block = ((i+j)%2 == 0) ? "##" : "  ";
                }
                else{
                    block = space[i].toString();
                }
                System.out.print(block + " ");
            }
            System.out.print((8 - j) + "\n");
        }
        System.out.println(" a  b  c  d  e  f  g  h");
    }

    public Piece[] getBoard(){
        return this.space;
    }
}