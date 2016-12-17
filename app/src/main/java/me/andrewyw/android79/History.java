package me.andrewyw.android79;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class History extends AppCompatActivity {

    private ListView listview;
    private ArrayList<Recording> games;
    static boolean loaded;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        loaded = false;
        //Load games from file
        games = (ArrayList<Recording>)Chess.gr.games;
        if(games != null){ loaded = true;}

        if(loaded) {
            listview = (ListView) findViewById(R.id.historyList);
            listview.setAdapter(new ArrayAdapter<>(this, R.layout.historygame, games));
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    playback(position);
                }
            });
        }

        Button nameSort = (Button)findViewById(R.id.nameSortButton);
        nameSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded){
                    Collections.sort(games, new Comparator<Recording>() {
                        @Override
                        public int compare(Recording lhs, Recording rhs) {
                            return lhs.compareTo(rhs);
                        }
                    });

                    listview.setAdapter(new ArrayAdapter<>(context, R.layout.historygame, games));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            playback(position);
                        }
                    });
                }
            }
        });

        Button dateSort = (Button)findViewById(R.id.dateSortButton);
        dateSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaded){
                    Collections.sort(games, new Comparator<Recording>() {
                        @Override
                        public int compare(Recording lhs, Recording rhs) {
                            return lhs.compareDate(rhs);
                        }
                    });
                    listview.setAdapter(new ArrayAdapter<>(context, R.layout.historygame, games));
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            playback(position);
                        }
                    });
                }
            }
        });
    }

    public void playback(int pos){

        Chess.recording = games.get(pos);

        Intent intent = new Intent(this, Playback.class);

        startActivity(intent);
    }

    public void historyHome(View view) {
        Intent intent = new Intent(this, Chess.class);
        startActivity(intent);
    }
}
