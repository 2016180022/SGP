package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class AnimSprite extends Sprite {
    private float framesPerSecond;
    private int frameCount;
    private int imageWidth;
    private int imageHeight;
    private float w, h;
    private ArrayList<Bitmap> animBitmap = new ArrayList<>();

    private Rect srcRect = new Rect();
//    private float time;
    private long createdOn;
    private String TAG = AnimSprite.class.getSimpleName();

    public AnimSprite(float x, float y, float w, float h, int bitmapResId, float framesPerSecond, int frameCount) {
        super(x, y, w, h, bitmapResId);
        int imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        this.framesPerSecond = framesPerSecond;
        if (frameCount == 0) {
            frameCount = imageWidth / imageHeight;
            imageWidth = imageHeight;
        } else {
            imageWidth = imageWidth / frameCount;
        }
        this.imageWidth = imageWidth;
        this.frameCount = frameCount;

        createdOn = System.currentTimeMillis();
    }

    public AnimSprite(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        createdOn = System.currentTimeMillis();
        this.framesPerSecond = 5;
    }

    public void setImage(ArrayList<Bitmap> bitmapList, int frameCount) {
        this.animBitmap = bitmapList;
        this.frameCount = frameCount;
    }


    @Override
    public void update(float frameTime) {
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        int index = Math.round(time * framesPerSecond) % frameCount;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        if (animBitmap.size() != 0) {
            bitmap = animBitmap.get(index);
//            Log.d(TAG, "set Bitmap in index" + index);
        }
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, null, dstRect, null);
//            Log.d(TAG, "draw");
        }
    }
}
