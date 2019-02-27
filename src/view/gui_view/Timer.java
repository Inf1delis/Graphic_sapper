package view.gui_view;

import javax.swing.*;
import java.awt.*;

public class Timer {
    private JTextField textField;
    private boolean stop;
    private long seconds;

    public Timer() {

        textField = GuiFieldView.fillTextField("000");
        stop = false;
        seconds = 0;
        new MyRunnable();
    }

    public JTextField getTextField() {
        return textField;
    }

    public void stopTimerLose() {
        textField.setForeground(Color.RED);
        stop = true;
    }

    public void stopTimerWin() {
        textField.setForeground(Color.green);
        stop = true;
    }


    class MyRunnable implements Runnable {
        Thread thread;

        MyRunnable() {
            thread = new Thread(this, "Секундомер");
            thread.start();
        }

        public void run() {
            while ( !stop ) {
                GuiFieldView.text(seconds, textField);
                try {
                    thread.sleep(1000);
                } catch (Exception e) {
                    break;
                }
                seconds++;
            }
        }
    }
}
