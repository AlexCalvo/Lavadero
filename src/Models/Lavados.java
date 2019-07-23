package Models;

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

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Lavados {

	public static final String[] columnas = { "ID", "Matricula", "modelo", "hora", "fecha", "telefono", "Complemento",
			"Trabajador","Observaciones","Propietario" };

	private int id;// clave principal
	private String matricula;
	private Modelo modelo;
	private LocalTime hora;
	private LocalDate fecha;
	private String telefono;
	private Complementos comp;
	private Trabajador trab;
	private String observaciones;
	private String propietario;

	public static List<Lavados> listaLavados() {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados")) {
				int id = (int) tupla[0];
				String matricula = (String) tupla[1];
				Modelo modelo = new Modelo((String) tupla[2]);
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
				LocalDate fecha = ((Date) tupla[4]).toLocalDate();
				String telefono = (String) tupla[5];
                Trabajador trab = new Trabajador((int) tupla[6]);
                Complementos prop = null;
                if (tupla[7] != null)
				    prop = new Complementos((String) tupla[7]);
				String obs =  (String) tupla[8];
				String p =  (String) tupla[9];
				lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, prop, trab,obs,p));
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

			for (Object[] tupla : miBD
					.Select("SELECT * FROM Lavados where Fecha >= '" + FechaIn + "' and Fecha <='" + FechaFin + "';")) {
				int id = (int) tupla[0];
				String matricula = (String) tupla[1];
				Modelo modelo = new Modelo((String) tupla[2]);
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
				LocalDate fecha = ((Date) tupla[4]).toLocalDate();
				String telefono = (String) tupla[5];
				Complementos comp = new Complementos((String) tupla[7]);
				Trabajador trab = new Trabajador((int) tupla[6]);
				String obs =  (String) tupla[8];
				String p =  (String) tupla[9];
				lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, comp, trab,obs,p));
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

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where Matricula = '" + m + "';")) {
				int id = (int) tupla[0];
				String matricula = (String) tupla[1];
				Modelo modelo = new Modelo((String) tupla[2]);
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
				LocalDate fecha = ((Date) tupla[4]).toLocalDate();
				String telefono = (String) tupla[5];
				Complementos prop = new Complementos((String) tupla[7]);
				Trabajador trab = new Trabajador((int) tupla[6]);
				String obs =  (String) tupla[8];
				String p =  (String) tupla[9];
				lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, prop, trab,obs,p));
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
				for (Object[] tupla : miBD.Select("Select * from Lavados where matricula = \"" + tmp[0] + "\" and Fecha >= '" + ini + "' and Fecha <='"
						+ fin + "';")) {
					int id = (int) tupla[0];
					String matricula = (String) tupla[1];
					Modelo modelo = new Modelo((String) tupla[2]);
					// TODO: Waiting on better fix
					LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
					LocalDate fecha = ((Date) tupla[4]).toLocalDate();
					String telefono = (String) tupla[5];
					Complementos prop = new Complementos((String) tupla[7]);
					Trabajador trab = new Trabajador((int) tupla[6]);
					String obs =  (String) tupla[8];
					String p =  (String) tupla[9];
					lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, prop, trab,obs,p));
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
					String matricula = (String) tupla[1];
					Modelo modelo = new Modelo((String) tupla[2]);
					// TODO: Waiting on better fix
					LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
					LocalDate fecha = ((Date) tupla[4]).toLocalDate();
					String telefono = (String) tupla[5];
					Complementos prop = new Complementos((String) tupla[7]);
					Trabajador trab = new Trabajador((int) tupla[6]);
					String obs =  (String) tupla[8];
					String p =  (String) tupla[9];
					lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, prop, trab,obs,p));
				}
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		return lista;
	}

	// Por propietario
	public static List<Lavados> listaLavadosPorComplemento(Complementos c) {
		List<Lavados> lista = new ArrayList<Lavados>();

		try (MySqlDB miBD = new MySqlDB()) {

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where Complementos_nombre = \"" + c.getNombre() + "\";")) {
				int id = (int) tupla[0];
				String matricula = (String) tupla[1];
				Modelo modelo = new Modelo((String) tupla[2]);
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
				LocalDate fecha = ((Date) tupla[4]).toLocalDate();
				String telefono = (String) tupla[5];
				Complementos prop = new Complementos((String) tupla[7]);
				Trabajador trab = new Trabajador((int) tupla[6]);
				String obs =  (String) tupla[8];
				String p =  (String) tupla[9];
				lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, prop, trab,obs,p));
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

			for (Object[] tupla : miBD.Select("SELECT * FROM Lavados where Trabajador_id = " + t.getId() + ";")) {
				int id = (int) tupla[0];
				String matricula = (String) tupla[1];
				Modelo modelo = new Modelo((String) tupla[2]);
				// TODO: Waiting on better fix
				LocalTime hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
				LocalDate fecha = ((Date) tupla[4]).toLocalDate();
				String telefono = (String) tupla[5];
				Trabajador trab = new Trabajador((int) tupla[7]);
                Complementos prop = new Complementos((String) tupla[6]);
				String obs =  (String) tupla[8];
				String p =  (String) tupla[9];
				lista.add(new Lavados(id, matricula, modelo, hora, fecha, telefono, prop, trab,obs,p));
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
			this.matricula = (String) tupla[1];
			this.modelo = new Modelo((String) tupla[2]);
			// TODO: Waiting on better fix
			this.hora = ((Time) tupla[3]).toLocalTime().minus(1, ChronoUnit.HOURS);
			this.fecha = ((Date) tupla[4]).toLocalDate();
			this.telefono = (String) tupla[5];
			this.trab = new Trabajador((int) tupla[6]);
			this.comp = new Complementos((String) tupla[7]);
			this.observaciones = (String)tupla[8];
			this.propietario = (String)tupla[9];

		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	private Lavados(int id, String matricula, Modelo modelo, LocalTime hora, LocalDate fecha, String telefono,
			Complementos prop, Trabajador trab,String observaciones,String propietario) {
		this.id = id;
		this.matricula = matricula;
		this.modelo = modelo;
		this.hora = hora;
		this.fecha = fecha;
		this.telefono = telefono;
		this.comp = prop;
		this.trab = trab;
		this.observaciones = observaciones;
		this.propietario = propietario;
	}

	public Lavados(String matricula, Modelo modelo, LocalTime hora, LocalDate fecha, String telefono, Complementos comp,
			Trabajador trab,String observaciones,String propietario) {
		try (MySqlDB miBD = new MySqlDB()) {

			// Get new ID
			Object[] tupla = miBD.Select("select MAX(id) from Lavados").get(0);

			int newId;
			if (tupla[0] == null)
				newId = 1;
			else
				newId = (int) tupla[0] + 1;

			miBD.Insert("INSERT INTO Lavados VALUES(" + newId + ", '" + matricula + "', '" + modelo.getNombre() + "','"
					+ hora.toString() + "', '" + fecha.toString() + "','" + telefono + "'," + trab.getId() + ",'"
					+ comp.getNombre() + "','"+observaciones + "','" + propietario + "');");

			this.id = newId;
			this.matricula = matricula;
			this.modelo = modelo;
			this.hora = hora;
			this.fecha = fecha;
			this.telefono = telefono;
			this.comp = comp;
			this.trab = trab;
			this.observaciones = observaciones;
			this.propietario = propietario;
			
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

	public Complementos getProp() {
		return comp;
	}

	public void setProp(Complementos prop) {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Update("UPDATE Lavados SET Complementos_id = '" + prop.getNombre() + "' where id = " + this.getId());

			this.comp = prop;
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
	

	public void delete() {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Delete("DELETE FROM Lavados WHERE id = " + this.id + ";");
			id = -1;
			matricula = null;
			modelo = null;
			hora = null;
			telefono = null;
			comp = null;
			trab = null;
			observaciones = null;
			propietario = null;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	

	@Override
	public String toString() {
		return "Lavados [id=" + id + ", matricula=" + matricula + ", modelo=" + modelo + ", hora=" + hora + ", fecha="
				+ fecha + ", telefono=" + telefono + ", comp=" + comp + ", trab=" + trab + ", observaciones="
				+ observaciones + ", propietario=" + propietario + "]";
	}

	public Object[] asArray() {
		Object[] tmp = new Object[columnas.length];
		tmp[0] = id;
		tmp[1] = matricula;
		tmp[2] = modelo;
		tmp[3] = hora;
		tmp[4] = fecha;
		tmp[5] = telefono;
		tmp[6] = comp;
		tmp[7] = trab;
		tmp[8] = observaciones;
		tmp[9] = propietario;
		

		return tmp;
	}

}