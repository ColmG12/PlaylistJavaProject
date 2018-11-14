public class Artist {

    private int artistID;
    private String artistName;
    private String nationOfArtist;

    //No argument constructor
    public Artist(){
        this.artistID = 0;
        this.artistName = ("Unknown");
        this.nationOfArtist = ("Unknown");
    }

    //3 argument constructor
    public Artist(int artistID, String artistName, String nationOfArtist) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.nationOfArtist = nationOfArtist;
    }

    //Getter
    public int getArtistID() {
        return artistID;
    }

    //Setter
    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    //Getter
    public String getArtistName() {
        return artistName;
    }

    //Setter
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //Getter
    public String getnationOfArtist() {
        return nationOfArtist;
    }

    //Setter
    public void setnationOfArtist(String nationality) {
        this.nationOfArtist = nationOfArtist;
    }

    @Override
    public String toString() {
        return "Artist ID: " + artistID +
                "\nArtist Name: " + artistName +
                "\nNation Of Artist: " + nationOfArtist;
    }
}
