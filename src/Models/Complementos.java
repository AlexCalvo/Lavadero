package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import java.util.ArrayList;
import java.util.List;

public class Complementos {

    public static final String[] columnas = {"DNI", "Nombre", "Precio"};


    private String id;
    private String nombre;
    private double precio;

    public static List<Complementos> listaComplementos() {
        List<Complementos> lista = new ArrayList<Complementos>();
        try (MySqlDB db = new MySqlDB()) {
            for (Object[] tupla : db.Select("Select * from Complementos")) {
                String id = (String) tupla[0];
                String nombre = (String) tupla[1];
                double precio = (double) tupla[2];
                lista.add(new Complementos(id, nombre, precio, true));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Complementos(String id, String nombre, double precio, boolean onlyLocal) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public Complementos(String id) {
        try (MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Complementos where id = '" + id + "'").get(0);

            this.id = (String) tupla[0];
            this.nombre = (String) tupla[1];
            this.precio = (double) tupla[2];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public Complementos(String id, String nombre, double precio) {
        try (MySqlDB db = new MySqlDB()) {
            db.Insert("insert into Complementos values('" + id + "', '" + nombre + "', '" + precio + "')");

            this.id = id;
            this.nombre = nombre;
            this.precio = precio;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        try (MySqlDB miBD = new MySqlDB()) {
            miBD.Delete("DELETE FROM Complementos WHERE id = '"+ this.id + "';");
            id = null;
            nombre = null;
            precio = 0;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }	
    
    public void setId(String id) {
		  try (MySqlDB db = new MySqlDB()) {
	            db.Update("update Complementos set id ='"+ id + "' where id = '" + this.id+"';");

	            this.id =id;
	        } catch (DatabaseException e) {
	            e.printStackTrace();
	        }
		}
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try (MySqlDB db = new MySqlDB()) {
            db.Update("update Complementos set nombre = '" + nombre + "' where id = '" + this.getId()+"';");

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
            db.Update("update Complementos set precio = '" + precio + "' where id = '" + this.getId()+"';");

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
        tmp[0] = id;
        tmp[1] = nombre;
        tmp[2] = precio;

        return tmp;
    }

}
