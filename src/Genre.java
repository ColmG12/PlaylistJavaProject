public class Genre {

    private int genreID;
    private String genreDescription;

    //No argument constructor
    public Genre(){
        this.genreID = 0;
        this.genreDescription = ("Unknown");
    }

    //2 argument constructor
    public Genre(int genreID, String genreDescription) {
        this.genreID = genreID;
        this.genreDescription = genreDescription;
    }

    //Getter
    public int getGenreID() {
        return genreID;
    }

    //Setter
    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    //Getter
    public String getgenreDescription() {
        return genreDescription;
    }

    //Setter
    public void setgenreDescription(String genreDescription) {
        this.genreDescription = genreDescription;
    }

    @Override
    public String toString() {
        return "Genre ID: " + genreID +
                "\nGenre Description: " + genreDescription;
    }
}
