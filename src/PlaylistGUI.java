/**
 * *
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//package com.javainterviewpoint;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javafx.embed.swing.JFXPanel; //need this for AudioFilePlayer as it uses JavaFX API

import javax.swing.*;

//import static sun.audio.AudioPlayer.player;

//import static com.sun.deploy.config.JREInfo.getAll;

import java.io.File;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.application.Platform;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;


public class PlaylistGUI extends javax.swing.JFrame implements ActionListener {

    //Delimiters used in the CSV file
    //private static final String COMMA_DELIMITER = ",";

    public static <songList> void main(String args[]) throws SQLException {
        //BufferedReader br = null;
        DBConnection dbConn = new DBConnection(); //establish a connection to the MariaDB
        Connection conn = dbConn.getConnection(); //get a reference to the database connection object
        Statement stmt = null;

        //Create List for holding Song objects
        List<Song> songList = new ArrayList<Song>();

        try {
            //Reading the csv file
            //br = new BufferedReader(new FileReader("ColmsSongDatabase.csv"));

            stmt = conn.createStatement(); //create a Statement object, needed for executing queries on the database

            //Execute a query to retrieve all song rows from the songs table
            //I used HeidiSQL to create the rows in the songs table, populated from the contents of a .csv file
            //just to populate it with a set of songs

            //Store the results of the query in a ResultSet object

            ResultSet rs = stmt.executeQuery("SELECT * FROM songs");

            //loop over all the records (rows) found in the songs table
            System.out.println("**********************Songs stored in the Song database***********************");
            while (rs.next()) {
                // retrieve and print the values for the current row
                int id = rs.getInt("ID");
                String title = rs.getString("Title");
                String artist = rs.getString("Artist");
                String genre = rs.getString("Genre");
                int year = rs.getInt("Year");
                String location = rs.getString("Location");
                int duration = rs.getInt("Duration");

                System.out.println("Row details are: " + id + "   " + title + "   " + artist + "   " + genre + "   "
                        + year + "  " + location + "  " + duration);

                JFXPanel fxPanel = new JFXPanel(); //must have this line of code in order to initialize the JavaFX
                // runtime so AudioFilePlayer can be used

                //if(id==496)
                //	RadioPlayer.playAudio("J:/ColmSongs/" + location);  //JB - I had this code to play a song from the database


                //JB - use the details above retrieved from the database to create each Song object

                //Save the song details in Song object
                Song song = new Song(id, title, artist, genre, year, location, duration);
                //songList.add(song);

                JButton audioButton;

                MediaPlayer mediaPlayer;

                fxPanel = new JFXPanel();

            }




            
            
            

            



            /*String line = "";
            //Read to skip the header
            //br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null)
            {
                String[] songDetails = line.split(COMMA_DELIMITER);


                if(songDetails.length > 0 )
                {
                    //Save the song details in Song object
                    Song song = new Song(Integer.parseInt(songDetails[0]),
                            songDetails[1], songDetails[2],
                            songDetails[3], Integer.parseInt(songDetails[4]),
                            songDetails[5], Integer.parseInt(songDetails[6]));
                    songList.add(song);
                }
            }

            //Lets print the Song List
            for(Song s : songList)
            {
                System.out.println(s.getID() + "   " + s.gettitle()+ "   "
                        + s.getartist() + "   " + s.getgenre()+ "   "
                        + s.getyear() + "   " + s.getlocation()+ "   "
                        + s.getduration() + "   ");
            }
        }*/
        } catch (SQLException sqlex) {
            //Handle exceptions related to JDBC
            sqlex.printStackTrace();
        } catch (Exception ex) {
            //Handle exceptions related to registration of JDBC driver
            ex.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }//end finally try-catch clauses
        }//end main try-catch-finally clause

        //Lets print the Song List
        System.out.println("\n\n\n\n\n\n\n\n**********************Songs stored in the ArrayList of Song objects***********************");
        for (Song s : songList) {
            System.out.println(s.getID() + "   " + s.gettitle() + "   "
                    + s.getartist() + "   " + s.getgenre() + "   "
                    + s.getyear() + "   " + s.getlocation() + "   "
                    + s.getduration() + "   ");
        }


        System.out.println("Goodbye!");


    }







      /*  class TestTableRightClick {

            int ID;
            String title;
            String artist;
            String genre;
            int year;
            String location;
            int duration;

            //protected void initUI() throws SQLException {
            class Song extends Vector<String> {


                List<Song> data = new ArrayList<>();
                Connection conn = null;
                private int ID;
                private String title;
                private String artist;
                private String genre;
                private int year;
                private String location;
                private int duration;

                class DBConnection {
                    private String driverClass = "org.mariadb.jdbc.Driver";
                    private Connection connection = null;
                    private String url = "jdbc:mariadb://localhost:3306/oopprojectdatabase";
                    //static final String url = "jdbc:mariadb://localhost:3306/oopprojectdatabase";
                    //DBConnection manager;


                    private String username = "root"; // your username
                    private String password = ""; //your password

                    public DBConnection() {
                        setConnection();
                    }

                    private List<Song> setConnection() {
                        try {
                            Class.forName(driverClass).newInstance();
                            connection = DriverManager.getConnection(url, "root", "");
                            String sql = "SELECT * FROM SONGS";
                            PreparedStatement stmt = conn.prepareStatement(sql);
                            ResultSet rs = stmt.executeQuery(sql);

                            while (rs.next()) {
                                // retrieve and print the values for the current row
                                Song song = new Song();
                                song.setID(rs.getInt("ID"));
                                song.settitle(rs.getString("Title"));
                                song.setartist(rs.getString("Artist"));
                                song.setgenre(rs.getString("Genre"));
                                song.setyear(rs.getInt("Year"));
                                song.setlocation(rs.getString("Location"));
                                song.setduration(rs.getInt("Duration"));
                                data.add(song);
                            }
                            rs.close();
                            conn.close();
                        } catch (Exception ex) {
                            System.err.println("Exception:" + ex.getMessage());
                            ex.printStackTrace();
                        }

                        return data;

                    }

                    public Connection getConnection() {
                        if (connection == null) {
                            setConnection();
                        }
                        return connection;
                    }


                    //List<Song> songList = new ArrayList<Song>();
                    final JFrame frame = new JFrame(TestTableRightClick.class.getSimpleName());
                    //Vector<String> columnNames = new Vector<String>(Arrays.asList("ID", "Title", "Artist", "Genre", "Year", "Location", "Duration"));
                    //Vector<Vector<String>> data = new Vector<Vector<String>>();
                    //for (int i = 0; i < 50; i++) {
                    // Vector<String> row = new Vector<String>();
                    //for (int j = 0; j < columnNames.size(); j++) {
                    //    row.add("Cell " + (i + 1) + "," + (j + 1));
                }

                public void setduration(int duration) {

                    this.duration = duration;
                }

                public void setlocation(String location) {

                    this.location = location;
                }

                public void setyear(int year) {

                    this.year = year;
                }

                public void setgenre(String genre) {
                    this.genre = genre;
                }

                public void setartist(String artist) {
                    this.artist = artist;
                }

                public void settitle(String title) {
                    this.title = title;
                }

                public void setID(int ID) {
                    this.ID = ID;
                }
                //data.add(row);
            }


            //data.add(row);
        }


        final JTable table = new JTable();
        final JPopupMenu popupMenu = new JPopupMenu();
        //JMenuItem deleteItem = new JMenuItem("Delete");
        //deleteItem.addActionListener(new ActionListener() {

        // @Override
        //public void actionPerformed(ActionEvent e) {
        //   JOptionPane.showMessageDialog(frame, "Right-click performed on table and choose DELETE");
        //}
        //}
        //popupMenu.add(deleteItem);
        // table.setComponentPopupMenu(popupMenu);
        //frame.add(new JScrollPane(table), BorderLayout.CENTER);
        // frame.pack();
        // frame.setVisible(true);
    }


        /*public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        new TestTableRightClick().initUI();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });*/


















    /*public static class TestTableRightClick {

        protected void initUI() throws SQLException {

            DBConnection dbConn = new DBConnection(); //establish a connection to the MariaDB
            Connection conn = dbConn.getConnection(); //get a reference to the database connection object
            Statement stmt = null;

            List<Song> songList = new ArrayList<Song>();

            stmt = conn.createStatement(); //create a Statement object, needed for executing queries on the database

            //Execute a query to retrieve all song rows from the songs table
            //I used HeidiSQL to create the rows in the songs table, populated from the contents of a .csv file
            //just to populate it with a set of songs

            //Store the results of the query in a ResultSet object

            ResultSet rs = stmt.executeQuery("SELECT * FROM songs");

            //List<Song> songList = new ArrayList<Song>();
            final JFrame frame = new JFrame(TestTableRightClick.class.getSimpleName());
            Vector<String> columnNames = new Vector<String>(Arrays.asList("ID", "Title", "Artist", "Genre", "Year", "Location", "Duration"));
            Vector<Vector<String>> data = new Vector<Vector<String>>();
            for (int i = 0; i < 50; i++) {
                Vector<String> row = new Vector<String>();
                for (int j = 0; j < columnNames.size(); j++) {
                    row.add("Cell " + (i + 1) + "," + (j + 1));
                }
                data.add(row);
            }
            final JTable table = new JTable(data, columnNames);
            final JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem deleteItem = new JMenuItem("Delete");
            deleteItem.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(frame, "Right-click performed on table and choose DELETE");
                }
            });
            popupMenu.add(deleteItem);
            table.setComponentPopupMenu(popupMenu);
            frame.add(new JScrollPane(table), BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {

                @Override
                public void run() {
                    try {
                        new TestTableRightClick().initUI();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }*/


    // Column Names
        /*String[] columnNames = {"ID", "Title", "Artist", "Genre", "Year", "Location", "Duration"};


    // Initializing the JTable
    JTable songtable = new JTable(data, columnNames);
            songtable.setBounds(30, 40, 200, 300);


    // adding it to JScrollPane
    JScrollPane scroll = new JScrollPane(songtable);
            radioframe.add(scroll);
    // Frame Size
            radioframe.setSize(1500, 600);
    // Frame Visible = true
            radioframe.setVisible(true);

    //
            radioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}*/


    //Constructor
    PlaylistGUI GUI = new PlaylistGUI();

    static {

        // Frame initialization
        JFrame radioframe = new JFrame();

        // Frame Title
        radioframe.setTitle("Playlist GUI");

        // Data to be displayed in the JTable
        String[][] data = {

                {"1", "HERE IN MY HEART", "AL MARTINO", "POP", "1952", "Al Martino-Here In My Heart.mp3", "183"},
                {"2", "CHANGING PARTNERS", "BING CROSBY", "MOTOWN & SOUL", "1952", "Bing Crosby-Changing Partners.mp3", "166"},
                {"3", "SILENT NIGHT HOLY NIGHT", "BING CROSBY", "POP", "1952", "Bing Crosby-Silent Night Holy Night.mp3", "160"},
                {"4", "YOU BELONG TO ME", "JO STAFFORD", "COUNTRY", "1952", "Jo Stafford-You Belong To Me.mp3", "191"},
                {"5", "SUCH A NIGHT", "JOHNNIE RAY", "LIVE", "1952", "Johnnie Ray-Such A Night.mp3", "145"},
                {"6", "TAKES TWO TO TANGO", "LOUIS ARMSTRONG", "1990s", "1952", "Louis Armstrong-Takes Two To Tango.mp3", "166"},
                {"7", "BECAUSE YOU'RE MINE", "MARIO LANZA", "REGGAE", "1952", "Mario Lanza-Because You're Mine.mp3", "212"},
                {"8", "A BLOSSOM FELL", "NAT KING COLE", "CLUB", "1952", "Nat King Cole-A Blossom Fell.mp3", "151"},
                {"9", "HALF AS MUCH", "ROSEMARY CLOONEY", "2010s", "1952", "Rosemary Clooney-Half As Much.mp3", "167"},
                {"10", "AUF WIEDERSEH'N SWEETHEART", "VERA LYNN", "REGGAE", "1952", "Vera Lynn-Auf Wiederseh'n Sweetheart.mp3", "156"},
                {"11", "BECAUSE YOU'RE MINE", "NAT KING COLE", "COUNTRY", "1953", "Nat King Cole-Because You're Mine.mp3", "192"},
                {"12", "ETERNALLY", "JIMMY YOUNG", "2000s", "1953", "Jimmy Young-Eternally.mp3", "142"},
                {"13", "LOOK AT THAT GIRL", "GUY MITCHELL", "INDIE", "1953", "Guy Mitchell-Look At That Girl.mp3", "172"},
                {"14", "GRANADA", "FRANKIE LAINE", "CLUB", "1953", "Frankie Laine-Garnada.mp3", "178"},
                {"15", "CAN'T I?", "NAT KING COLE", "DISCO", "1953", "Nat King Cole-Can't I.mp3", "193"},
                {"16", "KISS", "DEAN MARTIN", "1980s", "1953", "Dean Martin-Kiss.mp3", "142"},
                {"17", "FLIRTATION WALTZ", "WINIFRED ATWELL", "LIVE", "1953", "Winifred Atwell-Flirtation Waltz.mp3", "136"},
                {"18", "ANSWER ME", "DAVID WHITFIELD", "JAZZ", "1953", "David Whitfield-Answer Me.mp3", "148"},
                {"19", "HEY JOE", "FRANKIE LAINE", "COUNTRY", "1953", "Frankie Laine-Hey Joe!.mp3", "142"},
                {"20", "THE BRIDGE OF SIGHS", "DAVID WHITFIELD", "1950s", "1953", "David Whitfield-The Bridge Of Sighs.mp3", "173"},
                {"21", "MOONLIGHT SERENADE", "GLENN MILLER", "1990s", "1954", "Glenn Miller-Moonlight Serenade.mp3", "202"},
                {"22", "CHANGING PARTNERS", "KAY STARR", "LATIN", "1954", "Kay Starr-Changing Partners.mp3", "174"},
                {"23", "THE ISLE OF INNISFREE", "BING CROSBY", "R&B", "1954", "Bing Crosby-The Isle Of Innisfree.mp3", "184"},
                {"24", "BELL BOTTOM BLUES", "ALMA COGAN", "JAZZ", "1954", "Alma Cogan-Bell Bottom Blues.mp3", "154"},
                {"25", "THE KID'S LAST FIGHT", "FRANKIE LAINE", "DISCO", "1954", "Frankie Laine-The Kid's Last Fight.mp3", "203"},
                {"26", "SECRET LOVE", "DORIS DAY", "DISCO", "1954", "Doris Day-Secret Love.mp3", "160"},
                {"27", "WALKIN' MY WAY BACK HOME", "JOHNNIE RAY", "LOVE SONGS", "1954", "Johnnie Ray-Walkin' My Way Back Home.mp3", "144"},
                {"28", "THE HAPPY WANDERER", "STARGAZERS, THE", "COUNTRY", "1954", "Stargazers, The-Happy Wanderer.mp3", "146"},
                {"29", "WHERE THE WINDS BLOW", "FRANKIE LAINE", "HIP HOP & RAP", "1954", "Frankie Laine-Where The Winds Blow.mp3", "154"},
                {"30", "MOTHER NATURE AND FATHER TIME", "NAT KING COLE", "HIP HOP & RAP", "1954", "Nat King Cole-Mother Nature And Father Time.mp3", "193"},
                {"31", "MOBILE", "RAY BURNS", "2010s", "1955", "Ray Burns-Mobile.mp3", "175"},
                {"32", "LONELY BALLERINA", "MONTOVANI", "1950s", "1955", "Montovani-Lonely Ballerina.mp3", "170"},
                {"33", "TEACH ME TONIGHT", "DE CASTRO SISTERS, THE", "1960s", "1955", "De Casto Sisters, The-Teach Me Tonight.mp3", "168"},
                {"34", "A BLOSSOM FELL", "DICKIE VALENTINE", "POP", "1955", "Dickie Valentine-A Blossom Fell.mp3", "143"},
                {"35", "LET ME GO LOVER", "JOAN WEBBER", "DISCO", "1955", "Joan Weber-Let Me Go Lover.mp3", "144"},
                {"36", "MAJORCA", "PETULA CLARK", "JAZZ", "1955", "Petula Clark-Majorca.mp3", "177"},
                {"37", "I'LL WALK WITH GOD", "MARIO LANZA", "ROCK", "1955", "Mario Lanza-I'll Walk With God.mp3", "171"},
                {"38", "TENDERLY", "NAT KING COLE", "INDIE", "1955", "Nat King Cole-Tenderly.mp3", "176"},
                {"39", "LET ME GO LOVER", "DEAN MARTIN", "1990s", "1955", "Dean Martin-Let Me Go Lover.mp3", "182"},
                {"40", "LET ME GO LOVER", "RUBY MURRAY", "LIVE", "1955", "Ruby Murray-Let Me Go Lover.mp3", "152"},
                {"41", "SEE YOU LATER ALLIGATOR", "BILL HALEY & HIS COMETS", "LATIN", "1956", "Bill Haley & His Comets-See You Later Alligator.mp3", "163"},
                {"42", "LITTLE SERENADE", "EDDIE CALVERT", "2010s", "1956", "Eddie Calvert-Little Serenade.mp3", "184"},
                {"43", "TUMBLING TUMBLEWEEDS", "SLIM WHITMAN", "JAZZ", "1956", "Slim Whitman-Tumbling Tumbleweeds.mp3", "143"},
                {"44", "THE POOR PEOPLE OF PARIS", "WINIFRED ATWELL", "LOVE SONGS", "1956", "Winifred Atwell-The Poor People Of Paris.mp3", "121"},
                {"45", "THEME FROM 'THE THREEPENNY OPERA'", "DICK HYMAN TRIO, THE", "MOTOWN & SOUL", "1956", "Dick Hyman Trio, The-Theme From 'The Threepenny Opera'.mp3", "138"},
                {"46", "MORE", "JIMMY YOUNG", "2010s", "1956", "Jimmy Young-More.mp3", "140"},
                {"47", "THE TROUBLE WITH HARRY", "ALFI AND HARRY", "ROCK", "1956", "Alfi And Harry-The Trouble With Harry.mp3", "141"},
                {"48", "THEME FROM 'THE THREEPENNY OPERA'", "BILLY VAUGHN", "LOVE SONGS", "1956", "Billy Vaughn-Theme From 'The Three Penny Opera'.mp3", "146"},
                {"49", "JIMMY UNKNOWN", "LITA ROZA", "POP", "1956", "Lita Roza-Jimmy Unknown.mp3", "182"},
                {"50", "WHATEVER LOLA WANTS", "ALMA COGAN", "1950s", "1956", "Alma Cogan-Whatever Lola Wants.mp3", "166"},
                {"51", "DON'T CRY DADDY", "ELVIS PRESLEY", "MOTOWN & SOUL", "1957", "Elvis Presley-Don't Cry Daddy.mp3", "167"},
                {"52", "BY THE LIGHT OF THE SILVERY MOON", "LITTLE RICHARD", "REGGAE", "1957", "Little Richard-By The Light Of The Silvery Moon.mp3", "124"},
                {"53", "THE WISDOM OF A FOOL", "NORMAN WISDOM", "JAZZ", "1957", "Norman Wisdom-The Wisdom Of A Fool.mp3", "162"},
                {"54", "THE FAITHFUL HUSSAR (DON'T CRY MY LOVE)", "VERA LYNN", "ROCK", "1957", "Vera Lynn-The Faithful Hussar (Don't Cry My Love).mp3", "177"},
                {"55", "CUMBERLAND GAP", "VIPERS SKIFFLE GROUP, THE", "CLASSICAL", "1957", "Vipers Skiffle Group, The-Cumberland Gap.mp3", "116"},
                {"57", "THE WISDOM OF A FOOL", "RONNIE CARROLL", "2000s", "1957", "Ronnie Carroll-The Wisdom Of A Fool.mp3", "188"},
                {"58", "BLUE MONDAY", "FATS DOMINO", "REGGAE", "1957", "Fats Domino-Blue Monday.mp3", "135"},
                {"59", "MANGOS", "ROSEMARY CLOONEY", "CLUB", "1957", "Rosemary Clooney-Mangos.mp3", "154"},
                {"60", "WILLIE CAN", "ALMA COGAN", "1960s", "1957", "Alma Cogan-Willie Can.mp3", "129"},
                {"61", "THE PURPLE PEOPLE EATER", "SHEB WOOLEY", "LIVE", "1958", "Sheb Wooley-The Purple People Eater.mp3", "134"},
                {"62", "ZAMBESI", "EDDIE CALVERT", "CLUB", "1958", "Eddie Calvert-Zambesi.mp3", "163"},
                {"63", "RAVE ON", "BUDDY HOLLY", "1950s", "1958", "Buddy Holly-Rave On.mp3", "110"},
                {"64", "SUGAR MOON", "PAT BOONE", "LIVE", "1958", "Pat Boone-Sugar Moon.mp3", "116"},
                {"65", "THE PURPLE PEOPLE EATER", "JACKIE DENNIS", "2000s", "1958", "Jackie Dennis-The Purple People Eater.mp3", "140"},
                {"66", "I'M SORRY I MADE YOU CRY", "CONNIE FRANCIS", "2000s", "1958", "Connie Francis-I'm Sorry I Made You Cry.mp3", "149"},
                {"67", "TORERO-CHA CHA CHA", "RENATO CARASONE & HIS SEXTET", "COUNTRY", "1958", "Renato Carosone-Torero (Cha Cha Cha).mp3", "183"},
                {"68", "SICK AND TIRED", "FATS DOMINO", "ROCK", "1958", "Fats Domino-Sick And Tired.mp3", "154"},
                {"69", "TOM HARK", "TED HEATH & HIS MUSIC", "LOVE SONGS", "1958", "Ted Heath & His Music-Tom Hark.mp3", "121"},
                {"70", "WHEN THE BOYS TALK ABOUT THE GIRLS", "VALERIE CARR", "R&B", "1958", "Valerie Carr-When The Boys Talk About The Girls.mp3", "159"},
                {"71", "DONNA", "MARTY WILDE", "CLUB", "1959", "Marty Wilde-Donna.mp3", "132"},
                {"72", "C'MON EVERYBODY", "EDDIE COCHRAN", "COUNTRY", "1959", "Eddie Cochran-C'mon Everybody.mp3", "114"},
                {"73", "VENUS", "DICKIE VALENTINE", "R&B", "1959", "Dickie Valentine-Venus.mp3", "150"},
                {"74", "SING LITTLE BIRDIE", "PEARL CARR & TEDDY JOHNSON", "REGGAE", "1959", "Pearl Carr & Teddy Johnson-Sing Little Birdie.mp3", "112"},
                {"75", "BELOVED BE FAITHFUL", "TEDDY JOHNSON", "MOTOWN & SOUL", "1959", "Teddy Johnson-Beloved Be Faithful.mp3", "183"},
                {"76", "CIAO CIAO BAMBINA", "MARINO MARINI & HIS QUARTET", "R&B", "1959", "Marino Marini & His Quartet-Ciao Ciao Bambina.mp3", "158"},
                {"77", "CHARLIE BROWN", "COASTERS, THE", "1990s", "1959", "Coasters, The-Charlie Brown.mp3", "141"},
                {"78", "CIAO CIAO BAMBINA", "DOMENICO MODUGNO", "1980s", "1959", "Domenico Moduqno-Ciao Ciao Bambina.mp3", "187"},
                {"79", "THE STORY OF MY LOVE", "CONWAY TWITTY", "CLUB", "1959", "Conway Twitty-The Story Of My Love.mp3", "136"},
                {"80", "THE GIRL CAN'T HELP IT", "LITTLE RICHARD", "ROCK", "1959", "Little Richard-The Girl Can't Help It.mp3", "151"},
                {"81", "LOOKING HIGH HIGH HIGH", "BRYAN JOHNSON", "JAZZ", "1960", "Bryan Johnson-Looking High High High.mp3", "173"},
                {"82", "JOHNNY ROCCO", "MARTY WILDE", "COUNTRY", "1960", "Marty Wilde-Johnny Rocco.mp3", "148"},
                {"83", "BIG BEAT BOOGIE", "BERT WEEDON", "CLUB", "1960", "Bert Weedon-Big Beat Boogie.mp3", "116"},
                {"84", "WILD ONE", "BOBBY RYDELL", "HIP HOP & RAP", "1960", "Bobby Rydell-Wild One.mp3", "142"},
                {"85", "SINK THE BISMARCK", "DON LANG", "1990s", "1960", "Don Lang-Sink The Bismarck.mp3", "173"},
                {"86", "NO HIDING PLACE", "KEN MACKINTOSH", "MOTOWN & SOUL", "1960", "Ken Mackintosh-No Hiding Place.mp3", "138"},
                {"87", "HOUND DOG MAN", "FABIAN", "LOVE SONGS", "1960", "Fabian-Hound Dog Man.mp3", "137"},
                {"88", "LISTEN TO THE OCEAN", "NINA & FREDERICK", "CLASSICAL", "1960", "Nina & Frederik-Listen To The Ocean.mp3", "182"},
                {"89", "TEEN ANGEL", "MARK DINNING", "ROCK & ROLL", "1960", "Mark Dinning-Teen Angel.mp3", "162"},
                {"90", "MY HEART", "GENE VINCENT", "ROCK", "1960", "Gene Vincent-My Heart.mp3", "150"},
                {"91", "NIGHT FEVER", "ADAM GARCIA", "DISCO", "1961", "Adam Garcia-Night Fever.mp3", "215"},
                {"92", "RUN RUN RUN", "JO JO GUNNE", "CLUB", "1961", "Jo Jo Gunne-Run Run Run.mp3", "154"},
                {"93", "MY LITTLE BABY", "OUTLAWS, THE", "HIP HOP & RAP", "1961", "Outlaws, The-My Little Baby.mp3", "113"},
                {"94", "BLUE MOON", "MARCELS, THE", "MOTOWN & SOUL", "1961", "Marcels, The-Blue Moon.mp3", "135"},
                {"95", "KICKING UP THE LEAVES", "MARK WYNTER", "1950s", "1961", "Mark Wynter-Kicking Up The Leaves.mp3", "122"},
                {"96", "ANOTHER SLEEPLESS NIGH", "JIMMY CLANTON", "1970s", "1961", "Jimmy Clanton-Another Sleepless Night.mp3", "130"},
                {"97", "WHY", "ANTHONY NEWLEY", "2010s", "1961", "Anthony Newley-Why.mp3", "146"},
                {"98", "MY GIRL", "TEMPTATIONS, THE", "POP", "1961", "Temptations, The-My Girl.mp3", "177"},
                {"99", "THE PRICE OF LOVE", "EVERLY BROTHERS, THE", "INDIE", "1961", "Everly Brothers, The-The Price Of Love.mp3", "127"},
                {"100", "MY HEART HAS A MIND OF ITS OWN", "CONNIE FRANCIS", "2010s", "1961", "Connie Francis-My Heart Has A Mind Of Its Own.mp3", "153"},
                /*{"101	''IF EVERY DAY WAS LIKE CHRISTMAS''	''ELVIS PRESLEY''	''POP''	1962	''Elvis Presley-If Every Day Was Like Christmas.mp3''	174},
                {"102	''CODE OF LOVE''	''MIKE SARNE''	''ROCK & ROLL''	1962	''Mike Sarne-Code Of Love.mp3''	133},
                {"103	''KISS ME HONEY HONEY KISS ME''	''SHIRLEY BASSEY''	''MOTOWN & SOUL''	1962	''Shirley Bassey-Kiss Me Honey Honey Kiss Me.mp3''	145},
                {"104	''IF HE TELLS YOU''	''ADAM FAITH''	''CLUB''	1962	''Adam Faith-If He Tells You.mp3''	134},
                {"105	''YOUNG EMOTIONS''	''RICKY NELSON''	''INDIE''	1962	''Ricky Nelson-Young Emotions.mp3''	155},
                {"106	''TELSTAR''	''TORNADOS, THE''	''1990s''	1962	''Tornados, The-Telstar.mp3''	197},
                {"107	''TOMORROW RISING''	''CLIFF RICHARD''	''1950s''	1962	''Cliff Richard-Tomorrow Rising.mp3''	164},
                {"108	''THE LOCO-MOTION''	''LITTLE EVA''	''R&B''	1962	''Little Eva-The Locomotion.mp3''	144},
                {"109	''SHEILA''	''TOMMY ROE''	''1980s''	1962	''Tommy Roe-Sheila.mp3''	130},
                {"110	''MARY JANE''	''DEL SHANNON''	''DISCO''	1962	''Del Shannon-Mary Jane.mp3''	147},
                {"111	''COME OUTSIDE''	''MIKE SARNE & WENDY RICHARD''	''ROCK''	1963	''Mike Sarne & Wendy Richard-Come Outside.mp3''	172},
                {"112	''LOSING YOU''	''BRENDA LEE''	''POP''	1963	''Brenda Lee-Losing You.mp3''	153},
                {"113	''COUNT ON ME''	''JULIE GRANT''	''CLASSICAL''	1963	''Julie Grant-Count On Me.mp3''	136},
                {"114	''OUR DAY WILL COME''	''RUBY & THE ROMANTICS''	''LATIN''	1963	''Ruby & The Romantics-Our Day Will Come.mp3''	153},
                {"115	''WALK LIKE A MAN''	''FOUR SEASONS, THE''	''1980s''	1963	''Four Seasons, The-Walk Like A Man.mp3''	137},
                {"116	''WHAT WILL MY MARY SAY''	''JOHNNY MATHIS''	''MOTOWN & SOUL''	1963	''Johnny Mathis-What Will My Mary Say.mp3''	191},
                {"117	''NOBODY'S DARLIN' BUT MINE''	''FRANK IFIELD''	''2010s''	1963	''Frank Ifield-Nobody's Darlin' But Mine.mp3''	149},
                {"118	''DON'T YOU THINK IT'S TIME''	''MIKE BERRY & OUTLAWS, THE''	''POP''	1963	''Mike Berry & Outlaws, The-Don't You Think It's Time.mp3''	102},
                {"119	''SOME OTHER GUY''	''BIG THREE, THE''	''IRISH''	1963	''Big Three, The-Some Other Guy.mp3''	169},
                {"120	''HE'S SO FINE''	''CHIFFONS, THE''	''LOVE SONGS''	1963	''Chiffons, The-He's So Fine.mp3''	114},
                {"121	''MY WORLD OF BLUE''	''KARL DENVER''	''CLUB''	1964	''Karl Denver-My World Of Blue.mp3''	161},
                {"122	''TELL ME WHEN''	''APPLEJACKS, THE''	''CLUB''	1964	''Applejacks, The-Tell Me When.mp3''	141},
                {"123	''I WONDER''	''CRYSTALS, THE''	''IRISH''	1964	''Crystals, The-I Wonder.mp3''	172},
                {"124	''KING OF KINGS''	''EZZ RECO & THE LAUNCHERS WITH BOYSIE GRANT''	''LIVE''	1964	''Ezz Reco & The Launchers With Boysie Grant-King Of Kings.mp3''	168},
                {"125	''MOVE OVER DARLING''	''DORIS DAY''	''HIP HOP & RAP''	1964	''Doris Day-Move Over Darling.mp3''	217},
                {"126	''A WORLD WITHOUT LOVE''	''PETER & GORDON''	''CLASSICAL''	1964	''Peter & Gordon-A World Without Love.mp3''	159},
                {"127	''POOR ME''	''ADAM FAITH''	''COUNTRY''	1964	''Adam Faith-Poor Me.mp3''	107},
                {"128	''RUNAWAY''	''DEL SHANNON''	''HIP HOP & RAP''	1964	''Del Shannon-Runaway.mp3''	140},
                {"129	''POLK SALAD ANNIE''	''ELVIS PRESLEY''	''R&B''	1964	''Elvis Presley-Polk Salad Annie.mp3''	252},
                {"130	''MY BOY LOLLIPOP''	''MILLIE''	''CLASSICAL''	1964	''Millie-My Boy Lollipop.mp3''	120},
                {"131	''HOW LONG HAS IT BEEN''	''JIM REEVES''	''1950s''	1965	''Jim Reeves-How Long Has It Been.mp3''	150},
                {"132	''POOR MAN'S SON''	''ROCKIN' BERRIES, THE''	''JAZZ''	1965	''Rockin' Berries, The-Poor Man's Son.mp3''	126},
                {"133	''LONG LIVE LOVE''	''SANDIE SHAW''	''ROCK & ROLL''	1965	''Sandie Shaw-Long Live Love.mp3''	160},
                {"134	''(YOU'VE) NEVER BEEN IN LOVE LIKE THIS BEFORE''	''UNIT FOUR PLUS TWO''	''LATIN''	1965	''Unit Four Plus Two-(You've) Never Been in Love Like This Before.mp3''	154},
                {"135	''IKO IKO''	''DIXIE CUPS, THE''	''ROCK & ROLL''	1965	''Dixie Cups, The-Iko Iko.mp3''	122},
                {"136	''WHEN THE MORNING SUN DRIES THE DEW''	''QUIET FIVE, THE''	''CLASSICAL''	1965	''Quiet Five, The-When Morning Sun Dries The Dew.mp3''	197},
                {"137	''TRAINS AND BOATS AND PLANES''	''BURT BACHARACH''	''1970s''	1965	''Burt Bacharach-Trains And Boats And Planes.mp3''	163},
                {"138	''WALK RIGHT BACK''	''EVERLY BROTHERS, THE''	''IRISH''	1965	''Everly Brothers, The-Walk Right Back.mp3''	139},
                {"139	''TRAINS AND BOATS AND PLANES''	''BILLY J KRAMER & DAKOTAS, THE''	''LIVE''	1965	''Billy J Kramer & Dakotas, The-Trains And Boats And Planes.mp3''	170},
                {"140	''MARIE''	''BACHELORS, THE''	''POP''	1965	''Bachelors, The-Marie.mp3''	140},
                {"141	''THINK SOMETIMES ABOUT ME''	''SANDIE SHAW''	''CLASSICAL''	1966	''Sandie Shaw-Think Sometimes About Me.mp3''	142},
                {"142	''YOU CAN'T HURRY LOVE''	''SUPREMES, THE''	''1970s''	1966	''Supremes, The-You Can't Hurry Love.mp3''	162},
                {"143	''MY FRIEND''	''ROY ORBISON''	''HIP HOP & RAP''	1966	''Roy Orbison-My Friend.mp3''	160},
                {"144	''RIP IT UP''	''ELVIS PRESLEY''	''REGGAE''	1966	''Elvis Presley-Rip It Up.mp3''	116},
                {"145	''WALK WITH FAITH IN YOUR HEART''	''BACHELORS, THE''	''R&B''	1966	''Bachelors, The-Walk With Faith In Your Heart.mp3''	170},
                {"146	''UNDER NEW MANAGEMENT''	''BARRON KNIGHTS, THE''	''ROCK & ROLL''	1966	''Barron Knights, The-Under New Management.mp3''	366},
                {"147	''DANCING IN THE STREET''	''MARTHA REEVES & THE VANDELLAS''	''1980s''	1966	''Martha Reeves & The Vandellas-Dancing In The Street.mp3''	160},
                {"148	''HEART''	''RITA PAVONE''	''R&B''	1966	''Rita Pavone-Heart.mp3''	123},
                {"149	''EAST WEST''	''HERMAN'S HERMITS''	''REGGAE''	1966	''Herman's Hermits-East West.mp3''	117},
                {"150	''SUNSHINE SUPERMAN''	''DONOVAN''	''CLUB''	1966	''Donovan-Sunshine Superman.mp3''	300},
                {"151	''GET BACK''	''BEATLES, THE WITH BILLY PRESTON''	''1990s''	1967	''Beatles, The With Billy Preston-Get Back.mp3''	192},
                {"152	''THE OTHER MAN'S GRASS (IS ALWAYS GREENER)''	''PETULA CLARK''	''1950s''	1967	''Petula Clark-The Other Man's Grass (Is Always Greener).mp3''	172},
                {"153	''HANDBAGS AND GLADRAGS''	''CHRIS FARLOWE''	''MOTOWN & SOUL''	1967	''Chris Farlowe-Handbags And Gladrags.mp3''	235},
                {"154	''EMERALD CITY''	''SEEKERS, THE''	''1960s''	1967	''Seekers, The-Emerald City.mp3''	160},
                {"155	''BLACK VELVET BAND''	''DUBLINERS, THE''	''POP''	1967	''Dubliners, The-Black Velvet Band.mp3''	221},
                {"156	''PARADISE LOST''	''HERD, THE''	''R&B''	1967	''Herd, The-Paradise Lost.mp3''	212},
                {"157	''CHAIN OF FOOLS''	''ARETHA FRANKLIN''	''COUNTRY''	1967	''Aretha Franklin-Chain Of Fools.mp3''	165},
                {"158	''I DON'T BLAME YOU AT ALL''	''SMOKEY ROBINSON & MIRACLES, THE''	''1950s''	1967	''Smokey Robinson & The Miracles-I Don't Blame You At All.mp3''	188},
                {"159	''I HEARD IT THROUGH THE GRAPEVINE''	''GLADYS KNIGHT & THE PIPS''	''1960s''	1967	''Gladys Knight & The Pips-I Heard It Through The Grapevine.mp3''	170},
                {"160	''BLUE WORLD''	''MOODY BLUES, THE''	''1960s''	1967	''Moody Blues, The-Blue World.mp3''	313},
                {"161	''BABY COME BACK''	''EQUALS, THE''	''2010s''	1968	''Equals, The-Baby Come Back.mp3''	159},
                {"162	''ALBATROSS''	''FLEETWOOD MAC''	''1950s''	1968	''Fleetwood Mac-Albatross.mp3''	190},
                {"163	''TOY''	''CASUALS, THE''	''COUNTRY''	1968	''Casuals, The-Toy.mp3''	195},
                {"164	''NIGHTS IN WHITE SATIN''	''MOODY BLUES, THE''	''1970s''	1968	''Moody Blues, The-Nights In White Satin.mp3''	264},
                {"165	''SON OF A PREACHER MAN''	''DUSTY SPRINGFIELD''	''ROCK & ROLL''	1968	''Dusty Springfield-Son Of A Preacher Man.mp3''	144},
                {"166	''OB-LA-DI OB-LA-DA''	''MARMALADE''	''1960s''	1968	''Marmalade-Ob La Di Ob La Da.mp3''	179},
                {"167	''ATLANTIS''	''DONOVAN''	''COUNTRY''	1968	''Donovan-Atlantis.mp3''	198},
                {"168	''STOP HER ON SIGHT (S.O.S.)''	''EDWIN STARR''	''DISCO''	1968	''Edwin Starr-Stop Her On Sight (S.O.S).mp3''	137},
                {"169	''SOUL LIMBO''	''BOOKER T & THE M.G,'S''	''LIVE''	1968	''Booker T & The MG,'S-Soul Limbo.mp3''	142},
                {"170	''FOR ONCE IN MY LIFE''	''STEVIE WONDER''	''LOVE SONGS''	1968	''Stevie Wonder-For Once In My Life.mp3''	169},
                {"171	''I'M READY FOR YOUR LOVE''	''MARTHA REEVES & THE VANDELLAS''	''1990s''	1969	''Martha Reeves & The Vandellas-I'm Ready For Love.mp3''	177},
                {"172	''MAGICAL MYSTERY TOUR''	''BEATLES, THE''	''2010s''	1969	''Beatles, The-Magical Mystery Tour.mp3''	171},
                {"173	''MY SENTIMENTAL FRIEND''	''HERMAN'S HERMITS''	''ROCK''	1969	''Herman's Hermits-My Sentimental Friend.mp3''	196},
                {"174	''I'M LIVING IN SHAME''	''DIANA ROSS & SUPREMES, THE''	''LOVE SONGS''	1969	''Diana Ross & Supremes, The-I'm Livin' In Shame.mp3''	175},
                {"175	''AQUARIUS''	''FIFTH DIMENSION, THE''	''ROCK & ROLL''	1969	''Fifth Dimension, The-Aquarius.mp3''	290},
                {"176	''RING OF BRIGHT WATER''	''VAL DOONICAN''	''POP''	1969	''Val Doonican-Ring Of Bright Water.mp3''	177},
                {"177	''THE BOXER''	''SIMON & GARFUNKEL''	''REGGAE''	1969	''Simon & Garfunkel-The Boxer.mp3''	295},
                {"178	''THERE WON'T BE MANY COMING HOME''	''ROY ORBISON''	''INDIE''	1969	''Roy Orbison-There Won't Be Many Coming Home.mp3''	167},
                {"179	''GALVESTON''	''GLEN CAMPBELL''	''1980s''	1969	''Glen Campbell-Galveston.mp3''	167},
                {"180	''I SECOND THAT EMOTION''	''SMOKEY ROBINSON & MIRACLES, THE''	''1960s''	1969	''Smokey Robinson & The Miracles-I Second That Emotion.mp3''	166},
                {"181	''SHE'S NOT YOU''	''ELVIS PRESLEY''	''ROCK''	1970	''Elvis Presley-She's Not You.mp3''	132},
                {"182	''YOU'RE SUCH A GOOD LOOKING WOMAN''	''JOE DOLAN''	''HIP HOP & RAP''	1970	''Joe Dolan-You're Such A Good Looking Woman.mp3''	177},
                {"183	''FAREWELL IS A LONELY SOUND''	''JIMMY RUFFIN''	''1990s''	1970	''Jimmy Ruffin-Farewell Is A Lonely Sound.mp3''	174},
                {"184	''EVERYBODY GET TOGETHER''	''DAVE CLARK FIVE, THE''	''R&B''	1970	''Dave Clark Five, The-Everybody Get Together.mp3''	197},
                {"185	''WHO DO YOU LOVE''	''JUICY LUCY''	''ROCK & ROLL''	1970	''Juicy Lucy-Who Do You Love.mp3''	183},
                {"186	''LET IT BE''	''BEATLES, THE''	''2000s''	1970	''Beatles, The-Let It Be.mp3''	230},
                {"187	''CAN'T HELP FALLING IN LOVE''	''ANDY WILLIAMS''	''1980s''	1970	''Andy Williams-Can't Help Falling In Love.mp3''	192},
                {"188	''PIED PIPER''	''BOB & MARCIA''	''CLUB''	1970	''Bob & Marcia-Pied Piper.mp3''	164},
                {"189	''I'LL GO ON HOPING''	''DES O'CONNOR''	''INDIE''	1970	''Des O'Connor-I'll Go On Hoping.mp3''	144},
                {"190	''MY WOMAN'S MAN''	''DAVE DEE''	''REGGAE''	1970	''Dave Dee-My Woman's Man.mp3''	201},
                {"191	''BANNER MAN''	''BLUE MINK''	''1990s''	1971	''Blue Mink-Banner Man.mp3''	208},
                {"192	''JOY TO THE WORLD''	''THREE DOG NIGHT''	''ROCK''	1971	''Three Dog Night-Joy To The World.mp3''	215},
                {"193	''LAZY BONES''	''JONATHAN KING''	''POP''	1971	''Jonathan King-Lazy Bones.mp3''	136},
                {"194	''CHIRPY CHIRPY CHEEP CHEEP''	''MIDDLE OF THE ROAD''	''LIVE''	1971	''Middle Of The Road-Chirpy Chirpy Cheep Cheep.mp3''	178},
                {"195	''THE TRACKS OF MY TEARS''	''SMOKEY ROBINSON & MIRACLES, THE''	''1970s''	1971	''Smokey Robinson & The Miracles-The Tracks Of My Tears.mp3''	182},
                {"196	''YOUNG GIFTED AND BLACK''	''BOB & MARCIA''	''COUNTRY''	1971	''Bob & Marcia-Young Gifted And Black.mp3''	182},
                {"197	''SHE'S A LADY''	''TOM JONES''	''1970s''	1971	''Tom Jones-She's A Lady.mp3''	173},
                {"198	''CO-CO''	''SWEET, THE''	''2000s''	1971	''Sweet, The-Co Co.mp3''	190},
                {"199	''WHEN YOU ARE KING''	''WHITE PLAINS''	''CLUB''	1971	''White Plains-When You Are A King.mp3''	170},
                {"200	''DON'T LET IT DIE''	''HURRICANE SMITH''	''1960s''	1971	''Hurricane Smith-Don't Let It Die.mp3''	156},
                201	''METAL GURU''	''T REX''	''COUNTRY''	1972	''T Rex-Metal Guru.mp3''	145},
                202	''LADY ELEANOR''	''LINDISFARNE''	''LATIN''	1972	''Lindisfarne-Lady Eleanor.mp3''	252},
                203	''ROOF TOP SINGING''	''NEW WORLD''	''MOTOWN & SOUL''	1972	''New World-Roof Top Singing.mp3''	262},
                204	''VINCENT''	''DON MCLEAN''	''2000s''	1972	''Don McLean-Vincent.mp3''	239},
                205	''DOOBEDOOD'NDOOBE DOOBEDOOD'NDOOBE''	''DIANA ROSS''	''LATIN''	1972	''Diana Ross-Doobedood'ndoobe Doobedood'ndoobe.mp3''	179},
                206	''DON'T LET HIM TOUCH YOU''	''ANGELETTES, THE''	''1990s''	1972	''Angelettes, The-Don't Let Him Touch You.mp3''	203},
                207	''SONG SUNG BLUE''	''NEIL DIAMOND''	''IRISH''	1972	''Neil Diamond-Song Sung Blue.mp3''	192},
                208	''CALIFORNIA MAN''	''MOVE, THE''	''2000s''	1972	''Move, The-California Man.mp3''	216},
                209	''WHAT'S YOUR NAME''	''CHICORY TIP''	''LIVE''	1972	''Chicory Tip-What's Your Name.mp3''	175},
                210	''ROCKIN' ROBIN''	''MICHAEL JACKSON''	''IRISH''	1972	''Michael Jackson-Rockin' Robin.mp3''	153},
                211	''IN THE COUNTRY''	''CLIFF RICHARD & SHADOWS, THE''	''JAZZ''	1973	''Cliff Richard & Shadows, The-In The Country.mp3''	163},
                212	''SISTER JANE''	''NEW WORLD''	''POP''	1973	''New World-Sister Jane.mp3''	219},
                213	''CAN THE CAN''	''SUZI QUATRO''	''1990s''	1973	''Suzi Quatro-Can The Can.mp3''	215},
                214	''SUPERSTITION''	''STEVIE WONDER''	''MOTOWN & SOUL''	1973	''Stevie Wonder-Superstition.mp3''	267},
                215	''ARMED AND EXTREMELY DANGEROUS''	''FIRST CHOICE''	''CLASSICAL''	1973	''First Choice-Armed And Extremely Dangerous.mp3''	167},
                216	''YAH MO BE THERE''	''JAMES INGRAM & MICHAEL MCDONALD''	''INDIE''	1973	''James Ingram & Michael McDonald-Yah Mo Be There.mp3''	272},
                217	''RUBBER BULLETS''	''10CC''	''1950s''	1973	''10CC-Rubber Bullets.mp3''	284},
                218	''WALKING IN THE RAIN''	''PARTRIDGE FAMILY, THE''	''IRISH''	1973	''Partridge Family, The-Walking In The Rain.mp3''	180},
                219	''STUCK IN THE MIDDLE WITH YOU''	''STEALERS WHEEL''	''HIP HOP & RAP''	1973	''Stealers Wheel-Stuck In The Middle With You.mp3''	202},
                220	''VIVA LAS VEGAS''	''ELVIS PRESLEY''	''ROCK & ROLL''	1973	''Elvis Presley-Viva Las Vegas.mp3''	145},
                221	''BAND ON THE RUN''	''PAUL MCCARTNEY & WINGS''	''POP''	1974	''Paul McCartney & Wings-Band On The Run.mp3''	312},
                222	''(WIN, PLACE OR SHOW) SHE'S A WINNER''	''INTRUDERS, THE''	''1990s''	1974	''Intruders, The-(Win, Place Or Show) She's A Winner.mp3''	147},
                223	''BE THANKFUL FOR WHAT YOU'VE GOT''	''WILLIAM DEVAUGHN''	''HIP HOP & RAP''	1974	''William Devaughn-Be Thankful For What You've Got.mp3''	432},
                224	''MY GIRL BILL''	''JIM STAFFORD''	''1960s''	1974	''Jim Stafford-My Girl Bill.mp3''	196},
                225	''THE SIX TEENS''	''SWEET, THE''	''2010s''	1974	''Sweet, The-The Six Teens.mp3''	243},
                226	''WHEN WILL I SEE YOU AGAIN''	''THREE DEGREES, THE''	''REGGAE''	1974	''Three Degrees, The-When Will I See You Again.mp3''	178},
                227	''ZIP GUN BOOGIE''	''T REX''	''DISCO''	1974	''T Rex-Zip Gun Boogie.mp3''	200},
                228	''TONIGHT''	''RUBETTES, THE''	''IRISH''	1974	''Rubettes, The-Tonight.mp3''	211},
                229	''YOU MAKE ME FEEL BRAND NEW''	''STYLISTICS, THE''	''ROCK''	1974	''Stylistics, The-You Make Me Feel Brand New.mp3''	340},
                230	''THEME FROM TUBULAR BELLS''	''MIKE OLDFIELD''	''R&B''	1974	''Mike Oldfield-Theme From Tubular Bells.mp3''	258},
                231	''HOLD ME CLOSE''	''DAVID ESSEX''	''ROCK''	1975	''David Essex-Hold Me Close.mp3''	232},
                232	''OUT OF TIME''	''DAN MCCAFFERTY''	''MOTOWN & SOUL''	1975	''Dan McCafferty-Out Of Time.mp3''	235},
                233	''FATTIE BUM BUM''	''DIVERSIONS, THE''	''REGGAE''	1975	''Diversions, The-Fatty Bum Bum.mp3''	172},
                234	''WHO LOVES YOU''	''FOUR SEASONS, THE''	''1990s''	1975	''Four Seasons, The-Who Loves You.mp3''	246},
                235	''DO IT ANYWAY YOU WANNA''	''PEOPLE'S CHOICE''	''ROCK''	1975	''People's Choice-Do It Anyway You Wanna.mp3''	184},
                236	''OUT OF TIME''	''ROLLING STONES, THE''	''1990s''	1975	''Rolling Stones, The-Out Of Time.mp3''	226},
                237	''SOS''	''ABBA''	''2000s''	1975	''Abba-SOS.mp3''	202},
                238	''CHICK-A-BOOM''	''53RD AND A 3RD FT THE SOUND OF SHAG''	''1980s''	1975	''53rd And A 3rd ft The Sound Of Shag-Chick-A-Boom.mp3''	177},
                239	''NAPPY LOVE''	''GOODIES, THE''	''CLUB''	1975	''Goodies, The-Nappy Love.mp3''	219},
                240	''FEELINGS''	''MORRIS ALBERT''	''1990s''	1975	''Morris Albert-Feelings.mp3''	218},
                241	''REQUIEM''	''SLIK''	''CLASSICAL''	1976	''Slik-Requiem.mp3''	295},
                242	''MY RESISTANCE IS LOW''	''ROBIN SARSTEDT''	''ROCK & ROLL''	1976	''Robin Sarstedt-My Resistance Is Low.mp3''	167},
                243	''MIDNIGHT TRAIN TO GEORGIA''	''GLADYS KNIGHT & THE PIPS''	''1970s''	1976	''Gladys Knight & The Pips-Midnight Train To Georgia.mp3''	283},
                244	''TRACKS OF MY TEARS''	''LINDA RONSTADT''	''IRISH''	1976	''Linda Ronstadt-Tracks Of My Tears.mp3''	193},
                245	''MANCHESTER UNITED''	''MANCHESTER UNITED FC''	''LOVE SONGS''	1976	''Manchester United FC-Manchester United.mp3''	155},
                246	''SILLY LOVE SONGS''	''WINGS''	''LATIN''	1976	''Wings-Silly Love Songs.mp3''	354},
                247	''COMBINE HARVESTER''	''WURZELS, THE''	''R&B''	1976	''Wurzels, The-Combine Harvester.mp3''	182},
                248	''SHAKE IT DOWN''	''MUD''	''2010s''	1976	''Mud-Shake It Down.mp3''	208},
                249	''THIS IS IT''	''MELBA MOORE''	''DISCO''	1976	''Melba Moore-This Is It.mp3''	211},
                250	''9 TO 5''	''DOLLY PARTON''	''1960s''	1976	''Dolly Parton-9 tTo 5.mp3''	166},
                251	''UNDERCOVER ANGEL''	''ALAN O'DAY''	''R&B''	1977	''Alan O'Day-Undercover Angel.mp3''	214},
                252	''I FEEL LOVE''	''DONNA SUMMER''	''2010s''	1977	''Donna Summer-I Feel Love.mp3''	228},
                253	''ANGELO''	''BROTHERHOOD OF MAN''	''ROCK''	1977	''Brotherhood Of Man-Angelo.mp3''	195},
                254	''STRAWBERRY LETTER 23''	''BROTHERS JOHNSON, THE''	''ROCK & ROLL''	1977	''Brothers Johnson, The-Strawberry Letter 23.mp3''	300},
                255	''PRETTY VACANT''	''SEX PISTOLS, THE''	''1980s''	1977	''Sex Pistols, The-Pretty Vacant.mp3''	194},
                256	''DREAMS''	''FLEETWOOD MAC''	''1960s''	1977	''Fleetwood Mac-Dreams.mp3''	255},
                257	''THREE RING CIRCUS''	''BARRY BIGGS''	''CLASSICAL''	1977	''Barry Biggs-Three Ring Circus.mp3''	242},
                258	''FALCON''	''RAH BAND, THE''	''1980s''	1977	''Rah Band, The-Falcon.mp3''	376},
                259	''HEAVEN ON THE 7TH FLOOR''	''PAUL NICHOLAS''	''R&B''	1977	''Paul Nicholas-Heaven On The 7th Floor.mp3''	167},
                260	''ROADRUNNER''	''JONATHAN RICHMAN''	''R&B''	1977	''Jonathan Richman-Roadrunner.mp3''	245},
                261	''BROWN GIRL IN THE RING''	''BONEY M''	''IRISH''	1978	''Boney M-Brown Girl In The Ring.mp3''	188},
                262	''RIVERS OF BABYLON''	''BONEY M''	''LATIN''	1978	''Boney M-Rivers Of Babylon.mp3''	240},
                263	''BECAUSE THE NIGHT''	''PATTI SMITH GROUP, THE''	''LOVE SONGS''	1978	''Patti Smith Grorp, The-Because The Night.mp3''	186},
                264	''COME TO ME!''	''RUBY WINTERS''	''LOVE SONGS''	1978	''Ruby Winters-Come To Me!.mp3''	191},
                265	''LOVE IS IN THE AIR''	''JOHN PAUL YOUNG''	''LATIN''	1978	''John Paul Young-Love Is In The Air.mp3''	205},
                266	''SHAME''	''ALAN PRICE''	''REGGAE''	1978	''Alan Price-Shame.mp3''	167},
                267	''WHAT A WASTE''	''IAN DURY & THE BLOCKHEADS''	''1970s''	1978	''Ian Dury & The Blockheads-What A Waste!.mp3''	206},
                268	''THE DAY THE WORLD TURNED DAY-GLO''	''X-RAY SPEX''	''REGGAE''	1978	''X Ray Spex-The Day The World Turned Day Glo.mp3''	171},
                269	''DANCE A LITTLE BIT CLOSER''	''CHARO & THE SALSOUL ORCHESTRA''	''IRISH''	1978	''Charo & The Salsoul Orchestra-Dance A Little Bit Closer.mp3''	270},
                270	''NICE 'N' SLEAZY''	''STRANGLERS, THE''	''R&B''	1978	''Stranglers, The-Nice 'N' Sleazy.mp3''	194},
                271	''SHAKE YOUR BODY (DOWN TO THE GROUND)''	''JACKSONS, THE (JACKSON 5, THE)''	''2010s''	1979	''Jacksons, The (Jackson 5, The)-Shake Your Body (Down To The Ground).mp3''	227},
                272	''GIVE ME BACK ME BRAIN''	''DUFFO''	''R&B''	1979	''Duffo-Give Me Back Me Brain.mp3''	162},
                273	''IMAGINATION''	''ROCKY SHARPE & THE REPLAYS''	''1960s''	1979	''Rocky Sharpe & The Replays-Imagination.mp3''	159},
                274	''LET'S FLY AWAY''	''VOYAGE''	''1950s''	1979	''Voyage-Let's Fly Away.mp3''	299},
                275	''SHA LA LA MEANS I LOVE YOU''	''BARRY WHITE''	''JAZZ''	1979	''Barry White-Sha la la Means I Love You.mp3''	449},
                276	''THE STAIRCASE (MYSTERY)''	''SIOUXSIE & THE BANSHEES''	''ROCK & ROLL''	1979	''Siouxsie & The Banshees-The Staircase (Mystery).mp3''	188},
                277	''SOME GIRLS''	''RACEY''	''1960s''	1979	''Racey-Some Girls.mp3''	204},
                278	''THE LOGICAL SONG''	''SUPERTRAMP''	''1960s''	1979	''Supertramp-The Logical Song.mp3''	248},
                279	''HAVEN'T STOPPED DANCING YET''	''GONZALEZ''	''2010s''	1979	''Gonzalez-Haven't Stopped Dancing Yet.mp3''	254},
                280	''VIDEO KILLED THE RADIO STAR''	''BUGGLES, THE''	''1960s''	1979	''Buggles, The-Video Killed The Radio Star.mp3''	206},
                281	''THE NIGHT, THE WINE AND THE ROSES''	''LIQUID GOLD''	''LIVE''	1980	''Liquid Gold-The Night, The Wine & The Roses.mp3''	263},
                282	''INHERIT THE WIND''	''WILTON FELDER''	''IRISH''	1980	''Wilton Fender-Inherit The Wind.mp3''	228},
                283	''SMOKE ON THE WATER (LIVE) (NEW, LIVE & RARE VOL.3) (EP)''	''DEEP PURPLE''	''CLUB''	1980	''Deep Purple-Smoke On The Water (New, Live & Rare Vol.3 (EP).mp3''	223},
                284	''THE CRUNCH''	''RAH BAND, THE''	''1990s''	1980	''Rah Band, The-The Crunch.mp3''	156},
                285	''CELEBRATION''	''KOOL & THE GANG''	''CLASSICAL''	1980	''Kool & The Gang-Celebration.mp3''	219},
                286	''THE TIDE IS HIGH''	''BLONDIE''	''1970s''	1980	''Blondie-The Tide Is High.mp3''	229},
                287	''THE SAME OLD SCENE''	''ROXY MUSIC''	''DISCO''	1980	''Roxy Music-Same Old Scene.mp3''	239},
                288	''(JUST LIKE) STARTING OVER''	''JOHN LENNON''	''IRISH''	1980	''John Lennon-(Just Like) Starting Over.mp3''	237},
                289	''BABY JANE''	''ROD STEWART''	''1970s''	1980	''Rod Stewart-Baby Jane.mp3''	283},
                290	''MILES AWAY''	''JOHN FOXX''	''INDIE''	1980	''John Foxx-Miles Away.mp3''	194},
                291	''OSSIE'S DREAM (SPURS ARE ON THEIR WAY TO WEMBLEY)''	''TOTTENHAM HOTSPUR FC''	''2000s''	1981	''Tottenham Hotspur FC-Ossie's Dream (Spurs Are On Their Way To Wembley).mp3''	243},
                292	''WHEN YOU WERE SWEET SIXTEEN''	''FUREYS, THE & DAVEY ARTHUR''	''LIVE''	1981	''Fureys, The & Davey Arthur-When You Were Sweet Sixteen.mp3''	248},
                293	''EVERY LITTLE THING SHE DOES IS MAGIC''	''POLICE, THE''	''HIP HOP & RAP''	1981	''Police, The-Every Little Thing She Does Is Magic.mp3''	261},
                294	''LET'S GROOVE''	''EARTH WIND & FIRE ''	''JAZZ''	1981	''Earth Wind & Fire-Let's Groove.mp3''	247},
                295	''WHY DO FOOLS FALL IN LOVE''	''DIANA ROSS''	''LIVE''	1981	''Diana Ross-Why Do Fools Fall In Love.mp3''	288},
                296	''I WANT TO BREAK FREE''	''QUEEN''	''ROCK & ROLL''	1981	''Queen-I Want To Break Free.mp3''	258},
                297	''PRETTY IN PINK''	''PSYCHEDELIC FURS, THE''	''LOVE SONGS''	1981	''Psychedelic Furs, The-Pretty In Pink.mp3''	281},
                298	''ONCE IN A LIFETIME''	''TALKING HEADS''	''IRISH''	1981	''Talk Talk-It's My Life.mp3''	231},
                299	''JOLENE''	''DOLLY PARTON''	''1970s''	1981	''Dolly Parton-Jolene.mp3''	160},
                300	''IT'S GOING TO HAPPEN''	''UNDERTONES, THE''	''IRISH''	1981	''Undertones, The-It's Going To Happen.mp3''	218},
                301	''SAVE A PRAYER''	''DURAN DURAN''	''ROCK''	1982	''Duran Duran-Save A Prayer.mp3''	226},
                302	''LOVE PLUS ONE''	''HAIRCUT 100''	''LOVE SONGS''	1982	''Haircut 100-Love Plus One.mp3''	212},
                303	''UNDER PRESSURE''	''QUEEN & DAVID BOWIE''	''REGGAE''	1982	''Queen & David Bowie-Under Pressure.mp3''	244},
                304	''GIVE ME YOUR HEART TONIGHT''	''SHAKIN' STEVENS''	''2000s''	1982	''Shakin' Stevens-Give Me Your Heart Tonight.mp3''	185},
                305	''WHERE DID WE GO WRONG''	''LIQUID GOLD''	''LOVE SONGS''	1982	''Liquid Gold-Where Did We Go Wrong.mp3''	235},
                306	''TWO HEARTS TOGETHER''	''ORANGE JUICE''	''2010s''	1982	''Orange Juice-Two Hearts Together.mp3''	191},
                307	''UNIFORMS''	''PETE TOWNSHEND''	''ROCK & ROLL''	1982	''Pete Townshend-Uniforms.mp3''	223},
                308	''RUFF MIX''	''WONDER DOG''	''POP''	1982	''Wonder Dog-Ruff Mix.mp3''	207},
                309	''YOU'VE GOT ANOTHER THING COMIN'''	''JUDAS PRIEST''	''ROCK''	1982	''Judas Priest-You've Got Another Thing Comin'.mp3''	311},
                310	''I KNOW THERE'S SOMRTHING GOING ON''	''FRIDA''	''IRISH''	1982	''Frida-I Know There's Something Going On.mp3''	185},
                311	''WINGS OF A DOVE''	''MADNESS''	''DISCO''	1983	''Madness-Wings Of A Dove.mp3''	178},
                312	''(I CAN'T HELP) FALLING IN LOVE WITH YOU''	''UB40''	''DISCO''	1983	''UB40-(I Can't Help) Falling In Love With You.mp3''	204},
                313	''VOICES IN THE SKY''	''MOODY BLUES, THE''	''1980s''	1983	''Moody Blues, The-Voices In the Sky.mp3''	211},
                314	''IT'S RAINING MEN''	''WEATHER GIRLS, THE''	''1980s''	1983	''Weather Girls, The-It's Raining Men.mp3''	212},
                315	''BIG TIME''	''RICK JAMES''	''DISCO''	1983	''Rick James-Big Time.mp3''	388},
                316	''DON'T YOU GET SO MAD''	''JEFFREY OSBORNE''	''ROCK''	1983	''Jeffrey Osborne-Don't You Get So Mad.mp3''	228},
                317	''NATIVE LAND''	''EVERYTHING BUT THE GIRL''	''LATIN''	1983	''Everything But The Girl-Native Land.mp3''	187},
                318	''PASSION''	''ROD STEWART''	''1980s''	1983	''Rod Stewart-Passion.mp3''	332},
                319	''ROCK OF AGES''	''DEF LEPPARD''	''COUNTRY''	1983	''Def Leppard-Rock Of Ages.mp3''	248},
                320	''STREET OF DREAMS''	''RAINBOW''	''2000s''	1983	''Rainbow-Street Of Dreams.mp3''	265},
                321	''AGAINST ALL ODDS (TAKE A LOOK AT ME NOW)''	''PHIL COLLINS''	''1970s''	1984	''Phil Collins-Against All Odds (Take A Look At Me Now).mp3''	205},
                322	''THE CATERPILLAR''	''CURE, THE''	''LATIN''	1984	''Cure, The-The Caterpillar.mp3''	220},
                323	''GIVE ME TONIGHT''	''SHANNON''	''INDIE''	1984	''Shannon-Give Me Tonight.mp3''	367},
                324	''IT'S MY LIFE''	''TALK TALK''	''INDIE''	1984	''Take That-Patience.mp3''	202},
                325	''AUTOMATIC''	''POINTER SISTERS, THE''	''DISCO''	1984	''Pointer Sisters, The-Automatic.mp3''	241},
                326	''SOMEBODY'S WATCHING ME''	''ROCKWELL''	''1950s''	1984	''Rockwell-Somebody's Watching Me.mp3''	238},
                327	''SOMETIMES''	''ELAINE PAIGE''	''INDIE''	1984	''Elaine Paige-Sometimes.mp3''	155},
                328	''IN THE NIGHT''	''BARBARA DICKSON''	''ROCK''	1984	''Barbara Dickson-In The Night.mp3''	229},
                329	''BACKCHAT''	''QUEEN''	''ROCK''	1984	''Queen-Backchat.mp3''	248},
                330	''LOCOMOTION''	''ORCHESTRAL MANOEUVRES IN THE DARK''	''CLUB''	1984	''Orchestral Manoeuvres In The Dark-Locomotion.mp3''	223},
                331	''FEEL SO REAL''	''STEVE ARRINGTON''	''IRISH''	1985	''Steve Arrington-Feel So Real.mp3''	237},
                332	''NO REST''	''NEW MODEL ARMY''	''LOVE SONGS''	1985	''New Model Army-No Rest.mp3''	231},
                333	''I WANT YOUR LOVIN' (JUST A LITTLE BIT)''	''CURTIS HAIRSTON''	''LIVE''	1985	''Curtis Haiston-I Want Your Lovin' (Just A Little Bit).mp3''	330},
                334	''WALK LIKE A MAN''	''DIVINE''	''ROCK''	1985	''Divine-Walk Like A Man.mp3''	324},
                335	''DON'T FALL IN LOVE (I SAID)''	''TOYAH''	''2010s''	1985	''Toyah-Don't Fall In Love (I Said).mp3''	228},
                336	''RAGE TO LOVE''	''KIM WILDE''	''R&B''	1985	''Kim Wilde-Rage To Love.mp3''	260},
                337	''DESTINY CALLING''	''JAMES''	''IRISH''	1985	''James-Destiny Calling.mp3''	237},
                338	''LOVE DON'T LIVE HERE ANYMORE''	''JIMMY NAIL''	''1980s''	1985	''Jimmy Nail-Love Don't Live Here Anymore.mp3''	209},
                339	''WALKING ON SUNSHINE''	''KATRINA & THE WAVES''	''HIP HOP & RAP''	1985	''Katrina & The Waves-Walking On Sunshine.mp3''	219},
                340	''I WONDER IF I TAKE YOU HOME''	''LISA LISA & CULT JAM WITH FULL FORCE''	''MOTOWN & SOUL''	1985	''Lisa Lisa & Cult Jam With Full Force-I Wonder If I Take You Home.mp3''	203},
                341	''WONDERFUL WORLD (1986)''	''SAM COOKE''	''ROCK''	1986	''Sam Cooke-Wonderful World.mp3''	129},
                342	''HELLO DARLING''	''TIPPA IRIE''	''1950s''	1986	''Tippa Irie-Hello Darlin'.mp3''	214},
                343	''GALVESTON BAY''	''LONNIE HILL''	''JAZZ''	1986	''Lonnie Hill-Galveston Bay.mp3''	340},
                344	''ROCK ME AMADEUS''	''FALCO''	''R&B''	1986	''Falco-Rock Me Amadeus.mp3''	200},
                345	''E = MC2''	''BIG AUDIO DYNAMITE''	''INDIE''	1986	''Big Audio Dynamite-E=MC2.mp3''	269},
                346	''WHEN I THINK OF YOU''	''JANET JACKSON''	''LOVE SONGS''	1986	''Janet Jackson-When I Think Of You.mp3''	235},
                347	''CANDY''	''CAMEO''	''2000s''	1986	''Cameo-Candy.mp3''	247},
                348	''TALK TO ME''	''STEVIE NICKS''	''LIVE''	1986	''Stevie Nicks-Talk To Me.mp3''	250},
                349	''AFTER ALL THESE YEARS''	''FOSTER & ALLEN''	''1970s''	1986	''Foster & Allen-After All These Years.mp3''	234},
                350	''CAN'T HELP FALLING IN LOVE WITH YOU''	''LICK THE TINS''	''DISCO''	1986	''Lick The Tins-Can't Help Falling In Love.mp3''	187},
                351	''HUNG UP''	''MADONNA''	''HIP HOP & RAP''	1987	''Madonna-Hung Up.mp3''	198},
                352	''LA BAMBA''	''LOS LOBOS''	''1960s''	1987	''Los Lobos-La Bamba.mp3''	176},
                353	''EVERYTHING I OWN''	''BOY GEORGE''	''LOVE SONGS''	1987	''Boy George-Everything I Own.mp3''	236},
                354	''PASS THE MIC''	''BEASTIE BOYS, THE''	''1970s''	1987	''Beastie Boys, The-Pass The Mic.mp3''	257},
                355	''SPY IN THE HOUSE OF LOVE''	''WAS (NOT WAS)''	''1960s''	1987	''Was (Not Was)-Spy In The House Of Love.mp3''	256},
                356	''TOM'S DINER''	''SUZANNE VEGA''	''1980s''	1987	''Suzanne Vega-Tom's Diner.mp3''	129},
                357	''DRAGNET''	''ART OF NOISE, THE''	''DISCO''	1987	''Art Of Noise, The-Dragnet.mp3''	177},
                358	''I FOUND LOVIN'''	''STEVE WALSH''	''LATIN''	1987	''Steve Walsh-I Found Lovin'.mp3''	208},
                359	''REBEL WITHOUT A PAUSE''	''PUBLIC ENEMY''	''MOTOWN & SOUL''	1987	''Public Enemy-Rebel Without A Pause.mp3''	258},
                360	''WE'VE GOT TONIGHT''	''BOB SEGER & THE SILVER BULLET BAND''	''DISCO''	1987	''Bob Seger & The Silver Bullet-We've Got Tonight.mp3''	279},
                361	''DIGNITY''	''DEACON BLUE''	''1970s''	1988	''Deacon Blue-Dignity.mp3''	241},
                362	''WALK IN THE NIGHT''	''PAUL HARDCASTLE''	''MOTOWN & SOUL''	1988	''Paul Hardcastle-Walk In The Night.mp3''	238},
                363	''AIRHEAD''	''THOMAS DOLBY''	''R&B''	1988	''Thomas Dolby-Airhead.mp3''	309},
                364	''WHAT A WONDERFUL WORLD''	''LOUIS ARMSTRONG''	''2000s''	1988	''Louis Armstrong-What A Wonderful World.mp3''	140},
                365	''GOOD TIMES (1988)''	''CHIC''	''LATIN''	1988	''Chic-Good Times.mp3''	219},
                366	''I WANT YOUR LOVE''	''TRANSVISION VAMP''	''COUNTRY''	1988	''Transvision Vamp-I Want Your Love.mp3''	213},
                367	''SHOVE IT''	''CROSS''	''HIP HOP & RAP''	1988	''Cross-Shove It.mp3''	208},
                368	''PERFECT''	''FAIRGROUND ATTRACTION''	''MOTOWN & SOUL''	1988	''Fairground Attraction-Perfect.mp3''	217},
                369	''CONSTANT CRAVING''	''KD LANG''	''LIVE''	1988	''KD Lang-Constant Craving.mp3''	225},
                370	''HOLD BACK THE RIVER''	''JAMES BAY''	''CLUB''	1988	''James Bay-Hold Back The River.mp3''	235},
                371	''HERE COMES YOUR MAN''	''PIXIES, THE''	''2000s''	1989	''Pixies, The-Here Comes Your Man.mp3''	196},
                372	''AND A BANG ON THE EAR''	''WATERBOYS, THE''	''1970s''	1989	''Waterboys, The-And A Bang On The Ear.mp3''	445},
                373	''DAYS''	''KIRSTY MACCOLL''	''ROCK & ROLL''	1989	''Kirsty MacColl-Days.mp3''	177},
                374	''CHA CHA HEELS''	''EARTHA KITT & BRONSKI BEAT''	''1950s''	1989	''Eartha Kitt & Bronski Beat-Cha Cha Heels.MP3''	400},
                375	''KATHLEEN''	''ROACHFORD''	''REGGAE''	1989	''Roachford-Kathleen.mp3''	260},
                376	''YE KE YE KE''	''CLUBHOUSE''	''1970s''	1989	''Clubhouse-Light My Fire.mp3''	205},
                377	''BEDS ARE BURNING''	''MIDNIGHT OIL''	''LOVE SONGS''	1989	''Midnight Oil-Beds Are Burning.mp3''	255},
                378	''YOU'RE BEAUTIFUL''	''JAMES BLUNT''	''COUNTRY''	1989	''James Blunt-You're Beautiful.mp3''	204},
                379	''SIT DOWN''	''JAMES''	''LATIN''	1989	''James-Sit Down.mp3''	247},
                380	''BAMBOLERO''	''GYPSY KINGS, THE''	''LATIN''	1989	''Gypsy Kings, The-Bamboleo.mp3''	203},
                381	''COME TOGETHER''	''PRIMAL SCREAM''	''LATIN''	1990	''Primal Scream-Come Together.mp3''	293},
                382	''HEART LIKE A WHEEL''	''HUMAN LEAGUE, THE''	''1950s''	1990	''Human League, The-Heart Like A Wheel.mp3''	270},
                383	''RELEASE ME''	''WILSON PHILLIPS''	''INDIE''	1990	''Wilson Phillips-Release Me.mp3''	295},
                384	''NEXT TO YOU''	''ASWAD''	''HIP HOP & RAP''	1990	''Aswad-Next To You.mp3''	222},
                385	''BONITA APPLEBUM''	''A TRIBE CALLED QUEST''	''1990s''	1990	''A Tribe Called Quest-Bonita Applebum.mp3''	215},
                386	''LA SERENISSIMA''	''DNA''	''1950s''	1990	''DNA-La Serenissima.mp3''	223},
                387	''CLUB AT THE END OF THE STREET''	''ELTON JOHN''	''LIVE''	1990	''Elton John-Club At The End Of The Street.mp3''	291},
                388	''THE RIGHT COMBINATION''	''SEIKO MATSUDA & DONNIE WAHLBERG''	''1970s''	1990	''Seiko Matsuda & Donnie Wahlberg-The Right Combination.mp3''	267},
                389	''PINK CHAMPAGNE''	''SHAKIN' STEVENS''	''2010s''	1990	''Shakin' Stevens-Pink Champagne.mp3''	200},
                390	''GROOVE IS IN THE HEART''	''DEEE LITE''	''2010s''	1990	''Deee Lite-Groove Is In The Heart.mp3''	233},
                391	''SET ADRIFT ON MEMORY BLISS''	''PM DAWN''	''COUNTRY''	1991	''PM Dawn-Set Adrift On Memory Bliss.mp3''	251},
                392	''GUARANTEED''	''LEVEL 42''	''COUNTRY''	1991	''Level 42-Guaranteed.mp3''	231},
                393	''NEAR WILD HEAVEN''	''R.E.M.''	''1950s''	1991	''R.E.M.-Near Wild Heaven.mp3''	199},
                394	''ROMANTIC''	''KARYN WHITE''	''COUNTRY''	1991	''Karyn White-Romantic.mp3''	247},
                395	''LOVE… THY WILL BE DONE''	''MARTIKA''	''2000s''	1991	''Martika-Love...Thy Will Be Done.mp3''	302},
                396	''CHARLY''	''PRODIGY, THE''	''LIVE''	1991	''Prodigy, The-Charly.mp3''	236},
                397	''HAPPY TOGETHER''	''JASON DONOVAN''	''POP''	1991	''Jason Donovan-Happy Together.mp3''	189},
                398	''SUNSHINE ON A RAINY DAY (1991)''	''ZOE''	''ROCK''	1991	''Zoe-Sunshine On A Rainy Day.mp3''	285},
                399	''INSANITY''	''OCEANIC''	''1980s''	1991	''Oceanic-Insanity.mp3''	242},
                400	''WHAT CAN YOU DO FOR ME''	''UTAH SAINTS''	''MOTOWN & SOUL''	1991	''Utah Saints-What Can You Do For Me.mp3''	219},
                401	''MY LOVIN'''	''EN VOGUE''	''1990s''	1992	''En Vogue-My Lovin'.mp3''	282},
                402	''VIVA LAS VEGAS''	''ZZ TOP''	''ROCK & ROLL''	1992	''ZZ Top-Viva Las Vegas.mp3''	246},
                403	''COUNTRY HOUSE''	''BLUR''	''2000s''	1992	''Blur-Country House.mp3''	233},
                404	''FOOLS GOLD''	''STONE ROSES, THE''	''POP''	1992	''Stone Roses, The-Fools Gold.mp3''	258},
                405	''AM I THE SAME GIRL''	''SWING OUT SISTER''	''CLUB''	1992	''Swing Out Sister-Am I The Same Girl.mp3''	241},
                406	''SHE'S ON IT''	''BEASTIE BOYS, THE''	''1980s''	1992	''Beastie Boys, The-She's On It.mp3''	260},
                407	''MUSIC MOVES YOU''	''BLAME''	''JAZZ''	1992	''Blame-Music Moves You.mp3''	236},
                408	''DIVINE THING''	''SOUP DRAGONS, THE''	''2000s''	1992	''Soup Dragons, The-Divine Thing.mp3''	231},
                409	''IT'S NOT A LOVE THING''	''GEOFFREY WILLIAMS''	''ROCK & ROLL''	1992	''Geoffrey Williams-It's Not A Love Thing.mp3''	235},
                410	''STRAIGHT TO YOU''	''NICK CAVE & THE BAD SEEDS''	''R&B''	1992	''Nick Cave & The Bad Seeds-Straight To You.mp3''	275},
                411	''RED RED WINE''	''UB40''	''HIP HOP & RAP''	1993	''UB40-Red Red Wine.mp3''	182},
                412	''I DON'T WANNA FIGHT''	''TINA TURNER''	''CLASSICAL''	1993	''Tina Turner-I Don't Wanna Fight.mp3''	266},
                413	''JUMP AROUND''	''HOUSE OF PAIN''	''ROCK & ROLL''	1993	''House Of Pain-Jump Around.mp3''	212},
                414	''TOP O' THE MORNING TO YA''	''HOUSE OF PAIN''	''CLASSICAL''	1993	''House Of Pain-Top O' The Morning to Ya.mp3''	216},
                415	''BELIEVE''	''LENNY KRAVITZ''	''2010s''	1993	''Lenny Kravitz-Believe.mp3''	295},
                416	''CREEP''	''RADIOHEAD''	''1970s''	1993	''Radiohead-Creep.mp3''	235},
                417	''SIMPLE LIFE''	''ELTON JOHN''	''LOVE SONGS''	1993	''Elton John-Simple Life.mp3''	385},
                418	''THESE THINGS ARE WORTH FIGHTING FOR''	''GARY CLAIL ON-U SOUND SYSTEM''	''REGGAE''	1993	''Gary Clail & On U Sound System-These Things Are Worth Fighting For.mp3''	270},
                419	''I'M GONNA SOOTHE YOU''	''MARIA MCKEE''	''POP''	1993	''Maria McKee-I'm Gonna Soothe You.mp3''	218},
                420	''TURN OFF''	''MILLTOWN BROTHERS''	''JAZZ''	1993	''Milltown Brothers-Turn Off.mp3''	212},
                421	''MMM MMM MMM MMM''	''CRASH TEST DUMMIES''	''DISCO''	1994	''Crash Test Dummies-MMM MMM MMM MMM.mp3''	227},
                422	''SWEETS FOR MY SWEET''	''CJ LEWIS''	''ROCK & ROLL''	1994	''CJ Lewis-Sweets For My Sweet.mp3''	203},
                423	''100 PER CENT PURE LOVE''	''CRYSTAL WATERS''	''INDIE''	1994	''Crystal Waters-100 Per Cent Pure Love.mp3''	181},
                424	''I'LL STAND BY YOU''	''PRETENDERS, THE''	''IRISH''	1994	''Pretenders, The-I'll Stand By You.mp3''	240},
                425	''STAND BY ME''	''OASIS''	''1950s''	1994	''Oasis-Stand By Me.mp3''	356},
                426	''HIGH ON A HAPPY VIBE''	''URBAN COOKIE COLLECTIVE''	''LIVE''	1994	''Urban Cookie Collective-High On A Happy Vibe.mp3''	187},
                427	''PRESSURE''	''DRIZABONE''	''LIVE''	1994	''Drizabone-Pressure.mp3''	318},
                428	''IT'S NOT MY FAULT''	''BLINK''	''1960s''	1994	''Blink-It's Not My Fault.mp3''	222},
                429	''LIGHT MY FIRE''	''CLUBHOUSE''	''1980s''	1994	''Clubhouse-Ye ke Ye ke.mp3''	226},
                430	''I WANNA BE THE ONLY ONE''	''ETERNAL FT BEBE WINANS''	''COUNTRY''	1994	''Eternal ft Bebe Winans-I Wanna Be The Only One.mp3''	213},
                431	''POPSCENE''	''BLUR''	''2010s''	1995	''Blur-Popscene.mp3''	195},
                432	''SUPERSONIC''	''OASIS''	''1960s''	1995	''Oasis-Supersonic.mp3''	275},
                433	''EVERYBODY''	''CLOCK''	''1960s''	1995	''Clock-Everybody.mp3''	214},
                434	''OH FATHER''	''MADONNA''	''INDIE''	1995	''Madonna-Oh Father.mp3''	269},
                435	''JUST WHEN YOU'RE THINKIN' THINGS OVER''	''CHARLATANS, THE''	''INDIE''	1995	''Charlatans, The-Just When You're Thinkin' Things Over.mp3''	291},
                436	''HAPPY JUST TO BE WITH YOU''	''MICHELLE GAYLE''	''LATIN''	1995	''Michelle Gayle-Happy Just to Be With You.mp3''	234},
                437	''VENUS AS A BOY''	''BJORK''	''REGGAE''	1995	''Bjork-Venus As a Boy.mp3''	281},
                438	''MANDINKA''	''SINEAD O'CONNOR''	''ROCK''	1995	''Sinead O'Connor-Mandinka.mp3''	228},
                439	''SHOUT (IT OUT)''	''LOUCHIE LOU & MICHIE ONE''	''1980s''	1995	''Louchie Lou & Michie One-Shout (It Out).mp3''	209},
                440	''MOON''	''VIRUS''	''JAZZ''	1995	''Virus-Moon.mp3''	487},
                441	''STUPID GIRL''	''GARBAGE''	''POP''	1996	''Garbage-Stupid Girl.mp3''	259},
                442	''CHASING RAINBOWS''	''SHED SEVEN''	''LOVE SONGS''	1996	''Shed Seven-Chasing Rainbows.mp3''	263},
                443	''BEING BRAVE''	''MENSWEAR''	''HIP HOP & RAP''	1996	''Menswear-Being Brave.mp3''	284},
                444	''WHO'S THAT GIRL''	''MADONNA''	''IRISH''	1996	''Madonna-Who's That Girl.mp3''	240},
                445	''BREATHE (A LITTLE DEEPER)''	''BLAMELESS''	''1950s''	1996	''Blameless-Breathe (A Little Deeper).mp3''	228},
                446	''DON'T LEAVE''	''FAITHLESS''	''POP''	1996	''Faithless-Don't Leave.mp3''	240},
                447	''LOVE U MORE''	''SUNSCREEM''	''1950s''	1996	''Sunscreem-Love U More.mp3''	251},
                448	''DEMOCRACY''	''KILLING JOKE''	''POP''	1996	''Killing Joke-Democracy.mp3''	219},
                449	''MOVING ON UP (ON THE RIGHT SIDE)''	''BEVERLEY KNIGHT''	''COUNTRY''	1996	''Beverley Knight-Moving On Up (On The Right Side).mp3''	253},
                450	''ONE MORE GOOD NIGHT WITH THE BOYS''	''TASMIN ARCHER''	''LATIN''	1996	''Talking Heads-Once In A Lifetime.mp3''	260},
                451	''I'LL BE MISSING YOU''	''PUFF DADDY & FAITH EVANS''	''POP''	1997	''Puff Daddy & Faith Evans-I'll Be Missing You.mp3''	303},
                452	''MMMBOP''	''HANSON''	''MOTOWN & SOUL''	1997	''Hanson-Mmmbop.mp3''	238},
                453	''WONDERWALL''	''OASIS''	''1970s''	1997	''Oasis-Wonderwall.mp3''	258},
                454	''MEN IN BLACK''	''WILL SMITH''	''COUNTRY''	1997	''Will Smith-Men In Black.mp3''	233},
                455	''FREED FROM DESIRE''	''GALA''	''MOTOWN & SOUL''	1997	''Gala-Freed From Desire.mp3''	249},
                456	''FREE''	''ULTRA NATE''	''INDIE''	1997	''Ultra Nate-Free.mp3''	222},
                457	''CRAZY''	''ETERNAL''	''DISCO''	1997	''Eternal-Crazy.mp3''	237},
                458	''BITTER SWEET SYMPHONY''	''VERVE, THE''	''ROCK & ROLL''	1997	''Verve, The-Bitter Sweet Symphony.mp3''	359},
                459	''TUBTHUMPING''	''CHUMBAWAMBA''	''ROCK''	1997	''Chumbawamba-Tubthumping.mp3''	204},
                460	''CLOSER THAN CLOSE''	''ROSIE GAINES''	''COUNTRY''	1997	''Rosie Gaines-Closer Than Close.mp3''	223},
                461	''I LOVE THE WAY YOU LOVE ME''	''BOYZONE''	''MOTOWN & SOUL''	1998	''Boyzone-I Love The Way You Love Me.mp3''	225},
                462	''C'EST LA VIE''	''BEWITCHED''	''DISCO''	1998	''BeWitched-C'est La Vie.mp3''	174},
                463	''STOP''	''SPICE GIRLS, THE''	''2010s''	1998	''Spice Girls, The-Stop.mp3''	204},
                464	''FREAK ME''	''ANOTHER LEVEL''	''2000s''	1998	''Another Level-Freak Me.mp3''	225},
                465	''GOT THE FEELIN'''	''FIVE''	''JAZZ''	1998	''Five-Got The Feelin'.mp3''	208},
                466	''MUSIC SOUNDS BETTER WITH YOU''	''STARDUST''	''CLUB''	1998	''Stardust-Music Sounds Better With You.mp3''	204},
                467	''SAVE TONIGHT''	''EAGLE EYE CHERRY''	''CLASSICAL''	1998	''Eagle Eye Cherry-Save Tonight.mp3''	232},
                468	''DEEPER UNDERGROUND''	''JAMIROQUAI''	''LIVE''	1998	''Jamiroquai-Deeper Underground.mp3''	214},
                469	''LIFE IS A FLOWER''	''ACE OF BASE''	''2010s''	1998	''Ace Of Base-Life Is A Flower.mp3''	226},
                470	''LOOKING FOR LOVE''	''KAREN RAMIREZ''	''2010s''	1998	''Karen Ramirez-Looking For Love.mp3''	233},
                471	''BABY ONE MORE TIME''	''BRITNEY SPEARS''	''R&B''	1999	''Britney Spears-Baby One More Time.mp3''	210},
                472	''BETTER THE DEVIL YOU KNOW''	''STEPS''	''INDIE''	1999	''Steps-Better The Devil You Know.mp3''	228},
                473	''LIVIN' LA VIDA LOCA''	''RICKY MARTIN''	''HIP HOP & RAP''	1999	''Ricky Martin-Livin' La Vida Loca.mp3''	246},
                474	''BLUE (DA BA DEE)''	''EIFFEL 65''	''HIP HOP & RAP''	1999	''Eiffel 65-Blue (Da Ba Dee).mp3''	281},
                475	''MAMBO NO.5''	''LOU BEGA''	''1970s''	1999	''Lou Bega-Mambo No.5.mp3''	219},
                476	''THAT DON'T IMPRESS ME MUCH''	''SHANIA TWAIN''	''DISCO''	1999	''Shania Twain-That Don't Impress Me Much.mp3''	245},
                477	''9PM (TILL I COME)''	''ATB''	''INDIE''	1999	''ATB-9PM (Till I Come).mp3''	163},
                478	''GENIE IN A BOTTLE''	''CHRISTINA AGUILERA''	''POP''	1999	''Christina Aguilera-Genie In A Bottle.mp3''	217},
                479	''NO SCRUBS''	''TLC''	''1960s''	1999	''TLC-No Scrubs.mp3''	221},
                480	''SWEET LIKE CHOCOLATE''	''SHANKS & BIGFOOT''	''HIP HOP & RAP''	1999	''Shanks & Bigfoot-Sweet Like Chocolate.mp3''	194},
                481	''WHO LET THE DOGS OUT''	''BAHA MEN, THE''	''REGGAE''	2000	''Baha Men, The-Who Let The Dogs Out.mp3''	191},
                482	''FEELS SO GOOD''	''SONIQUE''	''1980s''	2000	''Sonique-Feels So Good.mp3''	228},
                483	''LOVE THE WAY YOU LIE''	''EMINEM FT RIHANNA''	''1950s''	2000	''Eminem ft Rihanna-Love The Way You Lie.mp3''	263},
                484	''RISE''	''GABRIELLE''	''LOVE SONGS''	2000	''Gabrielle-Rise.mp3''	219},
                485	''PURE SHORES''	''ALL SAINTS''	''CLASSICAL''	2000	''All Saints-Pure Shores.mp3''	270},
                486	''TOCA'S MIRACLE''	''FRAGMA''	''2000s''	2000	''Fragma-Toca's Miracle.mp3''	202},
                487	''DON'T STOP MOVIN'''	''S CLUB 7''	''R&B''	2000	''S Club 7-Don't Stop Movin'.mp3''	230},
                488	''ROCK DJ''	''ROBBIE WILLIAMS''	''ROCK''	2000	''Robbie Williams-Rock DJ.mp3''	256},
                489	''WHAT MAKES A MAN''	''WESTLIFE''	''1990s''	2000	''Westlife-What Makes A Man.mp3''	231},
                490	''FILL ME IN''	''CRAIG DAVID''	''COUNTRY''	2000	''Craig David-Fill Me In.mp3''	231},
                491	''ETERNAL FLAME''	''ATOMIC KITTEN''	''IRISH''	2001	''Atomic Kitten-Eternal Flame.mp3''	195},
                492	''IT WASN'T ME''	''SHAGGY''	''1990s''	2001	''Shaggy-It Wasn't Me.mp3''	228},
                493	''CAN'T GET YOU OUT OF MY HEAD''	''KYLIE MINOGUE''	''JAZZ''	2001	''Kylie Minogue-Can't Get You Out Of My Head.mp3''	229},
                494	''HEY BABY''	''DJ OTZI''	''JAZZ''	2001	''DJ Otzi-Hey Baby.mp3''	216},
                495	''REACH''	''S CLUB 7''	''REGGAE''	2001	''S Club 7-Reach.mp3''	243},
                496	''TEENAGE DIRTBAG''	''WHEATUS''	''2010s''	2001	''Wheatus-Teenage Dirtbag.mp3''	241},
                497	''PURE AND SIMPLE''	''HEAR'SAY''	''POP''	2001	''Hear'say-Pure And Simple.mp3''	227},
                498	''BECAUSE I GOT HIGH''	''AFROMAN''	''LOVE SONGS''	2001	''Afroman-Because I Got High.mp3''	198},
                499	''CLINT EASTWOOD''	''GORILLAZ''	''COUNTRY''	2001	''Gorillaz-Clint Eastwood.mp3''	220},
                500	''THE TIDE IS HIGH (GET THE FEELING)''	''ATOMIC KITTEN''	''LATIN''	2001	''Atomic Kitten-The Tide Is High (Get the Feeling).mp3''	205},
                501	''HERO''	''ENRIQUE IGLESIAS''	''2000s''	2002	''Enrique Iglesias-Hero.mp3''	264},
                502	''HIPS DON'T LIE''	''SHAKIRA FT WYCLEF JEAN''	''CLUB''	2002	''Shakira ft Wyclef Jean-Hips Don't Lie.mp3''	220},
                503	''UNCHAINED MELODY''	''GARETH GATES''	''R&B''	2002	''Gareth Gates-Unchained Melody.mp3''	234},
                504	''DILEMMA''	''NELLY FT KELLY ROWLAND''	''LATIN''	2002	''Nelly ft Kelly Rowland-Dilemma.mp3''	283},
                505	''WHOLE AGAIN''	''ATOMIC KITTEN''	''LIVE''	2002	''Atomic Kitten-Whole Again.mp3''	184},
                506	''HOW YOU REMIND ME''	''NICKELBACK''	''REGGAE''	2002	''Nickelback-How You Remind Me.mp3''	222},
                507	''LIGHT MY FIRE''	''WILL YOUNG''	''DISCO''	2002	''Will Young-Light My Fire.mp3''	196},
                508	''THE KETCHUP SONG''	''LAS KETCHUP''	''2000s''	2002	''Las Ketchup-The Ketchup Song.mp3''	209},
                509	''A LITTLE LESS CONVERSATION''	''ELVIS PRESLEY VS JLX''	''CLASSICAL''	2002	''Elvis vs JXL-A Little Less Conversation.mp3''	215},
                510	''BUSINESS''	''EMINEM''	''1960s''	2002	''Eminem-Business.mp3''	253},
                511	''I GOTTA FEELING''	''BLACK EYED PEAS, THE''	''ROCK''	2003	''Black Eyed Peas, The-I Gotta Feeling.mp3''	246},
                512	''IGNITION REMIX''	''R KELLY''	''JAZZ''	2003	''R Kelly-Ignition.mp3''	188},
                513	''BRING ME TO LIFE''	''EVANESCENCE''	''HIP HOP & RAP''	2003	''Evanescence-Bring Me To Life.mp3''	232},
                514	''BREATHE''	''BLU CANTRELL FT SEAN PAUL''	''1980s''	2003	''Blu Cantrell ft Sean Paul-Breathe.mp3''	229},
                515	''IN DA CLUB''	''50 CENT''	''1970s''	2003	''50 Cent-In Da Club.mp3''	225},
                516	''ALL THE THINGS SHE SAID''	''TATU''	''LIVE''	2003	''Tasmin Archer-One More Good Night With The Boys.mp3''	213},
                517	''CRAZY IN LOVE''	''BEYONCE''	''HIP HOP & RAP''	2003	''Beyonce-Crazy In Love.mp3''	249},
                518	''JUMP AROUND''	''GIRLS ALOUD''	''1950s''	2003	''Girls Aloud-Jump.mp3''	220},
                519	''MOVE YOUR FEET''	''JUNIOR SENIOR''	''JAZZ''	2003	''Junior Senior-Move Your Feet.mp3''	182},
                520	''THE REAL SLIM SHADY''	''EMINEM''	''1970s''	2003	''Eminem-The Real Slim Shady.mp3''	284},
                521	''CALL ON ME''	''ERIC PRYDZ''	''CLUB''	2004	''Eric Prydz-Call On Me.mp3''	171},
                522	''LEFT OUTSIDE ALONE''	''ANASTACIA''	''1970s''	2004	''Anastacia-Left Outside Alone.mp3''	234},
                523	''CHA CHA SLIDE''	''DJ CASPER''	''CLASSICAL''	2004	''DJ Casper-Cha Cha Slide.mp3''	221},
                524	''MILKSHAKE''	''KELIS''	''LOVE SONGS''	2004	''Kelis-Milkshake.mp3''	184},
                525	''MAD WORLD''	''MICHAEL ANDREWS FT GARY JULES''	''INDIE''	2004	''Michael Andrews ft Gary Jules-Mad World.mp3''	182},
                526	''LOSE MY BREATH''	''DESTINY'S CHILD''	''IRISH''	2004	''Destiny's Child-Lose My Breath.mp3''	244},
                527	''ROSES''	''OUTKAST''	''DISCO''	2004	''Outkast-Roses.mp3''	255},
                528	''TAKE ME TO THE CLOUDS ABOVE''	''LMC VS U2''	''ROCK & ROLL''	2004	''LMC vs U2-Take Me To The Clouds Above.mp3''	167},
                529	''DRY YOUR EYES''	''STREETS, THE''	''REGGAE''	2004	''Streets, The-Dry Your Eyes.mp3''	268},
                530	''BURN''	''USHER''	''LOVE SONGS''	2004	''Usher-Burn.mp3''	244},
                531	''FROGGY MIX''	''JAMES BROWN''	''HIP HOP & RAP''	2005	''James Brown-Froggy Mix.mp3''	425},
                532	''BAD DAY''	''DANIEL POWTER''	''POP''	2005	''Daniel Powter-Bad Day.mp3''	236},
                533	''DON'T CHA''	''PUSSYCAT DOLLS, THE FT BUSTA RHYMES''	''R&B''	2005	''Pussycat Dolls, The ft Busta Rhymes-Don't Cha.mp3''	242},
                534	''GHETTO GOSPEL''	''2PAC FT ELTON JOHN''	''1960s''	2005	''2 Pac ft Elton John-Ghetto Gospel.mp3''	240},
                535	''FEEL GOOD INC''	''GORILLAZ''	''DISCO''	2005	''Gorillaz-Feel Good Inc.mp3''	221},
                536	''LONELY''	''AKON''	''MOTOWN & SOUL''	2005	''Akon-Lonely.mp3''	216},
                537	''CHANGE''	''SUGABABES''	''CLASSICAL''	2005	''Sugababes-Change.mp3''	199},
                538	''YOU RAISE ME UP''	''WESTLIFE''	''2000s''	2005	''Westlife-You Raise Me Up.mp3''	243},
                539	''I LIKE THE WAY''	''BODYROCKERS''	''INDIE''	2005	''Bodyrockers-I Like The Way.mp3''	202},
                540	''YOU'LL SEE''	''MADONNA''	''LATIN''	2005	''Madonna-You'll See.mp3''	260},
                541	''WHENEVER WHEREVER''	''SHAKIRA''	''COUNTRY''	2006	''Shakira-Whenever Wherever.mp3''	197},
                542	''CRAZY''	''GNARLS BARKLEY''	''2000s''	2006	''Gnarls Barkley-Crazy.mp3''	181},
                543	''I DON'T FEEL LIKE DANCIN'''	''SCISSOR SISTERS''	''JAZZ''	2006	''Scissor Sisters-I Don't Feel Like Dancin'.mp3''	249},
                544	''NO TOMORROW''	''ORSON''	''COUNTRY''	2006	''Orson-No Tomorrow.mp3''	170},
                545	''CAN'T STOP THE FEELING''	''JUSTIN TIMBERLAKE''	''1980s''	2006	''Justin Timberlake-Can't Stop The Feeling.mp3''	235},
                546	''MANEATER''	''NELLY FURTADO''	''LIVE''	2006	''Nelly Furtado-Maneater.mp3''	198},
                547	''THAT'S MY GOAL''	''SHAYNE WARD''	''LATIN''	2006	''Shayne Ward-That's My Goal.mp3''	218},
                548	''SMILE''	''LILY ALLEN''	''HIP HOP & RAP''	2006	''Lily Allen-Smile.mp3''	197},
                549	''PATIENCE''	''TAKE THAT''	''HIP HOP & RAP''	2006	''T.A.T.U-All the Things She Said.mp3''	209},
                550	''AMERICA''	''RAZORLIGHT''	''CLUB''	2006	''Razorlight-America.mp3''	252},
                551	''RUDE BOY''	''RIHANNA''	''MOTOWN & SOUL''	2007	''Rihanna-Rude Boy.mp3''	223},
                552	''GRACE KELLY''	''MIKA''	''MOTOWN & SOUL''	2007	''Mika-Grace Kelly.mp3''	186},
                553	''BLEEDING LOVE''	''LEONA LEWIS''	''CLUB''	2007	''Leona Lewis-Bleeding Love.mp3''	264},
                554	''HOW TO SAVE A LIFE''	''FRAY, THE''	''INDIE''	2007	''Fray, The-How To Save A Life.mp3''	263},
                555	''RUBY''	''KAISER CHIEFS''	''2000s''	2007	''Kaiser Chiefs-Ruby.mp3''	205},
                556	''FOUNDATIONS''	''KATE NASH''	''DISCO''	2007	''Kate Nash-Foundations.mp3''	225},
                557	''THE SWEET ESCAPE''	''GWEN STEFANI FT AKON''	''IRISH''	2007	''Gwen Stefani ft Akon-The Sweet Escape.mp3''	250},
                558	''HEY THERE DELILAH''	''PLAIN WHITE T'S''	''2010s''	2007	''Plain White T's-Hey There Delilah.mp3''	232},
                559	''PUSH THE BUTTON''	''SUGABABES''	''JAZZ''	2007	''Sugababes-Push The Button.mp3''	219},
                560	''BEAUTIFUL GIRLS''	''SEAN KINGSTON''	''1950s''	2007	''Sean Kingston-Beautiful Girls.mp3''	243},
                561	''NOW YOU'RE GONE''	''BASSHUNTER''	''1950s''	2008	''Basshunter-Now You're Gone.mp3''	154},
                562	''ROCKSTAR''	''NICKELBACK''	''ROCK''	2008	''Nickelback-Rockstar.mp3''	255},
                563	''MERCY''	''DUFFY''	''REGGAE''	2008	''Duffy-Mercy.mp3''	222},
                564	''HELLO''	''ADELE''	''HIP HOP & RAP''	2008	''Adele-Hello.mp3''	199},
                565	''WE FOUND LOVE''	''RIHANNA FT CALVIN HARRIS''	''IRISH''	2008	''Rihanna ft Calvin Harris-We Found Love.mp3''	215},
                566	''CRANK THAT''	''SOULJA BOY''	''1990s''	2008	''Soulja Boy-Crank That.mp3''	225},
                567	''PIECE OF ME''	''BRITNEY SPEARS''	''REGGAE''	2008	''Britney Spears-Piece Of Me.mp3''	213},
                568	''WHAT'S IT GONNA BE''	''H TWO O FT PLATNUM''	''LIVE''	2008	''H Two O ft Platnum-What's It Gonna Be.mp3''	195},
                569	''APOLOGIZE''	''TIMBALAND FT ONEREPUBLIC''	''ROCK & ROLL''	2008	''Timbaland ft OneRepublic-Apologize.mp3''	184},
                570	''VALERIE''	''MARK RONSON FT AMY WINEHOUSE''	''CLASSICAL''	2008	''Mark Ronson ft Amy Winehouse-Valerie.mp3''	219},
                571	''BAD ROMANCE''	''LADY GAGA''	''1960s''	2009	''Lady Gaga-Bad Romance.mp3''	268},
                572	''MEET ME HALFWAY''	''BLACK EYED PEAS, THE''	''ROCK & ROLL''	2009	''Black Eyed Peas, The-Meet Me Halfway.mp3''	284},
                573	''IN FOR THE KILL''	''LA ROUX''	''1950s''	2009	''La Roux-In For The Kill.mp3''	248},
                574	''BORN THIS WAY''	''LADY GAGA''	''1970s''	2009	''Lady Gaga-Born This Way.mp3''	260},
                575	''SEX ON FIRE''	''KINGS OF LEON''	''REGGAE''	2009	''Kings Of Leon-Sex On Fire.mp3''	204},
                576	''USE SOMEBODY''	''KINGS OF LEON''	''ROCK''	2009	''Kings Of Leon-Use Somebody.mp3''	231},
                577	''WHERE IS THE LOVE?''	''BLACK EYED PEAS, THE''	''CLASSICAL''	2009	''Black Eyed Peas, The-Where Is The Love.mp3''	275},
                578	''SEXY CHICK''	''DAVID GUETTA FT AKON''	''ROCK & ROLL''	2009	''David Guetta ft Akon-Sexy Chick.mp3''	196},
                579	''EMPIRE STATE OF MIND''	''JAY-Z FT ALICIA KEYS''	''REGGAE''	2009	''Jay Z ft Alicia Keys-Empire State Of Mind.mp3''	278},
                580	''THE FEAR''	''LILY ALLEN''	''INDIE''	2009	''Lily Allen-The Fear.mp3''	207},
                581	''EMPIRE STATE OF MIND (PART II)''	''ALICIA KEYS''	''ROCK & ROLL''	2010	''Alicia Keys-Empire State Of Mind (Part II).mp3''	215},
                582	''PASS OUT''	''TINIE TEMPAH''	''JAZZ''	2010	''Tinie Tempah-Pass Out.mp3''	270},
                583	''DON'T STOP BELIEVIN'''	''JOURNEY''	''REGGAE''	2010	''Journey-Don't Stop Believin'.mp3''	254},
                584	''JUST DANCE''	''LADY GAGA''	''1980s''	2010	''Lady Gaga-Just Dance.mp3''	236},
                585	''WITHOUT ME''	''EMINEM''	''1980s''	2010	''Eminem-Without Me.mp3''	262},
                586	''SHE SAID''	''PLAN B''	''CLUB''	2010	''Plan B-She Said.mp3''	211},
                587	''WORK''	''RIHANNA FT DRAKE''	''LATIN''	2010	''Rihanna ft Drake-Work.mp3''	218},
                588	''FIREFLIES''	''OWL CITY''	''INDIE''	2010	''Owl City-Fireflies.mp3''	228},
                589	''ROAR''	''KATY PERRY''	''IRISH''	2010	''Katy Perry-Roar.mp3''	222},
                590	''HEY SOUL SISTER''	''TRAIN''	''CLUB''	2010	''Train-Hey Soul Sister.mp3''	215},
                591	''HOMETOWN GLORY''	''ADELE''	''INDIE''	2011	''Adele-Hometown Glory.mp3''	270},
                592	''ROLLING IN THE DEEP''	''ADELE''	''IRISH''	2011	''Adele-Rolling In The Deep.mp3''	227},
                593	''PARTY ROCK ANTHEM''	''LMFAO''	''CLASSICAL''	2011	''LMFAO-Party Rock Anthem.mp3''	261},
                594	''DOMINO''	''JESSIE J''	''JAZZ''	2011	''Jessie J-Domino.mp3''	231},
                595	''THE A TEAM''	''ED SHEERAN''	''1960s''	2011	''Ed Sheeran-The A Team.mp3''	260},
                596	''SET FIRE TO THE RAIN''	''ADELE''	''LATIN''	2011	''Adele-Set Fire to the Rain.mp3''	241},
                597	''POKER FACE''	''LADY GAGA''	''1990s''	2011	''Lady Gaga-Poker Face.mp3''	238},
                598	''SUPER BASS''	''NICKI MINAJ''	''ROCK & ROLL''	2011	''Nicki Minaj-Super Bass.mp3''	218},
                599	''A THOUSAND YEARS''	''CHRISTINA PERRI''	''R&B''	2011	''Christina Perri-A Thousand Years.mp3''	288},
                600	''JUST THE WAY YOU ARE''	''BRUNO MARS''	''CLASSICAL''	2011	''Bruno Mars-Just The Way You Are.mp3''	220},
                601	''SOMEBODY THAT I USED TO KNOW''	''GOTYE FT KIMBRA''	''HIP HOP & RAP''	2012	''Gotye ft Kimbra-Somebody That I Used To Know.mp3''	245},
                602	''TITANIUM''	''DAVID GUETTA FT SIA''	''CLASSICAL''	2012	''David Guetta ft Sia-Titanium.mp3''	245},
                603	''CALL ME MAYBE''	''CARLY RAE JEPSEN''	''CLUB''	2012	''Carly Rae Jepsen-Call Me Maybe.mp3''	193},
                604	''WE ARE YOUNG''	''FUN FT JANELLE MONAE''	''LATIN''	2012	''Fun ft Janelle Monáe-We Are Young.mp3''	252},
                605	''PRICE TAG''	''JESSIE J FT B.O.B.''	''CLASSICAL''	2012	''Jessie J ft B.O.B-Price Tag.mp3''	221},
                606	''FEEL THE LOVE''	''RUDIMENTAL FT JOHN NEWMAN''	''POP''	2012	''Rudimental ft John Newman-Feel The Love.mp3''	267},
                607	''MOVES LIKE JAGGER''	''MAROON 5 FT CHRISTINA AGUILERA''	''1970s''	2012	''Maroon 5 ft Christina Aguilera-Moves Like Jagger.mp3''	201},
                608	''NEXT TO ME''	''EMELI SANDE''	''JAZZ''	2012	''Emeli Sande-Next To Me.mp3''	203},
                609	''TAKE CARE''	''DRAKE FT RIHANNA''	''IRISH''	2012	''Drake ft Rihanna-Take Care.mp3''	132},
                610	''UMBRELLA''	''RIHANNA FT JAY-Z''	''LIVE''	2012	''Rihanna ft Jay Z-Umbrella.mp3''	255},
                611	''LET HER GO''	''PASSENGER''	''LATIN''	2013	''Passenger-Let Her Go.mp3''	252},
                612	''RADIOACTIVE''	''IMAGINE DRAGONS''	''1980s''	2013	''Imagine Dragons-Radioactive.mp3''	188},
                613	''POMPEII''	''BASTILLE''	''1960s''	2013	''Bastille-Pompeii.mp3''	214},
                614	''HO HEY''	''LUMINEERS''	''CLUB''	2013	''Lumineers-Ho Hey.mp3''	163},
                615	''GET LUCKY''	''DAFT PUNK FT PHARRELL WILLIAMS''	''LOVE SONGS''	2013	''Daft Punk ft Pharrell Williams-Get Lucky.mp3''	247},
                616	''BLURRED LINES''	''ROBIN THICKE FT PHARRELL WILLIAMS & T.I.''	''CLASSICAL''	2013	''Robin Thicke ft Pharrell Williams & T.I.-Blurred Lines.mp3''	271},
                617	''JUST GIVE ME A REASON''	''PINK FT NATE REUSS''	''1980s''	2013	''Pink ft Nate Ruess-Just Give Me A Reason.mp3''	242},
                618	''CAN'T HOLD US''	''MACKLEMORE & RYAN LEWIS FT RAY DALTON''	''COUNTRY''	2013	''Macklemore & Ryan Lewis ft Ray Dalton-Can't Hold Us.mp3''	258},
                619	''HEY BROTHER''	''AVICII''	''LOVE SONGS''	2013	''Avicii-Hey Brother.mp3''	253},
                620	''JAR OF HEARTS''	''CHRISTINA PERRI''	''REGGAE''	2013	''Christina Perri-Jar Of Hearts.mp3''	250},
                621	''HAPPY''	''PHARRELL WILLIAMS''	''1960s''	2014	''Pharrell Williams-Happy.mp3''	233},
                622	''TIMBER''	''PITBULL FT KESHA''	''1990s''	2014	''Pitbull ft Kesha-Timber.mp3''	204},
                623	''WAKE ME UP''	''AVICII''	''MOTOWN & SOUL''	2014	''Avicii-Wake Me Up.mp3''	249},
                624	''TRUMPETS''	''JASON DERULO''	''MOTOWN & SOUL''	2014	''Jason Derulo-Trumpets.mp3''	216},
                625	''HOW LONG WILL I LOVE YOU''	''ELLIE GOULDING''	''IRISH''	2014	''Ellie Goulding-How Long Will I Love You.mp3''	154},
                626	''ANIMALS''	''MARTIN GARRIX''	''2010s''	2014	''Martin Garrix-Animals.mp3''	304},
                627	''TURN BACK TIME''	''SUB FOCUS''	''ROCK & ROLL''	2014	''Sub Focus-Turn Back Time.mp3''	306},
                628	''CALIFORNIA GURLS''	''KATY PERRY FT SNOOP DOGG''	''INDIE''	2014	''Katy Perry ft Snoop Dogg-California Gurls.mp3''	236},
                629	''ROYALS''	''LORDE''	''1950s''	2014	''Lorde-Royals.mp3''	190},
                630	''STORY OF MY LIFE''	''ONE DIRECTION''	''2000s''	2014	''One Direction-Story Of My Life.mp3''	245},
                631	''UPTOWN FUNK''	''MARK RONSON FT BRUNO MARS''	''JAZZ''	2015	''Mark Ronson ft Bruno Mars-Uptown Funk.mp3''	270},
                632	''HULA HOOP''	''OMI''	''1990s''	2015	''OMI-Hula Hoop.mp3''	220},
                633	''SOMEONE NEW''	''HOZIER''	''JAZZ''	2015	''Hozier-Someone New.mp3''	241},
                634	''LOVE ME LIKE YOU DO''	''ELLIE GOULDING''	''LATIN''	2015	''Ellie Goulding-Love Me Like You Do.mp3''	252},
                635	''SEE YOU AGAIN''	''WIZ KHALIFA FT CHARLIE PUTH''	''MOTOWN & SOUL''	2015	''Wiz Khalifa ft Charlie Puth-See You Again.mp3''	226},
                636	''SOMEONE LIKE YOU''	''ADELE''	''LIVE''	2015	''Adele-Someone Like You.mp3''	285},
                637	''LEAN ON''	''MAJOR LAZER FT MO & DJ SNAKE''	''LIVE''	2015	''Major Lazer ft Mo & DJ Snake-Lean On.mp3''	176},
                638	''OVER AND OVER''	''JAMES BOYS, THE''	''DISCO''	2015	''James Boys, The-Over And Over.mp3''	133},
                639	''LOVE YOURSELF''	''JUSTIN BIEBER''	''1950s''	2015	''Justin Bieber-Love Yourself.mp3''	233},
                640	''SORRY''	''JUSTIN BIEBER''	''1960s''	2015	''Justin Bieber-Sorry.mp3''	200},
                641	''ONE DANCE''	''DRAKE FT WIZKID & KYLA''	''LATIN''	2016	''Drake ft Wizkid & Kyla-One Dance.mp3''	246},
                642	''7 YEARS''	''LUKAS GRAHAM''	''2010s''	2016	''Lukas Graham-7 Years.mp3''	231},
                643	''CHEAP THRILLS''	''SIA''	''POP''	2016	''Sia-Cheap Thrills.mp3''	224},
                644	''I TOOK A PILL IN IBIZA''	''MIKE POSNER''	''REGGAE''	2016	''Mike Posner-I Took A Pill In Ibiza.mp3''	282},
                645	''THIS IS WHAT YOU CAME FOR''	''CALVIN HARRIS FT RIHANNA''	''1990s''	2016	''Calvin Harris ft Rihanna-This Is What You Came For.mp3''	239},
                646	''CLOSER''	''CHAINSMOKERS FT HALSEY''	''HIP HOP & RAP''	2016	''Chainsmokers ft Halsey-Closer.mp3''	261},
                647	''WHAT DO YOU MEAN''	''JUSTIN BIEBER''	''1970s''	2016	''Justin Bieber-What Do You Mean.mp3''	209},
                648	''DON'T STOP THE MUSIC''	''RIHANNA''	''LOVE SONGS''	2016	''Rihanna-Don't Stop The Music.mp3''	269},
                649	''SEXYBACK''	''JUSTIN TIMBERLAKE''	''1990s''	2016	''Justin Timberlake-Sexyback.mp3''	246},
                650	''STITCHES''	''SHAWN MENDES''	''IRISH''	2016	''Shawn Mendes-Stitches.mp3''	204},
                651	''SHAPE OF YOU''	''ED SHEERAN''	''1970s''	2017	''Ed Sheeran-Shape Of You.mp3''	170},
                652	''CASTLE ON THE HILL''	''ED SHEERAN''	''1980s''	2017	''Ed Sheeran-Castle On The Hill.mp3''	263},
                653	''GALWAY GIRL''	''ED SHEERAN''	''1990s''	2017	''Ed Sheeran-Galway Girl.mp3''	233},
                654	''PERFECT''	''ED SHEERAN''	''2000s''	2017	''Ed Sheeran-Perfect.mp3''	193},
                655	''SYMPHONY''	''CLEAN BANDIT FT ZARA LARSSON''	''CLASSICAL''	2017	''Clean Bandit ft Zara Larsson-Symphony.mp3''	240},
                656	''SOMETHING JUST LIKE THIS''	''CHAINSMOKERS FT COLDPLAY''	''DISCO''	2017	''Chainsmokers ft Coldplay-Something Just Like This.mp3''	247},
                657	''YOU DON'T KNOW ME''	''JAX JONES FT RAYE''	''R&B''	2017	''Jax Jones ft Raye-You Don't Know Me.mp3''	214},
                658	''NEW RULES''	''DUA LIPA''	''LOVE SONGS''	2017	''Dua Lipa-New Rules.mp3''	204},
                659	''HAVANA''	''CAMILA CABELLO FT YOUNG THUG''	''2010s''	2017	''Camila Cabello ft Young Thug-Havana.mp3''	216},
                660	''CALL ON ME''	''STARLEY''	''DISCO''	2017	''Starley-Call On Me.mp3''	195},
                661	''HOTLINE BLING''	''DRAKE''	''INDIE''	2018	''Drake-Hotline Bling.mp3''	275},
                662	''ONE KISS''	''CALVIN HARRIS & DUA LIPA''	''1980s''	2018	''Calvin Harris & Dua Lipa-One Kiss.mp3''	214},
                663	''LAY IT ALL ON ME''	''RUDIMENTAL FT ED SHEERAN''	''MOTOWN & SOUL''	2018	''Rudimental ft Ed Sheeran-Lay It All On Me.mp3''	242},
                664	''BUDAPEST''	''GEORGE EZRA''	''CLASSICAL''	2018	''George Ezra-Budapest.mp3''	202},
                665	''SHOTGUN''	''GEORGE EZRA''	''JAZZ''	2018	''George Ezra-Shotgun.mp3''	177},
                666	''BE THE ONE''	''DUA LIPA''	''MOTOWN & SOUL''	2018	''Dua Lipa-Be The One.mp3''	212},
                667	''LIGHT AND DAY''	''POLYPHONIC SPREE, THE''	''INDIE''	2018	''Polyphonic Spree, The-Light And Day.mp3''	186},
                668	''EASY LOVE''	''SIGALA''	''R&B''	2018	''Sigala-Easy Love.mp3''	227},
                669	''I'LL BE THERE''	''JESS GLYNNE''	''ROCK & ROLL''	2018	''Jess Glynne-I'll Be There.mp3''	193},
                670	''HOW WE DO (PARTY)''	''RITA ORA''	''POP''	2018	''Rita Ora-How We Do (Party).mp3''	247},*/


        };

        // Column Names
        String[] columnNames = {"ID", "Title", "Artist", "Genre", "Year", "Location", "Duration"};


        // Initializing the JTable
        JTable songtable = new JTable(data, columnNames);
        songtable.setBounds(30, 40, 200, 300);


        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(radioframe, "Right-click performed on table and choose DELETE");
            }
        });
        popupMenu.add(deleteItem);
        songtable.setComponentPopupMenu(popupMenu);
        //radioframe.add(new JScrollPane(songtable), BorderLayout.CENTER);
        radioframe.pack();
        radioframe.setVisible(true);

        // adding it to JScrollPane
        JScrollPane scroll = new JScrollPane(songtable);
        radioframe.add(scroll);
        // Frame Size
        radioframe.setSize(1500, 600);
        // Frame Visible = true
        radioframe.setVisible(true);

        //
        radioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void Player()
    {

        //FlowLayout flowLayout = new FlowLayout();

        //setLayout(flowLayout);

        JButton audioButton = new JButton("Play");
        add(audioButton);

        //JButton randomButton = new JButton("Generate Random Number");
        //add(randomButton);

        audioButton.addActionListener(this);
        //randomButton.addActionListener(this);

        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    public static void playAudio(String path)
    {
        //create a new Media object using the file path specified in the call to playAudio
        //this will become the audio clip object we wish to play

        Media audioClip = new Media(new File(path).toURI().toString());

        //create a new MediaPlayer object that will be used to play the audio clip

        MediaPlayer mediaPlayer = new MediaPlayer(audioClip);

		/*when the clip has played to completion, the code below ensures that the JavaFX thread will terminate
		 I have commented it out though because you generally want a JavaFX thread to keep operating
		 after an audio file has been fully played, but a JavaFX thread won't terminate automatically
	     without a call to Platform.exit() or System.exit(). So, if you run this program with the code below commented out,
		 the JavaFX thread will continue after the audio clip has played meaning you can press the button again and again
		 to replay the audio - if you uncomment it, exceptions are thrown after the first few presses because the
		 audio requires JavaFX libraries which are no longer available as the thread they run on is terminated
		 The other button, for random number generation, based on the swing event-dispatch thread, continues to operate
		 as normal though, even with exceptions being thrown for the other thread*/

		/*mediaPlayer.setOnEndOfMedia(new Runnable() {
 			 public void run() {
		    	Platform.exit();
		  	}
		});*/

        /*now actually play the audio clip - some exception-handling code to attempt to play the .wav audio clip associated with audioFile1.
         *If it succeeds, you will hear the sound of a gunshot, otherwise it fails either because the audio clip could not be found, or the
         *MediaPlayer object had some difficulty in actually playing the file. If it fails, the "catch" clause executes and an error message
         *is displayed to the console*/

        try
        {
            mediaPlayer.play();
        }
        catch(Exception e)
        {
            System.out.println("The audio file " + path + " could not be played!");
        }

    }

    public void actionPerformed(ActionEvent e)
    {
		/*If the audioButton was pressed then call playAudio() to play the audio file associated with the reference audioFile1 (the gunshot)
		  or else, if the other button was pressed, display a message dialog showing a randomly generated number between 1 and 1000*/

        Object audioButton = null;
        if(e.getSource()==audioButton) {
            PlaylistGUI.playAudio ("Song Folder..");
        } else
            JOptionPane.showMessageDialog(null,"The randomly generated number was " + (int)(Math.random()*1000 + 1));
    }
}


    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

    @Override
    public void run() {
        new TestTableRightClick().initUI();
    }
}*/



















        
       /* }
        catch(Exception ee)
        {
            ee.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch(IOException ie)
            {
                System.out.println("Error occured while closing the BufferedReader");
                ie.printStackTrace();
            }
        }*/





        /*Constructor
        PlaylistGUI GUI = new PlaylistGUI();
        {
            // Frame initialization
            JFrame radioframe = new JFrame();

            // Frame Title
            radioframe.setTitle("Playlist GUI");

            // Column Names
            String[] columnNames = {"ID", "Title", "Artist", "Genre", "Year", "Location", "Duration"};

            //Data to be displayed in the JTable

            String [][] data = {

                    { "515", "In Da Club", "50 Cent", "1970s", "2003", "50 Cent-In Da Club.mp3", "225" }
                    //{ }
            };


            // Initializing the JTable
            JTable songtable= new JTable(data, columnNames);
            songtable.setBounds(30, 40, 200, 300);

            // adding it to JScrollPane
            JScrollPane scroll = new JScrollPane(songtable);
            radioframe.add(scroll);
            // Frame Size
            radioframe.setSize(1500, 600);
            // Frame Visible = true
            radioframe.setVisible(true);
            //
            radioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }


    /* Driver  method
    public static void main(String[] args)
    {
        new PlaylistGUI();
    }*/





