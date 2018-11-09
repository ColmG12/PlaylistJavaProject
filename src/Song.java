public class Song {

    private int songID;
    private String songTitle;
    private int year;
    private int durationMins;
    private int durationSecs;
    private int chartPeak;
    private Genre genre;
    private Artist artist;
    //Audio file location

    //No argument constructor
    public Song(){
        this.songID = 0;
        this.songTitle = ("Unknown");
        this.year = 0;
        this.durationMins = 0;
        this.durationSecs = 0;
        this.chartPeak = 0;
        this.genre = new Genre("Unknown", "Unknown");
        this.artist = new Artist(0, "Unknown", "Unknown");
    }
    // 8 argument constructor
    public Song(int songID, String songTitle, int year, int durationMins,
                int durationSecs, int chartPeak, Genre genre, Artist artist) {
        setSongID(songID);
        setSongTitle(songTitle);
        setYear(year);
        setDurationMins(durationMins);
        setDurationSecs(durationSecs);
        setChartPeak(chartPeak);
        setGenre(genre);
        setArtist(artist);
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDurationMins() {
        return durationMins;
    }

    public void setDurationMins(int durationMins) {
        this.durationMins = durationMins;
    }

    public int getDurationSecs() {
        return durationSecs;
    }

    public void setDurationSecs(int durationSecs) {
        this.durationSecs = durationSecs;
    }

    public int getChartPeak() {
        return chartPeak;
    }

    public void setChartPeak(int chartPeak) {
        this.chartPeak = chartPeak;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "Song ID: " + songID +
                "\nSong Title='" + songTitle +
                "\nYear: " + year +
                "\nDuration in Mins: " + durationMins +
                "\nDuration in Secs: " + durationSecs +
                "\nChart Peak: " + chartPeak +
                "\nGenre: " + genre.toString() +
                "\nArtist: " + artist.toString();
    }
}
