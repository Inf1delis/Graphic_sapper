package view.gui_view;

import model.Field;

import javax.swing.*;
import java.awt.*;

public class BombCounter {
    private JTextField textField;
    private int bombs;

    public BombCounter(Field field) {
        bombs = field.BOMB_QUANTITY;
        textField = GuiFieldView.fillTextField("");
        GuiFieldView.text(bombs, textField);
        textField.revalidate();
    }


    public void gameOver () {
        textField.setForeground(Color.RED);
    }

    public void winGame() {
        textField.setForeground(Color.green);
    }

    private void refresh() {
        GuiFieldView.text(bombs, textField);
        textField.revalidate();
    }

    public int getBombs() {
        return bombs;
    }

    public void plusBomb () {
        bombs += 1;
        refresh();
    }

    public void minusBomb() {
        bombs -= 1;

        refresh();
    }

    public JTextField getTextField() {
        return textField;
    }
}
