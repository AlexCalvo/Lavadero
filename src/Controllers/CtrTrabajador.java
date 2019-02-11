package Controllers;

import Models.Trabajador;
import UI.GUIPanel;
import UI.GUITrabajador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrTrabajador implements ActionListener, ListSelectionListener {
    private GUITrabajador view;
    private Trabajador selectedTrabajador;

    public CtrTrabajador(GUITrabajador view) {
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

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if (!listSelectionEvent.getValueIsAdjusting()) {

            int firstindex = listSelectionEvent.getFirstIndex();
            int lastIndex = listSelectionEvent.getLastIndex();
            ListSelectionModel selectionModel = view.getTable().getSelectionModel();
            TableModel model = view.getTable().getModel();
            int index = (int)((selectionModel.isSelectedIndex(lastIndex))?model.getValueAt(lastIndex, 0):model.getValueAt(firstindex, 0));


            selectedTrabajador = new Trabajador(index);
            System.out.println(selectedTrabajador);

        }
    }
}
