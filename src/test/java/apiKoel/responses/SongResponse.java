package apiKoel.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SongResponse {
    @JsonProperty("liked")
    private boolean liked;

    @JsonProperty("song_id")  // Use @JsonProperty to map JSON field
    private String songId; // Change the variable name to follow Java naming conventions

    @JsonProperty("play_count") // Use @JsonProperty to map JSON field
    private int playCount; // Change the variable name to follow Java naming conventions

    // Getters and setters
    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
