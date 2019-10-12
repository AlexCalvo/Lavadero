package UI;

import Controllers.ModelNotFoundException;
import Models.Lavados;
import Models.Modelo;
import Models.Complementos;
import Models.Trabajador;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.mxrck.autocompleter.TextAutoCompleter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class GUILavadosDiario extends GUIPanel {

	private Component panelGeneral;
	private JTable tableLavados;
	private JLabel lMatricula = new JLabel("Matricula:");
	private JLabel lModelo = new JLabel("Modelo:");
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
	private TextAutoCompleter autoModelo = createAutoCompleterModelo(tModelo);
	private TimePicker tHora = createTimePicker();
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

		panel.add(create2ElementPanel(lMatricula, tMatricula));
		panel.add(create2ElementPanel(lModelo, tModelo));
		panel.add(create2ElementPanel(lHora, tHora));
		panel.add(create2ElementPanel(lTelefono, tTelefono));
		panel.add(create2ElementPanel(lComplemento, tComplemento));
		panel.add(create2ElementPanel(lTrabajador, tTrabajador));
		panel.add(create2ElementPanel(lObservaciones, tObservaciones));
		panel.add(create2ElementPanel(lPropietario, tPropietario));
		panel.add(create2ElementPanel(lFactura, tFactura));

		//Empty pannel to center the ButtonPannel
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
		return this.tHora.getTime();
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
		try {
			return (Complementos) this.autoComplemento.findItem(autoComplemento.getItemSelected().toString());
		}catch(NullPointerException e) {
			return new Complementos("Ninguno");
		}
		
	}

	public void setFieldComplemento(Complementos propietario) {
		if (propietario == null)
			this.tComplemento.setText("");
		else
			this.tComplemento.setText(propietario.getNombre());
	}

	public Trabajador getFieldTrabajador() {
		try {
			return (Trabajador) this.autoTrabajador.findItem(autoTrabajador.getItemSelected().toString());
		}catch(NullPointerException e) {
			return new Trabajador(-1);
		}
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

	public TextAutoCompleter getAutoModelo() {
		return autoModelo;
	}

	public void setAutoModelo(TextAutoCompleter autoModelo) {
		this.autoModelo = autoModelo;
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