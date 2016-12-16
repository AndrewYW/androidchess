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

public class Recording implements Serializable{
    String name;
    SimpleDate date;
    List<String> commands;
    int plys;
    //FOR SERIALIZER
    public static final String storeDir = "dat";
    public static final String storeFile = "rec.dat";
    private static final long serialVersionUID = 1L;

    public Recording(){
        name = "";
        date = null;
        commands = new ArrayList<String>();
        plys = 0;
    }

    public void addMove(String command){
        commands.add(command);
        plys++;
    }
    public String getCommand(int i){
        return commands.get(i);
    }

    public int turns(){
        return plys;
    }

    public void remLast(){
        commands.remove(plys-1);
        plys--;
    }

    public Recording save(String n){
        this.name = n;
        date = new SimpleDate(System.currentTimeMillis()/*86400000*/);
        return this;
    }

    public String toString(){
        return name + "\n(" + date.toString() + ")";
    }


    //********************SERIALIZER********************
    public static void writeApp(Recording rec)	throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storeDir + File.separator + storeFile));
        oos.writeObject(rec);
        oos.close();
    }

    public static Recording readApp() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storeDir+File.separator+storeFile));
        Recording rec = (Recording)ois.readObject();
        ois.close();
        return rec;
    }
}