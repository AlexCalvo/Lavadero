package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class GUIPanel extends JPanel {

    protected JPanel create2ElementPanel(Component c1, Component c2) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(c1, BorderLayout.WEST);
        panel.add(c2, BorderLayout.CENTER);
        return panel;
    }

    public abstract void addController(ActionListener ctr);
}
