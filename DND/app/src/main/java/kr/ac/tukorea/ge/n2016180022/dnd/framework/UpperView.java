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
import androidx.lifecycle.SavedStateHandle;

import kr.ac.tukorea.ge.n2016180022.dnd.R;

public class UpperView extends View {

    private Bitmap bgUpper;
    private Bitmap gameTile;
    private Rect upperSrcRect = new Rect();
    private Rect upperDstRect = new Rect();
    private Rect tileSrcRect = new Rect();
    private Rect tileDstRect = new Rect();
    private int STATE;

    public final int GAMESTATE = 1;
    public final int CHARSTATE = 2;
    public final int EQUSTATE = 3;

    public UpperView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    void initView() {
        Resources res = getResources();

        bgUpper = BitmapFactory.decodeResource(res, R.mipmap.info_box);
        upperSrcRect.set(0, 0, bgUpper.getWidth(), bgUpper.getHeight());

        gameTile = BitmapFactory.decodeResource(res, R.mipmap.tile_town1);
        tileSrcRect.set(0, 0, gameTile.getWidth(), gameTile.getHeight());

        STATE = GAMESTATE;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        drawBackground(canvas);
        switch(STATE)
        {
            case GAMESTATE:
                drawGame(canvas);
                break;
            case CHARSTATE:
                drawChar();
                break;
            case EQUSTATE:
                drawEqu();
                break;
        }
    }

    private void drawGame(Canvas canvas) {
        tileDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(gameTile, tileSrcRect, tileDstRect, null);
    }

    private void drawChar() {

    }

    private void drawEqu() {

    }

    private void drawBackground(Canvas canvas) {
        upperDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bgUpper, upperSrcRect, upperDstRect, null);
    }
}
