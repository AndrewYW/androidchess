package me.andrewyw.android79;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Playback extends AppCompatActivity {


    static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);

        Button playNext = (Button)findViewById(R.id.nextMoveButton);
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(counter != Chess.recording.commands.size()){
                    Chess.g.players_turn(Chess.recording.getCommand(counter));
                    counter++;
                }

            }
        });
    }

    public void playbackHome(View v) {
        Intent intent = new Intent(this, Chess.class);
        startActivity(intent);
    }
}

