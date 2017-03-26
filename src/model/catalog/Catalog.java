package model.catalog;

import java.awt.*;
import java.io.*;
import java.util.Scanner;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.entries.concrete.*;
import model.entries.general.*;
import model.exception.exceptions.*;
import model.exception.handler.*;

public class Catalog {
    private ObservableList<MediaFile> contents;

    public Catalog() {
        contents = FXCollections.observableArrayList();
    }

    private void validateName(StringProperty name)  throws IllegalNameException {
        for (MediaFile m : contents)
            if (m.NAME.get().equals(name.get()))
                throw new IllegalNameException();
    }
    private void validateYear(IntegerProperty year) throws IllegalYearException     {
        if (year.get() < 0)
            throw new IllegalArgumentException();
    }
    private void validatePath(StringProperty path)  throws IllegalPathException {
        if (!path.get().matches("[dD]:\\\\([\\w ]+\\\\)*[\\w ]+\\.mp[34]"))
            throw new IllegalPathException();

    }

    public void add(MediaFile f)  {
        try {
            validateName(f.NAME);
            validateYear(f.YEAR);
            validatePath(f.PATH);
            contents.add(f);
        }
        catch (IllegalNameException e) { ExceptionHandler.illegalName(); e.printStackTrace(); }
        catch (IllegalYearException e) { ExceptionHandler.illegalYear(); e.printStackTrace(); }
        catch (IllegalPathException e) { ExceptionHandler.illegalPath(); e.printStackTrace(); }
    }
    public void list()            {
        for (MediaFile f : contents) {
            System.out.println(f.toStringConsole());
        }
    }
    public void play(String name) {
        try {
            int i = -1;
            for (MediaFile f : this.contents) // Look for a file with the associated name
                if (f.NAME.get().equals(name))
                    i = this.contents.indexOf(f);
            if (i == -1)
                throw new NoSuchMediaException(); // Not found exception
            System.out.println("Catalog.play " + i);
            Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + this.contents.get(i).PATH.get());}
        catch (IOException e)          { ExceptionHandler.IO(); e.printStackTrace(); }
        catch (NoSuchMediaException e) { ExceptionHandler.noSuchMedia(); e.printStackTrace(); }
    }
    public void save(String fileName) {
        try {
            BufferedWriter fd = new BufferedWriter(new FileWriter(new File(fileName)));

            fd.write("startFile\n");
            for (MediaFile media : this.contents) {
                fd.write("startObject\n");
                fd.write(media.TYPE.get() + "\n");
                fd.write(media.NAME.get() + "\n");
                fd.write(media.YEAR.get() + "\n");
                fd.write(media.PATH.get() + "\n");
            }
            fd.write("endFile");
            fd.close();
        }
        catch (IOException e)          { ExceptionHandler.IO();          e.printStackTrace(); }
        catch (IllegalPathException e) { ExceptionHandler.illegalPath(); e.printStackTrace(); }
    }
    public void load(String path) {
        try {
            BufferedReader fd = new BufferedReader(new FileReader(new File(path)));
            StringProperty tokenBuff = new SimpleStringProperty();
            StringProperty nameBuff  = new SimpleStringProperty();
            StringProperty pathBuff  = new SimpleStringProperty();
            IntegerProperty yearBuff = new SimpleIntegerProperty();

            this.contents.clear();

            tokenBuff.set(fd.readLine());
            System.out.println("FOUND: " + tokenBuff);
            if (!tokenBuff.get().startsWith("startFile")) // Does the file have a valid start token?
                throw new BadFileException();

            while (true) {

                tokenBuff.set(fd.readLine());

                System.out.println("FOUND: " + tokenBuff);

                if (!tokenBuff.get().startsWith("startObject") && !tokenBuff.get().startsWith("endFile")) // The next token must either mark an object or a valid end of file
                    throw new BadFileException();

                if (tokenBuff.get().startsWith("endFile")) // End of file token is found, stop trying to read from the file
                    break;

                tokenBuff.set(fd.readLine());
                if (!tokenBuff.get().startsWith("Movie") && !tokenBuff.get().startsWith("Track")) //An invalid object type was found
                    throw new BadFileException();

                // Being here means a valid object representation is found

                // Read name, year and path of object
                nameBuff.set(fd.readLine());
                yearBuff.set(Integer.parseInt(fd.readLine()));
                pathBuff.set(fd.readLine());

                // Validate dread data
                validateName(nameBuff);
                validateYear(yearBuff);
                validatePath(pathBuff);

                // Add to the current catalog
                if (tokenBuff.get().startsWith("Movie"))
                    contents.add(new Movie(nameBuff.get(), pathBuff.get() ,yearBuff.get()));
                if (tokenBuff.get().startsWith("Track"))
                    contents.add(new Track(nameBuff.get(), pathBuff.get() ,yearBuff.get()));
            }
            fd.close();
        }
        catch (IOException e)          { ExceptionHandler.IO();          e.printStackTrace(); }
        catch (BadFileException e)     { ExceptionHandler.badFile();     e.printStackTrace(); }
        catch (IllegalNameException e) { ExceptionHandler.illegalName(); e.printStackTrace(); }
        catch (IllegalYearException e) { ExceptionHandler.illegalYear(); e.printStackTrace(); }
        catch (IllegalPathException e) { ExceptionHandler.illegalPath(); e.printStackTrace(); }
    }

    public ObservableList<MediaFile> getContents() {
        return contents;
    }

    public void start() {
        Scanner user = new Scanner(System.in);
        String commandBuffer;
        String[] parsedCommand;
        Boolean exit = false;

        while (!exit) {
            System.out.print("command> ");
            commandBuffer = user.nextLine();
            parsedCommand = commandBuffer.split(" */ *");

            try {
                switch (parsedCommand[0]) {
                    case "play":
                        if (parsedCommand.length < 2)
                            throw new IllegalCommandException();
                        this.play(parsedCommand[1]);
                        break;
                    case "add":
                        if (parsedCommand.length < 5)
                            throw new IllegalCommandException();
                        if (parsedCommand[1].startsWith("Track")) {
                            this.add(new Track(parsedCommand[2], parsedCommand[4], Integer.parseInt(parsedCommand[3])));
                            break;
                        }
                        if (parsedCommand[1].startsWith("Movie")) {
                            this.add(new Movie(parsedCommand[2], parsedCommand[4], Integer.parseInt(parsedCommand[3])));
                            break;
                        }
                        throw new IllegalCommandException();
                    case "list":
                        this.list();
                        break;
                    case "save":
                        if (parsedCommand.length < 2)
                            throw new IllegalCommandException();
                        this.save(parsedCommand[1]);
                        break;
                    case "load":
                        if (parsedCommand.length < 2)
                            throw new IllegalCommandException();
                        this.load(parsedCommand[1]);
                        break;
                    case "help":
                        System.out.println("The available commands are:");
                        System.out.println("play/<name>");
                        System.out.println("add/<type>/<name>/<launch_year>/<path>");
                        System.out.println("list");
                        System.out.println("save/<file/path>");
                        System.out.println("load/<file/path>");
                        System.out.println("help");
                        System.out.println("quit");
                        break;
                    case "quit":
                        exit = true;
                        break;
                    default:
                        throw new IllegalCommandException();
                }
            } catch (IllegalCommandException e) {
                ExceptionHandler.illegalCommand();
                e.printStackTrace();

            }
        }
    }
}
