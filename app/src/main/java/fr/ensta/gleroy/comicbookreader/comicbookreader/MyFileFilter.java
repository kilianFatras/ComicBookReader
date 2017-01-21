package fr.ensta.gleroy.comicbookreader.comicbookreader;

import java.io.File;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

import static android.R.attr.path;


public class MyFileFilter{
    File[] files;
    File directory;
    ArrayList<String> booksName ;

    // Constructor
    MyFileFilter(){
        this.directory = new File(Environment.getExternalStorageDirectory() + File.separator + "ComicBooks");
        boolean success = true;
        if (!directory.exists()) {
            success = directory.mkdirs();
            Log.d("Folder","Created");
        }
        if (success) {
            // Do something on success
            Log.d("Folder", "Exist");
        } else {
            // Do something else on failure
            Log.e("Folder", "Can't be created !");
        }
        //this.directory = Environment.getRootDirectory();
        Log.i("Files", "Path: " + directory);

        this.files = directory.listFiles();
        Log.d("Files", "Size: "+ this.files.length);
    }
    public void viewFile(){

        Log.d("Files", "Size: "+ this.files.length);
        for (int i = 0; i < this.files.length; i++) {
            Log.d("Files", "FileName:" + this.files[i].getName());
        }
    }
    public void filterFile(){
        Log.d("Launch", "FilterFile method");
        this.booksName = new ArrayList<String>();
        for(int idFile = 0; idFile < this.files.length; idFile++) {
            String fileName = this.files[idFile].getName();

            if(fileName.toUpperCase().endsWith(".CBR") || fileName.toUpperCase().endsWith(".CBZ")) {
                this.booksName.add(this.booksName.size(),this.files[idFile].getName());
                Log.i("instance initializer: ", this.files[idFile].getName());
            }
        }

    }
}