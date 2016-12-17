package me.andrewyw.android79;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Playback extends AppCompatActivity {

    static TextView playbackText;
    static int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playback);
        counter = 0;

        playbackText = (TextView)findViewById(R.id.playbackText);
        Button playNext = (Button)findViewById(R.id.nextMoveButton);
        playNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while(counter != Chess.recording.commands.size()){

                    String buff = Chess.recording.getCommand(counter);
                    String start = buff.substring(0, 2);
                    String end = buff.substring(3, 5);
                    ImageView ini = (ImageView)findViewById(R.id.backgroundBoard).findViewWithTag(start);
                    ImageView dest = (ImageView)findViewById(R.id.backgroundBoard).findViewWithTag(end);
                    dest.setImageDrawable(ini.getDrawable());
                    ini.setImageResource(R.drawable.blank);
                    Chess.g.players_turn(Chess.recording.getCommand(counter));

                    String player = (Chess.g.turn) ?"White":"Black";
                    playbackText.setText(player + "'s turn");
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

