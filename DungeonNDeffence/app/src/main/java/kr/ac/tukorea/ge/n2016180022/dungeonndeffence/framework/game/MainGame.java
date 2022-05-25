package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private boolean onTouch;
    private MobGenerater generater = new MobGenerater();

    private ArrayList<Boolean> isEmpty = new ArrayList<>();
    public ArrayList<Mob> mobList = new ArrayList<>();

    public static MainGame get() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return (MainGame)singleton;
    }
    public enum Layer {
        bg, mob, sd, env, COUNT
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                onTouch = !onTouch;
//                Log.d(TAG, event.getX() + ", " + event.getY());
                if (onTouch){
                    setSD(event.getX(), event.getY(), 0);
//                    setSD(event.getX(), event.getY() + block(), 1);
//                    setSD(event.getX(), event.getY() - block(), 2);
//                    setSD(event.getX(), event.getY() - 2 * block(), 3);
                }
//                if (onTouch) Log.d(TAG, "now on Touch");
                break;
        }
        return true;
    }

    public void setSD(float x, float y, int sdIndex) {
        boolean xDone = false, yDone = false;

        int xIndex = (int) (x / block());
        int yIndex = (int) (y / block());

        if (1 < xIndex && xIndex < 10) xDone = true;
        if (1 < yIndex && yIndex < 5) yDone = true;

        if (xDone && yDone) {
            int index = 8 * (4 - yIndex) + xIndex - 1;
            if (isEmpty.get(index - 1)) {
                add(Layer.sd.ordinal(), new Sd(sdIndex, xIndex * block(), yIndex * block()));
                Log.d(TAG, "Set Sd in " + x + ", " + y);
            }
            isEmpty.set(index - 1, false);
        }
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        initList();

        add(Layer.bg.ordinal(), new Sprite(Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, R.mipmap.tile_wisdom));
        add(Layer.env.ordinal(), generater);
        generater.startSpawn(1);

        add(Layer.env.ordinal(), new AttackChecker());

//        Log.d(TAG, "Screen Size Is " + Metrics.width + ", " + Metrics.height);
    }

    public float size(float unit) {
        return unit * Metrics.height * 0.6f;
    }

    public float block() {
        return Metrics.width / 12;
    }

    private void initList() {
        for (int i = 0; i < 24; i++) isEmpty.add(i, true);
    }

}