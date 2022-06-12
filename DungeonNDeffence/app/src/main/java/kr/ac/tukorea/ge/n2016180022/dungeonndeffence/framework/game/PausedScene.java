package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Button;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.view.GameView;

public class PausedScene extends Scene {
    private static PausedScene singleton;
    public static PausedScene get() {
        if (singleton == null) {
            singleton = new PausedScene();
            singleton.init();
        }
        return singleton;
    }

    public enum Layer {
        ui, touchUi, COUNT
    }

    @Override
    public void init() {
        super.init();
        initLayers(Layer.COUNT.ordinal());

        add(Layer.ui.ordinal(), new Sprite(Metrics.width / 2, Metrics.height / 2, Metrics.width, Metrics.height, R.mipmap.pause_bg));

        float btn_width = Metrics.width / 2;
        float btn_height = btn_width * 56 / 350;
        float btn_x = Metrics.width / 2;
        float btn_y = Metrics.height / 2 + btn_height / 2;

        add(Layer.touchUi.ordinal(), new Button(btn_x, btn_y, btn_width, btn_height, R.mipmap.button_back, R.mipmap.button_back, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.released) Scene.popScene();
                return true;
            }
        }));

        btn_y += btn_height;
        add(Layer.touchUi.ordinal(), new Button(btn_x, btn_y, btn_width, btn_height, R.mipmap.button_main, R.mipmap.button_main, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                GameView.view.getActivity().finish();
                return true;
            }
        }));
    }

    @Override
    protected int getTouchLayerIndex() {
        return Layer.touchUi.ordinal();
    }

    @Override
    public boolean isTransparent() {
        return true;
    }

    @Override
    public boolean handleBackKey() {
        return true;
    }
}
