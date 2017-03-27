package fr.ensta.gleroy.comicbookreader.comicbookreader;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class Home extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MyFileFilter testFileFilter = new MyFileFilter();
        testFileFilter.viewFile();
        ArrayList<File> booksFile = testFileFilter.filterFile();

        gridView = (GridView) findViewById(R.id.gridView);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, getData(booksFile));
        gridView.setAdapter(gridAdapter);

        Log.d("Nombre d'enfant", Integer.toString(((GridView)gridView).getChildCount()));
        for(int index=0; index<((GridView)gridView).getChildCount(); ++index) {
            View nextChild = ((GridView)gridView).getChildAt(index);
            Log.d("view name",nextChild.getTag().toString());
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                final TextView mTextView = (TextView)view;
                Log.d("Position",Integer.toString(position));
                /*switch (position) {
                    case 0:
                        Intent newActivity0 = new Intent(DialogActivity.this,NewActivity0.class);
                        startActivity(newActivity0);
                        break;
                    case 1:
                        Intent newActivity1 = new Intent(DialogActivity.this,NewActivity1.class);
                        startActivity(newActivity1);
                        break;
                    case 0:
                        Intent newActivity2 = new Intent(DialogActivity.this,NewActivity2.class);
                        startActivity(newActivity2);
                        break;
                    case 0:
                        Intent newActivity3 = new Intent(DialogActivity.this,NewActivity3.class);
                        startActivity(newActivity3);
                        break;
                    default:
                        // Nothing do!
                }*/

            }
        });

    }


    public void openBook(View view){
        Intent intent = new Intent(this, Book.class);
        Log.d("open Book", "openBook: ");
        //ImageItem book = (ImageItem) view;
        //String message = editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    // Prepare some dummy data for gridview
    private ArrayList<ImageItem> getData(ArrayList<File> booksFile) {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();

        for (int i = 0; i < booksFile.size(); i++) {
            ZipBook book = new ZipBook(booksFile.get(i));
            Bitmap bitmap = book.getPageNumber(1);
            String fileName = booksFile.get(i).getName();
            // /!\ Revoir la facon de retirer l'extension si different de 4 caracteres.
            String bookName = fileName.substring(0, fileName.length() - 4);
            imageItems.add(new ImageItem(bitmap, bookName, fileName));
        }
        return imageItems;
    }


}
