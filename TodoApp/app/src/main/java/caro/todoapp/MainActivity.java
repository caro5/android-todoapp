package caro.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_EDIT_ITEM_CODE = 20;
    ArrayList<TodoItem> items;
    ArrayAdapter<TodoItem> itemsAdapter;
    ListView lvItems;
    EditText etnewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etnewItem = (EditText) findViewById(R.id.etNewItem);
        lvItems = (ListView) findViewById(R.id.lvItems);
        items = new ArrayList<>();
        readItems();
        itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setUpListViewListener();
    }


    private void setUpListViewListener() {
        //Edit Item on click
        lvItems.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TodoItem selectedItem = itemsAdapter.getItem(position);
                        Intent i = new Intent(MainActivity.this, EditItemActivity.class);
                        i.putExtra("itemText", selectedItem.text);
                        i.putExtra("itemPosition", position);
                        startActivityForResult(i, REQUEST_EDIT_ITEM_CODE);
                    }
                }
        );
        //Delete item on long click
        lvItems.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter,
                                                   View item, int pos, long id) {
                        items.remove(pos);
                        itemsAdapter.notifyDataSetChanged();
                        writeItems();
                        return true;
                    }
                }
        );
    }

    public void onAddItem(View v) {
        String itemText = etnewItem.getText().toString();
        itemsAdapter.add(new TodoItem(itemText));
        etnewItem.setText("");
        writeItems();
    }

    private void readItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        todoFile.delete(); 
        try {
            items = new ArrayList(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            items = new ArrayList<>();
        }
    }

    private void writeItems() {
        File filesDir = getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // REQUEST_EDIT_ITEM_CODE is defined above
        if (resultCode == RESULT_OK && requestCode == REQUEST_EDIT_ITEM_CODE) {
            // Extract name value from result extras
            int itemPos = data.getIntExtra("itemPosition", -1);
            String itemText = data.getStringExtra("itemText");
            items.set(itemPos, new TodoItem(itemText));
            // Toast the name to display temporarily on screen
            Toast.makeText(this, "Updated todo to ".concat(itemText), Toast.LENGTH_SHORT).show();
            writeItems();
            itemsAdapter.notifyDataSetChanged();
        }
    }

}