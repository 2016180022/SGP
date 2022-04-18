package kr.ac.tukorea.ge.n2016180022.dnd.framework;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.SavedStateHandle;

import kr.ac.tukorea.ge.n2016180022.dnd.R;

public class LowerView extends View {

    private Resources res = getResources();
    private Bitmap bgLower;
    private Rect lowerSrcRect = new Rect();
    private Rect lowerDstRect = new Rect();

    public LowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {

        bgLower = BitmapFactory.decodeResource(res, R.mipmap.background);
        lowerSrcRect.set(0, 0, bgLower.getWidth(), bgLower.getHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        lowerDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bgLower, lowerSrcRect, lowerDstRect, null);
    }
}
