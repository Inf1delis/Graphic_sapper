package view.gui_view;

import controller.gui_controller.Mouse;

import javax.swing.*;
import java.awt.*;

public class GameButton extends Component {
    private JLabel label;
    private JButton button;
    private JPanel card;
    private GuiFieldView guiFieldView;

    private GridBagConstraints constraint;

    public GameButton (GuiFieldView guiFieldView) {

        this.guiFieldView = guiFieldView;
        createLayers();
        constraint = guiFieldView.getMaker().make();

        button.addMouseListener(new Mouse(this));

        guiFieldView.getFieldPane().add(card, constraint);
    }

    private void createLayers() {
        card = new JPanel(new CardLayout());


        label = new JLabel();
        label.setFont(GuiFieldView.FONT);

        label.setHorizontalAlignment(0);

        button = new JButton(" ");
        button.setPreferredSize(new Dimension(25,25));
        button.setFont(GuiFieldView.FONT);
        button.setForeground(Color.GRAY);
        button.setBackground(Color.lightGray);

        JPanel card1 = new JPanel();
        card1.add(button);

        JPanel card2 = new JPanel();
        card2.setLayout(new BorderLayout());
        card2.add(label, BorderLayout.CENTER);

        card.add(card1, "card 1");
        card.add(card2, "card 2");


        card.setPreferredSize(new Dimension(35,35));

        card.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        guiFieldView.getjFrame().getContentPane().add(card);

    }

    public void setLabel() {
        int value = guiFieldView.getField().gameField[constraint.gridy][constraint.gridx].getBombNearCounter();
        if (value == -1) {
            label.setText("B");
            label.setForeground(Color.RED);
        } else if (value == 0 ) {
            label.setText(" ");
        } else {
            label.setText(Integer.toString(value));
            label.setForeground(Color.GRAY);
        }
    }

    public void hideButton() {

        CardLayout cardLayout = (CardLayout) card.getLayout();
        cardLayout.show(card, "card 2");
        guiFieldView.getFieldPane().revalidate();
    }

    public void checkUpgate() {
        int x = constraint.gridx;
        int y = constraint.gridy;
        if (guiFieldView.getField().gameField[y][x].visibility()) {
            if (checkFlag() && guiFieldView.getBombCounter().getBombs() < guiFieldView.getField().BOMB_QUANTITY){
                guiFieldView.getBombCounter().plusBomb();
                button.setText("");
            }
            hideButton();
        }

        guiFieldView.getFieldPane().revalidate();
    }

    public boolean checkFlag() {
        return button.getText().equals("?");
    }

    public JButton getButton() {
        return button;
    }

    public JPanel getCard() {
        return card;
    }

    public GuiFieldView getGuiFieldView() {
        return guiFieldView;
    }

    public GridBagConstraints getConstraint() {
        return constraint;
    }
}