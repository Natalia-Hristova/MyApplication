package com.telerik.bugtracker;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by hristova on 11/12/13.
 */
public class NewActivity extends EditActivity {
    static Bug newBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentBug = new Bug(this);
    }

    protected int getLayoutId() {
        return R.layout.activity_new;
    }

    @Override
    public void onBackPressed() {
        this.currentBug.delete();
        super.onBackPressed();
    }

    public static Bug getNewBug() {
        Bug result = newBug;
        newBug=null;
        return result;
    }

    @Override
    protected void onButtonDoneClick(){
        newBug=currentBug;
        super.onButtonDoneClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.newbug, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_done:
                onButtonDoneClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
