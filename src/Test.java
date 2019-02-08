import DB.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test {

    private final static String configFile = "database.config";

    public static void main(String[] args) throws FileNotFoundException {


        JsonObject obj = new JsonParser().parse(new FileReader(configFile)).getAsJsonObject();

        String server = obj.get("server").getAsString();
        String schema = obj.get("schema").getAsString();
        String user = obj.get("user").getAsString();
        String password = obj.get("password").getAsString();


        try (MySqlDB db = new MySqlDB(server,schema, user, password)) {

        } catch (DatabaseException e) {
            System.err.println(e);
        }
    }
}
