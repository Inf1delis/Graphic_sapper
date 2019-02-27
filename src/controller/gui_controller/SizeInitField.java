package controller.gui_controller;

import controller.AbstractController;
import model.Field;
import view.gui_view.GuiFieldView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SizeInitField {
    private JFrame frame;
    private JTextField wideField;
    private JTextField lengthField;
    private JTextField bombField;
    private JButton okButton;
    private JButton exitButton;
    private static Font FONT = GuiFieldView.FONT;


    static int wide;
    static int length;
    static int bombs;

    public SizeInitField() {
        this.frame = getJFrame();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        wideField = fillTextField();
        lengthField = fillTextField();
        bombField = fillTextField();

        GridBagConstraints wideFieldPositiion = GuiFieldView.getConstraint(1,0,1,1);
        GridBagConstraints lengthFieldPositiion = GuiFieldView.getConstraint(1,1,1,1);
        GridBagConstraints bombFieldPositiion = GuiFieldView.getConstraint(1,2,1,1);

        JLabel wideLabel = fillLabel("Ширина поля: ");
        JLabel lengthLabel = fillLabel("Высота поля: ");
        JLabel bombLabel = fillLabel("Бомб на поле: ");


        panel.add(wideLabel, GuiFieldView.getConstraint(0,0,1,1));
        panel.add(lengthLabel, GuiFieldView.getConstraint(0,1,1,1));
        panel.add(bombLabel, GuiFieldView.getConstraint(0,2,1,1));
        panel.add(wideField, wideFieldPositiion);
        panel.add(lengthField, lengthFieldPositiion);
        panel.add(bombField, bombFieldPositiion);

        okButton = fillButton("Ок");
        exitButton = fillButton("Выход");


        okButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                try {
                    wide = getInt(wideField);
                    length = getInt(lengthField);
                    bombs = getInt(bombField);
                    Field field = new Field(wide, length, bombs);
                    AbstractController controller = new GuiController(field);
                    controller.getClicks();

                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.dispose();
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(getErrorJFrame(), "Ошибка ввода!");
                    cleanFields();
                    frame.revalidate();
                }
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                frame.dispose();
            }
        });

        panel.add(okButton, GuiFieldView.getConstraint(0,3,1,1));
        panel.add(exitButton, GuiFieldView.getConstraint(1,3,1,1));

        frame.add(panel);
        frame.revalidate();
    }

    private void cleanFields() {
        wideField.setText("");
        lengthField.setText("");
        bombField.setText("");
    }

    private int getInt(JTextField field) {
            String text = field.getText();
            return Integer.parseInt(text);
    }

    public static void main(String[] args) {
        new SizeInitField();
    }

    JButton fillButton (String text) {
        JButton tmp = new JButton(text);
        tmp.setForeground(Color.gray);
        tmp.setFont(FONT);
        return tmp;
    }

    JLabel fillLabel (String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.gray);
        label.setFont(FONT);
        return label;
    }

     JTextField fillTextField() {
        JTextField textField = new JTextField(3);
        textField.setEditable(true);
        textField.setFont(FONT);
        textField.setForeground(Color.GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        return textField;
    }

    private JFrame getJFrame () {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 350, 200);
        jFrame.setTitle("Saper!");
        return jFrame;
    }

    static JFrame getErrorJFrame () {
        JFrame jFrame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2 - 150, 350, 200);
        jFrame.setTitle("Ошибка!");
        return jFrame;
    }


}
