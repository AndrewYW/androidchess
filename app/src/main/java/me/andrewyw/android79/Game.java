package me.andrewyw.android79;

import java.util.Random;

/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
*/

/**
 * Game class runs the Chess program.
 * @author Andrew Wang
 * @author Rumzi Tadros
 *
 */
public class Game {
    Recording history; //added for app

    /**
     * The chess board.
     */
    Board board;
    /**
     * The two players - white and black.
     */
    Player white, black;
    /**
     * Boolean value to see whose turn it is.
     */
    boolean turn; //true for white, false for black
	/*A space that has a pawn that can be captured via enpassant
	 * negative implies none.
	 */

    /**
     * Boolean value for when a draw is requested
     */
    boolean draw_Requested;
    /**
     * Boolean value for when a draw is accepted or denied.
     */
    boolean draw;
    /**
     * Boolean value for when a player resigns
     */
    boolean resigned;
    /**
     * Boolean value for when a stalemate occurs
     */
    boolean stalemate;
    /**
     * Scanner to read the input from console.
     */
    //private Scanner kb;

    private char promoto;

    /**
     * Game constructor. Will also attempt to take an input text file for debugging.
     */
    public Game(){
        history = new Recording(); //added for app
        white = new Player(true);
        black = new Player(false);
        board = new Board(white, black);
        white.shareBoard(board);
        black.shareBoard(board);
        turn = true;
        draw_Requested = false;
        draw = false;
        resigned = false;
        stalemate = false;
        promoto = 'Q';
		/*
		try {
			File debug = new File("src/chess/debug.txt");
			if(debug.exists() && !debug.isDirectory()){
				kb = new Scanner(debug);
			}
			else{
				kb = new Scanner(System.in);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Debug File issue.");
			e.printStackTrace();
		}
		*/
    }

    /**
     * Method to play or end game. If/else statements for different possibilities: Continue,
     * in check, draws, stalemates, checkmates, resignation.
     */
	/*
	public void play(){
		boolean inGame = true;
		while(inGame){
			board.loadBoard();
			if(isCheckmate()){
				System.out.println("Checkmate");
				String winner = (!turn) ?"White":"Black";
				System.out.println(winner + " wins");
				kb.close();
				return;
			}
			else if(stalemate){
				System.out.println("Stalemate");
				System.out.println("Draw");
				kb.close();
				return;
			}
			else if(draw){
				System.out.println("Draw");
				kb.close();
				return;
			}
			else if(resigned){
				String winner = (turn) ?"White":"Black";
				System.out.println(winner + " wins");
				kb.close();
				return;
			}
			else if(isInCheck()){
				System.out.println("Check\n");
				board.print();
				players_turn();
			}
			else{
				board.print();
				players_turn();
			}
		}
	}
	*/

    /**
     * Method for checking if a player is in stalemate.
     * @return boolean - true for stalemate,
     */
    public boolean isCheckmate(){
		/*
		 * Generate player's move set
		 * If not in check and move set is null - set stalemate
		 * Try each move in set, if any are playable then return false
		 * If none are playable and isInCheck - return true
		 * else stalemate
		 */
        Move[] moveset = turn ? white.generate_Move_Set() : black.generate_Move_Set();
        if(moveset == null && !this.isInCheck()){
            stalemate = true;
            return false;
        }
        for(int i=0; i<moveset.length; i++){
            if(this.try_move(moveset[i])){
                return false;
            }
        }
        if(this.isInCheck()){
            return true;
        }
        else
            return false;
    }
    /**
     * Method for checking if a player is in check.
     * Checks by generating movesets, and seeing if any piece's move set includes the king's position.
     * @return true if king is in check.
     */
    public boolean isInCheck(){
        int kings_space = turn ? white.getKingSpace() : black.getKingSpace();
        Move[] moveset = !turn ? white.generate_Move_Set() : black.generate_Move_Set();
        for(int i=0; i<moveset.length; i++){
            if(moveset[i].getEnd() == kings_space){
                return true;
            }
        }
        return false;
    }
    /**
     * Method for turn, as well as input error checking.
     *
     */
    public boolean players_turn(String input){
        Move move = null;

        //*******************************************
        if(input.compareTo("resign")==0){
            resigned = true;
            turn = !turn;
            history.addMove(input);
            return true;
        }
        else if(input.compareTo("draw")==0){
            if(draw_Requested){
                draw = true;
                turn = !turn;
                history.addMove(input);
                return true;
            }
            else{
                //System.out.println("Bad input. No draw Requested. You may request a draw 'x# x# draw?' or 'resign'.");
                return false;
            }
        }
        else{
            if(input.length()==11){
                if(input.substring(6).compareTo("draw?")==0){
                    draw_Requested = true;
                    move = this.parse_move(input.substring(0, 6));
                    if(move == null){
                        //System.out.println("Bad input.");
                        return false;
                    }

                }

            }
            else if(input.length() == 5){
                move = this.parse_move(input);
                if(move == null){
                    //System.out.println("Bad input.");
                    return false;
                }
            }
            else if(input.length() == 7){
                move = this.parse_move(input.substring(0, 6));
                if(move == null){
                    //System.out.println("Bad input.");
                    return false;
                }
                switch(input.charAt(6)){
                    case 'Q':
                    case 'R':
                    case 'B':
                    case 'N':
                        promoto = input.charAt(6);
                        break;
                    case 'P':
                    case 'K':
                    default:
                        //System.out.println("Bad input.");
                        //move = null; //forces ask for new input
                        return false;
                }
            }

            else{
                //System.out.println("Bad input.");
                return false;
            }
        }
        if(move != null){
            if(this.play_move(move)){
                if(input.length() == 7){
                    if(turn){
                        if(move.getEnd() > 55 && move.getEnd() < 64){
                            white.promote(move.getEnd(), board.getPiece_atSpace(move.getEnd()).getID(), input.toString().charAt(6));
                        }
                    }else{
                        if(move.getEnd() > -1 && move.getEnd() < 8){
                            black.promote(move.getEnd(), board.getPiece_atSpace(move.getEnd()).getID(), input.toString().charAt(6));
                        }
                    }
                }
                turn = !turn;
                history.addMove(input);
                return true;
            }
            else{
                //System.out.println("Illegal move.");
                return false;
            }

        }
        //************************************************
        turn = !turn;
        history.addMove(input);
        return true;
        //System.out.println();
    }

