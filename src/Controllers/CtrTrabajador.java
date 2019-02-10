package Controllers;

import UI.GUIPanel;
import UI.GUITrabajador;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrTrabajador implements ActionListener {
    Component view;

    public CtrTrabajador(Component view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "":
                break;
            case "2":
                break;
            default:
                break;
        }
    }
}
