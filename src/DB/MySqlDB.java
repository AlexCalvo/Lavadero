package DB;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDB implements AutoCloseable {

    private final static String configFile = "database.config";
    private Connection con;

    public MySqlDB() throws DatabaseException {
        try (FileReader file = new FileReader(configFile)) {
            JsonObject obj = new JsonParser().parse(file).getAsJsonObject();
            String server = obj.get("server").getAsString();
            String schema = obj.get("schema").getAsString();
            String user = obj.get("user").getAsString();
            String password = obj.get("password").getAsString();
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + schema + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false");

        } catch (SQLException ex) {
            throw new DatabaseException("Error al Conectar con la base de datos." + ex.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws DatabaseException {
        try {
            if (con != null) con.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Error al Cerrar la Conexion." + ex.getMessage());
        }
    }


    public Object SelectEscalar(String sel) throws DatabaseException {
        ResultSet rset;
        Object res = null;
        try {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(sel);
            rset.next();
            res = rset.getObject(1);
            rset.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Error en el SELECT: " + sel + ". " + ex.getMessage());
        }

        return res;
    }

    public List<Object[]> Select(String sel) throws DatabaseException {
        ResultSet rset;
        List<Object[]> lista = new ArrayList<Object[]>();
        try {
            Statement stmt = con.createStatement();
            rset = stmt.executeQuery(sel);
            ResultSetMetaData meta = rset.getMetaData();
            int numCol = meta.getColumnCount();
            while (rset.next()) {
                Object[] tupla = new Object[numCol];
                for (int i = 0; i < numCol; ++i) {
                    tupla[i] = rset.getObject(i + 1);
                }
                lista.add(tupla);
            }
            rset.close();
            stmt.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Error en el SELECT: " + sel + ". " + ex.getMessage());
        }

        return lista;
    }

    public void Insert(String ins) throws DatabaseException {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(ins);
            stmt.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Error en el INSERT: " + ins + ". " + ex.getMessage());
        }
    }

    public void Delete(String del) throws DatabaseException {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(del);
            stmt.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Error en el DELETE: " + del + ". " + ex.getMessage());
        }
    }

    public void Update(String up) throws DatabaseException {
        try {
            Statement stmt = con.createStatement();
            stmt.execute(up);
            stmt.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Error en el UPDATE: " + up + ". " + ex.getMessage());
        }
    }

}
