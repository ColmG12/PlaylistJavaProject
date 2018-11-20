import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by t00036478 on 01/02/2018.
 */
public class DBConnection {
    private  String driverClass = "org.mariadb.jdbc.Driver";
    private  Connection connection = null;
    private  String url = "jdbc:mariadb://localhost:3306/oopprojectdatabase";
    private  String username = "root"; // your username
    private  String password = ""; //your password
    public DBConnection(){
    }

    private void setConnection() {
        try {
            Class.forName(driverClass).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (Exception ex) {
            System.err.println("Exception:"+ ex.getMessage());
            ex.printStackTrace();
        }

    }
    private Connection getConnection() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    public static void main(String[] args){
        DBConnection manager = new DBConnection();
        manager.setConnection();

    }
}
