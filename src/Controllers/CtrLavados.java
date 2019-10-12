package Controllers;

import Models.Lavados;
import Models.Modelo;
import UI.*;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrLavados implements ActionListener, ListSelectionListener, DateChangeListener {

    private GUILavadosDiario view;
    private Lavados selectedLavados;
    private GUIModelo viewModelo;

    public CtrLavados(GUILavadosDiario view, GUIModelo viewModelo) {
        this.view = view;
        this.viewModelo = viewModelo;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println(actionEvent.getActionCommand());
        switch (actionEvent.getActionCommand()) {
            case "Insertar":
            	try {
                	selectedLavados = new Lavados(view.getFieldMatricula(), view.getFieldModelo(), view.getFieldHora(), view.getFieldFecha(),view.getFieldTelefono(), view.getFieldComplemento(), view.getFieldTrabajador(),view.getFieldObservaciones(),view.getFieldPropietario(), view.getFieldFactura());
            		
            		view.reloadData();
                    view.setFieldModelo(null);
                    view.setFieldMatricula("");
                    view.setFieldHora(null);
                    view.setFieldTelefono("");
                    view.setFieldComplemento(null);
                    view.setFieldTrabajador(null);
                    view.setFieldObservaciones(null);
                    view.setFieldPropietario(null);
                    view.setFieldFactura(false);

                    selectedLavados = null;
            	} catch(ModelNotFoundException e) {
            	    if (!view.getStringModelo().equals(""))
            		    ventana();
            	    else
                        JOptionPane.showMessageDialog(null,"El campo modelo no puede estar vacio.");
            	}
                
                break;
            case "Modificar":
                if (selectedLavados != null) {
                    selectedLavados.setModelo(view.getFieldModelo());
                    selectedLavados.setMatricula(view.getFieldMatricula());
                    selectedLavados.setHora(view.getFieldHora());
                    selectedLavados.setTelefono(view.getFieldTelefono());
                    if (view.getFieldComplemento() != null)
                        selectedLavados.setComp(view.getFieldComplemento());

                    selectedLavados.setObservaciones(view.getFieldObservaciones());
                    selectedLavados.setPropietario(view.getFieldPropietario());
                    selectedLavados.setTrab(view.getFieldTrabajador());
                    selectedLavados.setFactura(view.getFieldFactura());

                    view.reloadData();
                    view.setFieldModelo(null);
                    view.setFieldMatricula("");
                    view.setFieldHora(null);
                    view.setFieldTelefono("");
                    view.setFieldComplemento(null);
                    view.setFieldTrabajador(null);
                    view.setFieldObservaciones("");
                    view.setFieldPropietario("");
                    view.setFieldFactura(false);
                    
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
                    view.setFieldFactura(false);

                    selectedLavados = null;
                }
                break;
            default:
                break;
        }
    }

    private void ventana() {
    	
    	String prec = JOptionPane.showInputDialog("Inserte precio del nuevo modelo: ");
    	try {
            double p = Double.parseDouble(prec);
            Modelo newModelo = new Modelo(view.getStringModelo(), p);
            view.setFieldModelo(newModelo);
            view.getAutoModelo().addItem(newModelo);
            viewModelo.reloadData();

            this.actionPerformed(new ActionEvent(this, 3, "Insertar"));
        } catch (NullPointerException e) {
    	    //Pass
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
            view.setFieldComplemento(selectedLavados.getComp());
            view.setFieldTrabajador(selectedLavados.getTrab());
            view.setFieldObservaciones(selectedLavados.getObservaciones());
            view.setFieldPropietario(selectedLavados.getPropietario());
            view.setFieldFactura(selectedLavados.getFactura());

        }
    }

    @Override
    public void dateChanged(DateChangeEvent dateChangeEvent) {
        System.out.println("Fecha cambiada a " + dateChangeEvent.getNewDate());
        view.reloadData(dateChangeEvent.getNewDate());
    }
}