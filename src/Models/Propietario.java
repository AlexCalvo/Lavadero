package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import java.util.ArrayList;
import java.util.List;

public class Propietario {

    public static final String[] columnas = {"DNI", "Nombre", "Telefono"};

	
    private String id;
    private String nombre;
    private String telefono;

    public static List<Propietario> listaPropietarios() {
        List<Propietario> lista = new ArrayList<Propietario>();
        try(MySqlDB db = new MySqlDB()) {
            for (Object[] tupla: db.Select("Select * from Propietarios")) {
                String id = (String) tupla[0];
                String nombre = (String)tupla[1];
                String telefono = (String)tupla[2];
                lista.add(new Propietario(id, nombre, telefono, true));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Propietario(String id, String nombre, String telefono, boolean onlyLocal) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Propietario(String id) {
        try(MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Propietarios where id = '" + id + "'").get(0);

            this.id = (String) tupla[0];
            this.nombre = (String)tupla[1];
            this.telefono = (String)tupla[2];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Propietario(String id, String nombre, String telefono) {
        try(MySqlDB db = new MySqlDB()) {
            db.Insert("insert into Propietarios values('" + id + "', '" + nombre + "', '" + telefono + "')");

            this.id = id;
            this.nombre = nombre;
            this.telefono = telefono;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try(MySqlDB db = new MySqlDB()) {
            db.Update("update Propietarios set nombre = '" + nombre + "' where id = " + this.getId());

            this.nombre = nombre;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        try(MySqlDB db = new MySqlDB()) {
            db.Update("update Propietarios set telefono = '" + telefono + "' where id = " + this.getId());

            this.telefono = telefono;
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
        tmp[2]= telefono;

        return tmp;
    }
}
