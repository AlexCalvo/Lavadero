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

        panelModelo = new GUIModelo();
        ActionListener modCtr = new CtrModelo(panelModelo);
        panelModelo.addController(modCtr);
   
        panelDiario = new GUILavadosDiario();
        ActionListener lavCtr = new CtrLavados(panelDiario, panelModelo);
        panelDiario.addController(lavCtr);

        panelComplementos = new GUIComplementos();
        ActionListener propCtr = new CtrComplementos(panelComplementos);
        panelComplementos.addController(propCtr);
        
        panelTrabajador = new GUITrabajador();
        ActionListener trabCtr = new CtrTrabajador(panelTrabajador);
        panelTrabajador.addController(trabCtr);


        this.addTab("General", panelGeneral);
        this.addTab("Diario", new JScrollPane(panelDiario));
        this.addTab("Complementos", panelComplementos);
        this.addTab("Trabajador", panelTrabajador);
        this.addTab("Modelos", panelModelo);
    }
}