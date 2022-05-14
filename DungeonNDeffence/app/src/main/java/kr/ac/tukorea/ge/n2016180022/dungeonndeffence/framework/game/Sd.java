package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.util.Log;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;

public class Sd extends Sprite {

    private static final String TAG = Sd.class.getSimpleName();

    public enum Job {
        darktemplar, demonslayer, vegabond;

        int bitmapId() {
            return BITMAP_IDS[this.ordinal()];
        }

    }

    protected static int[] BITMAP_IDS = {
            R.mipmap.darktemplar,
            R.mipmap.demonslayer,
            R.mipmap.vegabond
    };

    public Sd(Job jobName, float left, float top) {
        super(left + MainGame.get().size(1) / 2, top + MainGame.get().size(1) / 2, MainGame.get().size(1), MainGame.get().size(1),  jobName.bitmapId());
//        Log.d(TAG, "SD created in " + left + " ~ " + (left + MainGame.get().size(1) + ", " + top + " ~ " + (top + MainGame.get().size(1))));
    }
}
