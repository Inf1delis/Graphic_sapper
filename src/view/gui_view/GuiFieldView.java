package view.gui_view;

import model.Field;
import view.ViewInterface;

import javax.swing.*;
import java.awt.*;

public class GuiFieldView implements ViewInterface {
    private Field field;
    private Timer timer;
    private JFrame jFrame;
    private JLayeredPane panel;
    private JLayeredPane fieldPane;
    private ConstraintsMaker maker;
    private BombCounter bombCounter;
    private JTextField messageField;
    public static GameButton[] gameButtons;
    public static Font FONT = new Font("DigtalFont.TTF", Font.BOLD, 25);

    public GuiFieldView(Field field) {
        this.field = field;

        jFrame = getJFrame();

        maker = new ConstraintsMaker(this.field.WIDE_OF_GAME_FIELD);

        fieldPane = new JLayeredPane();
        fieldPane.setLayout(new GridBagLayout());

        gameButtons = new GameButton[field.CELL_QUANTITY];

        for (int i = 0; i < field.CELL_QUANTITY; i++) {
            gameButtons[i] = new GameButton( this);
        }

        panel = new JLayeredPane();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints fieldPosition = getConstraint(0,1,4,1);

        GridBagConstraints timerPosition = getConstraint(3,0,1,1);

        GridBagConstraints bombCounterPosition = getConstraint(0,0,1,1);

        GridBagConstraints messageFieldPosition = getConstraint(1,0,2,1);

        int textColum = field.WIDE_OF_GAME_FIELD > 6 ? field.WIDE_OF_GAME_FIELD - 2 : 0;

        messageField = fillTextField("", textColum);
        messageField.setHorizontalAlignment(0);

        timer = new Timer();
        bombCounter = new BombCounter(field);

        panel.add(fieldPane, fieldPosition);
        panel.add(timer.getTextField(), timerPosition);
        panel.add(bombCounter.getTextField(), bombCounterPosition);
        panel.add(messageField, messageFieldPosition);

        jFrame.setContentPane(panel);

        panel.revalidate();
        panel.repaint();
        jFrame.revalidate();
    }


    public static GridBagConstraints getConstraint (int gridx, int gridy, int gridwidth, int gridheight) {
        GridBagConstraints tmp = new GridBagConstraints();

        tmp.gridx = gridx;
        tmp.gridy = gridy;
        tmp.gridwidth = gridwidth;
        tmp.gridheight = gridheight;
        return tmp;
    }

    private JFrame getJFrame () {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int fieldWidth = 400;
        int tmp = field.WIDE_OF_GAME_FIELD*40;
        if ( tmp > fieldWidth)  fieldWidth = tmp;

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, fieldWidth, field.LENGTH_OF_GAME_FIELD*40+ 50);
        jFrame.setTitle("Saper!");
        return jFrame;
    }



    @Override
    public void showField() {
        for (GameButton gameButton: gameButtons) {
            gameButton.checkUpgate();
        }
        panel.revalidate();
    }

    @Override
    public void printPositionRequest() { }

    @Override
    public void printErrorMessage(String error) {
        System.out.println(error);
    }

    public static JTextField fillTextField(String text, int colums) {
            JTextField textField = new JTextField(text, colums);
            textField.setEditable(false);
            textField.setFont(FONT);
            textField.setBackground(Color.LIGHT_GRAY);
            textField.setForeground(Color.GRAY);
            textField.setBorder(BorderFactory.createLoweredBevelBorder());

            return textField;

    }


    public static JTextField fillTextField(String text) {
        return fillTextField(text, 3);
    }

    public static void text (long value, JTextField textField) {
        if (value >= 0 && value <= 9) {
            textField.setText("00" + value);
        } else if (value > 9 && value < 99) {
            textField.setText("0" + value);
        } else if (value > 99 && value < 999) {
            textField.setText("" + value);
        }
        textField.revalidate();
    }

    public void showLabel(int x, int y) {
        int point = field.WIDE_OF_GAME_FIELD*y + x;
        gameButtons[point].hideButton();
        panel.revalidate();
    }

    public boolean checkFlag(int x, int y) {
        int point = field.WIDE_OF_GAME_FIELD*y + x;
        return gameButtons[point].checkFlag();
    }

    public Field getField() {
        return field;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    JLayeredPane getFieldPane() {
        return fieldPane;
    }

    ConstraintsMaker getMaker() {
        return maker;
    }

    public Timer getTimer() { return timer; }

    public JTextField getMessageField() { return messageField; }

    public BombCounter getBombCounter() {
        return bombCounter;
    }

    public void close() {
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.dispose();
    }

    public boolean retry () {
        int value = JOptionPane.showConfirmDialog(jFrame, "Попробуешь еще?");
        return value == JOptionPane.OK_OPTION;
    }
}
