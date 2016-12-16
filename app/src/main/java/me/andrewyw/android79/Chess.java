package me.andrewyw.android79;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Chess extends AppCompatActivity {

    public static Game g;
    public static GameRecs gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chess);


    }

    public void play(View view){
        Intent intent = new Intent(this, PlayGame.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}
