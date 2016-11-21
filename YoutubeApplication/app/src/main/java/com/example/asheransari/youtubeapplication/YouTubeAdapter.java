package com.example.asheransari.youtubeapplication;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class YouTubeAdapter extends ArrayAdapter<CustomClass>{

    public YouTubeAdapter(Activity activity, ArrayList<CustomClass> arrayList)
    {
        super(activity,0,arrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        CustomClass customClass = getItem(position);

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageID);
        imageView.setImageResource(customClass.getImageSource());

        TextView textView = (TextView) listItemView.findViewById(R.id.detailID);
        textView.setText(customClass.getDetail());

        TextView textView1 = (TextView)listItemView.findViewById(R.id.tempData);
        textView1.setText(customClass.getTempData());

        TextView circleView = (TextView)listItemView.findViewById(R.id.rating);
        circleView.setText((String.valueOf(customClass.getNumber())));

        GradientDrawable magnitudeDrawabale = (GradientDrawable)circleView.getBackground();

        int magnitudeColor = getMagnitudeColor(customClass.getNumber());

        magnitudeDrawabale.setColor(magnitudeColor);

        return listItemView;
    }
    private int getMagnitudeColor(double magnitude)
    {
        int magnitudeColorResourceID;
        int magnitudeFloor = (int)Math.floor(magnitude);

        switch(magnitudeFloor)
        {
            case 1:
                magnitudeColorResourceID = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceID = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceID = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceID = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceID = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceID = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceID = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceID = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceID = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceID = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceID);
    }

}
