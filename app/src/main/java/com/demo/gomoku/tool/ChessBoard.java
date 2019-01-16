package com.demo.gomoku.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.support.annotation.Nullable;

import com.demo.gomoku.R;

//Author: HE
//EditTime: 2019.1.16 15.55
//Description: 五子棋盘
public class ChessBoard extends View {
    private Bitmap bitmap;
    private Boolean setPicture;
    private int color;
    public ChessBoard(Context context) {
        super(context);
    }
//Author: HE
//EditTime: 2019.1.16 18:00
//Description: get the attrs from xml
    public ChessBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ChessBoard);
        int n = typedArray.getIndexCount();
        if (n < 1){
            this.setPicture = false;
            this.color = 0xffffff;
        }else{
            this.setPicture = true;
            BitmapDrawable bitmapDrawable = (BitmapDrawable)typedArray.getDrawable(0);
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Matrix matrix = new Matrix();
            //TODO(1): get the precise width and height of the chessboard
            float width = 1080;
            float height = 2160;
            matrix.postScale(width / bitmap.getWidth(),height/bitmap.getHeight());
            this.bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);

        }
        typedArray.recycle();
    }
//Author: HE
//EditTime: 2019.1.16 18:01
//Description: draws the chess-board
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        if(setPicture){
            canvas.drawBitmap(this.bitmap,0,0,paint);
        }else{
            paint.setColor(this.color);
            canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        }
    }
}
