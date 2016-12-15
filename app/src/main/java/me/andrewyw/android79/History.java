package me.andrewyw.android79;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class HistoryObject {
    String name;
    String date;
    ArrayList<String> moves;

    HistoryObject(String name, String date, ArrayList<String> moves){
        this.name = name;
        this.date = date;
        this.moves = moves;
    }

    // Used by ListView
    public String toString() {
        return name + "\n(" + date + ")";
    }

}

public class History extends AppCompatActivity {

    private ListView listview;
    private ArrayList<HistoryObject> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //Load games from file
        try {
            FileInputStream stream = openFileInput("");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            //rest of loading from file according to input style
        }catch (IOException e){
            //No file/empty file
            System.out.println("IO error");
        }

        listview = (ListView)findViewById(R.id.historyList);
        listview.setAdapter(new ArrayAdapter<HistoryObject>(this, R.layout.historygame,games));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playback(position);
            }
        });
    }

    public void playback(int pos){
        Bundle bundle = new Bundle();

        HistoryObject game = games.get(pos);
        bundle.putInt(Playback.GAME_INDEX, pos);
        bundle.putString(Playback.GAME_NAME, game.name);
        bundle.putString(Playback.GAME_DATE, game.date);

        //Need to read up on how to map string arraylists.
        //bundle.putStringArrayList(Playback.GAME_MOVES, game.moves);

        Intent intent = new Intent(this, Playback.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void historyHome(View view) {
        Intent intent = new Intent(this, Chess.class);
        startActivity(intent);
    }
}
