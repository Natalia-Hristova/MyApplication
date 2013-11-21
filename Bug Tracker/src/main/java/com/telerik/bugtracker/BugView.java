package com.telerik.bugtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hristova on 11/7/13.
 */
public class BugView extends RelativeLayout {
    private String title;
    private BugPriority priority;
    private BugStatus status;
    private String description;

    ImageView statusImage;
    TextView titleView;
    TextView dateView;
    Context context;

    public BugView(Context context) {
        super(context);
        LayoutInflater inf = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inf.inflate(R.layout.view_bug, this);
        this.context = context;
        this.statusImage = (ImageView)this.findViewById(R.id.imageView);
        this.titleView = (TextView)this.findViewById(R.id.titleView);
        this.dateView = (TextView)this.findViewById(R.id.dateView);
    }

    public void setTitle(String title) {
        this.title = title;
        this.titleView.setText(title);
    }

    public void setDate(Calendar date) {
        if(date == null){
            this.dateView.setText("");
            return;
        }

        SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        this.dateView.setText(format.format(date.getTime()));
    }

    public void setPriority(BugPriority priority) {
        this.priority = priority;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
        switch (status)
        {
            case NotDone:
            this.statusImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.bug_status_notdone));
                break;
           case Done:
               this.statusImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.bug_status_done));
               break;
           case InProgress:
               this.statusImage.setImageDrawable(this.context.getResources().getDrawable(R.drawable.bug_status_inprogress));
               break;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
