package UI;

import Controllers.CtrLavados;
import Controllers.CtrModelo;
import Controllers.CtrPropietario;
import Controllers.CtrTrabajador;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIMain extends JTabbedPane {
    private GUILavadosDiario panelDiario;
    private GUIPanel panelGeneral;
    private GUIPropietario panelPropietario;
    private GUITrabajador panelTrabajador;
    private GUIModelo panelModelo;

    public GUIMain() {
    	panelGeneral = null;
        this.addTab("General", new JScrollPane(panelGeneral));
         
        panelDiario = new GUILavadosDiario();
        ActionListener lavCtr = new CtrLavados(panelDiario);
        panelDiario.addController(lavCtr);
        this.addTab("Diario", new JScrollPane(panelDiario));

        panelPropietario = new GUIPropietario();
        ActionListener propCtr = new CtrPropietario(panelPropietario);
        panelPropietario.addController(propCtr);
        this.addTab("Propietarios", panelPropietario);
        
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