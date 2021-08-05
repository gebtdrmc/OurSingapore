package sg.edu.rp.c346.id20047223.oursingapore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    Button fivestars, btnadd;
    ListView lv;
    CustomAdapter ca;

    ArrayList<Island> al;
    ArrayAdapter<Island> aa;

    @Override
    protected void onResume(){
        super.onResume();

        aa.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnadd = findViewById(R.id.buttonAdd);
        fivestars = findViewById(R.id.btnFive);
        lv = findViewById(R.id.lvIslands);

        al = new ArrayList<Island>();
        ca = new CustomAdapter(this,
                R.layout.row, al);
        lv.setAdapter(ca);

        fivestars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.getSongByStars();
                dbh.close();
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Island island = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        ModifyActivity.class);
                i.putExtra("island", island);
                startActivity(i);
            }
        });

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View viewDialog = inflater.inflate(R.layout.activity_show, null);

                final EditText etInput1 = viewDialog.findViewById(R.id.etName);
                final EditText etInput2 = viewDialog.findViewById(R.id.etDesc);
                final EditText etArea = viewDialog.findViewById(R.id.etArea);
                final RadioGroup ratingbar = viewDialog.findViewById(R.id.rgrating);

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setView(viewDialog);
                myBuilder.setTitle("Insert island");
                myBuilder.setPositiveButton("Insert", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String namedata = etInput1.getText().toString();
                        String descdata = etInput2.getText().toString();
                        String areadata = etArea.getText().toString();
                        int area = Integer.valueOf(areadata);
                        int stars = 1;
                        switch (ratingbar.getCheckedRadioButtonId()) {
                            case R.id.rb1:
                                stars = 1;
                                break;
                            case R.id.rb2:
                                stars = 2;
                                break;
                            case R.id.rb3:
                                stars = 3;
                                break;
                            case R.id.rb4:
                                stars = 4;
                                break;
                            case R.id.rb5:
                                stars = 5;
                                break;
                        }
                        DBHelper dbh = new DBHelper(MainActivity.this);
                        long inserted_name = dbh.insertIslandName(namedata);
                        long inserted_desc = dbh.insertIslandDesc(descdata);
                        long inserted_area = dbh.insertIslandArea(area);

                        if (inserted_name != -1 && inserted_desc != -1 && inserted_area != -1) {
                            al.clear();
                            al.addAll(dbh.getAllIslands());
                            aa.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Insert successful",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                myBuilder.setNegativeButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

    }
}