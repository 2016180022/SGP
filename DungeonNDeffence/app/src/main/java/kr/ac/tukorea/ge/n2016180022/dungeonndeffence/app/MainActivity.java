package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game.MainGame;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainGame.get();
        setContentView(new GameView(this, null));
    }

    public void onBtnStart(View view) {
        startActivity(new Intent(this, GameActivity.class));
    }
}