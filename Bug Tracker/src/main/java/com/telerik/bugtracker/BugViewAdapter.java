package com.telerik.bugtracker;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hristova on 11/7/13.
 */
public class BugViewAdapter extends ArrayAdapter<Bug> {

    private Context context;

    public BugViewAdapter(Context context, List<Bug> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        BugView result = (BugView) convertView;
        if (result == null) {
            result = new BugView(context);
        }

        Bug bug = this.getItem(position);
        result.setTitle(bug.title);
        result.setDate(bug.date);
        result.setPriority(BugPriority.values()[bug.priority]);
        result.setStatus(BugStatus.values()[bug.status]);
        result.setDescription(bug.description);

        return result;
    }

}
