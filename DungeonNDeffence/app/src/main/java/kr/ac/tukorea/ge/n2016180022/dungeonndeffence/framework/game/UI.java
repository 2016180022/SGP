package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.R;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects.Sprite;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.BitmapPool;

public class UI implements GameObject {
    private int coin;
    private float x, y, w, h;
    private float block = MainGame.get().block();
    private Bitmap coinBitmap = BitmapPool.get(R.mipmap.coin);
    private Rect coinDstRect = new Rect();

    public UI(int coinAmount) {
        coin = coinAmount;
        this.x = block * 10 + block / 2;
        this.y = block * 3 / 10;
        this.w = this.h = block / 2;
        coinDstRect.set((int)(x - w / 2), (int)(y - h / 2), (int)(x + w / 2), (int)(y + h / 2));
    }

    @Override
    public void update(float frameTime) {


    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(coinBitmap, null, coinDstRect, null);
    }
}
