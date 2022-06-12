package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;

public class PausedScene extends Scene {
    private static PausedScene singleton;
    public static PausedScene get() {
        if (singleton == null) {
            singleton = new PausedScene();
        }
        return singleton;
    }

    public enum Layer {
        ui, COUNT
    }

    @Override
    public void init() {
        super.init();
        initLayers(Layer.COUNT.ordinal());

        add(Layer.ui.ordinal(), new Sprite(Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, R.mipmap.mob));
    }
}
