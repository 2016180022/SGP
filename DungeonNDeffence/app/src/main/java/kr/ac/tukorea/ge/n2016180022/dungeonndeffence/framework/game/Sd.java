package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;

public class Sd extends AnimSprite {
    private static final String TAG = Sd.class.getSimpleName();
    private static final float SIZE_RATE = 4.0f;
    private static final float drawTime = 0.1f;
    private float block = MainScene.get().block();
    private int xIndex, yIndex;
    private float range;
    private float damage;
    private int frameCount;

    private int bitmapIndex;
    private float elapsedTime;

    private float beforeLeft, beforeBottom;

    private Random randomIndex = new Random();

    //now drawing bitmap
    private ArrayList<Bitmap> drawBitmap = new ArrayList<>();
    //all-job bitmap list
    private static ArrayList<StateBitmap> jobBitmap = new ArrayList<>();
    //all-job SdInfo list
    private static ArrayList<SdInfo> sdInfos = new ArrayList<>();
    //this sd info
    private SdInfo info;


    enum State {
        idle, attack
    }

    public State state = State.idle;

    private class SdInfo {
        int id;
        String name;
        int idleFrame;
        int attackFrame;
        float range;
        float width;
    }

    class StateBitmap {
        ArrayList<Bitmap> idleBitmap = new ArrayList<>();
        ArrayList<Bitmap> attackBitmap = new ArrayList<>();
    }

    public Sd(int xIndex, int yIndex) {
        beforeLeft = xIndex * block;
        beforeBottom = yIndex * block;

        this.xIndex = xIndex;
        this.yIndex = yIndex;

        init();
    }

    @Override
    public void update(float frameTime) {
        elapsedTime += frameTime;
//        Log.d(TAG, "ETime is " + elapsedTime);
        //1 bitmap frame
        if (elapsedTime > drawTime) {
            this.bitmapIndex++;
            elapsedTime = 0;
        }

        if (bitmapIndex >= frameCount) {
            bitmapIndex = 0;
            setState(State.idle);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.bitmap = drawBitmap.get(bitmapIndex);

        this.w = bitmap.getWidth() * SIZE_RATE;
        this.h = bitmap.getHeight() * SIZE_RATE;

        dstRect.set(left, bottom - h, left + w, bottom);
        if (bitmap != null) canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    private void init() {
        if (sdInfos.size() == 0) {
            loadSdInfo();
//            Log.d(TAG, "loadSdInfo done in " + sdInfos.size() + " infos");
        }

        if (jobBitmap.size() == 0) {
            loadAllSdImage();
//            Log.d(TAG, "loadAllImage in " + jobBitmap.size() + "bitmaps");
        }

        int sdIndex = randomIndex.nextInt(sdInfos.size());

        this.info = sdInfos.get(sdIndex);
        this.range = info.range;
//        Log.d(TAG, "index " + sdIndex + " resize to " + resizeBitmap(info.height));
        setState(State.idle);

        //need to add data type in json file
        this.damage = 100;

        resizeBitmap();
    }

    private void resizeBitmap() {
        this.left = beforeLeft - MainScene.get().block() * 3 - 80 - (this.info.width - 161) * 3.5f;
        this.bottom = beforeBottom + 160;
    }

    private void loadSdInfo() {
        AssetManager assetManager = GameView.view.getContext().getAssets();
        try {
            InputStream is = assetManager.open("Sd.json");
            JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
            reader.beginArray();
            while (reader.hasNext())
            {
                reader.beginObject();
                SdInfo sdinfo = new SdInfo();
                while (reader.hasNext()) {
                    String name = reader.nextName();
                    if (name.equals("id")) {
                        sdinfo.id = reader.nextInt();
                    } else if (name.equals("name")) {
                        sdinfo.name = reader.nextString();
                    } else if (name.equals("idleframe")) {
                        sdinfo.idleFrame = reader.nextInt();
                    } else if (name.equals("attackframe")) {
                        sdinfo.attackFrame = reader.nextInt();
                    } else if (name.equals("range")) {
                        sdinfo.range = (float) reader.nextDouble();
                    } else if (name.equals("width")) {
                        sdinfo.width = (float) reader.nextDouble();
                    }
                }
                reader.endObject();
                sdInfos.add(sdinfo);
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAllSdImage() {
        AssetManager assetManager = GameView.view.getContext().getAssets();
        try {
            for (int i = 0; i < sdInfos.size(); i++) {
                Sd.SdInfo info = sdInfos.get(i);
                StateBitmap sBitmap = new StateBitmap();

                //load idle bitmap
                for (int j = 0; j < info.idleFrame; j++) {
                    String filename = "img/sd/" + info.id + "/idle/" + j + ".png";
                    InputStream is = assetManager.open(filename);
                    sBitmap.idleBitmap.add(BitmapFactory.decodeStream(is));
//                    Log.d(TAG, "load " + j + " idle state image");
                }
                //load attack bitmap
                for (int k = 0; k < info.attackFrame; k++) {
                    String filename = "img/sd/" + info.id + "/attack/" + k + ".png";
                    InputStream is = assetManager.open(filename);
                    sBitmap.attackBitmap.add(BitmapFactory.decodeStream(is));
//                    Log.d(TAG, "load " + k + " attack state image");
                }
                jobBitmap.add(sBitmap);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void changeBitmap(State state) {
        if (state == State.idle) {
            this.frameCount = this.info.idleFrame;
            drawBitmap = jobBitmap.get(this.info.id).idleBitmap;
        }
        else if (state == State.attack) {
            this.frameCount = this.info.attackFrame;
            drawBitmap = jobBitmap.get(this.info.id).attackBitmap;
        }
    }

    public void setState(State state) {
        this.state = state;
        changeBitmap(state);
        bitmapIndex = 0;
//        Log.d(TAG, "Set SD to " + state + "STATE");
    }

    public float attack(Mob m) {
        setState(State.attack);
        return m.currentHp -= this.damage;
    }

    public float getRange() {
        return range;
    }

    public int getPos() {return 100 * yIndex + xIndex;}

}
