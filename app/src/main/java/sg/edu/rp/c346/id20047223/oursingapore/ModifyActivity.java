package sg.edu.rp.c346.id20047223.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    TextView tvId, tvName, tvDescription, tvArea;
    EditText etID, etName, etDesc, etArea;
    Button btnUpdate, btnDelete, btnCancel;
    RatingBar rgRating;
    ArrayList<Island> al;
    ArrayAdapter<Island> aa;
    RadioButton r1, r2, r3, r4, r5;
    Island data;

    @Override
    protected void onResume(){
        super.onResume();

        btnCancel.performClick();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        tvId = findViewById(R.id.textViewid);
        etID = findViewById(R.id.etID);
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDesc);
        tvArea = findViewById(R.id.tvArea);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etArea = findViewById(R.id.etArea);
        rgRating = findViewById(R.id.rbStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Island) i.getSerializableExtra("island");

        etID.setText("ID: " + data.getId());
        etName.setText(data.getIslandName());
        etDesc.setText(data.getIslandDesc());
        etArea.setText(data.getIslandArea());
        switch (data.getRating()) {
            case 1:
                r1.setChecked(true);
                break;
            case 2:
                r2.setChecked(true);
                break;
            case 3:
                r3.setChecked(true);
                break;
            case 4:
                r4.setChecked(true);
                break;
            case 5:
                r5.setChecked(true);
                break;
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setIslandName(etName.getText().toString());
                data.setIslandDesc(etDesc.getText().toString());
                data.setIslandArea(Integer.parseInt(etArea.getText().toString().trim()));
                int rbID = rgRating.getNumStars();
                RadioButton rb = (RadioButton) findViewById(rbID);
                data.setRating(Integer.parseInt(rb.getText().toString()));

                dbh.updateIsland(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete this island.");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Cancel", null);

                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyActivity.this);
                        dbh.deleteIsland(data.getId());
                    }
                });

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyActivity.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard changes.");
                myBuilder.setCancelable(false);

                myBuilder.setPositiveButton("Do not discard", null);

                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
            }
        });

    }
}