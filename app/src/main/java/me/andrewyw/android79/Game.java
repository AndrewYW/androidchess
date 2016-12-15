package me.andrewyw.android79;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    public void home(View view) {
        Intent intent = new Intent(this, Chess.class);
        startActivity(intent);
    }

    public void resign(View view) {

    }

    public void draw(View view) {

    }

    public void undo(View view) {

    }

}