/*public class PlaylistGUI {

     //Constructor
    PlaylistGUI()
    {
        // Frame initiallization
        JFrame radioframe = new JFrame();

        // Frame Title
        radioframe.setTitle("Playlist GUI");

        // Data to be displayed in the JTable
        String[][] data = {
                { "515", "In Da Club", "50 Cent", "1970s", "2003", "50 Cent-In Da Club.mp3", "225" }
                //{ }
        };

        // Column Names
        String[] columnNames = { "ID", "Title", "Artist", "Genre", "Year", "Location", "Duration" };

        // Initializing the JTable
        JTable songtable= new JTable(data, columnNames);
        songtable.setBounds(30, 40, 200, 300);

        // adding it to JScrollPane
        JScrollPane scroll = new JScrollPane(songtable);
        radioframe.add(scroll);
        // Frame Size
        radioframe.setSize(1500, 600);
        // Frame Visible = true
        radioframe.setVisible(true);
        //
        //radioframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Driver  method
    public static void main(String[] args)
    {
        new PlaylistGUI();
    }*/





    /*public class Songs {

        @CsvBindByName
        private int ID;

        @CsvBindByName
        private String title;

        @CsvBindByName
        private String artist;

        @CsvBindByName
        private String genre;

        @CsvBindByName
        private int year;

        @CsvBindByName
        private String location;

        @CsvBindByName
        private int duration;

        List<Songs> beans = new CsvToBeanBuilder(FileReader("ColmsSongDatabase.csv"))
                .withType(Songs.class).build().parse();
    }*/

    /*public static void main(String[] args) throws IOException {
        Song[] s = new Song[671];

        FileReader fr=new FileReader("ColmsSongDatabase.csv");
        BufferedReader br=new BufferedReader(fr);
        String line="";
        String[] arrs=null;
        int num=0;
        while ((line=br.readLine())!=null) {
            arrs=line.split(",");

            s[num] = new Song(Integer.valueOf(arrs[0]), arrs[1], arrs[2], arrs[3]), arrs[4], arrs[5], arrs[6]);

            num++;
        }
        br.close();
        fr.close();

        for(int i=0 ; i< s.length; i++) {
            System.out.println(s[i].ID + " and " + s[i].title + " and " + s[i].artist + " and " +
                    s[i].genre + " and " + s[i].year + " and " + s[i].location + " and " +
                    s[i].duration);
        }
    }*/
//}



