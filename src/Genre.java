public class Genre {

    private String genreID;
    private String description;

    //No argument constructor
    public Genre(){
        this.genreID = ("Unknown");
        this.description = ("Unknown");
    }

    //2 argument constructor
    public Genre(String genreID, String description) {
        this.genreID = genreID;
        this.description = description;
    }

    public String getGenreID() {
        return genreID;
    }

    public void setGenreID(String genreID) {
        this.genreID = genreID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Genre ID: " + genreID +
                "\nDescription: " + description;
    }
}
