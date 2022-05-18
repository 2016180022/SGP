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
            checkHP(m, s, game);
            elapsedTime = 0;
        }
    }

    private void checkHP(Mob m, Sd s, MainGame game) {
        m.hp = m.hp - s.getDamage();
        Log.d(TAG, "HP: " + m.hp);
        if (m.hp <= 0) game.remove(m);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
