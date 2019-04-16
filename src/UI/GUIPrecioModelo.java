package UI;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.*;


public class GUIPrecioModelo extends GUIPanel{
	
	private JLabel text = new JLabel("Modelo no existente,introduce su precio para introducirlo");
	private JLabel lPrecio = new JLabel("Precio:");
	private JTextField tPrecio = new JTextField();
	private JButton bIns = new JButton("Insertar");
	
	public GUIPrecioModelo() {
		createVentana();
	}
	
	private void createVentana() {
		JPanel panel = new JPanel();
		panel.add(text);
		panel.add(create2ElementPanel(lPrecio, tPrecio));
		panel.add(createButtonPanel());

	}
	
	private Component createButtonPanel() {
		JPanel panelBotones = new JPanel();
		panelBotones.add(bIns);
		return panelBotones;
	}

	public double getFieldPrecio() {
		return Double.parseDouble(this.tPrecio.getText());
	}

	public void setFieldPrecio(String d) {
		this.tPrecio.setText(d);
	}

	@Override
	public void addController(ActionListener ctr) {
		bIns.addActionListener(ctr);
		bIns.setActionCommand("Insertar");		
	}
}
