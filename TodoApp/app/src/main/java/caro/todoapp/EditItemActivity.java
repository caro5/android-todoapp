package caro.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {
    private String itemText;
    private int itemPosition;
    private EditText stringEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        itemText = getIntent().getStringExtra("itemText");
        itemPosition = getIntent().getIntExtra("itemPosition", -1);

        stringEdit = (EditText) findViewById(R.id.etEditItem);
        stringEdit.setText(itemText);

    }

    public void onSaveEditedText(View v) {
        String itemText = stringEdit.getText().toString();
        Intent data = new Intent();
        data.putExtra("itemText", itemText);
        data.putExtra("itemPosition", itemPosition);
        setResult(RESULT_OK, data);
        finish();
    }
}
