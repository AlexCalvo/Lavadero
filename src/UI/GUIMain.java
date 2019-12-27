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
        panelModelo = new GUIModelo();
        panelDiario = new GUILavadosDiario();
        panelTrabajador = new GUITrabajador();
        panelComplementos = new GUIComplementos();

        ActionListener lavCtrg = new CtrLavadosGeneral(panelGeneral);
        ActionListener modCtr = new CtrModelo(panelModelo, panelDiario);
        ActionListener lavCtr = new CtrLavados(panelDiario, panelModelo);
        ActionListener complCtr = new CtrComplementos(panelComplementos, panelDiario, panelGeneral);
        ActionListener trabCtr = new CtrTrabajador(panelTrabajador, panelDiario, panelGeneral);

        panelGeneral.addController(lavCtrg);
        panelModelo.addController(modCtr);
        panelDiario.addController(lavCtr);
        panelComplementos.addController(complCtr);
        panelTrabajador.addController(trabCtr);


        this.addTab("General", panelGeneral);
        this.addTab("Diario", new JScrollPane(panelDiario));
        this.addTab("Complementos", panelComplementos);
        this.addTab("Trabajador", panelTrabajador);
        this.addTab("Modelos", panelModelo);
    }
}