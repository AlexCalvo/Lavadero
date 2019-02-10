package UI;

import javax.swing.*;
import java.awt.*;

public class GUIPropietario extends GUIPanel {
	 private JTabbedPane tabPanel;
	 private Component panelGeneral;
	 private JTable tablePropietario;
	 
     private JLabel lNombre = new JLabel("Nombre:");
     private JLabel lID = new JLabel("ID:");
     
     private JTextField tID = new JTextField();
     private JTextField tNombre = new JTextField();
     
     public GUIPropietario() {
         createPanelPropietario();
     }
	    
     private void createPanelPropietario() {
         this.setLayout(new BorderLayout());

         Object rowData[][] = { { "1" },
                 { "1" } };
         Object columnNames[] = { "Column One"};
         tablePropietario = new JTable(rowData, columnNames);

         this.add(tablePropietario, BorderLayout.CENTER);

         this.add(createSouthPanel(), BorderLayout.SOUTH);
     }
     
     private Component createSouthPanel() {
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(2,2,5,5));

         panel.add(create2ElementPanel(lID, tID));  
         panel.add(create2ElementPanel(lNombre, tNombre));
       
         
         return panel;
     }

}
