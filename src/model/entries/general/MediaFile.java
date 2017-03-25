package model.entries.general;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class MediaFile {
    public final StringProperty TYPE;
    public final StringProperty  PATH;
    public final StringProperty  NAME;
    public final IntegerProperty YEAR;

    protected MediaFile(String name, String path, Integer year, String type) {
        this.TYPE = new SimpleStringProperty(type);
        this.PATH = new SimpleStringProperty(path);
        this.NAME = new SimpleStringProperty(name);
        this.YEAR = new SimpleIntegerProperty(year);
    }

}