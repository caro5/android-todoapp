package caro.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by cwong on 7/24/16.
 */
public class TodoItemAdapter extends ArrayAdapter<TodoItem> {
    public TodoItemAdapter(Context context, ArrayList<TodoItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TodoItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvText = (TextView) convertView.findViewById(R.id.tvText);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        // Populate the data into the template view using the data object
        tvText.setText(item.text);
        tvDate.setText(item.dueDateStr);
        // Return the completed view to render on screen
        return convertView;
    }
}
