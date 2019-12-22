package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

	public static final String[] columnas = { "Nombre", "Precio" };

	private String nombre;
	private double precio;

	public static List<Modelo> listaModelos() {
		List<Modelo> lista = new ArrayList<Modelo>();
		try (MySqlDB db = new MySqlDB()) {
			for (Object[] tupla : db.Select("Select * from Modelo;")) {
				String nombre = (String) tupla[0];
				double precio = (double) tupla[1];
				lista.add(new Modelo(nombre, precio, true));
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
			this.precio = (double) tupla[1];
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	private Modelo(String nombre, double precio, boolean onlyLocal) {
		this.nombre = nombre;
		this.precio = precio;
	}

	public Modelo(String nombre, double precio) {
		try (MySqlDB db = new MySqlDB()) {
			db.Insert("insert into Modelo values('" + nombre + "'," + precio + ");");

			this.nombre = nombre;
			this.precio = precio;
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null,"Ya se existe ese modelo.");
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		try (MySqlDB db = new MySqlDB()) {
			db.Update("update Modelo set precio = " + precio + " where nombre = '" + this.getNombre() + "';");

			this.precio = precio;
		} catch (DatabaseException e) {
			e.printStackTrace();
		}
	}

	public void delete() {
		try (MySqlDB miBD = new MySqlDB()) {
			miBD.Delete("DELETE FROM Modelo WHERE nombre = '" + this.nombre + "';");
			nombre = null;
		} catch (DatabaseException e) {
			JOptionPane.showMessageDialog(null,"No se puede eliminar un modelo mientras este asociado a algun lavado.");
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return this.getNombre() + " -  " + this.getPrecio() + " €";
	}

	public Object[] asArray() {
		Object[] tmp = new Object[10];
		tmp[0] = nombre;
		tmp[1] = precio;

		return tmp;
	}
}