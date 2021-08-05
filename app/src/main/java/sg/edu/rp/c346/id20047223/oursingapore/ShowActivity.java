package sg.edu.rp.c346.id20047223.oursingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    TextView tvName, tvDescription, tvArea;
    EditText etName, etDesc, etArea;
    RadioGroup RGrating;
    ArrayList<Island> al;
    ArrayAdapter<Island> aa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDesc);
        tvArea = findViewById(R.id.tvArea);
        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDesc);
        etArea = findViewById(R.id.etArea);
        RGrating = findViewById(R.id.rgrating);


    }
}