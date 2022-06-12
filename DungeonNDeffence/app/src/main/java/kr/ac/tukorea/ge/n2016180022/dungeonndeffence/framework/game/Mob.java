package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.util.Gauge;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;

public class Mob extends AnimSprite {
    private static final String TAG = Mob.class.getSimpleName();
    private static float speed = 200;
    private static float drawTime = 0.4f;
    private static float block = MainScene.get().block();
    private float elapsedTime;
    private int desIndex;
    private Gauge gauge;
    private float hp;
    public float currentHp;

    private State state = State.idle;
    private int stageIndex;
    private int bitmapIndex;
    private int dyingFrameCount = 8;
    private int idleFrameCount = 4;

    public enum Stage {
        gran01, gran02, evil01, evil02, wisdom01, wisdom02, COUNT;

    };

    enum State {
        idle, dying, dead
    }

    private ArrayList<Bitmap> drawBitmap = new ArrayList<>();
    private static ArrayList<StateBitmap> stageBitmap = new ArrayList<>();

    private class StateBitmap {
        ArrayList<Bitmap> idleBitmap = new ArrayList<>();
        ArrayList<Bitmap> dyingBitmap = new ArrayList<>();
    }

    private ArrayList<Position> desList = new ArrayList<>();
    static Position des1 = new Position((int)block, (int)(5 * block));
    static Position des2 = new Position((int)(10 * block), (int)(5 * block));
    static Position des3 = new Position((int)(10 * block), (int)block);
    static Position des4 = new Position((int)block, (int)block);


    public Mob(int stageIndex, float hp) {
        this.stageIndex = stageIndex - 1;
        init(hp);
    }

    private void init(float hp) {
        for (int i = 0; i < 4; i++) {
            desList.add(DES_POS[i]);
        }

        this.currentHp = this.hp = hp;
        this.state = State.idle;

        if (stageBitmap.size() == 0) loadAllMobImage();
        drawBitmap = stageBitmap.get(stageIndex).idleBitmap;

        this.x = block;
        this.y = block;
        this.w = block;
        this.h = block;

        gauge = new Gauge(Metrics.size(R.dimen.mob_gauge_fgWidth), R.color.mob_gauge_fg, Metrics.size(R.dimen.mob_gauge_bgWidth), R.color.mob_gauge_bg, block / 2);
    }

    @Override
    public void draw(Canvas canvas) {
//        super.draw(canvas);
        this.bitmap = drawBitmap.get(bitmapIndex);

        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        dstRect.offset(block / 2, block / 2);

        if (bitmap != null) canvas.drawBitmap(bitmap, null, dstRect, null);

        gauge.setValue(currentHp / this.hp);
        gauge.draw(canvas, this.x + block / 2, this.y);
    }

    @Override
    public void update(float frameTime) {
        if (state == State.dead) return;
        elapsedTime += frameTime;
//        Log.d(TAG, "Mobs' frameTime is " + frameTime);
        if (elapsedTime > drawTime) {
            bitmapIndex++;
            elapsedTime = 0;
        }
//        Log.d(TAG, "mob's bitmapIndex is " + bitmapIndex);

        if (state == State.dying) {
            if (bitmapIndex >= dyingFrameCount - 1) state = State.dead;
        }
        else
        {
            if (bitmapIndex >= idleFrameCount) bitmapIndex = 0;
        }

        float d = speed * frameTime;
        Position des = DES_POS[desIndex];

        move(d, des);
    }

    private void loadAllMobImage() {
        AssetManager assetManager = GameView.view.getContext().getAssets();
        try {
            for (int i = 0; i < Stage.COUNT.ordinal(); i++) {
                StateBitmap sBitmap = new StateBitmap();

                //load idle bitmap
                for (int j = 0; j < idleFrameCount; j++) {
                    String filename = "img/mob/" + (i + 1) + "/idle/" + j + ".png";
                    InputStream is = assetManager.open(filename);
                    sBitmap.idleBitmap.add(BitmapFactory.decodeStream(is));
                    Log.d(TAG, "load " + j + " idle state image");
                }
                //load dying bitmap
                for (int k = 0; k < dyingFrameCount; k++) {
                    String filename = "img/mob/" + (i + 1) + "/dying/" + k + ".png";
                    InputStream is = assetManager.open(filename);
                    sBitmap.dyingBitmap.add(BitmapFactory.decodeStream(is));
                    Log.d(TAG, "load " + k + " dying state image");
                }
                stageBitmap.add(sBitmap);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void kill() {
        if (this.state == State.idle) {
            this.state = State.dying;
            drawBitmap = stageBitmap.get(stageIndex).dyingBitmap;
            bitmapIndex = 0;
        }
    }

    public State getState() {return this.state;}


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
