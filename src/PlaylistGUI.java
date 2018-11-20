
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
//import javax.swing.JPopupMenu;
//import java.awt.FlowLayout;

/*import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;*/

//package com.javainterviewpoint;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaylistGUI
{
    //Delimiters used in the CSV file
    private static final String COMMA_DELIMITER = ",";

    public static void main(String args[])
    {
        BufferedReader br = null;
        try
        {
            //Reading the csv file
            br = new BufferedReader(new FileReader("ColmsSongDatabase.csv"));

            //Create List for holding Employee objects
            List<Song> songList = new ArrayList<Song>();

            String line = "";
            //Read to skip the header
            br.readLine();
            //Reading from the second line
            while ((line = br.readLine()) != null)
            {
                String[] songDetails = line.split(COMMA_DELIMITER);


                if(songDetails.length > 0 )
                {
                    //Save the employee details in Employee object
                    Song song = new Song(Integer.parseInt(songDetails[0]),
                            songDetails[1], songDetails[2],
                            songDetails[3], Integer.parseInt(songDetails[4]),
                            songDetails[5], Integer.parseInt(songDetails[6]));
                    songList.add(song);
                }
            }

            //Lets print the Employee List
            for(Song s : songList)
            {
                System.out.println(s.getID()+"   "+s.gettitle()+"   "
                        +s.getartist()+"   "+s.getgenre()+"   "
                        +s.getyear()+"   "+s.getlocation()+"   "
                        +s.getduration()+"   ");
            }
        }
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
        }
    }
}


/*public class PlaylistGUI {

    // Constructor
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
