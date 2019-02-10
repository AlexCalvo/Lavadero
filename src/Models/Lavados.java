package Models;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import DB.DatabaseException;
import DB.MySqlDB;

public class Lavados
{
	    
	    public static final String[] columnas = {"ID", "Matricula", "marca", "modelo", "hora", "Tam", "precio", "telefono", "Propietario", "Trabajador"};
    
	    private int id;//clave principal
	    private String matricula;
	    private String marca;
	    private String modelo;
	    private String hora;
	    private String fecha;
	    private String tam;
	    private double precio;
	    private String telefono;
	    private Propietario prop;
	    private Trabajador trab;

        public static List<Lavados> listaLavados() {
     	   List<Lavados> lista = new ArrayList<Lavados>();

           try(MySqlDB miBD = new MySqlDB()) {
               
               for(Object[] tupla : miBD.Select("SELECT * FROM Lavados" )){
                   int id = (int)tupla[0];
                   String matricula = (String)tupla[1];
                   String marca = (String)tupla[2];
                   String modelo = (String)tupla[3];
                   String hora = ((Time)tupla[4]).toString();
                   String fecha = ((Date)tupla[5]).toString();
                   String tam = (String)tupla[6];
                   double precio = ((Integer)tupla[7]).doubleValue();
                   String telefono = (String) tupla[8];
                   Propietario prop = new Propietario((int)tupla[9]);
                   Trabajador trab = new Trabajador((int)tupla[10]);
                   lista.add(new Lavados(id, matricula, marca,modelo, hora, fecha, tam, precio,telefono, prop, trab));
               }
           } catch (DatabaseException e) {
               e.printStackTrace();
           }
        	
            return lista;
        }

        public Lavados(int id) {
        	try(MySqlDB miBD = new MySqlDB()){
        		Object[] tupla = miBD.Select("SELECT * FROM Lavados WHERE "
            			+ "id = "+ id + ";").get(0);
                this.id = (int)tupla[0];
                this.matricula = (String)tupla[1];
                this.marca = (String)tupla[2];
                this.modelo = (String)tupla[3];
                this.hora = ((Time)tupla[4]).toString();
                this.fecha = ((Date)tupla[5]).toString();
                this.tam = (String)tupla[6];
          	    this.precio = (double)tupla[7];
          	    this.telefono = (String) tupla[8];
          	    this.prop = (Propietario)tupla[9];
          	    this.trab = (Trabajador)tupla[10];
          	    
        	}catch (DatabaseException e) {
                e.printStackTrace();
            }
        	
        	
        	
         }

    private Lavados(int id, String matricula, String marca, String modelo, String hora, String fecha, String tam,
                    double precio, String telefono, Propietario prop, Trabajador trab) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.hora = hora;
        this.fecha = fecha;
        this.tam = tam;
        this.precio = precio;
        this.telefono = telefono;
        this.prop = prop;
        this.trab = trab;
    }

    public Lavados(String matricula, String marca, String modelo, String hora, String fecha, String tam,
                   double precio, String telefono, Propietario prop, Trabajador trab) {
			try (MySqlDB miBD = new MySqlDB()){

	             miBD.Insert("INSERT INTO Lavados VALUES('"+matricula+"', '"+marca+"','"+modelo+"','"+hora+"',"+tam+","+
                         this.precio +","+ this.telefono +","+ this.prop.getId() +","+ this.trab.getId() +");");

                //Get new ID
                Object[] tupla = miBD.Select("select MAX(id) from Lavados").get(0);
	             
	             this.id = (int)tupla[0];
	             this.matricula = matricula;
	             this.marca = marca;
	             this.modelo = modelo;
	             this.hora = hora;
	             this.fecha = fecha;
	             this.tam = tam;
	             this.precio = precio;
	             this.telefono = telefono;
	             this.prop = prop;
	             this.trab = trab;
			}catch (DatabaseException e) {
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
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET matricula = "+matricula+" where id = "+this.getId());
			
				this.matricula = matricula;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET marca = "+marca+" where id = "+this.getId());
			
				this.marca = marca;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getModelo() {
			return modelo;
		}

		public void setModelo(String modelo) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET modelo = "+modelo+" where id = "+this.getId());
			
				this.modelo = modelo;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getHora() {
			return hora;
		}

		public void setHora(String hora) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET hora = "+hora+" where id = "+this.getId());
			
				this.hora = hora;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getTam() {
			return tam;
		}

		public void setTam(String tam) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET tam = "+tam+" where id = "+this.getId());

				this.tam = tam;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public double getPrecio() {
			return precio;
		}

		public void setPrecio(double precio) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET precio = "+precio+" where id = "+this.getId());
			
				this.precio = precio;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getTelefono() {
			return telefono;
		}

		public void setTelefono(String telefono) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET telefono = "+telefono+" where id = "+this.getId());
			
				this.telefono = telefono;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}		}

		public Propietario getProp() {
			return prop;
		}

		public void setProp(Propietario prop) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Propietarios = "+prop.getId()+" where id = "+this.getId());
			
				this.prop = prop;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public Trabajador getTrab() {
			return trab;
		}

		public void setTrab(Trabajador trab) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Trabajador = "+trab.getId()+" where id = "+this.getId());
			
				this.trab = trab;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public void BorrarLavados(){
			 try (MySqlDB miBD = new MySqlDB()){
				 miBD.Delete("DELETE FROM Lavados WHERE id = "
	        			 +this.id+"';");
	        	 id = -1;
	       	    matricula = null;
	       	    marca = null;
	       	    modelo = null;
	       	    hora = null;
	       	    tam = null;
	       	    precio = -1;
	       	    telefono = null;
	       	    prop = null;
	       	    trab = null;
			 }catch (DatabaseException e) {
		            e.printStackTrace();
		        }
        	 
         }

		@Override
		public String toString() {
			return "Lavados [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo
					+ ", hora=" + hora + ", tam=" + tam + ", precio=" + precio + ", telefono=" + telefono + "]";
		}

		public Object[] asArray() {
            Object[] tmp = new Object[10];
            tmp[0] = id;
            tmp[1] = matricula;
            tmp[2] = marca;
            tmp[3] = modelo;
            tmp[4] = hora;
            tmp[5] = tam;
            tmp[6] = precio;
            tmp[7] = telefono;
            tmp[8] = prop;
            tmp[9] = trab;

            return tmp;
        }
       
}
