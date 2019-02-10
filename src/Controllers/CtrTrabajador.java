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
        System.out.println(actionEvent.getActionCommand());
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
                System.out.println("Insertando");
                break;
            case "Modificar":
                System.out.println("Modificando");
                break;
            case "Eliminar":
                System.out.println("Eliminando");
                break;
            default:
                break;
        }
    }
}
