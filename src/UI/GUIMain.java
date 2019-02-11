package UI;

import Controllers.CtrLavados;
import Controllers.CtrModelo;
import Controllers.CtrTrabajador;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIMain extends JTabbedPane {
    private GUILavadosDiario panelDiario;
    private GUIPanel panelGeneral;
    private GUIPanel panelPropietario;
    private GUITrabajador panelTrabajador;
    private GUIModelo panelModelo;

    public GUIMain() {
        panelDiario = new GUILavadosDiario();
        ActionListener lavCtr = new CtrLavados(panelDiario);
        panelDiario.addController(lavCtr);
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
        ActionListener modCtr = new CtrModelo(panelModelo);
        panelModelo.addController(modCtr);
        this.addTab("Modelos", panelModelo);
    }
}