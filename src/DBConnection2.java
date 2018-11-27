import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by t00036478 on 01/02/2018.
 */
public class DBConnection2 {
    private String insertSql = "INSERT INTO SONGS VALUES (671, 'New Rules','Dua Lipa', 'Love Songs', '2017', " +
            "                   'Dua Lipa-New Rules.mp3', '204')";
    private String selectSql = "SELECT * FROM SONGS";

    private   String driverClass = "org.mariadb.jdbc.Driver";
    private   Connection connection = null;
    private  String url = "jdbc:mariadb://localhost:3306/oopprojectdatabase";
    //static final String url = "jdbc:mariadb://localhost:3306/oopprojectdatabase";
    //DBConnection manager;


    private   String username = "root"; // your username
    private   String password = ""; //your password
    public DBConnection2(){
    	setConnection();
    }

    private  void setConnection() {
        try {
            Class.forName(driverClass).newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
        } catch (Exception ex) {
            System.err.println("Exception:" + ex.getMessage());
            ex.printStackTrace();
        }

    }
    public  Connection getConnection() {
        if (connection == null) {
            setConnection();
        }
        return connection;
    }

    private void persistSong() {
        try {
            Statement st = getConnection().createStatement();
// Execute the statement
            st.executeUpdate(insertSql);
            System.out.println("Song persisted successfully!");
        } catch (java.sql.SQLException ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
    }


    public static void main(String[] args){
        DBConnection2 manager = new DBConnection2();
        manager.setConnection();
        manager.persistSong();
    }
}
