package controller.gui_controller;

import exceptions.GameOverException;
import exceptions.WinException;
import view.gui_view.GameButton;
import view.gui_view.GuiFieldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse extends MouseAdapter {

    private JPanel card;
    private GuiFieldView guiFieldView;
    private GridBagConstraints constraint;
    private JButton button;

    public Mouse(GameButton gameButton) {
        card = gameButton.getCard();
        guiFieldView = gameButton.getGuiFieldView();
        constraint = gameButton.getConstraint();
        button = gameButton.getButton();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            try {
                if (!guiFieldView.checkFlag(constraint.gridx, constraint.gridy)) {
                    GuiController.userClick(constraint.gridx, constraint.gridy);
                    guiFieldView.showField();
                }

            } catch (GameOverException ex) {
                guiFieldView.getBombCounter().gameOver();
                guiFieldView.getTimer().stopTimerLose();
                guiFieldView.showField();


                guiFieldView.getMessageField().setForeground(Color.RED);
                guiFieldView.getMessageField().setText("Game Over!");

                retry();
            } catch (WinException ex) {

                guiFieldView.getBombCounter().winGame();
                guiFieldView.getTimer().stopTimerWin();
                guiFieldView.showField();

                guiFieldView.getMessageField().setForeground(Color.green);
                guiFieldView.getMessageField().setText("Ты победил!");

                retry();
            }
        }

        if (e.getButton() == MouseEvent.BUTTON3) {
            if (button.getText().equals("?") ) {
                button.setText(" ");
                if (guiFieldView.getBombCounter().getBombs()  < guiFieldView.getField().BOMB_QUANTITY) {
                    guiFieldView.getBombCounter().plusBomb();
                }

            } else
                if ( guiFieldView.getBombCounter().getBombs() > 0){
                    button.setText("?");
                    guiFieldView.getBombCounter().minusBomb();

                } else {
                    JOptionPane.showMessageDialog(SizeInitField.getErrorJFrame(), "Максимальное количество флажков!");
                }
            }


    }

    void retry() {
        if (guiFieldView.retry()) {
            guiFieldView.close();
            new SizeInitField();
        } else {
            guiFieldView.close();
        }
    }
}