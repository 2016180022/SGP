package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;

public class AttackChecker implements GameObject {

    float sx, sy, mx, my;

    public AttackChecker() { }

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.get();
        ArrayList<GameObject> mobs = game.objectsAt(MainGame.Layer.mob.ordinal());
        ArrayList<GameObject> sds = game.objectsAt(MainGame.Layer.sd.ordinal());
        for (GameObject mob : mobs) {
            Mob m = (Mob) mob;
            mx = m.x;
            my = m.y;
            if (m.onTarget) continue;
                for (GameObject sd : sds) {
                    Sd s = (Sd) sd;
                    sx = s.x;
                    sy = s.y;

                    float dis = (float) Math.sqrt((sx - mx) * (sx - mx) + (sy - my) * (sy - my));
                    if (s.getRange() >= dis) game.remove(m);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
