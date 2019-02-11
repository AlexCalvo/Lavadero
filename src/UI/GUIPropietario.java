package UI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import Models.Lavados;
import Models.Propietario;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GUIPropietario extends GUIPanel {
	 private JTabbedPane tabPanel;
	 private Component panelGeneral;
	 private JTable tablePropietario;
	 
     private JLabel lNombre = new JLabel("Nombre:");
     private JLabel lID = new JLabel("ID:");
     private JLabel lTelefono = new JLabel("Telefono:");
     
     private JTextField tID = new JTextField();
     private JTextField tNombre = new JTextField();
     private JTextField tTelefono = new JTextField();

    private JButton bIns = new JButton("Insertar");
    private JButton bMod = new JButton("Modificar");
    private JButton bEli = new JButton("Eliminar");
     
     public GUIPropietario() {
         createPanelPropietario();
     }
	    
     private void createPanelPropietario() {
         this.setLayout(new BorderLayout());

         tablePropietario = new JTable(new PropietarioTableModel());

         this.add(new JScrollPane(tablePropietario), BorderLayout.CENTER);

         this.add(createSouthPanel(), BorderLayout.SOUTH);
     }
     
     private Component createSouthPanel() {
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(2,2,5,5));

         panel.add(create2ElementPanel(lID, tID));  
         panel.add(create2ElementPanel(lNombre, tNombre));
         panel.add(create2ElementPanel(lTelefono, tTelefono));
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

    private class PropietarioTableModel extends AbstractTableModel {

         private String[] columnNames;
         private Object[][] data;

         public PropietarioTableModel() {
             columnNames = Propietario.columnas;
             List<Propietario> lista = Propietario.listaPropietarios();
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
