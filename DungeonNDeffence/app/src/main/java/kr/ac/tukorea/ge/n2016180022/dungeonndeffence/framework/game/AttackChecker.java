package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;

public class AttackChecker implements GameObject {

    private float sx, sy, mx, my;
    private int target;

    public AttackChecker() { }

    @Override
    public void update(float frameTime) {
        checkAttack();
    }

    private void checkAttack() {
        MainGame game = MainGame.get();
        ArrayList<GameObject> mobs = game.objectsAt(MainGame.Layer.mob.ordinal());
        ArrayList<GameObject> sds = game.objectsAt(MainGame.Layer.sd.ordinal());
        for (GameObject sd : sds) {
            Sd s = (Sd) sd;
            sx = s.x;
            sy = s.y;
            for (GameObject mob : mobs) {
                if (target >= s.targetCount) break;
                Mob m = (Mob) mob;
                mx = m.x;
                my = m.y;

                float dis = (float) Math.sqrt((sx - mx) * (sx - mx) + (sy - my) * (sy - my));
                if (s.getRange() >= dis) {
                    s.targetCount ++;
//                    attack();
                    game.remove(m);
                    s.targetCount--;
                }

            }
        }
    }

    private void attack(Mob m, Sd s, float frameTime) {

    }

    private void checkHP(Mob m, Sd s) {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
