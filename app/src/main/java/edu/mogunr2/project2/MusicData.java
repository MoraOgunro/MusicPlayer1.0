package edu.mogunr2.project2;

public class MusicData {
    private String title;
    private String artist;
    private String songWikipediaLink;
    private String artistWikipediaLink;
    private int coverID;
    private String songLink;
    public MusicData(String title, String artist, int coverID, String songLink, String swl, String awl){
       this.title = title;
       this.artist = artist;
       this.coverID = coverID;
       this.songLink = songLink;
       this.songWikipediaLink = swl;
       this.artistWikipediaLink = awl;
    }

    public String getTitle() {
        return title;
    }

    public int getCoverID() {
        return coverID;
    }

    public String getSongLink() {
        return songLink;
    }

    public String getArtist() {
        return artist;
    }

    public String getArtistWikipediaLink() {
        return artistWikipediaLink;
    }

    public String getSongWikipediaLink() {
        return songWikipediaLink;
    }
}
