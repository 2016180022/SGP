package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    public int targetCount;

    public enum Job {
        darktemplar, demonslayer, vegabond;

        int bitmapId() {
            return BITMAP_IDS[this.ordinal()];
        }

        ArrayList<Bitmap> jobBitmap = new ArrayList<>();
    }

    private class SdInfo {
        int id;
        String name;
        int frame;
        float range;
    }

    private ArrayList<SdInfo> sdInfos;
    private int sdIndex;
    private void loadSdInfo() {
        sdInfos = new ArrayList<>();
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
                    } else if (name.equals("frame")) {
                        sdinfo.frame = reader.nextInt();
                    } else if (name.equals("range")) {
                        sdinfo.range = (float) reader.nextDouble();
                    }
                }
                reader.endObject();
                sdInfos.add(sdinfo);
            }
            reader.endArray();
            sdIndex = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSdImage(int sdIndex) {
        this.sdIndex = sdIndex;
        SdInfo sdinfo = sdInfos.get(sdIndex);
        Job j = Job.valueOf(sdinfo.name);
        if (j.jobBitmap.size() != 0) return;
        AssetManager assetManager = GameView.view.getContext().getAssets();
        try {
            for (int i = 0; i < sdinfo.frame; i++) {
                String filename = "img/" + sdinfo.id + "/idle/" + i + ".png";
                InputStream is = assetManager.open(filename);
//                sdBitmaps.add(BitmapFactory.decodeStream(is));
                j.jobBitmap.add(BitmapFactory.decodeStream(is));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    protected static int[] BITMAP_IDS = {
            R.mipmap.darktemplar,
            R.mipmap.demonslayer,
            R.mipmap.vegabond
    };

    private void init(Job jobName) {
        this.range = 1000.f;
        this.targetCount = 1;
        this.attackDelay = 2.0f;
        this.damage = 10;

        if (sdInfos == null) {
            loadSdInfo();
            Log.d(TAG, "loadSdInfo done in " + sdInfos.size() + " infos");
        }
        if (jobName.jobBitmap.size() == 0) {
            loadSdImage(jobName.ordinal());
            Log.d(TAG, jobName.ordinal() + "'s image load");
        }
        setImage(jobName.jobBitmap, sdInfos.get(jobName.ordinal()).frame);
        Log.d(TAG, "SetImage done in " + jobName.jobBitmap.size() + " images");
    }


    public float getRange() {
        return range;
    }

    public float getAttackDelay() { return attackDelay;
    }

    public float getDamage() {
        return damage;
    }

    public Sd(Job jobName, float left, float top) {
        super(left + MainGame.get().size(1) / 2, top + MainGame.get().size(1) / 2, MainGame.get().size(1), MainGame.get().size(1));
//        Log.d(TAG, "SD created in " + left + " ~ " + (left + MainGame.get().size(1) + ", " + top + " ~ " + (top + MainGame.get().size(1))));
        init(jobName);
    }
}
