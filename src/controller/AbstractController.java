package controller;

import exceptions.GameOverException;
import exceptions.WinException;
import model.Field;
import view.ViewInterface;

public abstract class AbstractController implements ControllerInterface {

    protected int wide;
    protected int length;
    protected int bombs;
    protected static Field field;
    protected ViewInterface view;
    protected static boolean firstClick;


    public AbstractController() {

    }


    abstract public void getClicks();


    @Override
    public Field initField() {
        wide = getWide();
        length = getLength();
        bombs = getBombs();
        field = new Field(wide, length, bombs);
        return field;
    }


    @Override
    public int getWide() {
        return 10;
    }

    @Override
    public int getLength() {
        return 10;
    }

    @Override
    public int getBombs() {
        return 10;
    }

    protected static void clickArea(int x, int y, int xFrom, int yFrom , int xLimit, int yLimit) {
        for (int i = xFrom; i <= xLimit ; i++) {
            for (int j = yFrom; j <= yLimit ; j++) {
                userClick(x + i, y + j);
            }
        }
    }

    protected static void saperLogic(int x, int y) {

        if (x == 0) {
            if ( y == 0) { clickArea(x, y, 0, 0, 1, 1); }
            if ( y == field.LENGTH_OF_GAME_FIELD - 1) { clickArea(x, y, 0, -1, 1, 0); }
        }


        if (x == field.WIDE_OF_GAME_FIELD - 1) {
            if ( y == 0) { clickArea(x, y, -1, 0, 0, 1); }
            if ( y == field.LENGTH_OF_GAME_FIELD - 1) { clickArea(x, y, -1, -1, 0, 0); }
        }

        if (y >= 1 && x >= 1 && y <= field.LENGTH_OF_GAME_FIELD - 2 && x <= field.WIDE_OF_GAME_FIELD - 2 ) {
            clickArea(x, y, -1, -1, 1, 1);
        }

        if (y >= 1 && y <= field.LENGTH_OF_GAME_FIELD - 2) {
            if ( x == 0 ) { clickArea(x, y, 0, -1, 1, 1); }
            if ( x == field.WIDE_OF_GAME_FIELD - 1) { clickArea(x, y, -1, -1, 0, 1); }
        }


        if (x >= 1 && x <= field.WIDE_OF_GAME_FIELD - 2) {
            if ( y == 0 ) { clickArea(x, y, -1, 0, 1, 1); }
            if ( y == field.LENGTH_OF_GAME_FIELD - 1) { clickArea(x, y, -1, -1, 1, 0); }
        }
    }

    public static int userClick (int x, int y) {

        firstClick(x,y);

        if ( field.gameField[y][x].visibility() ) {
            return 1;
        }

        field.gameField[y][x].setVisible();
        field.plusOpenedCells();

        if (field.gameField[y][x].getBombNearCounter() != 0) {
            return winCheck();
        }

        saperLogic(x, y);

        return winCheck();
    }

    protected static int winCheck() throws WinException {
        if (field.getOpenedCells() + field.BOMB_QUANTITY == field.CELL_QUANTITY) {
            throw new WinException();
        }

        return 1;
    }

    public static void firstClick(int x, int y) {
        if (firstClick) {
            field.setBomb(x,y);
            firstClick = false;
            System.out.println("Бомбы установлены");
        } else if (field.gameField[y][x].isBomb()) {
            field.openBombs();
            throw new GameOverException();
        }
    }

}
