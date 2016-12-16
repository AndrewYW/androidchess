package me.andrewyw.android79;

public class King extends Piece {

    public King(Player p, int s, int i) {
        super(p, s, i);
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString(){
        return super.toString() + "K";
    }

    @Override
    public Move[] validMoves(Board b) {
        Move[] final_move_list;
        Move[] moves = new Move[10]; //max 10 moves for king
        int number_of_moves = 0;
        int current_space = this.space; //refers to current space that it is checking

        Piece temp = null;
        current_space = this.space + 8;
        temp = b.getPiece_atSpace(current_space);
        if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
            moves[number_of_moves] = new Move(this.getSpace(), current_space);
            number_of_moves++;
        }
        temp = null;
        current_space = this.space - 8;
        temp = b.getPiece_atSpace(current_space);
        if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
            moves[number_of_moves] = new Move(this.getSpace(), current_space);
            number_of_moves++;
        }

        if(!Piece.isRightBorder(this.space)){
            temp = null;
            current_space = this.space + 9;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space + 1;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 7;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
        }
        if(!Piece.isLeftBorder(this.space)){
            temp = null;
            current_space = this.space + 7;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 1;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
            temp = null;
            current_space = this.space - 9;
            temp = b.getPiece_atSpace(current_space);
            if(Piece.onBoard(current_space) && (temp == null || temp.getColor() != this.getColor())){
                moves[number_of_moves] = new Move(this.getSpace(), current_space);
                number_of_moves++;
            }
        }

        //Castling cases:
        temp = null;
        if(!this.hasMoved()){
            if(this.getColor()){
                //White version
                //King's side
                temp = b.getPiece_atSpace(7);
                if(temp != null && temp.toString().charAt(1)=='R' && !temp.hasMoved()){
                    temp = b.getPiece_atSpace(5);
                    if(temp == null){
                        temp = b.getPiece_atSpace(6);
                        if(temp==null){
                            moves[number_of_moves] = new Move(this.getSpace(), 6);
                            moves[number_of_moves].setCastle();
                            number_of_moves++;
                        }
                    }
                }
                temp = null;

                //Queen's side
                temp = b.getPiece_atSpace(0);
                if(temp != null && temp.toString().charAt(1)=='R' && !temp.hasMoved()){
                    temp = b.getPiece_atSpace(3);
                    if(temp == null){
                        temp = b.getPiece_atSpace(2);
                        if(temp == null){
                            temp = b.getPiece_atSpace(1);
                            if(temp==null){
                                moves[number_of_moves] = new Move(this.getSpace(), 1);
                                moves[number_of_moves].setCastle();
                                number_of_moves++;
                            }
                        }
                    }

                }
                temp = null;
            }
            else{
                //black version
                //King's side
                temp = b.getPiece_atSpace(63);
                if(temp != null && temp.toString().charAt(1)=='R' && !temp.hasMoved()){
                    temp = b.getPiece_atSpace(61);
                    if(temp == null){
                        temp = b.getPiece_atSpace(62);
                        if(temp==null){
                            moves[number_of_moves] = new Move(this.getSpace(), 62);
                            moves[number_of_moves].setCastle();
                            number_of_moves++;
                        }
                    }
                }
                temp = null;

                //Queen's side
                temp = b.getPiece_atSpace(56);
                if(temp != null && temp.toString().charAt(1)=='R' && !temp.hasMoved()){
                    temp = b.getPiece_atSpace(59);
                    if(temp == null){
                        temp = b.getPiece_atSpace(58);
                        if(temp == null){
                            temp = b.getPiece_atSpace(57);
                            if(temp==null){
                                moves[number_of_moves] = new Move(this.getSpace(), 57);
                                moves[number_of_moves].setCastle();
                                number_of_moves++;
                            }
                        }
                    }

                }
                temp = null;
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
