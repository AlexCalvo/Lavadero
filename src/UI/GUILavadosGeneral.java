package UI;

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

public class GUILavadosGeneral extends GUIPanel {

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
	

	private JLabel lsumatorioPrecio = new JLabel("Total:");

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
	private JTextField tPropietario= new JTextField();

	private JTextField tsumatorioPrecios = new JTextField();

	private JButton bIns = new JButton("Insertar");
	private JButton bMod = new JButton("Modificar");
	private JButton bEli = new JButton("Eliminar");

	private JLabel lFechaInicial = new JLabel("Fecha Inicial:");
	private JLabel lFechaFinal = new JLabel("Fecha Final:");
	private JLabel lNumVeces = new JLabel("Numero Veces:");
	private DatePicker tFechaInicial = new DatePicker();
	private DatePicker tFechaFinal = new DatePicker();
	private JTextField tNumVeces = new JTextField();

	private JButton bQueryEntreFechas = new JButton("Entre Fechas");
	private JButton bQueryMatricula = new JButton("Por Matricula");
	private JButton bQueryEntreFechasVeces = new JButton("Entre Fechas por veces");
	private JButton bQueryPorVeces = new JButton("Por Veces");
	private JButton bQueryComplemento = new JButton("Por Complemento");
	private JButton bQueryTrabajador = new JButton("Por Trabajador");
	private JButton bRefrescar = new JButton("Refrescar");

	public GUILavadosGeneral() {
		createPanelGeneral();
	}

	private void createPanelGeneral() {
		this.setLayout(new BorderLayout(0, 10));
		tableLavados = new JTable(new LavadosGeneralTableModel());// LocalDate.now()));
		calcularTotal();
		tsumatorioPrecios.setEditable(false);
		tableLavados.setRowHeight(25);
		tableLavados.setFont(new java.awt.Font("Tahoma", 0, 15));
		// tFecha.setDate(LocalDate.now());
		// this.add(create2ElementPanel(lFecha, tFecha), BorderLayout.NORTH);

		this.add(createNorthPanel(), BorderLayout.NORTH);

		this.add(new JScrollPane(tableLavados), BorderLayout.CENTER);
		this.add(createSouthPanel(), BorderLayout.SOUTH);
	}

