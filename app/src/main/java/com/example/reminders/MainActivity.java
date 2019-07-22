package com.example.reminders;

import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private RemindersDbAdapter mDbAdapter;
    private RemindersSimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListView = findViewById(R.id.reminders_list_view);
        mListView.setDivider(null);

        mDbAdapter = new RemindersDbAdapter(this);
        mDbAdapter.open();
        Cursor cursor = mDbAdapter.fetchAllReminders();
        String[] from = new String[]{ RemindersDbAdapter.COL_CONTENT   };
        int[] to = new int[]{ R.id.row_text };
        mCursorAdapter = new RemindersSimpleCursorAdapter(
                MainActivity.this,
                R.layout.reminders_row,
                cursor,
                from,
                to,
                0);
        mListView.setAdapter(mCursorAdapter);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void insertSomeReminders() {
        mDbAdapter.createReminder("Buy learn Android studio", true);
        mDbAdapter.createReminder("Buy Bike", false);
        mDbAdapter.createReminder("Sell Bike", true);
        mDbAdapter.createReminder("Create Planner app", true);
        mDbAdapter.createReminder("Dummy data", false);
        mDbAdapter.createReminder("Dummy data", true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                Log.d(getLocalClassName(), "Settings");
                return true;
            case R.id.action_new:
                Log.d(getLocalClassName(), "New Reminder");
                return true;
            case R.id.action_exit:
                Log.d(getLocalClassName(), "Exit");
                finish();
                return true;
                default:
                    return false;
        }
    }
}
