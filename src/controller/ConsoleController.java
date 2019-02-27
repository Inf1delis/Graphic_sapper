package controller;

import exceptions.GameOverException;
import exceptions.IncorrectCordinatesException;
import exceptions.WinException;
import model.Field;
import view.ConsoleView;
import java.util.Scanner;

public class ConsoleController extends AbstractController {
    private Scanner scanner;

    public ConsoleController() {
        scanner = new Scanner(System.in);
        firstClick = true;
        while (true) {
            try {
                getWide();
                getLength();
                getBombs();
                break;
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        field = new Field(wide, length, bombs);
        view = new ConsoleView(field);
        view.showField();
    }


    @Override
    public void getClicks() {
        while(true) {
            try {



                view.printPositionRequest();
                click();
                view.showField();

            } catch (IncorrectCordinatesException e) {
                view.printErrorMessage(e.getLocalizedMessage());

            } catch (GameOverException | WinException e) {
                view.showField();
                view.printErrorMessage(e.getLocalizedMessage());

                if (retry()) {
                    field = initField();
                }
                break;

            } catch (Exception e) {
                break;
            }
        }
    }

    private void click() {
        int x;
        int y;
        try {
            x = getInt();
            y = getInt();

        } catch (Exception e) {
            throw new IncorrectCordinatesException();
        }
        if (x >= wide || y >= length || x < 0 || y < 0) {
            throw new IncorrectCordinatesException();
        }
        userClick(x, y);
    }

    @Override
    public int getWide() {
        System.out.println("Введите ширину поля: ");
        wide = getInt();
        return wide;
    }

    @Override
    public int getLength() {
        System.out.println("Введите высоту поля: ");
        length = getInt();
        return length;
    }

    @Override
    public int getBombs() {
        System.out.println("Введите количество бомб: ");
        bombs = getInt();
        return bombs;
    }

    private int getInt() {
        try {
            return Integer.parseInt(scanner.next());
        } catch (Exception e) {
            throw new IncorrectCordinatesException();
        }
    }

    public boolean retry() {
        return scanner.next().equals("+");
    }
}
