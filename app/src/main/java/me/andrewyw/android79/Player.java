package me.andrewyw.android79;


public class Player {
    /**
     * Boolean color of player: True for white, false for black.
     */
    private boolean color;
    /**
     * Array of Pieces that the player owns.
     */
    private Piece[] myPiece;
    /**
     * Reference to board object to generate moves and pass to validMoves method
     */
    private Board board;

    /**
     * Default Player constructor.
     * @param c Boolean color value of player
     */
    public Player(boolean c){
        color = c;
        createPieces();
        board = null;
    }
    /**
     * Method to determine what board to use for the game.
     * @param b The board to be used
     */
    public void shareBoard(Board b){
        board = b;
    }
    /**
     * Method to find the color of the player
     * @return boolean color value
     */
    public boolean getColor(){
        return color;
    }

    /**
     * Method to create possible moves for the player
     * @return Array of Move objects, holding all possible moves
     */
    public Move[] generate_Move_Set(){
        Move[] final_move_list;
        Move[] moves = new Move[139]; //Hypothetical max move count
        Move[] tmp_list = new Move[27]; //max count from any given move set per piece
        int number_of_moves = 0;

        for(int i=0;i<16;i++){
            if(myPiece[i] != null){
                tmp_list = myPiece[i].validMoves(board);
                if(tmp_list == null)
                    continue;
                for(int j=0; j<tmp_list.length; j++){
                    if(tmp_list[j] != null){
                        moves[number_of_moves] = tmp_list[j];
                        number_of_moves++;
                    }
                }
            }
        }


        //Returns an appropriately sized list or null if there are no moves.
        if(number_of_moves == 0)
            return null;
        else{
            final_move_list = new Move[number_of_moves];
            for(int i=0; i<number_of_moves ;i++)
                final_move_list[i] = moves[i];
            return final_move_list;
        }
    }

    /**
     * Method to find the location of the player's king on the board
     * @return int Position of the king
     */
    public int getKingSpace(){
        return this.getPiece_byID(4).getSpace();
    }

    /**
     * Method to find a piece by its unique ID
     * @param id The piece's ID
     * @return The piece if there is an ID match
     */
    public Piece getPiece_byID(int id){
        return myPiece[id];
    }

    /**
     * Method to remove a piece when it's captured
     * @param id ID of piece to be removed
     */
    public void remove(int id){
        //this is used when a piece is captured
        myPiece[id]=null;
    }

    /**
     * Method to readd a piece that's removed (used for generating movesets)
     * @param p Piece to be readded
     */
    public void re_add(Piece p){
        myPiece[p.getID()] = p;
    }

    /**
     * Method to promote pawn to a given piece
     * @param space The position of the pawn
     * @param id The ID of the piece to be promoted
     * @param to The char for which piece the pawn wants to be replaced with.
     */
    public boolean promote(int space, int id, char to){
        int min = (color) ? 56 : 0;
        int max = (color) ? 63 : 7;
        if(space >= min && space <= max){
            switch(to){
                case 'Q':
                    myPiece[id] = new Queen(this, space, id);
                    break;
                case 'R':
                    myPiece[id] = new Rook(this, space, id);
                    break;
                case 'B':
                    myPiece[id] = new Bishop(this, space, id);
                    break;
                case 'N':
                    myPiece[id] = new Knight(this, space, id);
                    break;
            }
            board.loadBoard();
            return true;
        }
        return false;
    }

    /**
     * Method to initialize the pieces that a player owns.
     */
    private void createPieces(){
        myPiece = new Piece[16];
        int num = 0;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[0] = new Rook(this, num, 0);
        num = 1;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[1] = new Knight(this, num, 1);
        num = 2;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[2] = new Bishop(this, num, 2);
        num = 3;
        if(!color){num = 59;}
        myPiece[3] = new Queen(this, num, 3);
        num = 4;
        if(!color){num = 60;}
        myPiece[4] = new King(this, num, 4);
        num = 5;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[5] = new Bishop(this, num, 5);
        num = 6;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[6] = new Knight(this, num, 6);
        num = 7;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[7] = new Rook(this, num, 7);
        num = 8;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[8] = new Pawn(this, num, 8);
        num = 9;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[9] = new Pawn(this, num, 9);
        num = 10;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[10] = new Pawn(this, num, 10);
        num = 11;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[11] = new Pawn(this, num, 11);
        num = 12;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[12] = new Pawn(this, num, 12);
        num = 13;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[13] = new Pawn(this, num, 13);
        num = 14;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[14] = new Pawn(this, num, 14);
        num = 15;
        if(!color){num = (-1)*(num-63);} //flips orientation of pieces for black placement.
        myPiece[15] = new Pawn(this, num, 15);
    }

    public void demote(int id){
        myPiece[id] = new Pawn(this, myPiece[id].getSpace(), id);
        System.out.println("Demoted" + this);
    }

    /**
     * Method to return the color of a piece
     */
    public String toString(){
        return (this.getColor()) ? "White" : "Black";
    }
}