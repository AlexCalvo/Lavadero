package Controllers;

import Models.Complementos;
import UI.GUIComplementos;
import UI.GUILavadosDiario;
import UI.GUILavadosGeneral;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrComplementos implements ActionListener, ListSelectionListener {
	private GUIComplementos view;
	private Complementos selectedComplemento;
	private GUILavadosDiario lavadosView;
	private GUILavadosGeneral generalView;

	public CtrComplementos(GUIComplementos view, GUILavadosDiario lavadosView, GUILavadosGeneral generalView) {
		this.view = view;
		this.lavadosView = lavadosView;
		this.generalView = generalView;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		switch (actionEvent.getActionCommand()) {
		case "Insertar":
			if (!view.getFieldNombre().equals("")) {
				selectedComplemento = new Complementos(view.getFieldNombre(), view.getFieldPrecio());
				lavadosView.getAutoComplemento().addItem(selectedComplemento);
				generalView.getAutoComplemento().addItem(selectedComplemento);
				view.reloadData();
				view.setFieldNombre("");
				view.setFieldPrecio("");
				selectedComplemento = null;
			}
			break;
		case "Modificar":
			if (selectedComplemento != null) {
				if (!view.getFieldNombre().equals("")) {
					selectedComplemento.setNombre(view.getFieldNombre());
					selectedComplemento.setPrecio(view.getFieldPrecio());
					view.reloadData();
					view.setFieldNombre("");
					view.setFieldPrecio("");
					selectedComplemento = null;
				}
			}
			break;
		case "Eliminar":
			if (selectedComplemento != null) {
				lavadosView.getAutoComplemento().removeItem(selectedComplemento);
				generalView.getAutoComplemento().removeItem(selectedComplemento);
				selectedComplemento.delete();
				view.reloadData();
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
			String id = (String) ((selectionModel.isSelectedIndex(lastIndex)) ? model.getValueAt(lastIndex, 0)
					: model.getValueAt(firstindex, 0));

			selectedComplemento = new Complementos(id);
			view.setFieldNombre(selectedComplemento.getNombre());
			view.setFieldPrecio(selectedComplemento.getPrecio() + "");

		}
	}
}
