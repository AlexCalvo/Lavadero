package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Trabajador {

    public static final String[] columnas = {"ID", "Nombre"};


    private int id;
    private String nombre;

    public static List<Trabajador> listaTrabajador() {
        List<Trabajador> lista = new ArrayList<Trabajador>();
		try (MySqlDB db = new MySqlDB()) {
            for (Object[] tupla : db.Select("Select * from Trabajador")) {
                int id = (int) tupla[0];
                String nombre = (String) tupla[1];
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
        try (MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Trabajador where id = " + id).get(0);

            this.id = (int) tupla[0];
            this.nombre = (String) tupla[1];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Trabajador(String nombre) {
        try (MySqlDB db = new MySqlDB()) {

            //Get new ID
            Object[] tupla = db.Select("select MAX(id) from Trabajador").get(0);

            int newId;
            if (tupla[0] == null)
                newId = 1;
            else
                newId = (int) tupla[0] + 1;

            db.Insert("insert into Trabajador values(" + newId + ", '" + nombre + "')");


            this.id = newId;
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
        try (MySqlDB db = new MySqlDB()) {
            db.Update("update Trabajador set nombre = '" + nombre + "' where id = " + this.getId());

            this.nombre = nombre;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

    }

    public void delete() {
        try (MySqlDB miBD = new MySqlDB()) {
            miBD.Delete("DELETE FROM Trabajador WHERE id = "
                    + this.id + ";");
            id = -1;
            nombre = null;
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(null,"No se puede eliminar un trabajador mientras este asociado a algun lavado.");
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
        tmp[1] = nombre;

        return tmp;
    }
}
