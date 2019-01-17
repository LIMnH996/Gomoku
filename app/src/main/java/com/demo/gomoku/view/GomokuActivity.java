package com.demo.gomoku.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.demo.gomoku.R;
import com.demo.gomoku.tool.ChessBoard;

public class GomokuActivity extends AppCompatActivity implements GomokuActivityInterface{
    private  ChessBoard chessBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.chessBoard = findViewById(R.id.chess_board);

    }

    @Override
    public void drawThePiece(float x, float y, short type) {
        chessBoard.changeMap(x,y,type);
        chessBoard.invalidate();
    }

    @Override
    public void unableSelected() {
        Toast.makeText(this,"不可以选择已经落子的地方",Toast.LENGTH_LONG).show();
    }

    @Override
    public void gameOver() {
        Toast.makeText(GomokuActivity.this,"游戏结束",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showInTheEnd(String... string) {
        for (int i = 0; i < string.length; i++) {
            Toast.makeText(this,string[i],Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public float getXChessEvent() {
        return this.chessBoard.getMotionEventX();
    }

    @Override
    public float getYChessEvent() {
        return this.chessBoard.getMotionEventY();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
