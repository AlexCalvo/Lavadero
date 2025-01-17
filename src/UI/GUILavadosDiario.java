package UI;

import Controllers.CtrPrecioModelo;
import Controllers.ModelNotFoundException;
import Models.*;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class GUILavadosDiario extends GUIPanel {

	private Component panelGeneral;
	private JTable tableLavados;
	private JLabel lMatricula = new JLabel("Matricula:");
	private JLabel lModelo = new JLabel("Modelo:");
	private JLabel lTipoLavado = new JLabel("TipoLavado:");
	private JLabel lHora = new JLabel("Hora:");
	private JLabel lFecha = new JLabel("Fecha:");
	private JLabel lTelefono = new JLabel("Telefono:");
	private JLabel lComplemento = new JLabel("Complemento:");
	private JLabel lTrabajador = new JLabel("Trabajador:");
	private JLabel lObservaciones = new JLabel("Observaciones:");
	private JLabel lPropietario = new JLabel("Propietario:");
	private JLabel lFactura = new JLabel("Factura:");

	private JTextField tMatricula = new JTextField();
	private JTextField tModelo = new JTextField();
	private JTextField tTipoLavado = new JTextField();
	private TextAutoCompleter autoModelo = createAutoCompleterModelo(tModelo);
	private JTextField tHora = new JTextField();
	private DatePicker tFecha = new DatePicker();
	private JTextField tTelefono = new JTextField();
	private JTextField tComplemento = new JTextField();
	private TextAutoCompleter autoComplemento = createAutoCompleterComplemento(tComplemento);
	private JTextField tTrabajador = new JTextField();
	private TextAutoCompleter autoTrabajador = createAutoCompleterTrabajador(tTrabajador);
	private JTextField tObservaciones = new JTextField();
	private JTextField tPropietario = new JTextField();
	private JCheckBox tFactura = new JCheckBox();

	private JButton bIns = new JButton("Insertar");
	private JButton bMod = new JButton("Modificar");
	private JButton bEli = new JButton("Eliminar");

	public GUILavadosDiario() {
		createPanelDiario();
		setCellRender(tableLavados);
	}

	public void setCellRender(JTable table) {
		Enumeration<TableColumn> en = table.getColumnModel().getColumns();
		while (en.hasMoreElements()) {
			TableColumn tc = en.nextElement();
			tc.setCellRenderer(new CellRenderer());
		}
	}

	private void createPanelDiario() {
		this.setLayout(new BorderLayout(0, 10));
		tableLavados = new JTable(new LavadosDiariosTableModel(LocalDate.now()));
		tableLavados.setRowHeight(25);
		tableLavados.setFont(new java.awt.Font("Tahoma", 0, 15));
		tFecha.setDate(LocalDate.now());

		this.add(create2ElementPanel(lFecha, tFecha), BorderLayout.NORTH);
		this.add(new JScrollPane(tableLavados), BorderLayout.CENTER);

		this.add(createSouthPanel(), BorderLayout.SOUTH);
	}

	private Component createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 5, 5));

		panel.add(create2ElementPanel(lHora, tHora));
		panel.add(create2ElementPanel(lMatricula, tMatricula));
		panel.add(create2ElementPanel(lTelefono, tTelefono));
		panel.add(create2ElementPanel(lModelo, tModelo));
		panel.add(create2ElementPanel(lComplemento, tComplemento));
		panel.add(create2ElementPanel(lTrabajador, tTrabajador));

		panel.add(create2ElementPanel(lPropietario, tPropietario));
		panel.add(create2ElementPanel(lFactura, tFactura));
		panel.add(create2ElementPanel(lObservaciones, tObservaciones));

		// Empty pannel to center the ButtonPannel
		panel.add(new JPanel());
		panel.add(createButtonPanel(), BorderLayout.SOUTH);

		return panel;
	}

	public String getFieldMatricula() {
		return this.tMatricula.getText();
	}

	public void setFieldMatricula(String string) {
		this.tMatricula.setText(string);
	}

	
	public String getStringModelo() {
		return this.tModelo.getText();
	}

	public Modelo getFieldModelo() {
		try {
			return (Modelo) this.autoModelo.findItem(autoModelo.getItemSelected().toString());
		} catch (NullPointerException e) {
			throw new ModelNotFoundException();
		}
	}

	public void setFieldModelo(Modelo modelo) {
		if (modelo == null)
			this.tModelo.setText("");
		else
			this.tModelo.setText(modelo.toString());
	}

	public LocalTime getFieldHora() {
		String s = tHora.getText();
		int hora = -1;
		int minuto = -1;
		if(s.contains(":")) {
			hora = Integer.parseInt(s.split(":")[0]);
			minuto = Integer.parseInt(s.split(":")[1]);
		}else {
			if(s.length() == 3) {
				hora = Integer.parseInt(s.substring(0,1));
				minuto = Integer.parseInt(s.substring(1));
				
			}else if(s.length() == 4) {
				hora = Integer.parseInt(s.substring(0,2));
				minuto = Integer.parseInt(s.substring(2));
			}//Error
			
		}
		if((hora < 24 && hora >= 0) && (minuto < 60 && minuto >= 0)) {
			return LocalTime.of(hora,minuto,0);
		}else {
			return null;
		}
	}

	public LocalDate getFieldFecha() {
		return this.tFecha.getDate();
	}

	public void setFieldHora(LocalTime time) {
		if (time == null)
			this.tHora.setText("");
		else
			this.tHora.setText(time.toString());
	}

	public String getFieldTelefono() {
		return this.tTelefono.getText();
	}

	public void setFieldTelefono(String string) {
		this.tTelefono.setText(string);
	}

	public Complementos getFieldComplemento() {

		Complementos res = (Complementos) this.autoComplemento.findItem(tComplemento.getText());
		if(res == null){
			return new Complementos("Ninguno");
		}
		return res;

	}

	public void setFieldComplemento(Complementos propietario) {
		if (propietario == null)
			this.tComplemento.setText("");
		else
			this.tComplemento.setText(propietario.getNombre());
	}

	public Trabajador getFieldTrabajador() {

		Trabajador res = (Trabajador) this.autoTrabajador.findItem(tTrabajador.getText());
		if(res == null){
			return new Trabajador(-1);
		}
		return res;

	}

	public void setFieldTrabajador(Trabajador trabajador) {
		if (trabajador == null)
			this.tTrabajador.setText("");
		else
			this.tTrabajador.setText(trabajador.getNombre());
	}

	public boolean getFieldFactura() {
		return this.tFactura.isSelected();
	}

	public void setFieldFactura(boolean factura) {
		this.tFactura.setSelected(factura);
	}

	public String getFieldObservaciones() {
		return this.tObservaciones.getText();
	}

	public void setFieldObservaciones(String string) {
		this.tObservaciones.setText(string);
	}

	public String getFieldPropietario() {
		return this.tPropietario.getText();
	}

	public void setFieldPropietario(String string) {
		this.tPropietario.setText(string);
	}
	
	public String getFieldTipoLavado(double precioLavado) {
		
		String res = null;
		if(precioLavado - getFieldComplemento().getPrecio() == getFieldModelo().getPrecioExterior()) {
			res = "Exterior";
		}else if(precioLavado - getFieldComplemento().getPrecio() == getFieldModelo().getPrecioInterior()) {
			res = "Interior";
		}else {
			res = "Completo";
		}
		return res;
	}


	public double getFieldPrecio() {
		

		String s1 = "Precio Exterior: " + DoubleFormatter.df.format(getFieldModelo().getPrecioExterior()) + "�";
		String s2 = "Precio Interior: " + DoubleFormatter.df.format(getFieldModelo().getPrecioInterior()) + "�";
		String s3 = "Precio Completo: " + DoubleFormatter.df.format(getFieldModelo().getPrecioCompleto()) + "�";


		Object[] possibilities = { s1, s2, s3 };
		String s = null;
		
		// If a string was returned, say so.
		while ((s == null) || (s.length() <= 0)) {
			s = (String) JOptionPane.showInputDialog((JFrame)SwingUtilities.windowForComponent(this), "Elige tipo lavado: ",
					"Tipo Lavado", JOptionPane.PLAIN_MESSAGE, null, possibilities, s1);

			if (s.equals(s1)) {
				return getFieldModelo().getPrecioExterior() + getFieldComplemento().getPrecio();
			} else if (s.equals(s2)) {
				return getFieldModelo().getPrecioInterior() + getFieldComplemento().getPrecio();
			} else {
				return getFieldModelo().getPrecioCompleto() + getFieldComplemento().getPrecio();
			}
		}
		return 0;
		
	}


	// public void setFieldPrecio(String string) {
	// this.tPropietario.setText(string);
	// }

	public TextAutoCompleter getAutoModelo() {
		return autoModelo;
	}

	public void setAutoModelo(TextAutoCompleter autoModelo) {
		this.autoModelo = autoModelo;
	}

	public TextAutoCompleter getAutoComplemento() {
		return autoComplemento;
	}


	public TextAutoCompleter getAutoTrabajador() {
		return autoTrabajador;
	}

	private Component createButtonPanel() {
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(bIns);
		panelBotones.add(bMod);
		panelBotones.add(bEli);
		return panelBotones;
	}

	public JTable getTable() {
		return tableLavados;
	}

	@Override
	public void addController(ActionListener ctr) {
		bIns.addActionListener(ctr);
		bIns.setActionCommand("Insertar");
		bMod.addActionListener(ctr);
		bMod.setActionCommand("Modificar");
		bEli.addActionListener(ctr);
		bEli.setActionCommand("Eliminar");
		tableLavados.getSelectionModel().addListSelectionListener((ListSelectionListener) ctr);
		tFecha.addDateChangeListener((DateChangeListener) ctr);
	}

	public void reloadData() {
		reloadData(tFecha.getDate());
	}

	public void reloadData(LocalDate date) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosDiariosTableModel) tableLavados.getModel()).reloadData(date);
	}

	private class LavadosDiariosTableModel extends AbstractTableModel {

		private String[] columnNames;
		private Object[][] data;

		public LavadosDiariosTableModel(LocalDate date) {
			columnNames = Lavados.columnas;
			List<Lavados> lista = new ArrayList<Lavados>();

			for (Lavados lav : Lavados.listaLavados()) {
				if (lav.getFecha().equals(date))
					lista.add(lav);
			}

			data = new Object[lista.size()][columnNames.length];
			for (int i = 0; i < lista.size(); i++) {
				if (!lista.get(i).getFecha().equals(date))
					continue;
				data[i] = lista.get(i).asArray();
			}
		}

		public void reloadData(LocalDate date) {
			List<Lavados> lista = new ArrayList<Lavados>();

			for (Lavados lav : Lavados.listaLavados()) {
				if (lav.getFecha().equals(date))
					lista.add(lav);
			}

			data = new Object[lista.size()][columnNames.length];
			for (int i = 0; i < lista.size(); i++) {
				if (!lista.get(i).getFecha().equals(date))
					continue;
				data[i] = lista.get(i).asArray();
			}
			fireTableDataChanged();
		}

		@Override
		public int getRowCount() {
			return data.length;
		}

		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		@Override
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
	}

	private TimePicker createTimePicker() {
		TimePickerSettings settings = new TimePickerSettings();
		settings.generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, LocalTime.of(7, 0),
				LocalTime.of(21, 0));
		settings.use24HourClockFormat();
		return new TimePicker(settings);
	}

	private TextAutoCompleter createAutoCompleterModelo(JTextField textField) {
		TextAutoCompleter tmp = new TextAutoCompleter(textField);

		ArrayList list = (ArrayList) Modelo.listaModelos();
		tmp.addItems(list);

		return tmp;
	}

	private TextAutoCompleter createAutoCompleterComplemento(JTextField textField) {
		TextAutoCompleter tmp = new TextAutoCompleter(textField);

		ArrayList list = (ArrayList) Complementos.listaComplementos();
		tmp.addItems(list);

		return tmp;
	}

	private TextAutoCompleter createAutoCompleterTrabajador(JTextField textField) {
		TextAutoCompleter tmp = new TextAutoCompleter(textField);

		ArrayList list = (ArrayList) Trabajador.listaTrabajador();
		tmp.addItems(list);

		return tmp;
	}
}