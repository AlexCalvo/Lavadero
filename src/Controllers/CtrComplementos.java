package Controllers;

import Models.Complementos;
import UI.GUIComplementos;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrComplementos implements ActionListener, ListSelectionListener {
    private GUIComplementos view;
    private Complementos selectedComplemento;

    public CtrComplementos(GUIComplementos view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
                if (!view.getFieldId().equals("")) {
                    selectedComplemento = new Complementos(view.getFieldId(),view.getFieldNombre(),view.getFieldPrecio());
                    view.reloadData();
                    view.setFieldId("");
                    view.setFieldNombre("");
                    view.setFieldPrecio("");
                    selectedComplemento = null;
                }
                break;
            case "Modificar":
                if (selectedComplemento != null) {
                    if (!view.getFieldNombre().equals("")) {
                    	selectedComplemento.setId(view.getFieldId());
                    	selectedComplemento.setNombre(view.getFieldNombre());
                    	selectedComplemento.setPrecio(view.getFieldPrecio());
                        view.reloadData();
                        view.setFieldId("");
                        view.setFieldNombre("");
                        view.setFieldPrecio("");
                        selectedComplemento = null;
                    }
                }
                break;
            case "Eliminar":
                if (selectedComplemento != null) {
                    selectedComplemento.delete();
                    view.reloadData();
                    view.setFieldId("");
                    view.setFieldNombre("");
                    view.setFieldPrecio("");
                    selectedComplemento = null;
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


            selectedComplemento = new Complementos(id);
            view.setFieldId(selectedComplemento.getId());
            view.setFieldNombre(selectedComplemento.getNombre());
            view.setFieldPrecio(selectedComplemento.getPrecio()+"");

        }
    }
}
