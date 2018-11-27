import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
import java.sql.*;
import java.util.*;
import java.util.List;

//import com.sun.org.apache.bcel.internal.generic.POP;
import javafx.embed.swing.JFXPanel; //need this for AudioFilePlayer as it uses JavaFX API
import javax.swing.JPopupMenu;

public class Playlist {
    public static <Playlist> void main(String[] args) throws SQLException {

        DBConnection dbConn = new DBConnection(); //establish a connection to the MariaDB
        Connection conn = dbConn.getConnection(); //get a reference to the database connection object
        Statement stmt = null;

        //Create List for holding Song objects
        List<Song> reggaePlaylist = new ArrayList<Song>();


        try {
            //Reading the csv file
            //br = new BufferedReader(new FileReader("ColmsSongDatabase.csv"));

            stmt = conn.createStatement(); //create a Statement object, needed for executing queries on the database

            //Execute a query to retrieve all song rows from the songs table
            //I used HeidiSQL to create the rows in the songs table, populated from the contents of a .csv file
            //just to populate it with a set of songs

            //Store the results of the query in a ResultSet object

            ResultSet rs = stmt.executeQuery("SELECT * FROM songs WHERE Genre = 'Reggae'");

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
                Song songReggae = new Song(id, title, artist, genre, year, location, duration);
                reggaePlaylist.add(songReggae);

            }
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
        for (Song s : reggaePlaylist) {
            System.out.println(s.getID() + "   " + s.gettitle() + "   "
                    + s.getartist() + "   " + s.getgenre() + "   "
                    + s.getyear() + "   " + s.getlocation() + "   "
                    + s.getduration() + "   ");
        }


        System.out.println("Goodbye!");
        }



    public static class SuffleSongs {

        public static void main(String[] args) {

            List<String> reggaePlaylist = new ArrayList<String>();
            reggaePlaylist.add("Song1");
            reggaePlaylist.add("Song2");
            reggaePlaylist.add("Song3");
            reggaePlaylist.add("Song4");
            reggaePlaylist.add("Song5");
            reggaePlaylist.add("Song6");
            reggaePlaylist.add("Song7");
            reggaePlaylist.add("Song8");
            reggaePlaylist.add("Song9");
            reggaePlaylist.add("Song10");
            reggaePlaylist.add("Song11");
            reggaePlaylist.add("Song12");
            reggaePlaylist.add("Song13");
            reggaePlaylist.add("Song14");
            reggaePlaylist.add("Song15");
            reggaePlaylist.add("Song16");
            reggaePlaylist.add("Song17");
            reggaePlaylist.add("Song18");
            reggaePlaylist.add("Song19");
            reggaePlaylist.add("Song20");
            reggaePlaylist.add("Song21");
            reggaePlaylist.add("Song22");
            reggaePlaylist.add("Song23");
            reggaePlaylist.add("Song24");
            reggaePlaylist.add("Song25");
            reggaePlaylist.add("Song26");
            reggaePlaylist.add("Song27");
            reggaePlaylist.add("Song28");


            // shuffle the playlist
            for (int i=0; i<reggaePlaylist.size(); ++i) {
                Random rand = new Random();
                int temp = rand.nextInt(reggaePlaylist.size() -i) + i;
                Collections.swap(reggaePlaylist, i, temp);
            }

            // print the shuffled playlist
            for(int j = 0; j < reggaePlaylist.size(); ++j) {
                System.out.println(reggaePlaylist.get(j));
            }

        }

    }
    }

/*

import java.util.Random;

public class SuffleSongs {

    public static void main(String[] args) {

        List<String> playList = new ArrayList<String>();
        playList.add("Song1");
        playList.add("Song2");
        playList.add("Song3");
        playList.add("Song4");
        playList.add("Song5");
        playList.add("Song6");
        playList.add("Song7");
        playList.add("Song8");
        playList.add("Song9");
        playList.add("Song10");
        playList.add("Song11");
        playList.add("Song12");
        playList.add("Song13");
        playList.add("Song14");
        playList.add("Song15");
        playList.add("Song16");
        playList.add("Song17");
        playList.add("Song18");
        playList.add("Song19");
        playList.add("Song20");
        playList.add("Song21");

        // shuffle the playlist
        for (int i=0; i<playList.size(); ++i) {
            Random rand = new Random();
            int temp = rand.nextInt(playList.size() -i) + i;
            Collections.swap(playList, i, temp);
        }

        // print the shuffled playlist
        for(int j = 0; j < playList.size(); ++j) {
            System.out.println(playList.get(j));
        }

    }

}
 */