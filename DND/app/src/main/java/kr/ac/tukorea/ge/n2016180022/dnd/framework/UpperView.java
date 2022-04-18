package kr.ac.tukorea.ge.n2016180022.dnd.framework;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import kr.ac.tukorea.ge.n2016180022.dnd.R;

public class UpperView extends View {

    private Bitmap bgUpper;
    private Rect upperSrcRect = new Rect();
    private Rect upperDstRect = new Rect();

    public UpperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    void initView() {
        Resources res = getResources();
        bgUpper = BitmapFactory.decodeResource(res, R.mipmap.info_box);
        upperSrcRect.set(0, 0, bgUpper.getWidth(), bgUpper.getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        upperDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bgUpper, upperSrcRect, upperDstRect, null);
}
}
