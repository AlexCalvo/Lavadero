package UI;

import Models.Lavados;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.List;

public class GUILavadosDiario extends GUIPanel {

    private Component panelGeneral;
    private JTable tableLavados;
    private JLabel lMatricula =new JLabel("Matricula:");
    private JLabel lModelo = new JLabel("Modelo:");
    private JLabel lHora = new JLabel("Hora:");
    private JLabel lFecha = new JLabel("Fecha:");
    private JLabel lTelefono = new JLabel("Telefono:");
    private JLabel lPropietario = new JLabel("Propietario:");
    private JLabel lTrabajador =  new JLabel("Trabajador:");

    private JTextField tMatricula = new JTextField();
    private JTextField tModelo = new JTextField();
    private TimePicker tHora = createTimePicker();
    //private JTextField tFecha = new JTextField();
    private DatePicker tFecha = new DatePicker();
    private JTextField tTelefono = new JTextField();
    private JTextField tPropietario = new JTextField();
    private JTextField tTrabajador = new JTextField();

    private JButton bIns = new JButton("Insertar");
    private JButton bMod = new JButton("Modificar");
    private JButton bEli = new JButton("Eliminar");

    public GUILavadosDiario() {
        createPanelDiario();
    }

    private void createPanelDiario() {
        this.setLayout(new BorderLayout());
        tableLavados = new JTable(new LavadosDiariosTableModel());

        this.add(new JScrollPane(tableLavados), BorderLayout.CENTER);

        this.add(createSouthPanel(), BorderLayout.SOUTH);
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,2,5,5));

        panel.add(create2ElementPanel(lMatricula, tMatricula));
        panel.add(create2ElementPanel(lModelo, tModelo));
        panel.add(create2ElementPanel(lHora, tHora));
        panel.add(create2ElementPanel(lFecha, tFecha));
        panel.add(create2ElementPanel(lTelefono, tTelefono));
        panel.add(create2ElementPanel(lPropietario, tPropietario));
        panel.add(create2ElementPanel(lTrabajador, tTrabajador));
        panel.add(createButtonPanel());
               return panel;
    }
    
    private Component createButtonPanel() {
    	JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(bIns);
        panelBotones.add(bMod);
        panelBotones.add(bEli);
        return panelBotones;
    }

    @Override
    public void addController(ActionListener ctr) {

    }

    private class LavadosDiariosTableModel extends AbstractTableModel {

        private String[] columnNames;
        private Object[][] data;

        public LavadosDiariosTableModel() {
            columnNames = Lavados.columnas;
            List<Lavados> lista = Lavados.listaLavados();
            data = new Object[lista.size()][columnNames.length];
            for (int i = 0;i < lista.size(); i++) {
                data[i] = lista.get(i).asArray();
            }
        }

        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        @Override
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }
    private TimePicker createTimePicker() {
        TimePickerSettings settings = new TimePickerSettings();
        settings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, LocalTime.of(7, 0), LocalTime.of(21, 0));
        settings.use24HourClockFormat();
        return new TimePicker(settings);
    }
}
