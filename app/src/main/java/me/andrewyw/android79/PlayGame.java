package me.andrewyw.android79;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PlayGame extends AppCompatActivity {
    final Context context = this;
    private int undone = 1;
    /*
        0 = allowed to undo
        Any other = not allowed
     */
    static String first = "";
    static String second = "";
    static String buffer = "";

    public void imageClick(View view){

        if(first.compareTo("") == 0){
            first = String.valueOf(view.getId());
            buffer = first + " ";
        }else if(first.compareTo("") != 0 && second.compareTo("") == 0){
            second = String.valueOf(view.getId());
            buffer += second + " ";
        }else if(first.compareTo("") != 0 && second.compareTo("") != 0){
            first = String.valueOf(view.getId());
            buffer = first + " ";
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Chess.g = new Game();
        Chess.g.board.loadBoard();
        String[] positions = Chess.g.getBoard();



        Button playMoveButton = (Button)findViewById(R.id.playButton);
        playMoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Chess.g.players_turn(buffer.trim())){
                    first = "";
                    second = "";
                    buffer = "";

                }
                else {
                    AlertDialog.Builder error = new AlertDialog.Builder(context);
                    error.setTitle("Invalid move");
                    error
                        .setMessage("Move not allowed.")
                        .setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                first = "";
                                second = "";
                                buffer = "";
                            }
                        });
                    error.show();
                }
            }
        });



        Button undoButton = (Button) findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (undone == 0){
                    /*undo move*/
                    Chess.g = Chess.g.undo();
                    undone = 1;
                }
                else {
                    AlertDialog.Builder noUndoAlert = new AlertDialog.Builder(context);
                    noUndoAlert.setTitle("Cannot Undo");
                    noUndoAlert
                        .setMessage("Undo cannot be used")
                        .setCancelable(false)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                    AlertDialog noUndo = noUndoAlert.create();
                    noUndo.show();
                }
            }
        });


        Button resignButton = (Button) findViewById(R.id.resignButton);
        resignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder resignAlert = new AlertDialog.Builder(context);
                resignAlert.setTitle("Warning: About to resign");
                resignAlert
                    .setMessage("Confirm resignation?")
                    .setCancelable(true)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            /*Resignation goes here*/
                            Chess.g.players_turn("resign");
                            String winner = (Chess.g.turn) ?"White":"Black";
                            gameOver(winner);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                AlertDialog resignAlertDialog = resignAlert.create();
                resignAlertDialog.show();
            }
        });


        Button drawButton = (Button) findViewById(R.id.drawButton);
        drawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                AlertDialog.Builder drawAlert = new AlertDialog.Builder(context);
                drawAlert.setTitle("Warning: about to Draw");

                drawAlert
                    .setMessage("Confirm draw request? (Other player must agree)")
                    .setCancelable(true)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                            if(buffer.length() == 6){
                                Chess.g.draw_Requested = true;
                                buffer += "draw?";
                                Chess.g.players_turn(buffer.trim());

                                AlertDialog.Builder drawAlert2 = new AlertDialog.Builder(context);
                                drawAlert2.setTitle("Other player requests draw");

                                drawAlert2
                                        .setMessage("Confirm draw request?")
                                        .setCancelable(true)
                                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                        /* This is where draw goes */
                                                    Chess.g.players_turn("draw");
                                                    gameOver("draw");
                                            }
                                        })
                                        .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog2, int id2 ){
                                                Chess.g.draw_Requested = false;
                                                dialog2.cancel();
                                                buffer = "";
                                            }
                                        });
                                AlertDialog drawAlert2Dialog = drawAlert2.create();
                                drawAlert2Dialog.show();
                            }
                            else{
                                AlertDialog.Builder error = new AlertDialog.Builder(context);
                                error.setTitle("Draw formatting error");
                                error
                                        .setMessage("Make sure to select positions first")
                                        .setCancelable(false)
                                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                                first = "";
                                                second = "";
                                                buffer = "";
                                            }
                                        });
                                error.show();
                            }


                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

                AlertDialog drawAlertDialog = drawAlert.create();
                drawAlertDialog.show();
            }
        });


    Button aiButton = (Button)findViewById(R.id.AIButton);
    aiButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            while(!Chess.g.players_turn(Chess.g.getRand())){
                Chess.g.players_turn(Chess.g.getRand());
            }
        }
    });
    }


    public void home(View view) {
        AlertDialog.Builder gameHome = new AlertDialog.Builder(context);
        gameHome.setTitle("Warning: About to leave game");

        gameHome
            .setMessage("Returning will result in your game being lost. Are you sure?")
            .setCancelable(true)
            .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(context, Chess.class);
                    startActivity(intent);
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

        AlertDialog gameHomeAlert = gameHome.create();
        gameHomeAlert.show();

    }

    public void gameOver(String winner) {
        AlertDialog.Builder gameOver = new AlertDialog.Builder(context);
        if(winner.equals("draw")){ gameOver.setTitle("Draw."); }
        else {
            gameOver.setTitle(winner + " wins."); }

        gameOver
            .setMessage("Record game?")
            .setCancelable(true)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Record game by asking for title

                    AlertDialog.Builder addRecord = new AlertDialog.Builder(context);
                    addRecord.setTitle("Add title to game.");
                    final EditText title = new EditText(context);
                    title.setInputType(InputType.TYPE_CLASS_TEXT);
                    addRecord.setView(title);

                    addRecord.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Title confirmed
                            Chess.gr.games.add(Chess.g.history.save(title.getText().toString()));
                        }
                    });
                    addRecord.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    addRecord.show();

                }
            })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

        gameOver.show();
    }


}
