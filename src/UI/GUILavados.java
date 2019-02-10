package UI;

import Models.Lavados;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.util.List;

public class GUILavados extends GUIPanel {

    private Component panelGeneral;
    private JTable tableLavados;
    private JLabel lMatricula =new JLabel("Matricula:");
    private JLabel lMarca =  new JLabel("Marca:");
    private JLabel lModelo = new JLabel("Modelo:");
    private JLabel lHora = new JLabel("Hora:");
    private JLabel lFecha = new JLabel("Fecha:");
    private JLabel lTam = new JLabel("Tamano:");
    private JLabel lPrecio = new JLabel("Precio:");
    private JLabel lTelefono = new JLabel("Telefono:");
    private JLabel lPropietario = new JLabel("Propietario:");
    private JLabel lTrabajador =  new JLabel("Trabajador:");

    private JTextField tMatricula = new JTextField();
    private JTextField tMarca = new JTextField();
    private JTextField tModelo = new JTextField();
    private JTextField tHora = new JTextField();
    private JTextField tFecha = new JTextField();
    private JTextField tTam = new JTextField();
    private JTextField tPrecio = new JTextField();
    private JTextField tTelefono = new JTextField();
    private JTextField tPropietario = new JTextField();
    private JTextField tTrabajador = new JTextField();

    public GUILavados() {
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
        panel.add(create2ElementPanel(lMarca, tMarca));
        panel.add(create2ElementPanel(lModelo, tModelo));
        panel.add(create2ElementPanel(lHora, tHora));
        panel.add(create2ElementPanel(lFecha, tFecha));
        panel.add(create2ElementPanel(lTam, tTam));
        panel.add(create2ElementPanel(lPrecio, tPrecio));
        panel.add(create2ElementPanel(lTelefono, tTelefono));
        panel.add(create2ElementPanel(lPropietario, tPropietario));
        panel.add(create2ElementPanel(lTrabajador, tTrabajador));

        return panel;
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
}
