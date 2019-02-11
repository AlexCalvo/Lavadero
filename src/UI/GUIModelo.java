package UI;

import Models.Modelo;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIModelo extends GUIPanel {
    private JTabbedPane tabPanel;
    private Component panelGeneral;
    private JTable tableModelo;
    

    private JButton bIns = new JButton("Insertar");
    private JButton bMod = new JButton("Modificar");
    private JButton bEli = new JButton("Eliminar");

    private JLabel lNombre = new JLabel("Nombre:");
    private JLabel lPrecio = new JLabel("Precio:");
    private JLabel lMarca = new JLabel("Marca:");

    private JTextField tPrecio = new JTextField();
    private JTextField tNombre = new JTextField();
    private JTextField tMarca = new JTextField();



    public GUIModelo() {
        createPanelPropietario();
    }

    private void createPanelPropietario() {
        this.setLayout(new BorderLayout());

        tableModelo = new JTable(new ModeloTableModel());

        this.add(new JScrollPane(tableModelo), BorderLayout.CENTER);

        this.add(createSouthPanel(), BorderLayout.SOUTH);
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));

        panel.add(create2ElementPanel(lPrecio, tPrecio));
        panel.add(create2ElementPanel(lNombre, tNombre));
        panel.add(create2ElementPanel(lMarca, tMarca));
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
        return tableModelo;
    }

    @Override
    public void addController(ActionListener ctr) {
    	 bIns.addActionListener(ctr);
         bIns.setActionCommand("Insertar");
         bMod.addActionListener(ctr);
         bMod.setActionCommand("Modificar");
         bEli.addActionListener(ctr);
         bEli.setActionCommand("Eliminar");
         tableModelo.getSelectionModel().addListSelectionListener((ListSelectionListener) ctr);
     }
    
    public String getFieldNombre() {
        return this.tNombre.getText();
    }
    public double getFieldPrecio() {
        return Integer.parseInt(this.tPrecio.getText());
    }
    public String getFieldMarca() {
        return this.tMarca.getText();
    }
    
    
    public void setFieldNombre(String string) {
        this.tNombre.setText(string);
    }
    public void setFieldPrecio(String d) {
       this.tPrecio.setText(d);
    }
    public void setFieldMarca(String string) {
        this.tMarca.setText(string);
    }
    
    public void reloadData() {
        this.tableModelo.getSelectionModel().clearSelection();
        ((ModeloTableModel) tableModelo.getModel()).reloadData();
    }


    private class ModeloTableModel extends AbstractTableModel {

        private String[] columnNames;
        private Object[][] data;

        public ModeloTableModel() {
            columnNames = Modelo.columnas;
            List<Modelo> lista = Modelo.listaModelos();
            data = new Object[lista.size()][columnNames.length];
            for (int i = 0; i < lista.size(); i++) {
                data[i] = lista.get(i).asArray();
            }
        }

        public void reloadData() {
            List<Modelo> lista = Modelo.listaModelos();
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
