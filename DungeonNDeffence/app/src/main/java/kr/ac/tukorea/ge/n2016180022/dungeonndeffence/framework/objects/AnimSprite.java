package kr.ac.tukorea.ge.n2016180022.dungeonndeffence.framework.objects;


import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimSprite extends Sprite {
    private String TAG = AnimSprite.class.getSimpleName();

    private int frameCount;
    private float framesPerSecond = 5;
    private long createdOn;

    private int imageWidth;
    private int imageHeight;

    private Rect srcRect = new Rect();
    public float w, h;
    public float left, bottom;

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

    public AnimSprite() {}

    @Override
    public void update(float frameTime) {
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
