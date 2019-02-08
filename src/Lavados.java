import java.util.*;

import DB.DatabaseException;
import DB.MySqlDB;

import java.text.SimpleDateFormat;

public class Lavados
{
	 
	    private int ID;//clave principal
	    private String Matricula;
	    private String Marca;
	    private String Modelo;
	    private String Hora;
	    private enum Tam{
	    	Tipo1,Tipo2,Tipo3;//Los que sean
	    }
	    private Tam Tam;
	    private double Precio;
	    private int Telefono;
	    private Propietarios Prop;
	    private Trabajador Trab;

        public static List<Lavados> ListaLavados(Lavados l)
        {
            List<Lavados> lista = new ArrayList<Lavados>();
            MySqlDB miBD = new MySqlDB();
            
            for(Object[] tupla : miBD.Select("SELECT ID FROM Lavados WHERE Matricula = "+l.getMatricula()+ ";" )){
            	lista.add(new Lavados((int)tupla[1]));
            }
            
            return lista;
        }

        public Lavados(int id)
        {
        	MySqlDB miBD = new MySqlDB();
        	Object[] tupla = miBD.Select("SELECT*FROM Lavados WHERE "
        			+ "ID = "+ id + ";").get(0);
        	ID = (int)tupla[0];
      	    Matricula = (String)tupla[1];
      	    Marca = (String)tupla[2];
      	    Modelo = (String)tupla[3];
      	    Hora = (String)tupla[4];
      	    Tam = (Tam)tupla[5];
      	    Precio = (double)tupla[6];
      	    Telefono = (int)tupla[7];
      	    Prop = (Propietarios)tupla[8];
      	    Trab = (Trabajador)tupla[9];
      	    
        	
         }

		public Lavados(int ID, String Matricula, String Marca, String Modelo,String Hora,Tam Tam,
		double Precio, int Telefono, Propietarios Prop, Trabajador Trab)
         {
             MySqlDB miBD = new MySqlDB();
             miBD.Insert("INSERT INTO Lavados VALUES(" 
            		 +ID+ ", '"+Matricula+"', '"+Marca+"','"+Modelo+"','"+Hora+"',"+Tam+","+
            		 Precio+","+Telefono+","+Prop+","+Trab+");");
             
             this.ID = ID;
         }

       
		

		public int getID() {
			return ID;
		}

		public void setID(int iD) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET ID = "+iD+" where ID = "+this.getID());
			
				this.ID = iD;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getMatricula() {
			return Matricula;
		}

		public void setMatricula(String matricula) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Matricula = "+matricula+" where ID = "+this.getID());
			
				this.Matricula = matricula;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getMarca() {
			return Marca;
		}

		public void setMarca(String marca) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Marca = "+marca+" where ID = "+this.getID());
			
				this.Marca = marca;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getModelo() {
			return Modelo;
		}

		public void setModelo(String modelo) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Modelo = "+modelo+" where ID = "+this.getID());
			
				this.Modelo = modelo;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public String getHora() {
			return Hora;
		}

		public void setHora(String hora) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Hora = "+hora+" where ID = "+this.getID());
			
				this.Hora = hora;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public Tam getTam() {
			return Tam;
		}

		public void setTam(Tam tam) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Tam = "+tam+" where ID = "+this.getID());
			
				this.Tam = tam;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public double getPrecio() {
			return Precio;
		}

		public void setPrecio(double precio) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Precio = "+precio+" where ID = "+this.getID());
			
				this.Precio = precio;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public int getTelefono() {
			return Telefono;
		}

		public void setTelefono(int telefono) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Telefono = "+telefono+" where ID = "+this.getID());
			
				this.Telefono = telefono;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}		}

		public Propietarios getProp() {
			return Prop;
		}

		public void setProp(Propietarios prop) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Propietarios = "+prop+" where ID = "+this.getID());
			
				this.Prop = prop;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public Trabajador getTrab() {
			return Trab;
		}

		public void setTrab(Trabajador trab) {
			try(MySqlDB miBD = new MySqlDB()) {
				miBD.Update("UPDATE Lavados SET Trabajador = "+trab+" where ID = "+this.getID());
			
				this.Trab = trab;
			}catch(DatabaseException e) {
				e.printStackTrace();
			}
		}

		public void BorrarLavados(){
			 MySqlDB miBD = new MySqlDB();
        	 miBD.Delete("DELETE FROM Lavados WHERE ID = "
        			 +this.ID+ "';");
        	 ID = -1;
       	    Matricula = null;
       	    Marca = null;
       	    Modelo = null;
       	    Hora = null;
       	    Tam = null;
       	    Precio = -1;
       	    Telefono =-1;;
       	    Prop = null;
       	    Trab = null;
         }

		@Override
		public String toString() {
			return "Lavados [ID=" + ID + ", Matricula=" + Matricula + ", Marca=" + Marca + ", Modelo=" + Modelo
					+ ", Hora=" + Hora + ", Tam=" + Tam + ", Precio=" + Precio + ", Telefono=" + Telefono + "]";
		}
       
}
