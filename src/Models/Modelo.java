package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

	public static final String[] columnas = { "Nombre", "Precio Exterior", "Precio Interior", "Precio Completo" };

	private String nombre;
	private double precioExterior;
	private double precioInterior;
	private double precioCompleto;

	public static List<Modelo> listaModelos() {
		List<Modelo> lista = new ArrayList<Modelo>();
		try (MySqlDB db = new MySqlDB()) {
			for (Object[] tupla : db.Select("Select * from Modelo;")) {
				String nombre = (String) tupla[0];
				double precioExterior = (double) tupla[1];
				double precioInterior = (double) tupla[2];
				double precioCompleto = (double) tupla[3];

				lista.add(new Modelo(nombre, precioExterior, precioInterior, precioCompleto, true));
			}
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
		return lista;
	}

	public Modelo(String nombre) {
		try (MySqlDB db = new MySqlDB()) {
			Object[] tupla = db.Select("select * from Modelo where nombre = '" + nombre + "';").get(0);

			this.nombre = (String) tupla[0];
			this.precioExterior = (double) tupla[1];
			this.precioInterior = (double) tupla[2];
			this.precioCompleto = (double) tupla[3];
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	private Modelo(String nombre, double precioExterior, double precioInterior, double precioCompleto,
			boolean onlyLocal) {
		this.nombre = nombre;
		this.precioExterior = precioExterior;
		this.precioInterior = precioInterior;
		this.precioCompleto = precioCompleto;
	}

	public Modelo(String nombre, double precioExterior,double precioInterior, double precioCompleto) {
		try (MySqlDB db = new MySqlDB()) {
			db.Insert("insert into Modelo values('" + nombre + "'," + precioExterior + ", "+ precioInterior +" , "+ precioCompleto+ ");");

			this.nombre = nombre;
			this.precioExterior = precioExterior;
			this.precioInterior = precioInterior;
			this.precioCompleto = precioCompleto;
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null,"Ya existe ese modelo.");
			e.printStackTrace();
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		try (MySqlDB db = new MySqlDB()) {
			db.Update("update Modelo set nombre = '" + nombre + "' where nombre = '" + this.getNombre() + "';");

			this.nombre = nombre;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}

	}

	public double getPrecioExterior() {
		return precioExterior;
	}

	public void setPrecioExterior(double precio) {
		try (MySqlDB db = new MySqlDB()) {
			db.Update("update Modelo set precioExterior = " + precio + " where nombre = '" + this.getNombre() + "';");

			this.precioExterior = precio;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}
	
	public double getPrecioInterior() {
		return precioInterior;
	}

	public void setPrecioInterior(double precio) {
		try (MySqlDB db = new MySqlDB()) {
			db.Update("update Modelo set precioInterior = " + precio + " where nombre = '" + this.getNombre() + "';");

			this.precioInterior = precio;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}
	
	public double getPrecioCompleto() {
		return precioCompleto;
	}

	public void setPrecioCompleto(double precio) {
		try (MySqlDB db = new MySqlDB()) {
			db.Update("update Modelo set precioCompleto = " + precio + " where nombre = '" + this.getNombre() + "';");

			this.precioCompleto = precio;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Delete("DELETE FROM Modelo WHERE nombre = '" + this.nombre + "';");
			nombre = null;
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null,
					"No se puede eliminar un modelo mientras este asociado a algun lavado.");
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	public Object[] asArray() {
		Object[] tmp = new Object[10];
		tmp[0] = nombre;
		tmp[1] = precioExterior;
		tmp[2] = precioInterior;
		tmp[3] = precioCompleto;		
		
		return tmp;
	}
}