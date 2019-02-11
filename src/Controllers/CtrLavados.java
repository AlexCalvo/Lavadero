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

                break;
            case "Modificar":

                break;
            case "Eliminar":

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
            System.out.println(selectedLavados);

        }
    }

    @Override
    public void dateChanged(DateChangeEvent dateChangeEvent) {
        System.out.println("Fecha cambiada a " + dateChangeEvent.getNewDate());
        view.reloadData(dateChangeEvent.getNewDate());
    }
}
