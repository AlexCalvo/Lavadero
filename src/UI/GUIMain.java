package UI;

import Controllers.CtrTrabajador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUIMain extends JTabbedPane {
    private Component panelDiario;
    private Component panelGeneral;
    private Component panelPropietario;
    private Component panelTrabajador;
    private Component panelModelo;



    public GUIMain() {
        panelDiario = new JScrollPane(new GUILavadosDiario());
        this.addTab("Diario", panelDiario);

        panelGeneral = new JScrollPane();
        this.addTab("General", panelGeneral);

        panelPropietario = new JScrollPane(new GUIPropietario());
        this.addTab("Propietarios", panelPropietario);

        panelTrabajador = new GUITrabajador();
        ActionListener trabCtr = new CtrTrabajador(panelTrabajador);
        this.addTab("Trabajador", panelTrabajador);

        panelModelo = new GUIModelo();
        this.addTab("Modelos", panelModelo);
    }

}
