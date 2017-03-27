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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static java.lang.System.out;

/**
 * Created by gleroy on 28/01/17.
 */

public class ZipBook {
    FileInputStream inputStream;
    ZipInputStream zipStream;
    ZipEntry zEntry;
    ZipFile zipFile;
    String fileName;
    public ArrayList<File> pages = new ArrayList<File>();

    ZipBook(File inputFile){
        try {
            zipFile = new ZipFile(inputFile);
            inputStream = new FileInputStream(inputFile);
            zipStream = new ZipInputStream(inputStream);
            fileName = inputFile.getName();


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

    public int getAllPages(){
        try {
            String comicBookPath = Environment.getExternalStorageDirectory() + File.separator + "ComicBooks";
            while ((zEntry = zipStream.getNextEntry()) != null) {
                String filePath = comicBookPath + File.separator + zEntry.getName();
                Log.d("filepath is : ", filePath);
                Log.d("Unzip", "Unzipping " + zEntry.getName());
                if(zEntry.isDirectory()) {
                    Log.i("Directory", zEntry.getName());
                    File directory = new File(filePath);
                    directory.mkdirs();

                } else if(zEntry.getName().endsWith(".jpeg")){

                    File image = new File(filePath);
                    if(!(new File(filePath)).exists()){
                        BufferedInputStream is = new BufferedInputStream(zipFile.getInputStream(zEntry));

                        // establish buffer for writing file
                        int BUFFER = (int) zEntry.getSize();
                        byte data[] = new byte[BUFFER];

                        is.read(data, 0, BUFFER);

                        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                        FileOutputStream bmout = null;

                        try {
                            //Log.d("UNCOMPRESS STATUS page ", );

                            bmout = new FileOutputStream(image);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bmout); // bmp is your Bitmap instance

                        } catch (Exception e) {

                            e.printStackTrace();
                            Log.d("FUCK", "GENRE ENORME FUCK !!!!");
                        } finally {
                            try {
                                if (bmout != null) {
                                    bmout.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                        is.close();
                        zipStream.closeEntry();
                    }
                    pages.add(image);

                }
                zipStream.closeEntry();
            }
            return 1;

        }catch (Exception e){
            Log.d("Unzip", "Unzipping failed");
            e.printStackTrace();
            return 0;
        }

    }

    public Bitmap getPageBm(int pageNumber){
        File file = pages.get(pageNumber);
        Bitmap bMap = BitmapFactory.decodeFile(file.getAbsolutePath());
        return bMap;
    }
}