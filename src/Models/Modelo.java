package Models;

import DB.DatabaseException;
import DB.MySqlDB;

import java.util.ArrayList;
import java.util.List;

public class Modelo {

    public static final String[] columnas = {"Nombre", "Precio", "Marca"};

    private String nombre;
    private double precio;
    private String marca;

    public static List<Modelo> listaModelos() {
        List<Modelo> lista = new ArrayList<Modelo>();
        try (MySqlDB db = new MySqlDB()) {
            for (Object[] tupla : db.Select("Select * from Modelo;")) {
                String nombre = (String) tupla[0];
                double precio = (double) tupla[1];
                String marca = (String) tupla[2];
                lista.add(new Modelo(nombre, precio, marca, true));
            }
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public Modelo(String nombre) {
        try (MySqlDB db = new MySqlDB()) {
            Object[] tupla = db.Select("select * from Modelo where nombre = '" + nombre+"';").get(0);

            this.nombre = (String) tupla[0];
            this.precio = (double) tupla[1];
            this.marca = (String) tupla[2];
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    private Modelo(String nombre, double precio, String marca, boolean onlyLocal) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
    }

    public Modelo(String nombre, double precio, String marca) {
        try (MySqlDB db = new MySqlDB()) {
            db.Insert("insert into Modelo values('" + nombre + "'," + precio + ",'" + marca + "');");

            this.nombre = nombre;
            this.precio = precio;
            this.marca = marca;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        try (MySqlDB db = new MySqlDB()) {
            db.Update("update Modelo set nombre = '" + nombre + "' where nombre = '" + this.getNombre()+"';");

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
            db.Update("update Modelo set precio = " + precio + " where nombre = '" + this.getNombre()+"';");

            this.precio = precio;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        try (MySqlDB db = new MySqlDB()) {
            db.Update("update Modelo set marca = '" + marca + "' where nombre = '" + this.getNombre()+"';");

            this.marca = marca;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }
    
    public void delete() {
        try (MySqlDB miBD = new MySqlDB()) {
            miBD.Delete("DELETE FROM Modelo WHERE nombre = '"
                    + this.nombre + "';");
            nombre = null;
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.getNombre() + " -  " + this.getPrecio() + " $";
    }

    public Object[] asArray() {
        Object[] tmp = new Object[10];
        tmp[0] = nombre;
        tmp[1] = precio;
        tmp[2] = marca;

        return tmp;
    }
}