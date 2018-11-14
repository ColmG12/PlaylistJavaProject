public class SongGenres {

    private int songGenresID;
    private Genre genre;
    private Song song;

    //No argument constructor
    public songGenres(){
        this.songGenresID = 0;
        this.genre = new Genre (0, "Unknown");
        this.song = new Song (0, "Unknown", 0, 0, 0, 0,
        )
    }

    public SongGenres(int songGenres, Genre genre, Song song) {
        this.songGenres = songGenres;
        this.genre = genre;
        this.song = song;
    }
}
