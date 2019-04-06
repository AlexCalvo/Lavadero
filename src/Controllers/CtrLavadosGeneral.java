package Controllers;

import Models.Lavados;
import Models.Complementos;
import Models.Trabajador;
import UI.GUILavadosDiario;
import UI.GUILavadosGeneral;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class CtrLavadosGeneral implements ActionListener, ListSelectionListener, DateChangeListener {

	private GUILavadosGeneral view;
	private Lavados selectedLavados;

	public CtrLavadosGeneral(GUILavadosGeneral view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println(actionEvent.getActionCommand());
		switch (actionEvent.getActionCommand()) {
		case "EntreFechas":
			try {
				LocalDate fechaIni = view.getFieldFechaIni();
				LocalDate fechaFin = view.getFieldFechaFin();
				view.reloadEntreFechas(fechaIni, fechaFin);

			} catch (NullPointerException e) {

			}
			break;
		case "EntreFechasVeces":
			try {
				LocalDate fechaIni = view.getFieldFechaIni();
				LocalDate fechaFin = view.getFieldFechaFin();
				int x = Integer.parseInt(view.getNumVeces());
				view.reloadEntreFechasVeces(fechaIni, fechaFin, x);

			} catch (NullPointerException e) {

			} catch (NumberFormatException e) {

			}
			break;
		case "Matricula":
			try {
				String m = view.getFieldMatricula();
				view.reloadMatricula(m);

			} catch (NullPointerException e) {

			}
			break;
		case "Veces":
			try {
				int v = Integer.parseInt(view.getNumVeces());
				view.reloadVeces(v);

			} catch (NullPointerException e) {

			} catch (NumberFormatException e) {

			}

			break;
		case "Propietario":
			try {
				Complementos p = view.getFieldComplemento();

				view.reloadComplementos(p);
			} catch (NullPointerException e) {

			}

			break;
		case "Trabajador":
			try {
				Trabajador t = view.getFieldTrabajador();

				view.reloadTrabajador(t);
			} catch (NullPointerException e) {

			}

			break;
		case "Refrescar":
			view.reloadData();
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
			int id = (int) ((selectionModel.isSelectedIndex(lastIndex)) ? model.getValueAt(lastIndex, 0)
					: model.getValueAt(firstindex, 0));

			selectedLavados = new Lavados(id);
			view.setFieldModelo(selectedLavados.getModelo());
			view.setFieldMatricula(selectedLavados.getMatricula());
			view.setFieldHora(selectedLavados.getHora());
			view.setFieldTelefono(selectedLavados.getTelefono());
			view.setFieldComplemento(selectedLavados.getProp());
			view.setFieldTrabajador(selectedLavados.getTrab());

		}
	}

	@Override
	public void dateChanged(DateChangeEvent dateChangeEvent) {
		System.out.println("Fecha cambiada a " + dateChangeEvent.getNewDate());
		view.reloadData();
	}
}
