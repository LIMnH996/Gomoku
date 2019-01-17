package com.demo.gomoku.view;

import android.content.Context;

//Author: HE
//EditTime: 2019.1.17 17:48
//Description: GomokuActivity的接口
public interface GomokuActivityInterface {
    //Description: 更加坐标相对于棋盘的坐标画棋子
    void drawThePiece(float x,float y,short type);
    //Description: 选过的地方不可以再次选择的提示
    void unableSelected();
    //Description: 游戏结束
    void gameOver();
    //Description: 游戏结束时，显示赢家，祝贺等
    void showInTheEnd(String... string);
    //Description: 获得点击事件相对于棋盘的坐标
    float getXChessEvent();
    float getYChessEvent();
    //Description: 获取Context
    Context getContext();
}