    /**
     * Converts input lines into Move objects by converting to octal values
     * @param input The line input that a player enters (e.g: e2 e4)
     * @return Move object representing the player's input
     */
    public Move parse_move(String input){
        int start = 0;
        int end = 0;

        switch(input.charAt(0)){
            case 'a':
                start += 0;
                break;
            case 'b':
                start += 1;
                break;
            case 'c':
                start += 2;
                break;
            case 'd':
                start += 3;
                break;
            case 'e':
                start += 4;
                break;
            case 'f':
                start += 5;
                break;
            case 'g':
                start += 6;
                break;
            case 'h':
                start += 7;
                break;
            default:
                return null;
        }

        switch(input.charAt(1)){
            case '1':
                start += 0;
                break;
            case '2':
                start += 8;
                break;
            case '3':
                start += 16;
                break;
            case '4':
                start += 24;
                break;
            case '5':
                start += 32;
                break;
            case '6':
                start += 40;
                break;
            case '7':
                start += 48;
                break;
            case '8':
                start += 56;
                break;
            default:
                return null;
        }

        if(input.charAt(2)!=' ')
            return null;

        switch(input.charAt(3)){
            case 'a':
                end += 0;
                break;
            case 'b':
                end += 1;
                break;
            case 'c':
                end += 2;
                break;
            case 'd':
                end += 3;
                break;
            case 'e':
                end += 4;
                break;
            case 'f':
                end += 5;
                break;
            case 'g':
                end += 6;
                break;
            case 'h':
                end += 7;
                break;
            default:
                return null;
        }

        switch(input.charAt(4)){
            case '1':
                end += 0;
                break;
            case '2':
                end += 8;
                break;
            case '3':
                end += 16;
                break;
            case '4':
                end += 24;
                break;
            case '5':
                end += 32;
                break;
            case '6':
                end += 40;
                break;
            case '7':
                end += 48;
                break;
            case '8':
                end += 56;
                break;
            default:
                return null;
        }
        return new Move(start, end);
    }
    /**
     * Method to play a move
     * @param m Move object to be performed
     * @return boolean value for move success/failure
     */
    public boolean play_move(Move m){
        Move[] moveSet = (turn) ? white.generate_Move_Set() : black.generate_Move_Set();
        Move actual_move = null;
        for(int i=0; i<moveSet.length;i++){
            if(moveSet[i].equals(m)){
                actual_move = moveSet[i];
                break;
            }
        }
        if(actual_move == null){
            return false;
        }
        else{
            if(this.try_move(actual_move)){
                this.do_move(actual_move);
                promoto = 'Q';
                return true;
            }
            else{
                return false;
            }
        }
    }

