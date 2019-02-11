package UI;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import Models.Lavados;
import Models.Trabajador;

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
     }
	    
     private void createPanelTrabajador() {
         this.setLayout(new BorderLayout());

         tableTrabajador = new JTable(new TrabajadorTableModel());

         this.add(new JScrollPane(tableTrabajador), BorderLayout.CENTER);

         this.add(createSouthPanel(), BorderLayout.SOUTH);
     }

     private Component createSouthPanel() {
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(1,2,5,5));

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

     @Override
     public void addController(ActionListener ctr) {
        bIns.addActionListener(ctr);
        bIns.setActionCommand("Insertar");
        bMod.addActionListener(ctr);
        bMod.setActionCommand("Modificar");
        bEli.addActionListener(ctr);
        bEli.setActionCommand("Eliminar");
     }
     
     private class TrabajadorTableModel extends AbstractTableModel{
    	 
    	 private String[] columnNames;
         private Object[][] data;
         
         public TrabajadorTableModel() {
             columnNames = Trabajador.columnas;
             List<Trabajador> lista = Trabajador.listaTrabajador();
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


