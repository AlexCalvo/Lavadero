package UI;

import javax.swing.*;
import java.awt.*;

public class GUIMain extends JTabbedPane {
    private Component panelDiario;
    private Component panelGeneral;
    private Component panelPropietario;
    private Component panelTrabajador;



    public GUIMain() {
        panelDiario = new JScrollPane(new GUILavados());
        this.addTab("Diario", panelDiario);

        panelGeneral = new JScrollPane();
        this.addTab("General", panelGeneral);

        panelPropietario = new JScrollPane(new GUIPropietario());
        this.addTab("Propietarios", panelPropietario);

        panelTrabajador = new GUITrabajador();
        this.addTab("Trabajador", panelTrabajador);
    }

}
