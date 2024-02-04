package com.example.readandwritedata.Controls;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class Methods {
    public static File createOrGetDirectory() {
        File myDirectory = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS) + "/ReadAndWriteData");
        if (!myDirectory.exists()) {
            myDirectory.mkdirs();
        }
        return myDirectory;
    }

    public void saveToTextFile(String value, String fileName) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(Methods.createOrGetDirectory().toString() + fileName, true)));
            if (fileName.equalsIgnoreCase("/errors.txt")) {
                out.println(new Date());
            }
            out.println(value);
            out.close();

//            PrintWriter writer = new PrintWriter(Methods.createOrGetDirectory().toString() + fileName, "UTF-8");
//            writer.println(value);
//            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
