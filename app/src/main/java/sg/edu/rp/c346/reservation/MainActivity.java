package sg.edu.rp.c346.reservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etTelephone;
    EditText etSize;
    CheckBox checkBox;
    Button btReserve;
    Button btReset;

    EditText etDate;
    EditText etTime;
    int Year;
    int Month;
    int Day;
    int Hour;
    int Min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Reservation");

        etName = findViewById(R.id.editTextName);
        etTelephone = findViewById(R.id.editTextTelephone);
        etSize = findViewById(R.id.editTextSize);
        checkBox = findViewById(R.id.checkBox);
        btReserve = findViewById(R.id.buttonReserve);
        btReset = findViewById(R.id.buttonReset);

        etTime = findViewById(R.id.etTime);
        etDate = findViewById(R.id.etDate);

        Calendar c = Calendar.getInstance();
        Year = c.get(Calendar.YEAR);
        Month = c.get(Calendar.MONTH);
        Day = c.get(Calendar.DAY_OF_MONTH);
        Hour = c.get(Calendar.HOUR);
        Min = c.get(Calendar.MINUTE);

        btReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isSmoke = "";
                if (checkBox.isChecked()) {
                    isSmoke = "smoking";
                } else {
                    isSmoke = "non-smoking";
                }

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Confirm your order");
                myBuilder.setMessage("New Reservation \nName: " + etName.getText().toString() + "\nSmoking : " +
                       isSmoke + "\nSize : " +etSize.getText().toString() + "\nDate : " + Day + "/" + Month + "/" + Year + "\nTime : " + Hour + ":" + Min );
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("CONFIRM", null);
                myBuilder.setNeutralButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etName.setText(null);
                etTelephone.setText(null);
                etSize.setText(null);
                checkBox.setChecked(false);
                etDate.setText(null);
                etTime.setText(null);
                Calendar c = Calendar.getInstance();
                Year = c.get(Calendar.YEAR);
                Month = c.get(Calendar.MONTH);
                Day = c.get(Calendar.DAY_OF_MONTH);
                Hour = c.get(Calendar.HOUR_OF_DAY);
                Min = c.get(Calendar.MINUTE);
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog.OnDateSetListener dpList =new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Year = year;
                        Month = month;
                        Day = dayOfMonth;
                        etDate.setText(Day + "/" + Month + "/" + Year);
                    }
                };

                DatePickerDialog dpDialog = new DatePickerDialog(MainActivity.this, dpList, Year , Month , Day );
                dpDialog.show();


            }
        });

        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener myTimeList = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Hour = hourOfDay;
                        Min = minute;
                        etTime.setText(Hour + ":" + Min);
                    }
                };

                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this, myTimeList, Hour ,Min, true );
                myTimeDialog.show();

            }
        });
    }
}