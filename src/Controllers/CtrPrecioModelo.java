package Controllers;

import Models.Modelo;
import UI.GUIPrecioModelo;
import UI.GUILavadosDiario;
import UI.GUIModelo;
import UI.GUIPanel;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrPrecioModelo implements ActionListener, ListSelectionListener {
	private GUIPrecioModelo view;
	private GUIModelo modeloView;
	private GUILavadosDiario lavadoView;
	private Modelo newModelo;

	public CtrPrecioModelo(GUIPrecioModelo view) {
		this.view = view;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		switch (actionEvent.getActionCommand()) {
        case "Insertar":
            if (!lavadoView.getFieldModelo().getNombre().equals("")) {
                newModelo = new Modelo(lavadoView.getFieldModelo().getNombre(),view.getFieldPrecio());
                modeloView.reloadData();
                modeloView.setFieldNombre("");
                modeloView.setFieldPrecio("");
                newModelo = null;
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
	            ListSelectionModel selectionModel = modeloView.getTable().getSelectionModel();
	            TableModel model = modeloView.getTable().getModel();
	            if (selectionModel.isSelectionEmpty())
	                return;
	            String id = (String) ((selectionModel.isSelectedIndex(lastIndex)) ? model.getValueAt(lastIndex, 0) : model.getValueAt(firstindex, 0));


	            newModelo = new Modelo(id);
	            modeloView.setFieldNombre(newModelo.getNombre());
	            modeloView.setFieldPrecio(newModelo.getPrecio() + "");

	        }
        }



}