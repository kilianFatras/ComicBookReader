package fr.ensta.gleroy.comicbookreader.comicbookreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * Created by gleroy on 28/01/17.
 */

public class ZipBook {
    FileInputStream inputStream;
    ZipInputStream zipStream;
    ZipEntry zEntry;
    ZipFile zipFile;

    ZipBook(File inputFile){
        try {
            zipFile = new ZipFile(inputFile);
            inputStream = new FileInputStream(inputFile);
            zipStream = new ZipInputStream(inputStream);

        }catch (Exception e) {
            Log.d("Unzip", "Unzipping failed");
            e.printStackTrace();
        }
    }

    public Bitmap getPageNumber(int numberPage){
        try {
            int cpt = 1;
            while ((zEntry = zipStream.getNextEntry()) != null) {
                Log.d("Unzip", "Unzipping " + zEntry.getName());
                if(zEntry.isDirectory()){
                    Log.i("Directory", zEntry.getName());
                }else {
                    if(zEntry.getName().endsWith(".jpeg")){
                        if(cpt == numberPage){
                            BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(zEntry));

                            // establish buffer for writing file
                            int BUFFER = (int) zEntry.getSize();
                            byte data[] = new byte[BUFFER];

                            is.read(data, 0, BUFFER);


                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                            is.close();
                            zipStream.closeEntry();
                            return bitmap;
                        }
                        cpt++;
                    }
                }
                zipStream.closeEntry();
            }

        }catch (Exception e){
            Log.d("Unzip", "Unzipping failed");
            e.printStackTrace();
        }
        return null;
    }
}
