package model.entries.concrete;

import model.entries.general.MediaFile;

public class Track extends MediaFile {

    public Track(String name, String path, Integer year) {
        super(name, path, year, "Track");
    }

}
