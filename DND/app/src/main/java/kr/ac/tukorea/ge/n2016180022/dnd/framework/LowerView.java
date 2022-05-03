package kr.ac.tukorea.ge.n2016180022.dnd.framework;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.lifecycle.SavedStateHandle;

import java.util.ArrayList;

import kr.ac.tukorea.ge.n2016180022.dnd.R;
import kr.ac.tukorea.ge.n2016180022.dnd.app.GameActivity;

public class LowerView extends View {

    private Resources res = getResources();
    private Bitmap bgLower;
    private Rect lowerSrcRect = new Rect();
    private Rect lowerDstRect = new Rect();
    private Bitmap charDarktemplar;
    private Bitmap charDemonslayer;
    private Bitmap charGodsword;
    private Rect charSrcRect = new Rect();
    private Rect charDstRect = new Rect();

    public final int CHARSTATE = 1;
    public final int EQUSTATE = 2;
    public int STATE;
    private Rect equDstRect = new Rect();
    private Bitmap equ1;
    private Bitmap equ2;
    private Bitmap equ3;
    private Rect equSrcRect = new Rect();

    public LowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        bgLower = BitmapFactory.decodeResource(res, R.mipmap.background);
        lowerSrcRect.set(0, 0, bgLower.getWidth(), bgLower.getHeight());

        charDarktemplar = BitmapFactory.decodeResource(res, R.mipmap.darktemplar);
        charDemonslayer = BitmapFactory.decodeResource(res, R.mipmap.demonslayer);
        charGodsword = BitmapFactory.decodeResource(res, R.mipmap.godsword);

        charSrcRect.set(0, 0, charDarktemplar.getWidth(), charDarktemplar.getHeight());

        equ1 = BitmapFactory.decodeResource(res, R.mipmap.item_1);
        equ2 = BitmapFactory.decodeResource(res, R.mipmap.item_2);
        equ3 = BitmapFactory.decodeResource(res, R.mipmap.item_3);

        equSrcRect.set(0, 0, equ1.getWidth(), equ1.getHeight());

        STATE = CHARSTATE;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        drawBackground(canvas);

        switch(STATE)
        {
            case CHARSTATE:
                drawChar(canvas);
                break;
            case EQUSTATE:
                drawEqu(canvas);
                break;
        }
    }

    private void drawChar(Canvas canvas) {
        //        for (int i = 0; i < charList.size(); i++)
//        {
//            charDstRect.set(canvas.getWidth() / 10 * (i + 1), canvas.getHeight() / 5, canvas.getWidth() / 5 * (i + 1), canvas.getHeight() / 5);
//            canvas.drawBitmap(charList.get(i), );
//        }
        charDstRect.set(canvas.getWidth() / 10, canvas.getHeight() / 5, canvas.getWidth() / 5, canvas.getHeight() / 10 * 9);
        canvas.drawBitmap(charDarktemplar, charSrcRect, charDstRect, null);

        charDstRect.set(canvas.getWidth() / 10 + 200, canvas.getHeight() / 5, canvas.getWidth() / 5 + 200, canvas.getHeight() / 10 * 9);
        canvas.drawBitmap(charDemonslayer, charSrcRect, charDstRect, null);

        charDstRect.set(canvas.getWidth() / 10 + 400, canvas.getHeight() / 5, canvas.getWidth() / 5 + 400, canvas.getHeight() / 10 * 9);
        canvas.drawBitmap(charGodsword, charSrcRect, charDstRect, null);
    }

    private void drawEqu(Canvas canvas) {
        equDstRect.set(canvas.getWidth() / 10, canvas.getHeight() / 5, canvas.getWidth() / 5, canvas.getHeight() / 10 * 9);
        canvas.drawBitmap(equ1, equSrcRect, equDstRect, null);

        equDstRect.set(canvas.getWidth() / 10 + 200, canvas.getHeight() / 5, canvas.getWidth() / 5 + 200, canvas.getHeight() / 10 * 9);
        canvas.drawBitmap(equ2, equSrcRect, equDstRect, null);

        equDstRect.set(canvas.getWidth() / 10 + 400, canvas.getHeight() / 5, canvas.getWidth() / 5 + 400, canvas.getHeight() / 10 * 9);
        canvas.drawBitmap(equ3, equSrcRect, equDstRect, null);
    }

    private void drawBackground(Canvas canvas) {
        lowerDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bgLower, lowerSrcRect, lowerDstRect, null);
    }
}
