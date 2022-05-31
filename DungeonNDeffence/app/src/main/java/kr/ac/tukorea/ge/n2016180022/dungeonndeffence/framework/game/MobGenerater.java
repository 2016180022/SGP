package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;

public class MobGenerater implements GameObject {
    private static final float GEN_TIME = 1.0f;
    private static final int WAVE_COUNT = 1;
    private final float genTime;
    private int wave;
    private float elapsedTime;
    private boolean spawning;

    public int round;

    public MobGenerater() {
        genTime = GEN_TIME;
    }

    @Override
    public void update(float frameTime) {
        if (spawning) {
            elapsedTime += frameTime;
            if (elapsedTime > genTime) {
                spawn();
                elapsedTime = 0;
            }
        }
        if (this.wave >= WAVE_COUNT) {
            spawning = false;
            checkMobCount();
        }
    }

    public void startSpawn(int round) {
        this.wave = 0;
        spawning = true;
        this.round = round;
    }

    private void spawn() {
        Mob m = new Mob(Mob.Stage.evil01, getHp());
        MainGame.get().add(MainGame.Layer.mob.ordinal(), m);
        MainGame.get().mobList.add(m);
        this.wave++;
    }

    private void checkMobCount() {
        int count = MainGame.get().mobList.size();
        if (count == 0) {
                this.round++;
                startSpawn(this.round);
        }
    }

    private float getHp() {
        float hp = 100 + round * 8f;
        return hp;
    }

    @Override
    public void draw(Canvas canvas) {

    }


}
