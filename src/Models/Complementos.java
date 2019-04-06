package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import java.util.ArrayList;
import java.util.List;

public class Complementos {

    public static final String[] columnas = {"Nombre", "Precio"};


    private String nombre;
    private double precio;

    public static List<Complementos> listaComplementos() {
        List<Complementos> lista = new ArrayList<Complementos>();
        try (MySqlDB db = new MySqlDB()) {
            for (Object[] tupla : db.Select("Select * from Complementos")) {
                String nombre = (String) tupla[0];
                double precio = (double) tupla[1];
                lista.add(new Complementos(nombre, precio, true));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Complementos(String nombre, double precio, boolean onlyLocal) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public Complementos(String nombre) {
        try (MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Complementos where nombre = '" + nombre + "'").get(0);

            this.nombre = (String) tupla[0];
            this.precio = (double) tupla[1];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Complementos( String nombre, double precio) {
        try (MySqlDB db = new MySqlDB()) {
            db.Insert("insert into Complementos values('" + nombre + "', '" + precio + "')");

            this.nombre = nombre;
            this.precio = precio;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        try (MySqlDB miBD = new MySqlDB()) {
            miBD.Delete("DELETE FROM Complementos WHERE nombre = '"+ this.nombre+ "';");
            nombre = null;
            precio = 0;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

   
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try (MySqlDB db = new MySqlDB()) {
            db.Update("update Complementos set nombre = '" + nombre + "' where nombre = '" + this.getNombre()+"';");

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
            db.Update("update Complementos set precio = '" + precio + "' where id = '" + this.getNombre()+"';");

            this.precio = precio;
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
        tmp[0] = nombre;
        tmp[1] = precio;

        return tmp;
    }

}
