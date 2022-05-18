package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.hardware.lights.LightState;

import java.util.ArrayList;
import java.util.List;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;

public class Mob extends Sprite {
    public enum Stage {
//        gran01, gran02, evil01, evil02, wisdom01, wisdom02;
        gran01;

        int bitmapId() {return BITMAP_IDS[this.ordinal()];}
    };

    protected float spawnX, spawnY, width, height;
    protected static float speed = 500;
    ArrayList<Position> desList = new ArrayList<>();
    protected int desIndex;
    public float hp;

    static Position des1 = new Position(0, 500);
    static Position des2 = new Position(1200, 500);
    static Position des3 = new Position(1200, 0);
    static Position des4 = new Position(0, 0);


    public Mob(Stage stage, float hp) {
//        super(spawnX, spawnY, width, height, stage.bitmapId());
        super(0, 0, 1000, 1000, stage.bitmapId());
        init();
    }

    private void init() {
        for (int i = 0; i < 4; i++) {
            desList.add(DES_POS[i]);
        }

        this.hp = hp;
    }

    @Override
    public void update(float frameTime) {
        float d = speed * frameTime;
        Position des = DES_POS[desIndex];

        move(d, des);
    }

    protected static int[] BITMAP_IDS = {
            R.mipmap.mob
    };

    protected static Position[] DES_POS = {
            des1, des2, des3, des4
    };

    protected void moveY(float d) {
        this.y += d;

        dstRect.offset(0, d);
    }

    protected void moveX(float d) {
        this.x += d;
        dstRect.offset(d, 0);
    }

    private void move(float d, Position des) {
        switch (desIndex) {
            case 0:
                moveY(d);
                if (this.y >= des.y) {
                    moveY(-d);
                    desIndex++;
                }
                break;
            case 1:
                moveX(d);
                if (this.x >= des.x) {
                    moveX(-d);
                    desIndex++;
                }
                break;
            case 2:
                moveY(-d);
                if (this.y <= des.y) {
                    moveY(d);
                    desIndex++;
                }
                break;
            case 3:
                moveX(-d);
                if (this.x <= des.x) {
                    moveX(d);
                    desIndex = 0;
                }
                break;
        }
    }
}
