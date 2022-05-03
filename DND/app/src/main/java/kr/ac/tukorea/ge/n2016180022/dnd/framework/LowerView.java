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
    private ArrayList charList = new ArrayList();
    private Bitmap charDarktemplar;
    private Bitmap charDemonslayer;
    private Bitmap charGodsword;
    private Rect charSrcRect = new Rect();
    private Rect charDstRect = new Rect();

    public final int CHARSTATE = 1;
    public final int EQUSTATE = 2;
    public int STATE;

    public LowerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        bgLower = BitmapFactory.decodeResource(res, R.mipmap.background);
        lowerSrcRect.set(0, 0, bgLower.getWidth(), bgLower.getHeight());

        charList.add("darktemplar");
        charList.add("demonslayer");
        charList.add("godsword");

        charDarktemplar = BitmapFactory.decodeResource(res, R.mipmap.darktemplar);
        charDemonslayer = BitmapFactory.decodeResource(res, R.mipmap.demonslayer);
        charGodsword = BitmapFactory.decodeResource(res, R.mipmap.godsword);

        charSrcRect.set(0, 0, charDarktemplar.getWidth(), charDarktemplar.getHeight());

        STATE = CHARSTATE;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);

        lowerDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bgLower, lowerSrcRect, lowerDstRect, null);

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
}
