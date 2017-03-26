package model.entries.concrete;

import model.entries.general.MediaFile;

public class Movie extends MediaFile {

    public Movie(String name, String path, Integer year) {
        super(name, path, year, "Movie");
    }


}
