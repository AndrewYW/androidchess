package me.andrewyw.android79;

public class Knight extends Piece {

    public Knight(Player p, int s, int i) {
        super(p, s, i);
    }

    @Override
    public String toString(){
        return super.toString() + "N";
    }

    @Override
    public Move[] validMoves(Board b) {
        Move[] final_move_list;
        Move[] moves = new Move[8]; //knight has maximum 8 moves
        int number_of_moves = 0;
        int current_space = this.space; //refers to current space that it is checking
        Piece temp = null;

        if(!Piece.isLeftBorder(this.space)){
            temp = null;
            current_space = this.space + 15;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 17;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
        }
        if(!Piece.isLeftBorder(this.space-1)){
            temp = null;
            current_space = this.space + 6;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 10;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
        }
        if(!Piece.isRightBorder(this.space)){
            temp = null;
            current_space = this.space + 17;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 15;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
        }
        if(!Piece.isRightBorder(this.space+1)){
            temp = null;
            current_space = this.space + 10;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 6;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
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

}
