package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.BitmapPool;

public class UI implements GameObject {
    private float block = MainScene.get().block();
    private int coin;
    private int stage, round;
    private float x, y, w, h;
    private Bitmap coinBitmap = BitmapPool.get(R.mipmap.coin);
    private Rect coinDstRect = new Rect();
    private Paint coinTextPaint = new Paint();

    public UI(int coinAmount) {
        coin = coinAmount;
        initCoin();
        coinTextPaint.setTextSize(block / 2);
        coinTextPaint.setColor(Color.WHITE);
    }

    private void initCoin() {
        this.x = block * 10 + block / 2;
        this.y = block * 3 / 10;
        this.w = this.h = block / 2;
        coinDstRect.set((int)(x - w / 2), (int)(y - h / 2), (int)(x + w / 2), (int)(y + h / 2));
    }

    @Override
    public void update(float frameTime) {
        coin = MainScene.get().getCoin();
        stage = MainScene.get().getStage();
        round = MainScene.get().getRound();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(coinBitmap, null, coinDstRect, null);
        canvas.drawText("" + coin, block * 10 + block * 4 / 5, block / 2, coinTextPaint);

        canvas.drawText("STAGE: " + stage, 0, block * 0.4f, coinTextPaint);
        canvas.drawText("ROUND: " + round, 0, block * 0.8f, coinTextPaint);
    }


}
