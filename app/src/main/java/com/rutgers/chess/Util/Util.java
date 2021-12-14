package com.rutgers.chess.Util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.rutgers.chess.MainActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Util {
    private static final String storeDir = "data";

    public static void writeSave(ArrayList<ChessMove> moves, String name) {
        try {
            //storeDir + File.separator +
            File file = new File(storeDir + File.separator +name);
            FileOutputStream fos = new FileOutputStream(storeDir + File.separator +name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(moves);
            oos.close();
            fos.close();

        } catch (IOException ioe) {
            MainActivity.getInstance().printCorruptSave();
        }
    }


    public static ArrayList<ChessMove> readUserList(String name) {
        ArrayList<ChessMove> moves = new ArrayList<ChessMove>();

        try {
            FileInputStream fis = new FileInputStream(storeDir + File.separator + name);
            ObjectInputStream ois = new ObjectInputStream(fis);

            moves = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }  catch (IOException ioe) {
            MainActivity.getInstance().printCorruptSave();
            return null;
        }  catch (ClassNotFoundException c)  {
            MainActivity.getInstance().printCorruptSave();
            return null;
        }

        return moves;
    }
}
