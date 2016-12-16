package me.andrewyw.android79;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Playback extends AppCompatActivity {

    public static final String GAME_INDEX = "gameIndex";
    public static final String GAME_NAME = "gameName";
    public static final String GAME_DATE = "gameDate";
    public static final ArrayList<String> GAME_MOVES = null;
    static int counter = 0;

    private int gameIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        Recording record;


        Button playNext = (Button)findViewById(R.id.nextMoveButton);
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(!Chess.g.players_turn(Chess.g.getRand())){
                    Chess.g.players_turn(record.getCommand(counter));
                }
                counter++;
            }
        });
    }

    public void playbackHome(View v) {
        Intent intent = new Intent(this, Chess.class);
        startActivity(intent);
    }
}

