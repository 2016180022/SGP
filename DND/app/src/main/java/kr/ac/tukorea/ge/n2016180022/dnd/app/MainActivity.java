package kr.ac.tukorea.ge.n2016180022.dnd.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.ac.tukorea.ge.n2016180022.dnd.R;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnChar;
    private Button btnEqu;
    private Button btnQuit;

    public int State;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnStart = findViewById(R.id.startButton);
        btnChar = findViewById(R.id.charButton);
        btnEqu = findViewById(R.id.equButton);
        btnQuit = findViewById(R.id.quitButton);
    }

    public void OnClick(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        switch(view.getId())
        {
            case R.id.startButton:
                intent.putExtra("actState", "GAME");
                break;
            case R.id.charButton:
                intent.putExtra("actState", "CHAR");
                break;
            case R.id.equButton:
                intent.putExtra("actState", "EQU");
                break;
        }
        startActivity(intent);
    }

}