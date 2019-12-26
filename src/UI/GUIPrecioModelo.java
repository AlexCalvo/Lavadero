package UI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Models.Modelo;


public class GUIPrecioModelo extends JDialog implements ActionListener{
	
	private GUIModelo modeloView;
	private GUILavadosDiario lavadoView;
	private Modelo newModelo;
	
//	private boolean selected = false;
	
	private JLabel text = new JLabel("Modelo no existente,introduce sus precios para introducirlo");
	private JLabel lPrecioExterior = new JLabel("Precio Exterior:");
	private JLabel lPrecioInterior = new JLabel("Precio Interior:");
	private JLabel lPrecioCompleto = new JLabel("Precio Completo:");
	private JTextField tPrecioExterior = new JTextField();
	private JTextField tPrecioInterior = new JTextField();
	private JTextField tPrecioCompleto = new JTextField();
	private JButton bIns = new JButton("Insertar");
	
	public GUIPrecioModelo(JFrame frame) {
		super(frame,true);
		getContentPane().add(createVentana());
		pack();
		setVisible(true);
	}
	
	private JPanel createVentana() {
		JPanel panel = new JPanel();
		panel.add(text);
		panel.add(create2ElementPanel(lPrecioExterior, tPrecioExterior));
		panel.add(create2ElementPanel(lPrecioInterior, tPrecioInterior));
		panel.add(create2ElementPanel(lPrecioCompleto, tPrecioCompleto));
		panel.add(createButtonPanel());
		return panel;

	}

	protected JPanel create2ElementPanel(Component c1, Component c2) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(c1, BorderLayout.WEST);
        panel.add(c2, BorderLayout.CENTER);
        return panel;
    }
	
	private Component createButtonPanel() {
		JPanel panelBotones = new JPanel();
		panelBotones.add(bIns);
		bIns.addActionListener(this);
		return panelBotones;
	}
	
//	public boolean isSelected() {
//		return selected;
//	}
//	
//	public void setSelected(boolean sel) {
//		selected = sel;
//	}

	public double getFieldPrecioExterior() {
		return Double.parseDouble(this.tPrecioExterior.getText());
	}

	public void setFieldPrecioExterior(String d) {
		this.tPrecioExterior.setText(d);
	}
	
	public double getFieldPrecioInterior() {
		return Double.parseDouble(this.tPrecioInterior.getText());
	}

	public void setFieldPrecioInterior(String d) {
		this.tPrecioInterior.setText(d);
	}
	
	public double getFieldPrecioCompleto() {
		return Double.parseDouble(this.tPrecioCompleto.getText());
	}

	public void setFieldPrecioCompleto(String d) {
		this.tPrecioCompleto.setText(d);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		switch (actionEvent.getActionCommand()) {
        case "Insertar":
        	this.setVisible(false);
            if (!lavadoView.getFieldModelo().getNombre().equals("")) {
                newModelo = new Modelo(lavadoView.getFieldModelo().getNombre(),this.getFieldPrecioExterior(),this.getFieldPrecioInterior(),this.getFieldPrecioCompleto());
                modeloView.reloadData();
                modeloView.setFieldNombre("");
                modeloView.setFieldPrecioExterior("");
                modeloView.setFieldPrecioInterior("");
                modeloView.setFieldPrecioCompleto("");
                newModelo = null;
            }
            break;
		default:
          break;
		}
		
	}

	
//	public void addController(ActionListener ctr) {
//		bIns.addActionListener(ctr);
//		bIns.setActionCommand("Insertar");		
//	}

	
}
