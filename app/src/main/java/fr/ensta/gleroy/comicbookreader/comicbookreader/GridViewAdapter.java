package fr.ensta.gleroy.comicbookreader.comicbookreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gleroy on 20/01/17.
 */

public class GridViewAdapter extends ArrayAdapter {
    private Context context;
    private int layoutResourceId;
    private ArrayList data = new ArrayList();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = (ImageItem) data.get(position);
        holder.imageTitle.setText(item.getTitle());
        holder.image.setImageBitmap(item.getImage());
//        holder.image.setOnClickListener(new MyLovelyOnClickListener(item.getFileName()));
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

//    public class MyLovelyOnClickListener implements View.OnClickListener
//    {
//
//        String fileName;
//        public MyLovelyOnClickListener(String filename) {
//            this.fileName = filename;
//        }
//
//        @Override
//        public void onClick(View v)
//        {
//            Log.i("Click on", fileName);
//            //Intent intent = new Intent(this, Book.class);
//            //ImageItem book = (ImageItem) view;
//            //String message = editText.getText().toString();
//            //intent.putExtra(EXTRA_MESSAGE, message);
//            //startActivity(intent);
//        }
//
//    };
}

