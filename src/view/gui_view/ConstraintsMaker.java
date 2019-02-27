package view.gui_view;

import java.awt.*;

public class ConstraintsMaker {
    private int x = 0;
    private int y = 0;
    private int xBorder;

    ConstraintsMaker (int xBorder) {
        this.xBorder = xBorder;
    }

    GridBagConstraints make () {
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.weightx = 0;
        constraint.weighty = 0;
        constraint.gridx = x;
        constraint.gridy = y;
        constraint.gridwidth = 1;
        constraint.gridheight = 1;

        if (x == xBorder - 1) {
            x = 0;
            y++;
        } else {
            x++;
        }

        return constraint;
    }
}