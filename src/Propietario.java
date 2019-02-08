import DB.DatabaseException;
import DB.MySqlDB;

import java.util.ArrayList;
import java.util.List;

public class Propietario {

    private int id;
    private String nombre;
    private String telefono;

    public static List<Propietario> listaPropietarios() {
        List<Propietario> lista = new ArrayList<Propietario>();
        try(MySqlDB db = new MySqlDB()) {
            for (Object[] tupla: db.Select("Select * from Propietarios")) {
                int id = (int) tupla[0];
                String nombre = (String)tupla[1];
                String telefono = (String)tupla[2];
                lista.add(new Propietario(id, nombre, telefono));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Propietario(int id, String nombre, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Propietario(int id) {
        try(MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Propietarios where id = " + id).get(0);

            this.id = (int) tupla[0];
            this.nombre = (String)tupla[1];
            this.telefono = (String)tupla[2];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Propietario(String nombre, String telefono) {
        try(MySqlDB db = new MySqlDB()) {
            db.Insert("insert into Propietarios values('" + nombre + "', '" + telefono + "')");

            //Get new ID
            Object[] tupla = db.Select("select MAX(id) from Propietarios").get(0);

            this.id = (int)tupla[0];
            this.nombre = nombre;
            this.telefono = telefono;
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
}
