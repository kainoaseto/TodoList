package me.kainoseto.todo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;


/**
 * Created by Kainoa on 10/15/2016.
 */

public class EditItemDetailActivity extends AppCompatActivity {
    private EditText nameEditText;
    private EditText descEditText;
    private Switch  doneSwitch;
    private String name;
    private String description;
    private boolean done;
    private int idx;
    private boolean newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdetails);

        nameEditText = (EditText) findViewById(R.id.edit_name);
        descEditText = (EditText) findViewById(R.id.edit_desc);
        doneSwitch = (Switch) findViewById(R.id.switch_done);

        Intent intent = getIntent();
        Bundle intentData = intent.getExtras();

        newItem = intentData.getBoolean("NEW");

        if (!newItem) {
            name = intentData.getString("NAME");
            description = intentData.getString("DESC");
            done = intentData.getBoolean("DONE");
            idx = intentData.getInt("POSITION");
            nameEditText.setText(name);
            descEditText.setText(description);
            doneSwitch.setChecked(done);
        }

        FloatingActionButton fab_save = (FloatingActionButton) findViewById(R.id.fab_saveitem);
        fab_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(newItem) {
                    MainActivity.todoDbHelper.addToDoItem(nameEditText.getText().toString(), descEditText.getText().toString(), doneSwitch.isChecked());
                } else {
                    MainActivity.todoDbHelper.updateName(idx, nameEditText.getText().toString());
                    MainActivity.todoDbHelper.updateDesc(idx, descEditText.getText().toString());
                    MainActivity.todoDbHelper.updateDone(idx, doneSwitch.isChecked());
                }

                Intent detailViewIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(detailViewIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(newItem) {
            MainActivity.todoDbHelper.addToDoItem(nameEditText.getText().toString(), descEditText.getText().toString(), doneSwitch.isChecked());
        } else {
            MainActivity.todoDbHelper.updateName(idx, nameEditText.getText().toString());
            MainActivity.todoDbHelper.updateDesc(idx, descEditText.getText().toString());
            MainActivity.todoDbHelper.updateDone(idx, doneSwitch.isChecked());
        }
    }

}
