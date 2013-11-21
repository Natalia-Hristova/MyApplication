package com.telerik.bugtracker;

import android.content.Context;
import android.support.v4.app.NavUtils;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;


public class StatisticsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

// define chart
        Context appContext = getApplicationContext();

//        ViewGroup root = (ViewGroup) findViewById(R.id.pieView);
//        root.addView(createPieChart(), 0);

       getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

//    public View createPieChart() {
//        RadPieChart pieChart = new RadPieChart(this);
//        initData();
//
//        PieSeries pieSeries = new PieSeries(this);
//
//        GenericDataPointBinding dpb = new GenericDataPointBinding();
//        dpb.ValueSelector = new Function() {
//            @Override
//            public Object apply(Object argument) {
//                return ((IntegerData) argument).value;
//            }
//        };
//        pieSeries.setValueBinding(dpb);
//
//        pieSeries.setItemsSource(data);
//        pieChart.getSeries().add(pieSeries);
//
//        return pieChart;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.statistics, menu);
        return true;

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
            return rootView;
        }
    }

}
