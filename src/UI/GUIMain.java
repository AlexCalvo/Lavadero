package UI;

import Controllers.CtrTrabajador;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIMain extends JTabbedPane {
    private GUIPanel panelDiario;
    private GUIPanel panelGeneral;
    private GUIPanel panelPropietario;
    private GUITrabajador panelTrabajador;
    private GUIPanel panelModelo;


    public GUIMain() {
        panelDiario = new GUILavadosDiario();
        this.addTab("Diario", new JScrollPane(panelDiario));

        panelGeneral = null;
        this.addTab("General", new JScrollPane(panelGeneral));

        panelPropietario = new GUIPropietario();
        this.addTab("Propietarios", new JScrollPane(panelPropietario));

        panelTrabajador = new GUITrabajador();
        ActionListener trabCtr = new CtrTrabajador(panelTrabajador);
        panelTrabajador.addController(trabCtr);
        this.addTab("Trabajador", panelTrabajador);

        panelModelo = new GUIModelo();
        this.addTab("Modelos", panelModelo);
    }

}
