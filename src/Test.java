import DB.DatabaseException;
import DB.MySqlDB;

public class Test {

    private final static String configFile = "database.config";

    public static void main(String[] args)  {
        try (MySqlDB db = new MySqlDB()) {

        } catch (DatabaseException e) {
            System.err.println(e);
        }
    }
}
