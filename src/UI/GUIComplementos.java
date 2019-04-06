package UI;

import Models.Complementos;
import Models.Trabajador;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIComplementos extends GUIPanel {
    private JTabbedPane tabPanel;
    private Component panelGeneral;
    private JTable tableComplemento;

    private JLabel lNombre = new JLabel("Nombre:");
    private JLabel lID = new JLabel("ID:");
    private JLabel lPrecio = new JLabel("Precio:");

    private JTextField tID = new JTextField();
    private JTextField tNombre = new JTextField();
    private JTextField tPrecio = new JTextField();

    private JButton bIns = new JButton("Insertar");
    private JButton bMod = new JButton("Modificar");
    private JButton bEli = new JButton("Eliminar");

    public GUIComplementos() {
        createPanelComplementos();
    }

    private void createPanelComplementos() {
        this.setLayout(new BorderLayout());

        tableComplemento = new JTable(new ComplementoTableModel());
        tableComplemento.setRowHeight(25);
        tableComplemento.setFont(new java.awt.Font("Tahoma", 0, 15)); 
        this.add(new JScrollPane(tableComplemento), BorderLayout.CENTER);

        this.add(createSouthPanel(), BorderLayout.SOUTH);
    }

    private Component createSouthPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 5, 5));

        panel.add(create2ElementPanel(lID, tID));
        panel.add(create2ElementPanel(lNombre, tNombre));
        panel.add(create2ElementPanel(lPrecio, tPrecio));
        panel.add(createButtonPanel());


        return panel;
    }
    
    
    public String getFieldNombre() {
        return this.tNombre.getText();
    }

    public void setFieldNombre(String string) {
        this.tNombre.setText(string);
    }
    
    public String getFieldId() {
        return this.tID.getText();
    }

    public void setFieldId(String string) {
        this.tID.setText(string);
    }
   

    public double getFieldPrecio() {
        return Double.parseDouble(this.tPrecio.getText());
    }
    
    public void setFieldPrecio(String d) {
        this.tPrecio.setText(d);
     }
    

    private Component createButtonPanel() {
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        panelBotones.add(bIns);
        panelBotones.add(bMod);
        panelBotones.add(bEli);
        return panelBotones;
    }

    public void reloadData() {
        this.tableComplemento.getSelectionModel().clearSelection();
        ((ComplementoTableModel) tableComplemento.getModel()).reloadData();
    }

    public JTable getTable() {
        return tableComplemento;
    }

    @Override
    public void addController(ActionListener ctr) {
        bIns.addActionListener(ctr);
        bIns.setActionCommand("Insertar");
        bMod.addActionListener(ctr);
        bMod.setActionCommand("Modificar");
        bEli.addActionListener(ctr);
        bEli.setActionCommand("Eliminar");
        tableComplemento.getSelectionModel().addListSelectionListener((ListSelectionListener) ctr);
    }
    
    
    private class ComplementoTableModel extends AbstractTableModel {

        private String[] columnNames;
        private Object[][] data;

        public ComplementoTableModel() {
            columnNames = Complementos.columnas;
            List<Complementos> lista = Complementos.listaComplementos();
            data = new Object[lista.size()][columnNames.length];
            for (int i = 0; i < lista.size(); i++) {
                data[i] = lista.get(i).asArray();
            }
        }
        
        public void reloadData() {
            List<Complementos> lista = Complementos.listaComplementos();
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
