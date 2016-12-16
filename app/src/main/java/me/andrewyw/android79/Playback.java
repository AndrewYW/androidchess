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

    private int gameIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        int counter = 0;

        Button playNext = (Button)findViewById(R.id.nextMoveButton);
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dis is the playnext button thingy.
            }
        });
    }

    public void playbackHome(View v) {
        Intent intent = new Intent(this, Chess.class);
        startActivity(intent);
    }
}

