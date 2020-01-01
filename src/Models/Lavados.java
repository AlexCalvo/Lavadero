package Models;

import Controllers.EmptyLicenseException;
import Controllers.MissingHourException;
import DB.DatabaseException;
import DB.MySqlDB;
import UI.GUIMain;
import UI.GUIPrecioModelo;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Lavados {

	public static final String[] columnas = { "ID", "MATRICULA", "TIPO LAVADO", "MODELO", "PRECIO", "HORA", "FECHA",
			"TELEFONO", "COMPLEMENTO", "TRABAJADOR", "OBSERVACIONES", "PROPIETARIO", "FACTURA" };

	private int id;// clave principal
	private String matricula;
	private String tipoLavado;
	private Modelo modelo;
	private double precio;
	private LocalTime hora;
	private LocalDate fecha;
	private String telefono;
	private Complementos comp;
	private Trabajador trab;
	private String observaciones;
	private String propietario;
	private boolean factura;

	public static List<Lavados> listaLavados() {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados order by fecha")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Lista tickets
	public static List<Lavados> listaTickets(LocalDate ini, LocalDate fin) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados WHERE Fecha >= '" + ini + "' and Fecha <='" + fin
					+ "' and Factura = false order by id")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Lista facturas
	public static List<Lavados> listaFacturas(LocalDate ini, LocalDate fin) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados WHERE Fecha >= '" + ini + "' and Fecha <='" + fin
					+ "' and factura = 1 order by fecha")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Todos los lavados entre dos fechas dadas
	public static List<Lavados> listaLavadosPorFechas(LocalDate FechaIn, LocalDate FechaFin) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where Fecha >= '" + FechaIn + "' and Fecha <='"
					+ FechaFin + "' order by fecha;")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Todos los lavados del coche con una matricula concreta
	public static List<Lavados> listaLavadosPorMatricula(String m) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where Matricula = '" + m + "' order by fecha;")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Dadas dos fechas, que muestre los coches que han ido x veces
	public static List<Lavados> listaLavadosPorVecesFechas(LocalDate ini, LocalDate fin, int x) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tmp : miBD.Select("SELECT matricula FROM Lavados where Fecha >= '" + ini + "' and Fecha <='"
					+ fin + "'" + "  group by Matricula having count(Matricula) = " + x + ";")) {
				for (Object[] tupla : miBD.Select("Select * from Lavados where matricula = \"" + tmp[0]
						+ "\" and Fecha >= '" + ini + "' and Fecha <='" + fin + "' order by fecha;")) {
					int id = (int) tupla[0];
					LocalDate fecha = ((Date) tupla[1]).toLocalDate();
					// TODO: Waiting on better fix
					LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
					String matricula = (String) tupla[3];
					String tipoLavado = (String) tupla[4];
					Modelo modelo = new Modelo((String) tupla[5]);
					String telefono = (String) tupla[6];
					String p = (String) tupla[7];
					double precio = (double) tupla[8];
					boolean f = (Boolean) tupla[9];
					String obs = (String) tupla[10];
					Trabajador trab = new Trabajador((int) tupla[11]);
					Complementos comp = null;
					if (tupla[12] != null)
						comp = new Complementos((String) tupla[12]);
					lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab,
							obs, p, f));
				}
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Por veces sin fechas
	public static List<Lavados> listaLavadosPorVeces(int x) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tmp : miBD
					.Select("SELECT Matricula FROM Lavados group by Matricula having count(Matricula) = " + x + ";")) {
				for (Object[] tupla : miBD.Select("Select * from Lavados where matricula = \"" + tmp[0] + "\";")) {
					int id = (int) tupla[0];
					LocalDate fecha = ((Date) tupla[1]).toLocalDate();
					// TODO: Waiting on better fix
					LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
					String matricula = (String) tupla[3];
					String tipoLavado = (String) tupla[4];
					Modelo modelo = new Modelo((String) tupla[5]);
					String telefono = (String) tupla[6];
					String p = (String) tupla[7];
					double precio = (double) tupla[8];
					boolean f = (Boolean) tupla[9];
					String obs = (String) tupla[10];
					Trabajador trab = new Trabajador((int) tupla[11]);
					Complementos comp = null;
					if (tupla[12] != null)
						comp = new Complementos((String) tupla[12]);
					lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab,
							obs, p, f));
				}
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Por Complementos
	public static List<Lavados> listaLavadosPorComplemento(Complementos c) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD
					.Select("SELECT * FROM Lavados where Complemento_id = '" + c.getNombre() + "' order by fecha")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Por trabajador
	public static List<Lavados> listaLavadosPorTrabajador(Trabajador t) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where Trabajador_id = " + t.getId() + " order by fecha;")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Por trabajador
	public static List<Lavados> listaLavadosPorPropietario(String prop) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where propietario = '" + prop + "' order by fecha;")) {
				int id = (int) tupla[0];
				LocalDate fecha = ((Date) tupla[1]).toLocalDate();
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
				String matricula = (String) tupla[3];
				String tipoLavado = (String) tupla[4];
				Modelo modelo = new Modelo((String) tupla[5]);
				String telefono = (String) tupla[6];
				String p = (String) tupla[7];
				double precio = (double) tupla[8];
				boolean f = (Boolean) tupla[9];
				String obs = (String) tupla[10];
				Trabajador trab = new Trabajador((int) tupla[11]);
				Complementos comp = null;
				if (tupla[12] != null)
					comp = new Complementos((String) tupla[12]);
				lista.add(new Lavados(id, matricula, tipoLavado, modelo, precio, hora, fecha, telefono, comp, trab, obs,
						p, f));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Lavados(int id) {
		try (MySqlDB miBD = new MySqlDB()) {
			Object[] tupla = miBD.Select("SELECT * FROM Lavados WHERE " + "id = " + id + ";").get(0);
			this.id = (int) tupla[0];
			this.fecha = ((Date) tupla[1]).toLocalDate();
			this.hora = ((Time) tupla[2]).toLocalTime().minus(1, ChronoUnit.HOURS);
			this.matricula = (String) tupla[3];
			this.tipoLavado = (String) tupla[4];
			this.modelo = new Modelo((String) tupla[5]);
			this.telefono = (String) tupla[6];
			this.propietario = (String) tupla[7];
			this.precio = (double) tupla[8];
			this.factura = (Boolean) tupla[9];
			this.observaciones = (String) tupla[10];
			this.trab = new Trabajador((int) tupla[11]);
			this.comp = new Complementos((String) tupla[12]);

		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	private Lavados(int id, String matricula, String tipoLavado, Modelo modelo, double precio, LocalTime hora,
			LocalDate fecha, String telefono, Complementos prop, Trabajador trab, String observaciones,
			String propietario, boolean factura) {
		this.id = id;
		this.matricula = matricula;
		this.tipoLavado = tipoLavado;
		this.modelo = modelo;
		this.precio = precio;
		this.hora = hora;
		this.fecha = fecha;
		this.telefono = telefono;
		this.comp = prop;
		this.trab = trab;
		this.observaciones = observaciones;
		this.propietario = propietario;
		this.factura = factura;
	}

	public Lavados(String matricula, String tipoLavado, Modelo modelo, double precio, LocalTime hora, LocalDate fecha,
			String telefono, Complementos comp, Trabajador trab, String observaciones, String propietario,
			boolean factura) {
		try (MySqlDB miBD = new MySqlDB()) {

			// Get new ID
			Object[] tupla = miBD.Select("select MAX(id) from Lavados").get(0);

			int newId;
			if (tupla[0] == null)
				newId = 1;
			else
				newId = (int) tupla[0] + 1;

			if (matricula.equals("")) {
				throw new EmptyLicenseException();
			}

			if (hora == null) {
				throw new MissingHourException();
			}

			miBD.Insert("INSERT INTO Lavados VALUES(" + newId + ", '" + fecha.toString() + "','" + hora.toString()
					+ "', '" + matricula + "', '" + tipoLavado + "', '" + modelo.getNombre() + "','" + telefono + "','"
					+ propietario + "'," + precio + ", " + factura + ",'" + observaciones + "'," + trab.getId() + ",'"
					+ comp.getNombre() + "');");

			this.id = newId;
			this.matricula = matricula;
			this.tipoLavado = tipoLavado;
			this.modelo = modelo;
			this.precio = precio;
			this.hora = hora;
			this.fecha = fecha;
			this.telefono = telefono;
			this.comp = comp;
			this.trab = trab;
			this.observaciones = observaciones;
			this.propietario = propietario;
			this.factura = factura;
		} catch (MissingHourException e) {
			JOptionPane.showMessageDialog(null, "El campo hora no puede estar vacio.");
		} catch (EmptyLicenseException e) {
			JOptionPane.showMessageDialog(null, "La matricula no puede estar vacia.");
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public int getId() {
		return id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET matricula = '" + matricula + "' where id = " + this.getId());

			this.matricula = matricula;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public String getTipoLavado() {
		return tipoLavado;
	}

	public void setTipoLavado(String tipoLavado) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET Tipo_Lavado = '" + tipoLavado + "' where id = " + this.getId());

			this.tipoLavado = tipoLavado;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET modelo = '" + modelo.getNombre() + "' where id = " + this.getId());

			this.modelo = modelo;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET precio = " + precio + " where id = " + this.getId());

			this.precio = precio;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET hora = '" + hora.toString() + "' where id = " + this.getId());

			this.hora = hora;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET fecha = '" + fecha.toString() + "' where id = " + this.getId());

			this.fecha = fecha;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET telefono = '" + telefono + "' where id = " + this.getId());

			this.telefono = telefono;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public Complementos getComp() {
		return comp;
	}

	public void setComp(Complementos comp) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET Complemento_id = '" + comp.getNombre() + "' where id = " + this.getId());

			this.comp = comp;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public Trabajador getTrab() {
		return trab;
	}

	public void setTrab(Trabajador trab) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET Trabajador_id = " + trab.getId() + " where id = " + this.getId());

			this.trab = trab;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET observaciones = '" + observaciones + "' where id = " + this.getId());

			this.observaciones = observaciones;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET propietario = '" + propietario + "' where id = " + this.getId());

			this.propietario = propietario;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public boolean getFactura() {
		return factura;
	}

	public void setFactura(boolean factura) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET factura = " + factura + " where id = " + this.getId());

			this.factura = factura;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Delete("DELETE FROM Lavados WHERE id = " + this.id + ";");
			id = -1;
			matricula = null;
			tipoLavado = null;
			modelo = null;
			precio = -1;
			hora = null;
			telefono = null;
			comp = null;
			trab = null;
			observaciones = null;
			propietario = null;
			factura = false;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return "Lavados [id=" + id + ", matricula=" + matricula + ", "+tipoLavado+ ",modelo=" + modelo + ",precio=" + DoubleFormatter.df.format(precio) + ", hora="
				+ hora + ", fecha=" + fecha + ", telefono=" + telefono + ", comp=" + comp + ", trab=" + trab
				+ ", observaciones=" + observaciones + ", propietario=" + propietario + ", factura=" + factura + "]";
	}

	public Object[] asArray() {
		Object[] tmp = new Object[columnas.length];
		tmp[0] = id;
		tmp[1] = matricula;
		tmp[2] = tipoLavado;
		tmp[3] = modelo;
		tmp[4] = DoubleFormatter.df.format(precio);
		tmp[5] = hora;
		tmp[6] = fecha;
		tmp[7] = telefono;
		tmp[8] = comp;
		tmp[9] = trab;
		tmp[10] = observaciones;
		tmp[11] = propietario;
		tmp[12] = factura;

		return tmp;
	}

}