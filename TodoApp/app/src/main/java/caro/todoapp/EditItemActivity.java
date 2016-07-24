package caro.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class EditItemActivity extends AppCompatActivity {
    private String itemText;
    private String itemDate;
    private int itemPosition;
    private EditText stringEdit;
    private DatePicker datePicker;
    private String formattedDate;
    SimpleDateFormat dateFormat;
    String[] values;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        itemText = getIntent().getStringExtra("itemText");
        itemDate = getIntent().getStringExtra("itemDate");
        itemPosition = getIntent().getIntExtra("itemPosition", -1);

        stringEdit = (EditText) findViewById(R.id.etEditItem);
        stringEdit.setText(itemText);
        stringEdit.setSelection(itemText.length());

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        if (itemDate != null && itemDate != "") {
            values = itemDate.split("-");
            System.out.print(values);
            int month = -1;
            try {
                Date date = new SimpleDateFormat("MM").parse(values[0]);
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                month = cal.get(Calendar.MONTH);
            } catch (Exception e) {
                e.printStackTrace();
            }
            datePicker.updateDate(Integer.parseInt(values[2]), month, Integer.parseInt(values[1]));
        }
    }
    public void onSaveEditedText(View v) {
        int day  = datePicker.getDayOfMonth();
        int month= datePicker.getMonth();
        int year = datePicker.getYear();

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String selectedDate = sdf.format(datePicker.getCalendarView().getDate());
        itemText = stringEdit.getText().toString();
        Intent data = new Intent();
        data.putExtra("itemText", itemText);
        data.putExtra("itemDate", selectedDate);
        data.putExtra("itemPosition", itemPosition);
        setResult(RESULT_OK, data);
        finish();
    }
}
