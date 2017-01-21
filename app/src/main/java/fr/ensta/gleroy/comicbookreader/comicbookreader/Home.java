package fr.ensta.gleroy.comicbookreader.comicbookreader;
import java.io.File;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.FileFilter;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("Alarm","Here we are");
        MyFileFilter testFileFilter = new MyFileFilter();
        testFileFilter.viewFile();
        //testFileFilter.filterFile();
    }

}
