package UI;

import Models.Trabajador;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

public class GUITrabajador extends GUIPanel {
    private JTabbedPane tabPanel;
    private Component panelGeneral;
    private JTable tableTrabajador;

    private JButton bIns = new JButton("Insertar");
    private JButton bMod = new JButton("Modificar");
    private JButton bEli = new JButton("Eliminar");

    private JLabel lNombre = new JLabel("Nombre:");

    private JTextField tNombre = new JTextField();

    public GUITrabajador() {
        createPanelTrabajador();
        setCellRender(tableTrabajador);
    }
    
    public void setCellRender(JTable table) {
        Enumeration<TableColumn> en = table.getColumnModel().getColumns();
        while (en.hasMoreElements()) {
            TableColumn tc = en.nextElement();
            tc.setCellRenderer(new CellRenderer());
        }
    }

    private void createPanelTrabajador() {
        this.setLayout(new BorderLayout());

        tableTrabajador = new JTable(new TrabajadorTableModel());
        tableTrabajador.setRowHeight(25);
        tableTrabajador.setFont(new java.awt.Font("Tahoma", 0, 15)); 
        this.add(new JScrollPane(tableTrabajador), BorderLayout.CENTER);

        this.add(createSouthPanel(), BorderLayout.SOUTH);
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 5, 5));

        panel.add(create2ElementPanel(lNombre, tNombre));
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

    public JTable getTable() {
        return tableTrabajador;
    }

    @Override
    public void addController(ActionListener ctr) {
        bIns.addActionListener(ctr);
        bIns.setActionCommand("Insertar");
        bMod.addActionListener(ctr);
        bMod.setActionCommand("Modificar");
        bEli.addActionListener(ctr);
        bEli.setActionCommand("Eliminar");
        tableTrabajador.getSelectionModel().addListSelectionListener((ListSelectionListener) ctr);
    }

    public String getFieldNombre() {
        return this.tNombre.getText();
    }

    public void setFieldNombre(String string) {
        this.tNombre.setText(string);
    }

    public void reloadData() {
        this.tableTrabajador.getSelectionModel().clearSelection();
        ((TrabajadorTableModel) tableTrabajador.getModel()).reloadData();
    }

    private class TrabajadorTableModel extends AbstractTableModel {

        private String[] columnNames;
        private Object[][] data;

        public TrabajadorTableModel() {
            columnNames = Trabajador.columnas;
            List<Trabajador> lista = Trabajador.listaTrabajador();
            data = new Object[lista.size()][columnNames.length];
            for (int i = 0; i < lista.size(); i++) {
                data[i] = lista.get(i).asArray();
            }
        }

        public void reloadData() {
            List<Trabajador> lista = Trabajador.listaTrabajador();
            data = new Object[lista.size()][columnNames.length];
            for (int i = 0; i < lista.size(); i++) {
                data[i] = lista.get(i).asArray();
            }
            fireTableDataChanged();
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


