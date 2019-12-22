package UI;


import Models.Lavados;
import Models.Modelo;
import Models.Complementos;
import Models.Trabajador;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CellRenderer extends DefaultTableCellRenderer implements TableCellRenderer{

	public CellRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	    //establecemos el fondo blanco o vacío
	    setBackground(null);
	    //COnstructor de la clase DefaultTableCellRenderer
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
 
        //Establecemos las filas que queremos cambiar el color. == 0 para pares y != 0 para impares
        boolean oddRow = (row % 2 == 0);
 
        //Creamos un color para las filas. El 200, 200, 200 en RGB es un color gris
        Color c = new Color(234, 234, 234);
	 
	        //Si las filas son pares, se cambia el color a gris
	    if (oddRow) {
	        setBackground(c);
	    }
	    return this;
	}
}
