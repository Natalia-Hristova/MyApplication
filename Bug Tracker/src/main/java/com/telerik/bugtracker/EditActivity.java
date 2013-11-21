package com.telerik.bugtracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends ActionBarActivity {
    protected Bug currentBug;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        Spinner priority = (Spinner) this.findViewById(R.id.spinnerPriority);
        ArrayAdapter<BugPriority> adapter = new ArrayAdapter<BugPriority>(this, android.R.layout.simple_list_item_1, android.R.id.text1, BugPriority.values());
        priority.setAdapter(adapter);

        Spinner status = (Spinner) this.findViewById(R.id.spinnerStatus);
        status.setAdapter(new ArrayAdapter<BugStatus>(this, android.R.layout.simple_list_item_1, android.R.id.text1, BugStatus.values()));

        int bugPosition = getIntent().getIntExtra("position", -1);

        if (bugPosition == -1) {
            return;
        }

        currentBug = MainActivity.adapter.getItem(bugPosition);

        TextView bugNumberField = ((TextView) findViewById(R.id.plainTextViewNumber));
        bugNumberField.setText(String.valueOf(currentBug.getId()));

        TextView titleField = ((TextView) findViewById(R.id.editTextTitle));
        titleField.setText(currentBug.title);

        Spinner statusField = ((Spinner) findViewById(R.id.spinnerStatus));

        for (int i = 0; i < BugStatus.values().length; i++) {
            if (BugStatus.values()[currentBug.status] == BugStatus.values()[i]) {
                statusField.setSelection(i);
                break;
            }
        }

        Spinner priorityField = ((Spinner) findViewById(R.id.spinnerPriority));

        for (int i = 0; i < BugPriority.values().length; i++) {
            if (BugPriority.values()[currentBug.priority] == BugPriority.values()[i]) {
                priorityField.setSelection(i);
                break;
            }
        }

        TextView descriptionField = ((TextView) findViewById(R.id.editTextDescription));
        descriptionField.setText(currentBug.description);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    protected int getLayoutId() {
        return R.layout.activity_edit;
    }

    private Integer getIntFromBugStatus(BugStatus status) {
        for (int i = 0; i < BugStatus.values().length; i++) {
            if (status == BugStatus.values()[i]) {
                return i;
            }
        }
        return -1;
    }

    private Integer getIntFromBugPriority(BugPriority priority) {
        for (int i = 0; i < BugPriority.values().length; i++) {
            if (priority == BugPriority.values()[i]) {
                return i;
            }
        }
        return -1;
    }


    protected void onButtonDoneClick() {

        //Set the title of the bug to the field title
        TextView newTitle = ((TextView) findViewById(R.id.editTextTitle));
        currentBug.title = newTitle.getText().toString();

        Spinner newStatus = ((Spinner) findViewById(R.id.spinnerStatus));
        currentBug.status = getIntFromBugStatus((BugStatus) newStatus.getSelectedItem());

        Spinner newPriority = ((Spinner) findViewById(R.id.spinnerPriority));
        currentBug.priority = getIntFromBugPriority((BugPriority) newPriority.getSelectedItem());

        //Set the description of the bug to the field description
        TextView newDescription = ((TextView) findViewById(R.id.editTextDescription));
        currentBug.description = newDescription.getText().toString();

        currentBug.save();

        Context context = getApplicationContext();
        CharSequence text = "Saved.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        super.onBackPressed();
    }

    private void onButtonDeleteClick() {

        currentBug.delete();
        MainActivity.adapter.remove(currentBug);

        Context context = getApplicationContext();
        CharSequence text = "Deleted.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        super.onBackPressed();
    }

    private AlertDialog AskOption() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
                .setTitle("Confirm")
                .setMessage("Are you sure you want to delete this bug?")

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        onButtonDeleteClick();
                        dialog.dismiss();
                    }
                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
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
            case R.id.action_delete:
                AlertDialog diaBox = AskOption();
                diaBox.show();
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
            View rootView = inflater.inflate(R.layout.fragment_edit, container, false);
            return rootView;
        }
    }

}
