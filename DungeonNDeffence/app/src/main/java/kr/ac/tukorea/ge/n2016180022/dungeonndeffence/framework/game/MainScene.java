package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();
    private boolean onTouch;
    private MobGenerater generater = new MobGenerater();
    private Sprite bgSprite;
    private int stageIndex;
    private int coin;
    private int stage, round;

    public UI ui;

    private ArrayList<Boolean> isEmpty = new ArrayList<>();
    public ArrayList<Mob> mobList = new ArrayList<>();

    private static MainScene singleton;
    public static MainScene get() {
        if (singleton == null) {
            singleton = new MainScene();
        }
        return singleton;
    }
    public enum Layer {
        bg, mob, sd, ui, env, COUNT
    }

    public int RES_MAP[] = { R.mipmap.tile_town1, R.mipmap.tile_evildom, R.mipmap.tile_wisdom };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                onTouch = !onTouch;
//                Log.d(TAG, event.getX() + ", " + event.getY());
                if (onTouch){

                    setSD(event.getX(), event.getY());
//                    setSD(event.getX(), event.getY() + block(), 7);
//                    setSD(event.getX(), event.getY() + 2 * block(), 0);
//                    setSD(event.getX(), event.getY() + 2 * block(), 3);
                }
//                if (onTouch) Log.d(TAG, "now on Touch");
                break;
        }
        return true;
    }

    public void setSD(float x, float y) {
        if (coin < 100) return;
        boolean xDone = false, yDone = false;

        int xIndex = (int) (x / block());
        int yIndex = (int) (y / block());

        if (1 < xIndex && xIndex < 10) xDone = true;
        if (1 < yIndex && yIndex < 5) yDone = true;

        if (xDone && yDone) {
            int index = 8 * (4 - yIndex) + xIndex - 1;
            if (isEmpty.get(index - 1)) {
                add(Layer.sd.ordinal(), new Sd(xIndex, yIndex));
                addCoin(-100);
//                Log.d(TAG, "Set Sd in " + x + ", " + y);
            }
            isEmpty.set(index - 1, false);
        }
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        initList();

        this.stageIndex = 1;
        setBG(stageIndex);

        stage = round = 1;

        coin = 200;
        add(Layer.ui.ordinal(), new UI(coin));

        add(Layer.env.ordinal(), generater);
        generater.startSpawn(stage, round);

        add(Layer.env.ordinal(), new AttackChecker());

//        Log.d(TAG, "Screen Size Is " + Metrics.width + ", " + Metrics.height);
    }

    public void setBG(int stageIndex) {
        int stage = (stageIndex - 1) / 2;
        if (bgSprite == null) {
            bgSprite = new Sprite(Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, RES_MAP[stage]);
            add(Layer.bg.ordinal(), bgSprite);
        }
        else bgSprite.setBitmap(RES_MAP[stage]);
    }

    public float block() {
        return Metrics.width / 12;
    }

    private void initList() {
        for (int i = 0; i < 24; i++) isEmpty.add(i, true);
    }

    public int getCoin() { return coin; }
    public void addCoin(int amount) { this.coin += amount; }

    public int getStage() {return this.stage;}
    public int getRound() {return this.round;}
    public void setStage(int stage) {this.stage = stage;}
    public void setRound(int round) {this.round = round;}

}