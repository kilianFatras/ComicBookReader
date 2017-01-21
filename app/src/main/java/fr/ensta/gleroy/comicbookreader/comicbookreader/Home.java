package fr.ensta.gleroy.comicbookreader.comicbookreader;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.io.File;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MyFileFilter testFileFilter = new MyFileFilter();
        testFileFilter.viewFile();
        ArrayList<String> booksName = testFileFilter.filterFile();

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData(booksName));
        gridView.setAdapter(gridAdapter);


    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData(ArrayList<String> booksName) {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();

        for (int i = 0; i < booksName.size(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
            String fileName = booksName.get(i);
            // /!\ Revoir la facon de retirer l'extension si different de 4 caracteres.
            String bookName = fileName.substring(0, fileName.length() - 4);
            imageItems.add(new ImageItem(bitmap, bookName));
        }
        return imageItems;
    }
}
