import javax.swing.*;
import java.awt.*;

public class GUITrabajador extends JTabbedPane {
	 private JTabbedPane tabPanel;
	 private Component panelGeneral;
	 private JTable tableTrabajador;
	 
     private JLabel lNombre =      new JLabel("Nombre:      ");
   
     private JTextField tNombre = new JTextField();
     
     public GUITrabajador() {

         panelGeneral = createPanelGeneral();
         this.addTab("General", panelGeneral);
     }
	    
     private Component createPanelTrabajador() {

         JPanel panelBorder = new JPanel();
         panelBorder.setLayout(new BorderLayout());

         Object rowData[][] = { { "1" },
                 { "1" } };
         Object columnNames[] = { "Column One"};
         tableTrabajador = new JTable(rowData, columnNames);

         panelBorder.add(tableTrabajador, BorderLayout.CENTER);

         panelBorder.add(createSouthPanel(), BorderLayout.SOUTH);

         return new JScrollPane(panelBorder);
     }
     
     private JPanel create2ElementPanel(Component c1, Component c2) {
         JPanel panel = new JPanel(new BorderLayout());
         panel.add(c1, BorderLayout.WEST);
         panel.add(c2, BorderLayout.CENTER);
         return panel;
     }
     
     private Component createSouthPanel() {
         JPanel panel = new JPanel();
         panel.setLayout(new GridLayout(5,2,5,5));

         panel.add(create2ElementPanel(lNombre, tNombre));
         
         return panel;
     }
     
     private Component createPanelGeneral() {
         return null;
     }
}
