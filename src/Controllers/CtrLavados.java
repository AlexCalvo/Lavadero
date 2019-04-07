package Controllers;

import Models.Lavados;
import UI.GUILavadosDiario;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrLavados implements ActionListener, ListSelectionListener, DateChangeListener {

    private GUILavadosDiario view;
    private Lavados selectedLavados;

    public CtrLavados(GUILavadosDiario view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
                selectedLavados = new Lavados(view.getFieldMatricula(), view.getFieldModelo(), view.getFieldHora(), view.getFieldFecha(),view.getFieldTelefono(), view.getFieldComplemento(), view.getFieldTrabajador(),view.getFieldObservaciones(),view.getFieldPropietario());
                view.reloadData();
                view.setFieldModelo(null);
                view.setFieldMatricula("");
                view.setFieldHora(null);
                view.setFieldTelefono("");
                view.setFieldComplemento(null);
                view.setFieldTrabajador(null);
                view.setFieldObservaciones(null);
                view.setFieldPropietario(null);
                selectedLavados = null;
                break;
            case "Modificar":
                if (selectedLavados != null) {
                    selectedLavados.setModelo(view.getFieldModelo());
                    selectedLavados.setMatricula(view.getFieldMatricula());
                    selectedLavados.setHora(view.getFieldHora());
                    selectedLavados.setTelefono(view.getFieldTelefono());
                    if (view.getFieldComplemento() != null)
                        selectedLavados.setProp(view.getFieldComplemento());

                    selectedLavados.setTrab(view.getFieldTrabajador());

                    view.reloadData();
                    view.setFieldModelo(null);
                    view.setFieldMatricula("");
                    view.setFieldHora(null);
                    view.setFieldTelefono("");
                    view.setFieldComplemento(null);
                    view.setFieldTrabajador(null);
                    view.setFieldObservaciones("");
                    view.setFieldPropietario("");
                    
                    selectedLavados = null;
                }
                break;
            case "Eliminar":
                if (selectedLavados != null) {
                    selectedLavados.delete();
                    view.reloadData();
                    view.setFieldModelo(null);
                    view.setFieldMatricula("");
                    view.setFieldHora(null);
                    view.setFieldTelefono("");
                    view.setFieldComplemento(null);
                    view.setFieldTrabajador(null);
                    view.setFieldObservaciones("");
                    view.setFieldPropietario("");
                    selectedLavados = null;
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


            selectedLavados = new Lavados(id);
            view.setFieldModelo(selectedLavados.getModelo());
            view.setFieldMatricula(selectedLavados.getMatricula());
            view.setFieldHora(selectedLavados.getHora());
            view.setFieldTelefono(selectedLavados.getTelefono());
            view.setFieldComplemento(selectedLavados.getProp());
            view.setFieldTrabajador(selectedLavados.getTrab());
            view.setFieldObservaciones(selectedLavados.getObservaciones());
            view.setFieldPropietario(selectedLavados.getPropietario());

        }
    }

    @Override
    public void dateChanged(DateChangeEvent dateChangeEvent) {
        System.out.println("Fecha cambiada a " + dateChangeEvent.getNewDate());
        view.reloadData(dateChangeEvent.getNewDate());
    }
}