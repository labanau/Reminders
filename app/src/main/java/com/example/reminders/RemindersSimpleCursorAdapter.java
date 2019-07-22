package com.example.reminders;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.recyclerview.widget.RecyclerView;

import com.example.reminders.R;
import com.example.reminders.RemindersDpAdapter;

public class RemindersSimpleCursorAdapter extends SimpleCursorAdapter {
    //###########################################
    //  CONSTRUCTOR
    //###########################################
    public RemindersSimpleCursorAdapter (Context context, int layout, Cursor cursor, String[]
                                         from, int[] to, int flags) {
        super(context, layout, cursor, from, to , flags);
    }

    //###########################################
    //  To use ViewHolder we need to overrode the two following methods
    //###########################################

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        super.bindView(view, context, cursor);

        ViewHolder holder = (ViewHolder) view.getTag();

        if ( holder != null) {
            holder = new ViewHolder();
            holder.colImp = cursor.getColumnIndexOrThrow(RemindersDpAdapter.COL_IMPORTANT);
            holder.listTab = view.findViewById(R.id.row_tab);
            view.setTag(holder);
        }
        if(cursor.getInt(holder.colImp) > 0) {
            holder.listTab.setBackgroundColor(context.getResources().getColor(R.color.orange));
        } else {
            holder.listTab.setBackgroundColor(context.getResources().getColor(R.color.green));
        }
    }

    static class ViewHolder {
        int colImp;
        View listTab;
    }
}
