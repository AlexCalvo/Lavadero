package UI;

import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;
import Controllers.CtrPrecioModelo;

public class GUIEmergente extends JTabbedPane{
    private GUIPrecioModelo panel;

    public GUIEmergente() {
		panel = new GUIPrecioModelo();
        ActionListener ctr = new CtrPrecioModelo(panel);
        panel.addController(ctr);
        this.addTab("Nuevo Modelo", panel);
    }
}
