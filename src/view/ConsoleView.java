package view;

import model.Cell;
import model.Field;


public class ConsoleView implements ViewInterface {
    private Field field;

    public ConsoleView(Field field) {
        this.field = field;
    }

    @Override
    public void printPositionRequest() {
        System.out.println("Введите координаты, которые хотите разминировать: ");
    }

    @Override
    public void printErrorMessage(String error) {
        System.out.println(error);
    }

    @Override
    public void showField() {
        for (int i = 0; i < field.LENGTH_OF_GAME_FIELD; i++) {
            for (int j = 0; j < field.WIDE_OF_GAME_FIELD; j++) {
                if ( field.gameField[i][j].visibility() ) {
                    String str = " " + bombCounterToString( field.gameField[i][j] ) + " ";
                    System.out.print(str);
                    continue;
                }
                System.out.print(" * ");

            }

            System.out.println();
        }
    }


    private String bombCounterToString(Cell cell) {
        if (cell.getBombNearCounter() == 0) {
            return "+";
        }

        return Integer.toString( cell.getBombNearCounter() );
    }


}
