package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.interfaces.GameObject;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.BitmapPool;
import kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.res.Metrics;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    public float x, y, radius;
    public Sprite(float x, float y, int radiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public Sprite(float x, float y, float w, float h, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = w / 2;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        bitmap = BitmapPool.get(bitmapResId);
    }

    public Sprite() { }

    public void setBitmap(int bitmapResId) {
        this.bitmap = BitmapPool.get(bitmapResId);
    }

    public void setDstRectWithRadius() {
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
    }

    public void setDstRect(float width, float height) {
        dstRect.set(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }

    @Override
    public void update(float frameTime) {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
