package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Canvas;
import android.util.Log;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;

public class AttackChecker implements GameObject {

    private float sx, sy, mx, my;
    private int target;
    private float elapsedTime;
    private String TAG = AttackChecker.class.getSimpleName();

    public AttackChecker() { }

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.get();
        ArrayList<GameObject> mobs = game.objectsAt(MainGame.Layer.mob.ordinal());
        ArrayList<GameObject> sds = game.objectsAt(MainGame.Layer.sd.ordinal());
        for (GameObject sd : sds) {
            Sd s = (Sd) sd;
            if (s.state == Sd.State.idle) {
                int sdPos = s.getPos();
                sy = (sdPos / 100) * game.block();
                sx = (sdPos - (sdPos / 100) * 100) * game.block();
                for (GameObject mob : mobs) {
                    Mob m = (Mob) mob;
                    mx = m.x;
                    my = m.y;

                    float dis = (float) Math.sqrt((sx - mx) * (sx - mx) + (sy - my) * (sy - my));
                    Log.d(TAG, "distance is " + dis);
                    if (s.getRange() >= dis) {
                        m.hp = s.attack(m);
                        if (m.hp < 0) {
                            m.kill();
                            game.mobList.remove(m);

                        }
                        break;
                    }
                }
            }
        }
        for (GameObject mob : mobs) {
            Mob m = (Mob)mob;
            if (m.getState() == Mob.State.dead) {
                game.remove(m);
                UI.addCoin(100);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
