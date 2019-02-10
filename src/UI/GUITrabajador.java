package UI;

import javax.swing.*;
import java.awt.*;

public class GUITrabajador extends GUIPanel {
	 private JTabbedPane tabPanel;
	 private Component panelGeneral;
	 private JTable tableTrabajador;
	 
     private JLabel lNombre = new JLabel("Nombre:");
   
     private JTextField tNombre = new JTextField();
     
     public GUITrabajador() {
         createPanelTrabajador();
     }
	    
     private void createPanelTrabajador() {
         this.setLayout(new BorderLayout());

         Object rowData[][] = { { "1" },
                 { "1" } };
         Object columnNames[] = { "Column One"};
         tableTrabajador = new JTable(rowData, columnNames);

         this.add(tableTrabajador, BorderLayout.CENTER);

         this.add(createSouthPanel(), BorderLayout.SOUTH);
     }

     private Component createSouthPanel() {
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(1,2,5,5));

         panel.add(create2ElementPanel(lNombre, tNombre));
         
         return panel;
     }

}
