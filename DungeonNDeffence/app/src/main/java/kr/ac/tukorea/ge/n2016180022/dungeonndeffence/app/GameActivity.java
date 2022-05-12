package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game.BaseGame;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game.MainGame;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;


public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainGame.get();
        setContentView(new GameView(this, null));
    }

    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        BaseGame.clear();
        super.onDestroy();
    }
}