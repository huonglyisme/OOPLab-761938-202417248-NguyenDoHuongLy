package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private static int nbCompactDiscs = 0;

    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title) {
        super(++nbCompactDiscs, title, null, 0);
    }

    public CompactDisc(String title, String category, float cost) {
        super(++nbCompactDiscs, title, category, cost);
    }

    public CompactDisc(String title, String category, float cost, String artist) {
        super(++nbCompactDiscs, title, category, cost);
        this.artist = artist;
    }

    public CompactDisc(String title, String category, float cost, String artist, String director, int length) {
        super(++nbCompactDiscs, title, category, cost, director, length);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (track == null) {
            return;
        }

        if (tracks.contains(track)) {
            System.out.println("The track already exists.");
        } else {
            tracks.add(track);
            System.out.println("The track has been added.");
        }
    }

    public void removeTrack(Track track) {
        if (track == null) {
            return;
        }

        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("The track has been removed.");
        } else {
            System.out.println("The track was not found.");
        }
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("ERROR: CD length is non-positive!");
            return;
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD length: " + getLength());

        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        return "CD - " + getTitle() + " - " + getCategory() + " - " + artist + " - " + getCost() + " $";
    }
}
