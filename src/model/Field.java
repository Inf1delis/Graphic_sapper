package model;

import controller.ControllerInterface;
import exceptions.GameOverException;
import exceptions.IncorrectGameFieldSizeException;
import exceptions.WinException;
import view.gui_view.GuiFieldView;

import java.util.concurrent.ThreadLocalRandom;

public class Field {
    public final int WIDE_OF_GAME_FIELD; // X
    public final int LENGTH_OF_GAME_FIELD; // Y
    public final int BOMB_QUANTITY;

    private int openedCells;
    public final int CELL_QUANTITY;

    public Cell[][] gameField;

    private Bomb[] bombs;

    public Field(int wide, int length, int bombs) {
        CELL_QUANTITY = wide*length;
        if (wide <= 2 || length <= 2 || bombs >= CELL_QUANTITY || bombs < 0) {
            throw new IncorrectGameFieldSizeException();
        }
        openedCells = 0;
        WIDE_OF_GAME_FIELD = wide;
        LENGTH_OF_GAME_FIELD = length;
        BOMB_QUANTITY = bombs;
        gameField = new Cell[LENGTH_OF_GAME_FIELD][WIDE_OF_GAME_FIELD];

        setField();
        System.out.println("Поле определно");
        //setBombs();
    }

    public void plusOpenedCells() {
        openedCells++;
    }

    public int getOpenedCells() {
        return openedCells;
    }

    private void setField () {
        for (int i = 0; i < LENGTH_OF_GAME_FIELD; i++) {
            for (int j = 0; j < WIDE_OF_GAME_FIELD; j++) {
                gameField[i][j] =  new Cell();
            }
        }
    }



    public void setBomb (int X, int Y) {
        bombs = new Bomb[BOMB_QUANTITY];

        for (int i = 0; i < BOMB_QUANTITY; i++) {
            int x = ThreadLocalRandom.current().nextInt(0, WIDE_OF_GAME_FIELD);
            int y = ThreadLocalRandom.current().nextInt(0, LENGTH_OF_GAME_FIELD);
            if ( !gameField[y][x].isBomb() && X != x && Y != y) {
                bombs[i] = new Bomb(x, y);
                gameField[y][x] = bombs[i];
                saperLogic(x,y);
            } else {
                i--;
            }

        }
    }


    public void openBombs () {
        for (Bomb bomb: bombs) {
            bomb.setVisible();
        }
    }

    private void clickArea(int x, int y, int xFrom, int yFrom , int xLimit, int yLimit) {
        for (int i = xFrom; i <= xLimit ; i++) {
            for (int j = yFrom; j <= yLimit ; j++) {
                gameField[y+j][x+i].plusBombNearCounter();
            }
        }
    }


    public void saperLogic(int x, int y) {

        if (x == 0) {
            if ( y == 0) { clickArea(x, y, 0, 0, 1, 1); }
            if ( y == LENGTH_OF_GAME_FIELD - 1) { clickArea(x, y, 0, -1, 1, 0); }
        }


        if (x == WIDE_OF_GAME_FIELD - 1) {
            if ( y == 0) { clickArea(x, y, -1, 0, 0, 1); }
            if ( y == LENGTH_OF_GAME_FIELD - 1) { clickArea(x, y, -1, -1, 0, 0); }
        }

        if (y >= 1 && x >= 1 && y <= LENGTH_OF_GAME_FIELD - 2 && x <= WIDE_OF_GAME_FIELD - 2 ) {
            clickArea(x, y, -1, -1, 1, 1);
        }

        if (y >= 1 && y <= LENGTH_OF_GAME_FIELD - 2) {
            if ( x == 0 ) { clickArea(x, y, 0, -1, 1, 1); }
            if ( x == WIDE_OF_GAME_FIELD - 1) { clickArea(x, y, -1, -1, 0, 1); }
        }


        if (x >= 1 && x <= WIDE_OF_GAME_FIELD - 2) {
            if ( y == 0 ) { clickArea(x, y, -1, 0, 1, 1); }
            if ( y == LENGTH_OF_GAME_FIELD - 1) { clickArea(x, y, -1, -1, 1, 0); }
        }
    }

}