package com.demo.gomoku.model;

import android.content.Context;
import android.widget.Toast;

public class Board {
    //Author: Kyle
    //EditTime: 2019/1/15 14:11
    //Description: 1) 五子棋的模型类 整个棋盘
    private enum GameState {
        IN_PROGRESS, FINISHED
    }

    private static final int BOARD_SIZE = 10;

    private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];
    private Player winner;
    private GameState state;
    private Player currentTurn;

    public void restart() {
        clearCells();
        winner = null;
        state = GameState.IN_PROGRESS;
        currentTurn = Player.WHITE;
    }

    private void clearCells() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public Player mark(int row, int col, Context context) {
        Player playerThatMoved = null;
        if (isValid(row, col, context)) {
            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;
            if (isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED;
                winner = currentTurn;
            } else {
                flipCurrentTurn();
            }
        }
        return playerThatMoved;
    }

    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {
        int flag[] = new int[4];
        for (int r = currentRow-1; r > 0; r--) {
            if (isSamePlayer(player, r, currentCol)) {
                flag[0]++;
            }else {
                break;
            }
        }
        for (int r = currentRow+1; r <= BOARD_SIZE; r++) {
            if (isSamePlayer(player, r, currentCol)) {
                flag[0]++;
            }else {
                break;
            }
        }
        if (flag[0] == 4) {
            return true;
        }

        for (int c = currentCol-1; c > 0; c--) {
            if (isSamePlayer(player, currentRow, c)) {
                flag[1]++;
            }else {
                break;
            }
        }
        for (int c = currentCol+1; c <= BOARD_SIZE; c++) {
            if (isSamePlayer(player, currentRow, c)) {
                flag[1]++;
            }else {
                break;
            }
        }
        if (flag[1] == 4) {
            return true;
        }

        for (int r = currentRow - 1,c = currentCol-1; r>0 && c > 0; r--,c--) {
            if (isSamePlayer(player, r, c)) {
                flag[2]++;
            }else {
                break;
            }
        }
        for (int r = currentRow + 1,c = currentCol+1; r<=BOARD_SIZE && c <= BOARD_SIZE; r++,c++) {
            if (isSamePlayer(player, r, c)) {
                flag[2]++;
            }else {
                break;
            }
        }
        if (flag[2] == 4) {
            return true;
        }

        for (int r = currentRow - 1,c = currentCol+1; r>0 && c <= BOARD_SIZE; r--,c++) {
            if (isSamePlayer(player, r, c)) {
                flag[3]++;
            }else {
                break;
            }
        }
        for (int r = currentRow + 1,c = currentCol-1; r<=BOARD_SIZE && c > 0 ; r++,c--) {
            if (isSamePlayer(player, r, c)) {
                flag[3]++;
            }else {
                break;
            }
        }
        if (flag[3] == 4) {
            return true;
        }
        return false;
    }

    private boolean isSamePlayer(Player player, int row, int col) {
        return cells[row][col].getValue() == player;
    }

    private boolean isValid(int row, int col, Context context) {
        if (state == GameState.FINISHED) {
            Toast.makeText(context, "游戏已经结束了哦，亲", Toast.LENGTH_SHORT).show();
            return false;
        } else if (isOutOfBounds(row) || isOutOfBounds(col)) {
            return false;
        } else if (isCellValueAlreadySet(row, col)) {
            Toast.makeText(context, "这块地方已经被下过了哦", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private boolean isOutOfBounds(int idx) {
        return idx < 0 || idx >= BOARD_SIZE;
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }

    private void flipCurrentTurn() {
        currentTurn = (currentTurn == Player.WHITE ? Player.BLACK : Player.WHITE);
    }

}
