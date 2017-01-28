package fr.ensta.gleroy.comicbookreader.comicbookreader;

import android.graphics.Bitmap;

/**
 * Created by gleroy on 20/01/17.
 */

public class ImageItem {
    private Bitmap image;
    private String title;
    private String fileName;

    public ImageItem(Bitmap image, String title, String fileName) {
        super();
        this.image = image;
        this.title = title;
        this.fileName = fileName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() { return fileName; }
}