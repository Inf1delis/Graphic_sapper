package controller.gui_controller;

import controller.AbstractController;
import exceptions.GameOverException;
import exceptions.WinException;
import model.Field;
import view.gui_view.GameButton;
import view.gui_view.GuiFieldView;


public class GuiController extends AbstractController {
    static GuiFieldView guiView;

    public GuiController(Field field) {

        this.field = field;
        firstClick = true;

        guiView = new GuiFieldView(field);

        guiView.showField();
    }

    @Override
    public void getClicks() {
        try {

            guiView.printPositionRequest();

            guiView.showField();

        } catch (GameOverException | WinException e) {
            guiView.showField();
            guiView.printErrorMessage(e.getLocalizedMessage());


        } catch (Exception e) {
            System.out.println(e);
        }

    }



    @Override
    public Field initField() {
        return null;
    }

    @Override
    public int getWide() {
        return super.getWide();
    }

    @Override
    public int getLength() {
        return super.getLength();
    }

    @Override
    public int getBombs() {
        return super.getBombs();
    }


    public static int userClick (int x, int y) {

        if (!guiView.checkFlag(x, y)) {
            if (firstClick) {
                field.setBomb(x, y);

                for (GameButton gameButton : GuiFieldView.gameButtons) {
                    gameButton.setLabel();
                }

                firstClick = false;
                System.out.println("Бомбы установлены");

            } else if (field.gameField[y][x].isBomb()) {
                field.openBombs();
                throw new GameOverException();
            }

            if (field.gameField[y][x].visibility()) {
                return 1;
            }

            field.gameField[y][x].setVisible();
            guiView.showLabel(x, y);
            field.plusOpenedCells();

            if (field.gameField[y][x].getBombNearCounter() != 0) {
                return winCheck();
            }


            saperLogic(x, y);
            return winCheck();
        }
        return 1;
    }
}