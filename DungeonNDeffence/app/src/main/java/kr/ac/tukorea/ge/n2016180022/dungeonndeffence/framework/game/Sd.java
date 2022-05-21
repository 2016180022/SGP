package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.renderscript.ScriptGroup;
import android.util.JsonReader;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;

public class Sd extends AnimSprite {
    private static final String TAG = Sd.class.getSimpleName();
    private float range;
    private float attackDelay;
    private float damage;
    private int frameCount;
    private long createdOn;
    private float framesPerSecond = 5;
    public int targetCount;

    //now drawing bitmap
    private ArrayList<Bitmap> drawBitmap = new ArrayList<>();
    //all-job bitmap list
    private static ArrayList<StateBitmap> jobBitmap = new ArrayList<>();
    //all-job SdInfo list
    private static ArrayList<SdInfo> sdInfos = new ArrayList<>();
    //this sd info
    private SdInfo info;

    private class SdInfo {
        int id;
        String name;
        int idleFrame;
        int attackFrame;
        float range;
    }

    class StateBitmap {
        ArrayList<Bitmap> idleBitmap = new ArrayList<>();
        ArrayList<Bitmap> attackBitmap = new ArrayList<>();
    }

    public Sd(int sdIndex, float left, float top) {
        super(left + MainGame.get().size(1) / 2, top + MainGame.get().size(1) / 2, MainGame.get().size(1), MainGame.get().size(1));
        createdOn = System.currentTimeMillis();
        init(sdIndex);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        int index = Math.round(time * framesPerSecond) % frameCount;
        this.bitmap = drawBitmap.get(index);
        if (bitmap != null) canvas.drawBitmap(bitmap, null, dstRect, null);
    }

    private void init(int sdIndex) {
        if (sdInfos.size() == 0) {
            loadSdInfo();
            Log.d(TAG, "loadSdInfo done in " + sdInfos.size() + " infos");
        }

        if (jobBitmap.size() == 0) {
            loadAllSdImage();
            Log.d(TAG, "loadAllImage in " + jobBitmap.size() + "bitmaps");
        }

        this.info = sdInfos.get(sdIndex);
        this.range = info.range;
        changeBitmap(0);

        //need to add data type in json file
        this.targetCount = 1;
        this.attackDelay = 2.0f;
        this.damage = 10;

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
                    String filename = "img/" + info.id + "/idle/" + j + ".png";
                    InputStream is = assetManager.open(filename);
                    sBitmap.idleBitmap.add(BitmapFactory.decodeStream(is));
                    Log.d(TAG, "load " + j + " idle state image");
                }
                //load attack bitmap
//                for (int k = 0; k < info.attackFrame; k++) {
//                    String filename = "img/" + info.id + "/attack/" + k + ".png";
//                    InputStream is = assetManager.open(filename);
//                    sBitmap.attackBitmap.add(BitmapFactory.decodeStream(is));
//                    Log.d(TAG, "load " + k + " attack state image");
//                }
                jobBitmap.add(sBitmap);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void changeBitmap(int state) {
        if (state == 0) {
            this.frameCount = this.info.idleFrame;
            drawBitmap = jobBitmap.get(this.info.id).idleBitmap;
        }
        else if (state == 1) {
            this.frameCount = this.info.attackFrame;
            drawBitmap = jobBitmap.get(this.info.id).attackBitmap;
        }
    }

    public float getRange() {
        return range;
    }

    public float getAttackDelay() { return attackDelay;
    }

    public float getDamage() {
        return damage;
    }
}
