package Controllers;

import Models.Lavados;
import Models.Complementos;
import Models.Trabajador;
import Reports.*;
import UI.GUILavadosGeneral;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class CtrLavadosGeneral implements ActionListener, ListSelectionListener, DateChangeListener {

	private enum Reports {
		ENTRE_FECHAS, MATRICULA, COMPLEMENTO, TRABAJADOR, VECES, VECES_FECHA, GENERAL,PROPIETARIO,FACTURAS,POR_DIAS
	}

	private GUILavadosGeneral view;
	private Lavados selectedLavados;
	private Reports lastReport;

	public CtrLavadosGeneral(GUILavadosGeneral view) {
		this.view = view;
		lastReport = Reports.GENERAL;
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		System.out.println(actionEvent.getActionCommand());
		switch (actionEvent.getActionCommand()) {
			case "EntreFechas":
				try {
					lastReport = Reports.ENTRE_FECHAS;
					LocalDate fechaIni = view.getFieldFechaIni();
					LocalDate fechaFin = view.getFieldFechaFin();
					if (fechaIni == null || fechaFin == null)
						throw new NullPointerException();
					view.reloadEntreFechas(fechaIni, fechaFin);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Los campos de fechas deben tener un valor.");
				}
				break;
			case "EntreFechasVeces":
				try {
					lastReport = Reports.VECES_FECHA;
					LocalDate fechaIni = view.getFieldFechaIni();
					LocalDate fechaFin = view.getFieldFechaFin();
					int x = Integer.parseInt(view.getNumVeces());
					view.reloadEntreFechasVeces(fechaIni, fechaFin, x);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Los campos de fechas y el campo numero veces deben tener un valor.");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Los campos de fechas y el campo numero veces deben tener un valor.");
				}
				break;
			case "Matricula":
				try {
					lastReport = Reports.MATRICULA;
					String m = view.getFieldMatricula();
					if (m.equals(""))
						throw new NullPointerException();
					view.reloadMatricula(m);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"El campo matricula debe tener un valor.");
				}
				break;
			case "Veces":
				try {
					lastReport = Reports.VECES;
					int v = Integer.parseInt(view.getNumVeces());
					view.reloadVeces(v);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"El campo numero veces debe tener un valor.");
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"El campo numero veces debe tener un valor.");
				}

				break;
			case "Complemento":
				try {
					lastReport = Reports.COMPLEMENTO;
					Complementos p = view.getFieldComplemento();
					view.reloadComplementos(p);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"El campo complemento debe tener un valor.");
				}

				break;
			case "Trabajador":
				try {
					lastReport = Reports.TRABAJADOR;
					Trabajador t = view.getFieldTrabajador();
					view.reloadTrabajador(t);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"El campo trabajador debe tener un valor.");
				}

				break;
			case "Propietario":
				try {
					lastReport = Reports.PROPIETARIO;
					String p = view.getFieldPropietario();
					view.reloadPropietario(p);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"El campo propietario debe tener un valor.");
				}

				break;
			case "MostrarFacturas":
				try {
					lastReport = Reports.FACTURAS;
					LocalDate fechaIni = view.getFieldFechaIni();
					LocalDate fechaFin = view.getFieldFechaFin();
					if (fechaIni == null || fechaFin == null)
						throw new NullPointerException();
					view.reloadFacturas(fechaIni, fechaFin);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Los campos fecha deben tener un valor");
				}

				break;
			case "MostrarTickets":
				try {
					LocalDate fechaIni = view.getFieldFechaIni();
					LocalDate fechaFin = view.getFieldFechaFin();
					if (fechaIni == null || fechaFin == null)
						throw new NullPointerException();
					view.reloadTickets(fechaIni, fechaFin);
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null,"Los campos fecha deben tener un valor");
				}


				break;
				
			case "Refrescar":
				view.reloadData();
				lastReport = Reports.GENERAL;
				break;
			case "Factura":
				try {
					String baseDirectoy = "";

					//TODO
					JFileChooser chooser = new JFileChooser("Z:\\PCP 2020\\CAJA");
					//JFileChooser chooser = new JFileChooser();
					FileNameExtensionFilter filtro = new FileNameExtensionFilter(".PDF", "pdf");
					chooser.setFileFilter(filtro);
					
					java.io.File nombre =new java.io.File(chooser.getSelectedFile()+".pdf");
					chooser.setSelectedFile(nombre);
					
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						baseDirectoy = chooser.getSelectedFile().getAbsolutePath();
					} else
						return;
					LocalDate fechaIni = view.getFieldFechaIni();
					LocalDate fechaFin = view.getFieldFechaFin();
					Tickets.generateTicket(baseDirectoy,fechaIni,fechaFin);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Los campos de fechas deben tener un valor.");
					e.printStackTrace();
				}
				break;

			case "PorDia":
				String baseDirectory = "";
				LocalDate fechaInii = view.getFieldFechaIni();
				LocalDate fechaFinn = view.getFieldFechaFin();
				
				//TODO//
				JFileChooser chooserDias = new JFileChooser("Z:\\PCP 2020\\CAJA");
				//JFileChooser chooser = new JFileChooser("C:\\Users\\Hp\\Desktop\\CosasLavadero");
				
				FileNameExtensionFilter filtroDias = new FileNameExtensionFilter(".PDF", "pdf");
				chooserDias.setFileFilter(filtroDias);
				java.io.File nombreDias =new java.io.File(chooserDias.getSelectedFile()+".pdf");
				chooserDias.setSelectedFile(nombreDias);
				
				if (chooserDias.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					baseDirectory = chooserDias.getSelectedFile().getAbsolutePath();
					
				} else
					return;
				
				try {
					InformePorDias.generateInforme(baseDirectory, fechaInii, fechaFinn);
				} catch (FileNotFoundException e) { // File not found or access denied
					JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
					e.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Los campos de fechas deben tener un valor.");
					e.printStackTrace();
				}
				break;
			case "PorDiaFacturas":
				String baseDirectoryF = "";
				LocalDate fechaIniiF = view.getFieldFechaIni();
				LocalDate fechaFinnF = view.getFieldFechaFin();

				//TODO//
				JFileChooser chooserDiasF = new JFileChooser("Z:\\PCP 2020\\CAJA");
				//JFileChooser chooser = new JFileChooser("C:\\Users\\Hp\\Desktop\\CosasLavadero");

				FileNameExtensionFilter filtroDiasFacturas = new FileNameExtensionFilter(".PDF", "pdf");
				chooserDiasF.setFileFilter(filtroDiasFacturas);
				java.io.File nombreDiasFacturas =new java.io.File(chooserDiasF.getSelectedFile()+".pdf");
				chooserDiasF.setSelectedFile(nombreDiasFacturas);

				if (chooserDiasF.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					baseDirectoryF = chooserDiasF.getSelectedFile().getAbsolutePath();

				} else
					return;

				try {
					InformePorDiasFacturas.generateInforme(baseDirectoryF, fechaIniiF, fechaFinnF);
				} catch (FileNotFoundException e) { // File not found or access denied
					JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
					e.printStackTrace();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Los campos de fechas deben tener un valor.");
					e.printStackTrace();
				}
				break;
				case "Informe":
				String baseDirectoy = "";

				//TODO//
				JFileChooser chooser = new JFileChooser("Z:\\PCP 2020\\CAJA");
				//JFileChooser chooser = new JFileChooser("C:\\Users\\Hp\\Desktop\\CosasLavadero");
				
				FileNameExtensionFilter filtro = new FileNameExtensionFilter(".PDF", "pdf");
				chooser.setFileFilter(filtro);
				java.io.File nombre =new java.io.File(chooser.getSelectedFile()+".pdf");
				chooser.setSelectedFile(nombre);
				
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					baseDirectoy = chooser.getSelectedFile().getAbsolutePath();
					
				} else
					return;

				switch (lastReport) {
					case ENTRE_FECHAS:
						LocalDate fechaIni = view.getFieldFechaIni();
						LocalDate fechaFin = view.getFieldFechaFin();
						try {
							InformeEntreFechas.generateInforme(baseDirectoy, fechaIni, fechaFin);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Los campos de fechas deben tener un valor.");
							e.printStackTrace();
						}
						break;
					case COMPLEMENTO:
						try {
							Complementos c = view.getFieldComplemento();
							InformePorComplemento.generateInforme(baseDirectoy, c);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							JOptionPane.showMessageDialog(null,"El campo complemento debe tener un valor.");
							e.printStackTrace();
						}
						break;
					case VECES_FECHA:

						try {
							LocalDate fechaInicial = view.getFieldFechaIni();
							LocalDate fechaFinal = view.getFieldFechaFin();
							int v = Integer.parseInt(view.getNumVeces());
							InformePorVecesFechas.generateInforme(baseDirectoy, v, fechaInicial, fechaFinal);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Los campos de fechas y el campo numero veces deben tener un valor.");
						}
						break;
					case VECES:
						try {
							int veces = Integer.parseInt(view.getNumVeces());
							InformePorVeces.generateInforme(baseDirectoy, veces);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							JOptionPane.showMessageDialog(null,"El campo numero veces debe tener un valor.");
							e.printStackTrace();
						}
						break;
					case GENERAL:
						try {
							InformeGeneral.generateInforme(baseDirectoy);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							e.printStackTrace();
						}
						break;
					case MATRICULA:
						try {
							String m = view.getFieldMatricula();
							InformePorMatricula.generateInforme(baseDirectoy, m);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							JOptionPane.showMessageDialog(null,"El campo matricula debe tener un valor.");
							e.printStackTrace();
						}
						break;
					case TRABAJADOR:
						try {
							Trabajador t = view.getFieldTrabajador();
							InformePorTrabajador.generateInforme(baseDirectoy, t);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							JOptionPane.showMessageDialog(null,"El campo trabajador debe tener un valor.");
							e.printStackTrace();
						}
						break;
					case PROPIETARIO:
						try {
							String propietario = view.getFieldPropietario();
							InformePorPropietario.generateInforme(baseDirectoy, propietario);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						}  catch (Exception e) {
							JOptionPane.showMessageDialog(null,"El campo propietario debe tener un valor.");
							e.printStackTrace();
						}
						break;
					case FACTURAS:
						LocalDate fechaInic = view.getFieldFechaIni();
						LocalDate fechaFini = view.getFieldFechaFin();
						try {
							Facturas.generateInforme(baseDirectoy, fechaInic, fechaFini);
						} catch (FileNotFoundException e) { // File not found or access denied
							JOptionPane.showMessageDialog(null,"Archivo no encontrado o acceso denegado.");
							e.printStackTrace();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null,"Los campos de fechas deben tener un valor.");
							e.printStackTrace();
						}
				}
				break;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent listSelectionEvent) {
		if (!listSelectionEvent.getValueIsAdjusting()) {

			int firstindex = listSelectionEvent.getFirstIndex();
			int lastIndex = listSelectionEvent.getLastIndex();
			ListSelectionModel selectionModel = view.getTable().getSelectionModel();
			TableModel model = view.getTable().getModel();
			if (selectionModel.isSelectionEmpty())
				return;
			int id = (int) ((selectionModel.isSelectedIndex(lastIndex)) ? model.getValueAt(lastIndex, 0)
					: model.getValueAt(firstindex, 0));

			selectedLavados = new Lavados(id);
			view.setFieldModelo(selectedLavados.getModelo());
			view.setFieldMatricula(selectedLavados.getMatricula());
			view.setFieldHora(selectedLavados.getHora());
			view.setFieldTelefono(selectedLavados.getTelefono());
			view.setFieldComplemento(selectedLavados.getComp());
			view.setFieldTrabajador(selectedLavados.getTrab());
            view.setFieldObservaciones(selectedLavados.getObservaciones());
            view.setFieldPropietario(selectedLavados.getPropietario());

		}
	}

	@Override
	public void dateChanged(DateChangeEvent dateChangeEvent) {
		System.out.println("Fecha cambiada a " + dateChangeEvent.getNewDate());
		view.reloadData();
	}
}