    /**
     * Helper method for play_move.
     * @param m Move to be played.
     * @return boolean to see if move is viable.
     */
    public boolean try_move(Move m){
        boolean result = false;
        boolean canCastle = true;
        boolean promoted = false;
        Piece temp = null;

        if(m.isEnp()){
            temp = board.getPiece_atSpace(board.get_enpassantable());
            if(!turn){
                if(temp!=null){
                    white.remove(temp.getID());
                }
            }
            else{
                if(temp!=null){
                    black.remove(temp.getID());
                }
            }
        }
        else if(m.isCastle()){
            if(m.getEnd() == 6){
                canCastle = this.try_move(new Move(m.getStart(), 5));
                board.getPiece_atSpace(7).occupy(5);
            }
            else if(m.getEnd() == 1){
                canCastle = this.try_move(new Move(m.getStart(), 2));
                board.getPiece_atSpace(0).occupy(2);
            }
            else if(m.getEnd() == 57){
                canCastle = this.try_move(new Move(m.getStart(), 58));
                board.getPiece_atSpace(56).occupy(58);
            }
            else if(m.getEnd() == 62){
                canCastle = this.try_move(new Move(m.getStart(), 61));
                board.getPiece_atSpace(63).occupy(61);
            }
        }
        else{
            temp = board.getPiece_atSpace(m.getEnd());
            if(!turn){
                if(temp!=null){
                    white.remove(temp.getID());
                }
            }
            else{
                if(temp!=null){
                    black.remove(temp.getID());
                }
            }
        }
        board.getPiece_atSpace(m.getStart()).occupy(m.getEnd());
        board.loadBoard();
        if(board.getPiece_atSpace(m.getEnd()).toString().charAt(1)=='P'){
            if(turn){
                promoted = white.promote(m.getEnd(), board.getPiece_atSpace(m.getEnd()).getID(), promoto);
            }
            else{
                promoted = black.promote(m.getEnd(), board.getPiece_atSpace(m.getEnd()).getID(), promoto);
            }

        }
        board.loadBoard();
        result = !this.isInCheck() && canCastle;
        board.getPiece_atSpace(m.getEnd()).occupy(m.getStart());
        board.loadBoard();
        if(promoted){
            if(turn){
                white.demote(board.getPiece_atSpace(m.getStart()).getID());
            }
            else
                black.demote(board.getPiece_atSpace(m.getStart()).getID());
        }
        if(m.isEnp()){
            if(!turn)
                white.re_add(temp);
            else
                black.re_add(temp);
        }
        else if(m.isCastle()){
            if(m.getEnd() == 6){
                board.getPiece_atSpace(5).occupy(7);
            }
            else if(m.getEnd() == 1){
                board.getPiece_atSpace(2).occupy(0);
            }
            else if(m.getEnd() == 57){
                board.getPiece_atSpace(58).occupy(56);
            }
            else if(m.getEnd() == 62){
                board.getPiece_atSpace(61).occupy(63);
            }
        }
        else{
            if(temp != null){
                if(!turn)
                    white.re_add(temp);
                else
                    black.re_add(temp);
            }
        }
        board.loadBoard();
        return result;
    }
    /**
     * Helper method for play_move. Actually performs the move after testing viability.
     * @param m The move to be played.
     */
    public void do_move(Move m){
        //System.out.print(board.getPiece_atSpace(m.getStart())); //for debugging
        Piece temp = null;
        if(m.isEnp()){
            temp = board.getPiece_atSpace(board.get_enpassantable());
            if(!turn){
                if(temp!=null){
                    white.remove(temp.getID());
                }
            }
            else{
                if(temp!=null){
                    black.remove(temp.getID());
                }
            }
        }
        else if(m.isCastle()){
            if(m.getEnd() == 6){
                board.getPiece_atSpace(7).occupy(5);
            }
            else if(m.getEnd() == 1){
                board.getPiece_atSpace(0).occupy(2);
            }
            else if(m.getEnd() == 57){
                board.getPiece_atSpace(56).occupy(58);
            }
            else if(m.getEnd() == 62){
                board.getPiece_atSpace(63).occupy(61);
            }
        }
        else{
            temp = board.getPiece_atSpace(m.getEnd());
            if(!turn){
                if(temp!=null){
                    white.remove(temp.getID());
                }
            }
            else{
                if(temp!=null){
                    black.remove(temp.getID());
                }
            }
        }
        board.getPiece_atSpace(m.getStart()).occupy(m.getEnd());
        board.loadBoard();
        board.getPiece_atSpace(m.getEnd()).pieceMoved();
        if(m.isTwoStep()){
            board.set_enpassantable(m.getEnd());
        }
        else{
            board.set_enpassantable(-1);
        }
        if(board.getPiece_atSpace(m.getEnd()).toString().charAt(1)=='P'){
            if(turn)
                white.promote(m.getEnd(), board.getPiece_atSpace(m.getEnd()).getID(), promoto);
            else
                black.promote(m.getEnd(), board.getPiece_atSpace(m.getEnd()).getID(), promoto);
        }
        board.loadBoard();
    }

    //added for the app
    public String[] getBoard(){
        String[] boardlayout = new String[64];
        for(int i = 0; i < 64; i++){
            if(board.getBoard()[i] != null){
                boardlayout[i] = board.getBoard()[i].toString();
            }
            else{
                boardlayout[i] = " ";
            }
        }
        return boardlayout;
    }

    public String getRand(){
        String command = "";
        Move[] set = (turn) ? white.generate_Move_Set() : black.generate_Move_Set();
        Random rn = new Random();
        int options = set.length;
        int i = rn.nextInt()%(options - 1);
        if(i<0){
            i = i * -1;
        }
        command = set[i].toString();


        return command;
    }

    public Recording getGame(){
        return history;
    }

    public Game undo(){
        history.remLast();
        Game g = new Game();
        for(String c : history.commands){
            g.players_turn(c);
        }
        return g;
    }
}





