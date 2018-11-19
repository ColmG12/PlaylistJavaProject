public class Song {

    private int ID;
    private String title;
    private String artist;
    private String genre;
    private int year;
    private String location;
    private int duration;

    //No argument constructor
    public Song(){
        this.ID = 0;
        this.title = ("Unknown");
        this.artist = ("Unknown");
        this.genre = ("Unknown");
        this.year = 0;
        this.location = ("Unknown");
        this.duration = 0;
    }
    // 7 argument constructor
    public Song(int ID, String title, String artist, String genre,
                int year, String location, int duration) {
        setID(ID);
        settitle(title);
        setartist(artist);
        setgenre(genre);
        setyear(year);
        setlocation(location);
        setduration(duration);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getartist() {
        return artist;}

    public void setartist(String artist) {
        this.artist = artist;
    }

    public String getgenre() {
        return genre;}

    public void setgenre(String genre) {
        this.genre = genre;
    }

    public int getyear() {

        return year;
    }

    public void setyear(int year) {

        this.year = year;
    }

    public String getlocation() {

        return location;
    }

    public void setlocation(String location) {

        this.location = location;
    }

    public int getduration() {

        return duration;
    }

    public void setduration(int duration) {

        this.duration = duration;
    }


    @Override
    public String toString() {
        return "Song ID: " + ID +
                "\nSong Title: " + title +
                "\nArtist: " + artist +
                "\nGenre: " + genre +
                "\nYear: " + year +
                "\nLocation: " + location +
                "\nDuration: " + duration;
    }


}
