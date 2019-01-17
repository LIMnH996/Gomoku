package com.demo.gomoku.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.support.annotation.Nullable;

import com.demo.gomoku.R;

//Author: HE
//EditTime: 2019.1.16 15.55
//Description: 五子棋盘
public class ChessBoard extends View {
    //Author: HE
    //EditTime: 2019.1.17 16:58
    //Description: 新加参数：scale 控制图片缩放 short[10][10] 绘棋子 left 棋盘左上角的左坐标 top 棋盘左上角的上坐标 distance 棋盘格子间隔 widthLine 棋盘线的宽度
    //Description: pieceR 棋子半径 black 黑棋 white 白棋 undermined 无 motionEventX 和 motionEventY 最近一次触碰相对于View的坐标
    private Bitmap bitmap;
    private Boolean setPicture;
    private int color;
    private boolean scale = true;
    private short[][] map = new short[10][10];
    private float leftChess;
    private float topChess;
    private float distance;
    private float pieceR;
    private final int widthLine = 10;
    public final static short black = 2;
    public final static short white = 1;
    public final static short undetermined = 0;
    private float motionEventX;
    private float motionEventY;
    public ChessBoard(Context context) {
        super(context);
    }
//Author: HE
//EditTime: 2019.1.17 16:12
//Description: 将缩放图片的步骤放至ondraw
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
            this.bitmap = bitmap;
        }
        typedArray.recycle();
    }
//Author: HE
//EditTime: 2019.1.16 18:01
//Description: draws the chess-board
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.scale){
            imageScale();
        }
        Paint paint = new Paint();
        //background
        if(setPicture){
            canvas.drawBitmap(this.bitmap,0,0,paint);
        }else{
            paint.setColor(this.color);
            canvas.drawRect(0,0,getWidth(),getHeight(),paint);
        }
        //线条
        drawLine(canvas);
        //棋子
        drawPieces(canvas);
    }
    //Author: HE
    //EditTime: 2019.1.17 16:07
    //Description: 将背景图片缩放至棋盘一样大
    //Description: 只进行一次缩放
    private void imageScale(){
        float width = getWidth();
        float height = getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(width/this.bitmap.getWidth(),height/this.bitmap.getHeight());
        this.bitmap = Bitmap.createBitmap(bitmap,0,0,this.bitmap.getWidth(),this.bitmap.getHeight(),matrix,true);
        this.scale = false;
    }
    //Author: HE
    //EditTime: 2019.1.17 16:21
    //Description: 画五子棋格子
    private  void drawLine(Canvas canvas){
        Paint paint =  new Paint();
        paint.setARGB(150,0,0,0);
        float left = getWidth() / 10;
        this.leftChess = left;
        float right = getWidth() - left;
        float top = getHeight() /10;
        this.topChess = top;
        float horizontalDistance = getWidth()/10 *8 /10;
        this.distance = horizontalDistance;
        this.pieceR = (distance - 10 - distance /10 ) / 2;
        float bottom = top + 10 * horizontalDistance;
        //vertical
        for (int i = 0; i < 11; i++) {
            canvas.drawRect(left,top + i * horizontalDistance,right,top + i * horizontalDistance + 10,paint );
        }
        //horizontal
        for (int i = 0; i < 11; i++) {
            canvas.drawRect(left + i * horizontalDistance,top,left + i * horizontalDistance + 10,bottom,paint);
        }
    }
    //Author: HE
    //EditTime: 2019.1.17 17:00
    //Description: 绘制棋子
    private  void  drawPieces(Canvas canvas){
        Paint black = new Paint();
        Paint white = new Paint();
        black.setColor(Color.BLACK);
        black.setStrokeWidth(this.pieceR);
        white.setStrokeWidth(this.pieceR);
        white.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (this.map[i][j] == 0){
                }else if (this.map[i][j] == 1){
                    canvas.drawCircle(this.leftChess + ((2 * j + 1) * distance + this.widthLine) / 2,this.topChess + ((2 * i + 1) * distance + this.widthLine) / 2 ,this.pieceR ,white);
                }else{
                    canvas.drawCircle(this.leftChess + ((2 * j + 1) * distance + this.widthLine) / 2,this.topChess + ((2 * i + 1) * distance + this.widthLine) / 2 ,this.pieceR ,white);                }
            }
        }
    }
    //Author: HE
    //EditTime: 2019.1.17 17:45
    //Description: 修改棋子,参数1，2为触碰点的相对于chessboard的坐标。参数3为落子类型
    public void changeMap(float pixelX, float pixelY,short type) {
        int x= -1,y= -1;
        for (int i = 0; i < 10; i++) {
            if (this.leftChess + i * this.distance < pixelX && this.leftChess + (i +1) * this.distance > pixelX ){x = i;break;}
        }
        for (int i = 0; i < 10; i++) {
            if (this.topChess + i * this.distance < pixelY && this.topChess + (i +1) * this.distance > pixelY ){y = i;break;}
        }
        if(x == -1 || y == -1)
        {
        }else {
            this.map[y][x] = type;
        }
    }
//Author: HE
//EditTime: 2019.1.17 19:27
//Description: 将最近一次点击事件保存
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //TODO(1): 当this.event = event 之后，为什么在activity中的event.getRawY 和event.getY的值就一样了呢？
        this.motionEventX = event.getX();
        this.motionEventY = event.getY();
        return super.onTouchEvent(event);
    }

    public float getMotionEventX() {
        return motionEventX;
    }

    public float getMotionEventY() {
        return motionEventY;
    }
}
