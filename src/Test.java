import DB.DatabaseException;
import DB.MySqlDB;
import UI.GUIMain;

import javax.swing.*;
import java.awt.*;

public class Test {

    private final static String configFile = "database.config";

    public static void main(String[] args)  {
        JFrame frame = new JFrame("");
        JTabbedPane panel = new GUIMain();

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(640, 480));
        frame.pack();
        frame.setVisible(true);



        try (MySqlDB db = new MySqlDB()) {

        } catch (DatabaseException e) {
            System.err.println(e);
        }
    }
}
