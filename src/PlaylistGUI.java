
import javax.swing.*;
import java.awt.FlowLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//package com.javainterviewpoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.embed.swing.JFXPanel; //need this for AudioFilePlayer as it uses JavaFX API


public class PlaylistGUI
{
    //Delimiters used in the CSV file
    //private static final String COMMA_DELIMITER = ",";

    public static void main(String args[])
    {
        //BufferedReader br = null;
        DBConnection dbConn = new DBConnection(); //establish a connection to the MariaDB
        Connection conn = dbConn.getConnection(); //get a reference to the database connection object
        Statement stmt = null;

        //Create List for holding Song objects
        List<Song> songList = new ArrayList<Song>();

        try
        {
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
                                                       + year + "  "  + location + "  "  + duration);

				JFXPanel fxPanel = new JFXPanel(); //must have this line of code in order to initialize the JavaFX
                                                    // runtime so AudioFilePlayer can be used

            //if(id==496)
            //	RadioPlayer.playAudio("J:/ColmSongs/" + location);  //JB - I had this code to play a song from the database




               //JB - use the details above retrieved from the database to create each Song object

                //Save the song details in Song object
                Song song = new Song(id,title,artist,genre,year,location,duration);
                songList.add(song);

		    }
            
            
            
            
            
            /*
            



            String line = "";
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
		    }
        catch (SQLException sqlex) {
            //Handle exceptions related to JDBC
            sqlex.printStackTrace();
        }
        catch (Exception ex) {
            //Handle exceptions related to registration of JDBC driver
            ex.printStackTrace();
        }
        finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            }
            catch (SQLException sqlex) {
            	sqlex.printStackTrace();
            }
            try {
                if (conn != null)
                	conn.close();
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }//end finally try-catch clauses
        }//end main try-catch-finally clause

         //Lets print the Song List
         System.out.println("\n\n\n\n\n\n\n\n**********************Songs stored in the ArrayList of Song objects***********************");
           for(Song s : songList)
            {
                System.out.println(s.getID() + "   " + s.gettitle()+ "   "
                        + s.getartist() + "   " + s.getgenre()+ "   "
                        + s.getyear() + "   " + s.getlocation()+ "   "
                        + s.getduration() + "   ");
            }




        System.out.println("Goodbye!");
        
        

        
        
        
        
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

        //Constructor
        new PlaylistGUI();
        {
            // Frame initialization
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

    /* Driver  method
    public static void main(String[] args)
    {
        new PlaylistGUI();
    }*/
    }
}


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
