public class Artist {

    private int artistID;
    private String artistName;
    private String nationality;

    //No argument constructor
    public Artist(){
        this.artistID = 0;
        this.artistName = ("Unknown");
        this.nationality = ("Unknown");
    }

    public Artist(int artistID, String artistName, String nationality) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.nationality = nationality;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Artist ID: " + artistID +
                "\nArtist Name: " + artistName +
                "\nNationality: " + nationality;
    }
}
