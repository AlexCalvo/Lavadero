package UI;

import javax.swing.*;
import java.awt.*;

public class GUILaunch {
    public static JFrame createGUI() {
        JFrame window = new JFrame("Lavadero Puerto S.L.");
        JTabbedPane panel = new GUIMain();

        window.setIconImage(new ImageIcon("logo.png").getImage());
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(new Dimension(640, 480));
        window.pack();
        window.setVisible(true);

        return window;
    }
}
