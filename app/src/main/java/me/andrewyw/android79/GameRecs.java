package me.andrewyw.android79;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static me.andrewyw.android79.Chess.ct;

public class GameRecs implements Serializable{
    List<Recording> games;
    //FOR SERIALIZER
    public static final String storeFile = "records.dat";
    private static final long serialVersionUID = 1L;

    public GameRecs(){
        games = new ArrayList<Recording>();
    }


    //********************SERIALIZER********************
    public static void writeApp(GameRecs gr)	throws IOException {
        File file = new File(ct.getFilesDir(), storeFile);
        file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(gr);
        oos.close();
    }

    public static GameRecs readApp() throws IOException, ClassNotFoundException{
        File file = new File(ct.getFilesDir(), storeFile);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        GameRecs gr = (GameRecs)ois.readObject();
        ois.close();
        return gr;
    }
}
