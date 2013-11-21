package com.telerik.bugtracker;

import android.content.Context;

import com.orm.SugarRecord;

import java.util.Calendar;

public class Bug extends SugarRecord<Bug> {
    static long bugCounter;
    public String title;
    public Integer priority = 0;
    public Calendar date;
    public Integer status = 0;
    public String description;
    //long bugId = 1;

    public Bug(Context context) {
        super(context);
    }

//    public Bug(Context context, String bugTitle) {
//        super(context);
//        title = bugTitle;
//        bugCounter++;
//        this.bugId = bugCounter;
//    }
}