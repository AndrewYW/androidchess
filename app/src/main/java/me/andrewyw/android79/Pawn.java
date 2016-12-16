package me.andrewyw.android79;

public class Pawn extends Piece {

    public Pawn(Player p, int s, int i) {
        super(p, s, i);
    }

    @Override
    public String toString(){
        return super.toString() + "P";
    }

    @Override
    public Move[] validMoves(Board b) {
        Move[] final_move_list;
        Move[] moves = new Move[4]; //pawn has max 4 moves
        int number_of_moves = 0;
        int current_space = this.space; //refers to current space that it is checking
        int multiplier = (this.getColor()) ? 1 : (-1);//Changes algorithm based on color

        //regular move
        Piece temp = null;
        current_space = this.space + multiplier*8;
        temp = b.getPiece_atSpace(current_space);
        if(Piece.onBoard(current_space) && temp == null){
            moves[number_of_moves] = new Move(this.getSpace(), current_space);
            number_of_moves++;
            //if hasn't moved yet
            if(!this.hasMoved()){
                current_space = this.space + multiplier*16;
                temp = b.getPiece_atSpace(current_space);
                if(temp == null){
                    moves[number_of_moves] = new Move(this.getSpace(), current_space);
                    moves[number_of_moves].setTwoStep();
                    number_of_moves++;
                }
            }
        }

        //capture moves
        temp = null;
        current_space = this.space;
        if(!Piece.isLeftBorder(current_space)){
            if(this.getColor()){
                current_space += 7;
                temp = b.getPiece_atSpace(current_space);
                if(Piece.onBoard(current_space) && (temp != null && temp.getColor() != this.getColor())){
                    moves[number_of_moves] = new Move(this.getSpace(), current_space);
                    number_of_moves++;
                }
            }
            else{
                current_space -= 9;
                temp = b.getPiece_atSpace(current_space);
                if(Piece.onBoard(current_space) && (temp != null && temp.getColor() != this.getColor())){
                    moves[number_of_moves] = new Move(this.getSpace(), current_space);
                    number_of_moves++;
                }
            }
        }
        temp = null;
        current_space = this.space;
        if(!Piece.isRightBorder(current_space)){
            if(this.getColor()){
                current_space += 9;
                temp = b.getPiece_atSpace(current_space);
                if(Piece.onBoard(current_space) && (temp != null && temp.getColor() != this.getColor())){
                    moves[number_of_moves] = new Move(this.getSpace(), current_space);
                    number_of_moves++;
                }
            }
            else{
                current_space -= 7;
                temp = b.getPiece_atSpace(current_space);
                if(Piece.onBoard(current_space) && (temp != null && temp.getColor() != this.getColor())){
                    moves[number_of_moves] = new Move(this.getSpace(), current_space);
                    number_of_moves++;
                }
            }
        }
        temp = null;
        current_space = this.space;

        //enpassant
        if(b.get_enpassantable() != -1){
            if(current_space+1 == b.get_enpassantable() && b.getPiece_atSpace(b.get_enpassantable()).getColor() != this.getColor()){
                if(!Piece.isRightBorder(current_space)){
                    if(this.getColor()){
                        current_space += 9;
                        if(Piece.onBoard(current_space)){
                            moves[number_of_moves] = new Move(this.getSpace(), current_space);
                            moves[number_of_moves].setEnp();
                            number_of_moves++;
                        }
                    }
                    else{
                        current_space -= 7;
                        if(Piece.onBoard(current_space)){
                            moves[number_of_moves] = new Move(this.getSpace(), current_space);
                            moves[number_of_moves].setEnp();
                            number_of_moves++;
                        }
                    }
                }
            }
            else if(current_space-1 == b.get_enpassantable() && b.getPiece_atSpace(b.get_enpassantable()).getColor() != this.getColor()){
                if(!Piece.isLeftBorder(current_space)){
                    if(this.getColor()){
                        current_space += 7;
                        if(Piece.onBoard(current_space)){
                            moves[number_of_moves] = new Move(this.getSpace(), current_space);
                            moves[number_of_moves].setEnp();
                            number_of_moves++;
                        }
                    }
                    else{
                        current_space -= 9;
                        if(Piece.onBoard(current_space)){
                            moves[number_of_moves] = new Move(this.getSpace(), current_space);
                            moves[number_of_moves].setEnp();
                            number_of_moves++;
                        }
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

}
