package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.util.Log;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;

public class MainGame extends BaseGame {
    private static final String TAG = MainGame.class.getSimpleName();

    public static MainGame get() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return (MainGame)singleton;
    }
    public enum Layer {
        bg, sd, COUNT
    }

    public float sdPositionX(int index) {
//        int column = index % R.dimen.tile_sd_column;
        int column = (index - 1) % 8;
        float fix = - block() / 4;
        return (column - 1) * block() + fix;
    }

    public float sdPositionY(int index) {
//        int row = (index - 1) / R.dimen.tile_sd_column;
        int row = (index - 1) / 8;
        return (-row + 1) * block();
    }

    public float size(float unit) {
        return unit * Metrics.height * 0.6f;
    }

    public float block() {
        return Metrics.width / 12;
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        add(Layer.bg.ordinal(), new Sprite(Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, R.mipmap.tile_wisdom));
        Log.d(TAG, "Screen Size Is " + Metrics.width + ", " + Metrics.height);
        add(Layer.sd.ordinal(), new Sd(Sd.Job.darktemplar, sdPositionX(17), sdPositionY(17)));
    }

}