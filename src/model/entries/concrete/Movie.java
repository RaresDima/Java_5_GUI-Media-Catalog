package model.entries.concrete;

import model.entries.general.MediaFile;

public class Movie extends MediaFile {

    public Movie(String name, String path, Integer year) {
        super(name, path, year, "Movie");
    }

    public String toString() {
        StringBuilder buff = new StringBuilder("");

        buff.append("\nTYPE: ");
        buff.append(this.TYPE.get());
        buff.append("\nNAME: ");
        buff.append(this.NAME.get());
        buff.append("\nLAUNCH YEAR: ");
        buff.append(this.YEAR.get());
        buff.append("\nPATH: ");
        buff.append(this.PATH.get());
        buff.append("\n");

        return new String(buff);
    }
}
