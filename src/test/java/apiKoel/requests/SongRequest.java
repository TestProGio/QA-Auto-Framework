package apiKoel.requests;

public class SongRequest {
    private String song;

    public SongRequest(String song) {
        this.song = song;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }
}
