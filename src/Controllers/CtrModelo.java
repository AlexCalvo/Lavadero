package Controllers;

import Models.Modelo;
import UI.GUILavadosDiario;
import UI.GUIModelo;
import UI.GUIPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrModelo implements ActionListener, ListSelectionListener {
    private GUIModelo view;
    private Modelo selectedModelo;
    private GUILavadosDiario lavadosView;

    public CtrModelo(GUIModelo panelModelo, GUILavadosDiario lavadosView) {
        this.view = panelModelo;
        this.lavadosView = lavadosView;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
                if (!view.getFieldNombre().equals("")) {
                    selectedModelo = new Modelo(view.getFieldNombre(),view.getFieldPrecioExterior(),view.getFieldPrecioInterior(),view.getFieldPrecioCompleto());
                    lavadosView.getAutoModelo().addItem(selectedModelo);
                    view.reloadData();
                    view.setFieldNombre("");
                    view.setFieldPrecioExterior("");
                    view.setFieldPrecioInterior("");
                    view.setFieldPrecioCompleto("");
                    selectedModelo = null;
                }
                break;
            case "Modificar":
                if (selectedModelo != null) {
                    if (!view.getFieldNombre().equals("")) {
                        selectedModelo.setNombre(view.getFieldNombre());
                        selectedModelo.setPrecioExterior(view.getFieldPrecioExterior());
                        selectedModelo.setPrecioInterior(view.getFieldPrecioInterior());
                        selectedModelo.setPrecioCompleto(view.getFieldPrecioCompleto());
                        view.reloadData();
                        view.setFieldNombre("");
                        view.setFieldPrecioExterior("");
                        view.setFieldPrecioInterior("");
                        view.setFieldPrecioCompleto("");
                      
                        selectedModelo = null;
                    }
                }
                break;
            case "Eliminar":
                if (selectedModelo != null) {
                    lavadosView.getAutoModelo().removeItem(selectedModelo);
                    selectedModelo.delete();
                    view.reloadData();
                    view.setFieldNombre("");
                    view.setFieldPrecioExterior("");
                    view.setFieldPrecioInterior("");
                    view.setFieldPrecioCompleto("");
                    selectedModelo = null;
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


            selectedModelo = new Modelo(id);
            view.setFieldNombre(selectedModelo.getNombre());
            view.setFieldPrecioExterior(selectedModelo.getPrecioExterior() + "");
            view.setFieldPrecioInterior(selectedModelo.getPrecioInterior() + "");
            view.setFieldPrecioCompleto(selectedModelo.getPrecioCompleto() + "");
        }
    }
}
