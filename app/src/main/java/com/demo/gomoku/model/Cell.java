package com.demo.gomoku.model;

public class Cell {
    //Author: Kyle
    //EditTime: 2019/1/15 14:15
    //Description: 1) 棋盘上的每一个格子
    private Player value;

    public Player getValue() {
        return value;
    }

    public void setValue(Player value) {
        this.value = value;
    }
}
