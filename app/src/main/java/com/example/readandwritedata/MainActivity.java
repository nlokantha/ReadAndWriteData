package com.example.readandwritedata;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.readandwritedata.Controls.Methods;

import java.io.File;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_WRITE = 1;
    Methods methods=new Methods();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_WRITE);
        } else {
            Methods.createOrGetDirectory();
        }

        methods.saveToTextFile("Hello I am Namal","/Test.txt");




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode==REQUEST_CODE_WRITE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Methods.createOrGetDirectory();
            }else {
                Toast.makeText(this, "Permission Denied, Unable to create folder.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createFolder() {
        String folderName = "READANDWRITE";
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)){
            File folder = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS), folderName);
            if (!folder.exists()) {
                if (folder.mkdirs()) {
                    Toast.makeText(this, "Folder created successfully.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Failed to create folder.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Folder already exists.", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "External storage is not available or writable.", Toast.LENGTH_SHORT).show();
        }
    }

}