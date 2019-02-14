package Controllers;

import Models.Propietario;
import UI.GUIPropietario;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrPropietario implements ActionListener, ListSelectionListener {
    private GUIPropietario view;
    private Propietario selectedPropietario;

    public CtrPropietario(GUIPropietario view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
                if (!view.getFieldId().equals("")) {
                    selectedPropietario = new Propietario(view.getFieldId(),view.getFieldNombre(),view.getFieldTelefono());
                    view.reloadData();
                    view.setFieldId("");
                    view.setFieldNombre("");
                    view.setFieldTelefono("");
                    selectedPropietario = null;
                }
                break;
            case "Modificar":
                if (selectedPropietario != null) {
                    if (!view.getFieldNombre().equals("")) {
                    	selectedPropietario.setId(view.getFieldId());
                    	selectedPropietario.setNombre(view.getFieldNombre());
                    	selectedPropietario.setTelefono(view.getFieldTelefono());
                        view.reloadData();
                        view.setFieldNombre("");
                        view.setFieldNombre("");
                        view.setFieldTelefono("");
                        selectedPropietario = null;
                    }
                }
                break;
            case "Eliminar":
                if (selectedPropietario != null) {
                    selectedPropietario.delete();
                    view.reloadData();
                    selectedPropietario = null;
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
            String id = (String) ((selectionModel.isSelectedIndex(lastIndex)) ? model.getValueAt(lastIndex, 0) : model.getValueAt(firstindex, 0));


            selectedPropietario = new Propietario(id);
            view.setFieldId(selectedPropietario.getId());
            view.setFieldNombre(selectedPropietario.getNombre());
            view.setFieldTelefono(selectedPropietario.getTelefono());

        }
    }
}
