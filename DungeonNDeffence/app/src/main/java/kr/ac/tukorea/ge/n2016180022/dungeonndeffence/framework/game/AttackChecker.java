package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Canvas;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;

public class AttackChecker implements GameObject {

    private float sx, sy, mx, my;
    private int target;
    private float elapsedTime;

    public AttackChecker() { }

    @Override
    public void update(float frameTime) {
        MainGame game = MainGame.get();
        ArrayList<GameObject> mobs = game.objectsAt(MainGame.Layer.mob.ordinal());
        ArrayList<GameObject> sds = game.objectsAt(MainGame.Layer.sd.ordinal());
        for (GameObject sd : sds) {
            Sd s = (Sd) sd;
            sx = s.x;
            sy = s.y;
            for (GameObject mob : mobs) {
//                if (target >= s.targetCount) break;
                Mob m = (Mob) mob;
                mx = m.x;
                my = m.y;

                float dis = (float) Math.sqrt((sx - mx) * (sx - mx) + (sy - my) * (sy - my));
                if (s.getRange() >= dis) {
                    checkAttack(m, s, frameTime, game);
                }
            }
        }
    }

    private void checkAttack(Mob m, Sd s, float frameTime, MainGame game) {
        elapsedTime += frameTime;
        if (elapsedTime > s.getAttackDelay()) {
            game.remove(m);
            elapsedTime = 0;
        }
    }

    private void checkHP(Mob m, Sd s) {

    }

    @Override
    public void draw(Canvas canvas) {

    }
}
