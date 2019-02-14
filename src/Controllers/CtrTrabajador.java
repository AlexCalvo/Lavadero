package Controllers;

import Models.Trabajador;
import UI.GUITrabajador;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
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
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
                if (!view.getFieldNombre().equals("")) {
                    selectedTrabajador = new Trabajador(view.getFieldNombre());
                    view.reloadData();
                    view.setFieldNombre("");
                    selectedTrabajador = null;
                }
                break;
            case "Modificar":
                if (selectedTrabajador != null) {
                    if (!view.getFieldNombre().equals("")) {
                        selectedTrabajador.setNombre(view.getFieldNombre());
                        view.reloadData();
                        view.setFieldNombre("");
                        selectedTrabajador = null;
                    }
                }
                break;
            case "Eliminar":
                if (selectedTrabajador != null) {
                    selectedTrabajador.delete();
                    view.reloadData();
                    selectedTrabajador = null;
                }
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
            if (selectionModel.isSelectionEmpty())
                return;
            int id = (int) ((selectionModel.isSelectedIndex(lastIndex)) ? model.getValueAt(lastIndex, 0) : model.getValueAt(firstindex, 0));


            selectedTrabajador = new Trabajador(id);
            view.setFieldNombre(selectedTrabajador.toString());

        }
    }
}