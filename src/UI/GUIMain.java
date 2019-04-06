package UI;

import Controllers.CtrLavados;
import Controllers.CtrLavadosGeneral;
import Controllers.CtrModelo;
import Controllers.CtrComplementos;
import Controllers.CtrTrabajador;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUIMain extends JTabbedPane {
    private GUILavadosDiario panelDiario;
    private GUILavadosGeneral panelGeneral;
    private GUIComplementos panelComplementos;
    private GUITrabajador panelTrabajador;
    private GUIModelo panelModelo;

    public GUIMain() {
		panelGeneral = new GUILavadosGeneral();
        ActionListener lavCtrg = new CtrLavadosGeneral(panelGeneral);
        panelGeneral.addController(lavCtrg);
        this.addTab("General", panelGeneral);
        
   
        panelDiario = new GUILavadosDiario();
        ActionListener lavCtr = new CtrLavados(panelDiario);
        panelDiario.addController(lavCtr);
        this.addTab("Diario", new JScrollPane(panelDiario));

        panelComplementos = new GUIComplementos();
        ActionListener propCtr = new CtrComplementos(panelComplementos);
        panelComplementos.addController(propCtr);
        this.addTab("Complementos", panelComplementos);
        
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