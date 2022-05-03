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
    private Bitmap charImage;
    private Rect charSrcRect = new Rect();
    private Rect charDstRect = new Rect();
    private Paint infoText = new Paint();
    private Bitmap skillImage;
    private Rect skillSrcRect = new Rect();
    private Rect skillDstRect = new Rect();
    private Bitmap itemImage;
    private Rect itemSrcRect = new Rect();
    private Rect itemDstRect = new Rect();

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

        charImage = BitmapFactory.decodeResource(res, R.mipmap.darktemplar);
        charSrcRect.set(0, 0, charImage.getWidth(), charImage.getHeight());

        infoText.setColor(Color.WHITE);
        infoText.setTextSize(50);

        skillImage = BitmapFactory.decodeResource(res, R.mipmap.skill_1);
        skillSrcRect.set(0, 0, skillImage.getWidth(), skillImage.getHeight());

        itemImage = BitmapFactory.decodeResource(res, R.mipmap.item_1);
        itemSrcRect.set(0, 0, itemImage.getWidth(), itemImage.getHeight());

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
                drawChar(canvas);
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

    private void drawChar(Canvas canvas) {
        charDstRect.set(canvas.getWidth() / 8, canvas.getHeight() / 6, canvas.getWidth() / 8 + 400, canvas.getHeight() / 6 + 300);
        canvas.drawBitmap(charImage, charSrcRect, charDstRect, null);

        canvas.drawText("다크템플러", canvas.getWidth() / 8 + 100, canvas.getHeight() * 2 / 3, infoText);
        canvas.drawText("AD:190 AB:110 AS:020", canvas.getWidth() / 8 - 30, canvas.getHeight() * 7 / 8, infoText);

        skillDstRect.set(canvas.getWidth() * 5 / 8, canvas.getHeight() / 5, canvas.getWidth() * 6 / 8, canvas.getHeight() / 5 + 150);
        canvas.drawBitmap(skillImage, skillSrcRect, skillDstRect, null);
        canvas.drawText("이보브", canvas.getWidth() * 5 / 8 + 50, canvas.getHeight() / 5 + 250, infoText);

        itemDstRect.set(canvas.getWidth() * 5 / 8, canvas.getHeight() * 3 / 5, canvas.getWidth() * 6 / 8, canvas.getHeight() * 3 / 5 + 150);
        canvas.drawBitmap(itemImage, itemSrcRect, itemDstRect, null);
        canvas.drawText("부스팅 펄스 튜브", canvas.getWidth() * 5 / 8 - 50, canvas.getHeight() * 3 / 5 + 250, infoText);

    }

    private void drawEqu() {

    }

    private void drawBackground(Canvas canvas) {
        upperDstRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.drawBitmap(bgUpper, upperSrcRect, upperDstRect, null);
    }
}
