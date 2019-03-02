package Controllers;

import Models.Lavados;
import Models.Propietario;
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
                break;
            case "EntreFechasVeces":
                break;
            case "Matricula":
                break;
            case "Veces":
                break;
            case "Propietario":
                try {
                    Propietario p = view.getFieldPropietario();

                    view.reloadPropietario(p);
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


            selectedLavados = new Lavados(id);
            view.setFieldModelo(selectedLavados.getModelo());
            view.setFieldMatricula(selectedLavados.getMatricula());
            view.setFieldHora(selectedLavados.getHora());
            view.setFieldTelefono(selectedLavados.getTelefono());
            view.setFieldPropietario(selectedLavados.getProp());
            view.setFieldTrabajador(selectedLavados.getTrab());

        }
    }

    @Override
    public void dateChanged(DateChangeEvent dateChangeEvent) {
        System.out.println("Fecha cambiada a " + dateChangeEvent.getNewDate());
        view.reloadData();
    }
}
