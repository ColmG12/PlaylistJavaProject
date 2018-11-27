// Read data from file

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.Statement;

//package com.javainterviewpoint;

import java.util.ArrayList;
import java.util.List;


//package com.javainterviewpoint;

/*
public class ReadFile {

    //private int ID;
    private String title;
    private String artist;
    private String genre;
    //private int year;
    private String location;
    //private int duration;

    public ReadFile(String line) {
        String[] split = line.split(",");
        //ID = Integer.parseInt(split[0]);
        title = split[1];
        artist = split[2];
        genre = split[3];
        //year = Integer.parseInt(split[4]);
        location = split[5];
        //duration = Integer.parseInt(split[6]);
    }

    @Override
    public String toString() {
        return //"Song ID: " + ID +
                "\nSong Title: " + title +
                "\nArtist: " + artist +
                "\nGenre: " + genre +
                //"\nYear: " + year +
                "\nLocation: " + location;
               // "\nDuration: " + duration;
    }


    public static void main(String[] args) throws IOException {

        DBConnection dbConn = new DBConnection(); //establish a connection to the MariaDB
        Connection conn = dbConn.getConnection(); //get a reference to the database connection object
        Statement stmt = null;



        File f1 = new File("ColmsSongDatabase.csv");
        Scanner scanner = new Scanner(f1);
        List<Song> tuneList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            //String[] data = {"ID", "Title", "Artist", "Genre", "Year", "Location", "Duration"};
            String[] data = scanner.nextLine().split(",");
            tuneList.add(new Song(data[0], data[1], data[2], data[3]));
        }
        scanner.close();
        System.out.println(tuneList);
    }
}

*/