	private Component createNorthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 4, 4, 4));

		panel.add(create2ElementPanel(lFechaInicial, tFechaInicial));
		panel.add(create2ElementPanel(lFechaFinal, tFechaFinal));
		panel.add(create2ElementPanel(lMatricula, tMatricula));
		panel.add(create2ElementPanel(lNumVeces, tNumVeces));
		panel.add(create2ElementPanel(lComplemento, tComplemento));
		panel.add(create2ElementPanel(lTrabajador, tTrabajador));

		return panel;
	}

	private Component createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2, 6, 6));

		panel.add(createQueryButtons());
		// panel.add(create2ElementPanel(lMatricula, tMatricula));
		// panel.add(create2ElementPanel(lModelo, tModelo));
		// panel.add(create2ElementPanel(lHora, tHora));
		// panel.add(create2ElementPanel(lFecha, tFecha));
		// panel.add(create2ElementPanel(lTelefono, tTelefono));
		// panel.add(create2ElementPanel(lPropietario, tPropietario));
		// panel.add(create2ElementPanel(lTrabajador, tTrabajador));
		// panel.add(createButtonPanel(), BorderLayout.SOUTH);

		return panel;
	}

	public String getFieldMatricula() {
		return this.tMatricula.getText();
	}

	public void setFieldMatricula(String string) {
		this.tMatricula.setText(string);
	}

	public Modelo getFieldModelo() {
		return (Modelo) this.autoModelo.findItem(autoModelo.getItemSelected().toString());
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

	public LocalDate getFieldFechaIni() {
		return this.tFechaInicial.getDate();
	}

	public LocalDate getFieldFechaFin() {
		return this.tFechaFinal.getDate();
	}

	public String getNumVeces() {
		return this.tNumVeces.getText();
	}

	public void setNumVeces(String n) {
		this.tNumVeces.setText(n);
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
		return (Complementos) this.autoComplemento.findItem(autoComplemento.getItemSelected().toString());
	}

	public void setFieldComplemento(Complementos complemento) {
		if (complemento == null)
			this.tComplemento.setText("");
		else
			this.tComplemento.setText(complemento.getNombre());
	}

	public Trabajador getFieldTrabajador() {
		return (Trabajador) this.autoTrabajador.findItem(autoTrabajador.getItemSelected().toString());
	}

	public void setFieldTrabajador(Trabajador trabajador) {
		if (trabajador == null)
			this.tTrabajador.setText("");
		else
			this.tTrabajador.setText(trabajador.getNombre());
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
	/*
	 * private Component createButtonPanel() { JPanel panelBotones = new JPanel();
	 * panelBotones.setLayout(new FlowLayout()); panelBotones.add(bIns);
	 * panelBotones.add(bMod); panelBotones.add(bEli); return panelBotones; }
	 */

	private Component createQueryButtons() {
		JPanel panelQuery = new JPanel();
		panelQuery.setLayout(new FlowLayout());
		panelQuery.setLayout(new GridLayout(2, 2, 5, 5));

		panelQuery.add(bQueryEntreFechas);
		panelQuery.add(bQueryMatricula);
		panelQuery.add(bQueryEntreFechasVeces);
		panelQuery.add(bQueryPorVeces);
		panelQuery.add(bQueryComplemento);
		panelQuery.add(bQueryTrabajador);
		panelQuery.add(bRefrescar);
		panelQuery.add(create2ElementPanel(lsumatorioPrecio, tsumatorioPrecios));
		return panelQuery;
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

		bQueryEntreFechas.setActionCommand("EntreFechas");
		bQueryEntreFechas.addActionListener(ctr);
		bQueryEntreFechasVeces.setActionCommand("EntreFechasVeces");
		bQueryEntreFechasVeces.addActionListener(ctr);
		bQueryMatricula.setActionCommand("Matricula");
		bQueryMatricula.addActionListener(ctr);
		bQueryPorVeces.setActionCommand("Veces");
		bQueryPorVeces.addActionListener(ctr);
		bQueryComplemento.setActionCommand("Complemento");
		bQueryComplemento.addActionListener(ctr);
		bQueryTrabajador.setActionCommand("Trabajador");
		bQueryTrabajador.addActionListener(ctr);
		bRefrescar.setActionCommand("Refrescar");
		bRefrescar.addActionListener(ctr);

		tableLavados.getSelectionModel().addListSelectionListener((ListSelectionListener) ctr);
		tFecha.addDateChangeListener((DateChangeListener) ctr);
	}

	public void reloadData() {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).reloadData();
		calcularTotal();
	}

	public void reloadTrabajador(Trabajador t) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).fillFilteredByTrabajador(t);
		calcularTotal();
	}

	public void reloadComplementos(Complementos p) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).fillFilteredByComplemento(p);
		calcularTotal();
	}

	public void reloadVeces(int v) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).fillFilteredByVeces(v);
		calcularTotal();
	}

	public void reloadEntreFechas(LocalDate fechaIni, LocalDate fechaFin) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).fillFilteredEntreFechas(fechaIni, fechaFin);
		calcularTotal();
	}

	public void reloadEntreFechasVeces(LocalDate fechaIni, LocalDate fechaFin, int x) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).fillFilteredEntreFechasVeces(fechaIni, fechaFin, x);
		calcularTotal();
	}

	public void reloadMatricula(String m) {
		this.tableLavados.getSelectionModel().clearSelection();
		((LavadosGeneralTableModel) tableLavados.getModel()).fillFilteredByMatricula(m);
		calcularTotal();
	}
	

	private void calcularTotal() {
		double acumulador = 0;
		double num = 0;

		for (int i = 0; i < tableLavados.getRowCount(); i++) {
			num = ((Modelo) tableLavados.getValueAt(i, 2)).getPrecio();
			acumulador += num;
		}

		tsumatorioPrecios.setText(acumulador + " Eur");
	}

	private class LavadosGeneralTableModel extends AbstractTableModel {

		private String[] columnNames;
		private Object[][] data;

		public LavadosGeneralTableModel() {
			columnNames = Lavados.columnas;
			List<Lavados> lista = Lavados.listaLavados();

			alterTable(lista);
		}

		public void reloadData() {
			List<Lavados> lista = Lavados.listaLavados();

			alterTable(lista);
		}

		public void fillFilteredByTrabajador(Trabajador t) {
			List<Lavados> lista = Lavados.listaLavadosPorTrabajador(t);

			alterTable(lista);
		}

		public void fillFilteredByComplemento(Complementos p) {
			List<Lavados> lista = Lavados.listaLavadosPorComplemento(p);

			alterTable(lista);
		}

		public void fillFilteredByVeces(int v) {
			List<Lavados> lista = Lavados.listaLavadosPorVeces(v);

			alterTable(lista);
		}

		public void fillFilteredEntreFechas(LocalDate ini, LocalDate fin) {
			List<Lavados> lista = Lavados.listaLavadosPorFechas(ini, fin);

			alterTable(lista);
		}

		public void fillFilteredEntreFechasVeces(LocalDate ini, LocalDate fin, int x) {
			List<Lavados> lista = Lavados.listaLavadosPorVecesFechas(ini, fin, x);

			alterTable(lista);
		}

		private void alterTable(List<Lavados> lista) {
			data = new Object[lista.size()][columnNames.length];
			for (int i = 0; i < lista.size(); i++) {
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

		public void fillFilteredByMatricula(String m) {
			List<Lavados> lista = Lavados.listaLavadosPorMatricula(m);

			data = new Object[lista.size()][columnNames.length];
			for (int i = 0; i < lista.size(); i++) {
				data[i] = lista.get(i).asArray();
			}
			fireTableDataChanged();
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