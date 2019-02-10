package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import java.util.ArrayList;
import java.util.List;

public class Trabajador {

    public static final String[] columnas = {"ID", "Nombre"};

	
    private int id;
    private String nombre;

    public static List<Trabajador> listaTrabajador() {
        List<Trabajador> lista = new ArrayList<Trabajador>();
        try(MySqlDB db = new MySqlDB()) {
            for (Object[] tupla: db.Select("Select * from Trabajador")) {
                int id = (int) tupla[0];
                String nombre = (String)tupla[1];
                lista.add(new Trabajador(id, nombre));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Trabajador(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Trabajador(int id) {
        try(MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Trabajador where id = " + id).get(0);

            this.id = (int) tupla[0];
            this.nombre = (String)tupla[1];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Trabajador(String nombre) {
        try(MySqlDB db = new MySqlDB()) {
            db.Insert("insert into Trabajador values('" + nombre + "')");

            //Get new ID
            Object[] tupla = db.Select("select MAX(id) from Trabajador").get(0);

            this.id = (int)tupla[0];
            this.nombre = nombre;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try(MySqlDB db = new MySqlDB()) {
            db.Update("update Trabajador set nombre = '" + nombre + "' where id = " + this.getId());

            this.nombre = nombre;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return this.getNombre();
    }

	public Object[] asArray() {
        Object[] tmp = new Object[10];
        tmp[0] = id;
        tmp[1] =nombre;

        return tmp;
    }
}
