package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

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
        player, COUNT
    }

    public void init() {
        initLayers(Layer.COUNT.ordinal());

        add(Layer.player.ordinal(), new Sprite(Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, R.mipmap.tile_wisdom));
    }

}