package UI;

import Controllers.CtrModelo;
import Controllers.CtrTrabajador;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIMain extends JTabbedPane {
    private GUIPanel panelDiario;
    private GUIPanel panelGeneral;
    private GUIPropietario panelPropietario;
    private GUITrabajador panelTrabajador;
    private GUIModelo panelModelo;


    public GUIMain() {
        panelDiario = new GUILavadosDiario();
        this.addTab("Diario", new JScrollPane(panelDiario));

        panelGeneral = null;
        this.addTab("General", new JScrollPane(panelGeneral));

        panelPropietario = new GUIPropietario();
        ActionListener propCtr = new CtrPropietario(panelPropietario);
        panelTrabajador.addController(propCtr);
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
