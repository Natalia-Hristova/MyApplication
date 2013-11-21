package com.telerik.bugtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hristova on 11/15/13.
 */
public class BugStatusViewAdapter extends ArrayAdapter<BugStatusGroup> {
    private Context context;

    public BugStatusViewAdapter(Context context) {
        super(context, 0, BugStatusGroup.values());
        this.context = context;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        FrameLayout viewGroup = new FrameLayout(context);

        LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inf.inflate(R.layout.view_spinner_status, viewGroup);

        ImageView statusImage = (ImageView) viewGroup.findViewById(R.id.imageViewStatus);
        TextView statusText = (TextView) viewGroup.findViewById(R.id.textViewStatus);

        BugStatusGroup currentStatus = BugStatusGroup.values()[position];
        String stringStatus=currentStatus.toString();

        switch (currentStatus) {
            case AllBugs:
                stringStatus="All bugs";
                break;
            case NotDone:
                statusImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.bug_status_notdone));
                stringStatus="Not done";
                break;
            case Done:
                statusImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.bug_status_done));
                stringStatus="Done";
                break;
            case InProgress:
                statusImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.bug_status_inprogress));
                stringStatus="In progress";
                break;
        }

        statusText.setText(stringStatus);

        return viewGroup;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return getDropDownView(position, convertView, parent);
    }
}
