package fr.ensta.gleroy.comicbookreader.comicbookreader;

import java.io.File;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

import static android.R.attr.path;


public class MyFileFilter{
    File directory;
    File[] files;
    File path;
    public void MyFileFilter(){
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        Log.i("Files", "Path: " + path);
        //directory = new File(path);
        //files = path.listFiles();
    }
    public void viewFile(){

        Log.d("Files", "Size: "+ files.length);
        for (int i = 0; i < files.length; i++) {
            Log.d("Files", "FileName:" + files[i].getName());
        }
    }
    public void filterFile(){
        //ArrayList<String> filesFilter;
        for(int idFile = 0; idFile < files.length; idFile++)
        {
            if((files[idFile].getName().substring(files[idFile].getName().indexOf(".") + 1) == "cbz")
                    || (files[idFile].getName().substring(files[idFile].getName().indexOf(".") + 1) == "cbr"))
            {
                //filesFilter.add(filesFilter.size(),files[idFile]);
                //Log.i("instance initializer: ", files[idFile]);
            }
        }

    }
}