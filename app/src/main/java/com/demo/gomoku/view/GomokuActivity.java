package com.demo.gomoku.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.demo.gomoku.R;
import com.demo.gomoku.tool.ChessBoard;

public class GomokuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChessBoard chessBoard = findViewById(R.id.chess_board);
    }
}
