package com.example.uts_pppb1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class detailDataUser extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText tanggalLahir;
    Button btnNewsShow;
    Spinner spinner;
    String spinners; //set nentuin posisi berita ketika di klik


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_user);
        tanggalLahir = findViewById(R.id.date_input);
        btnNewsShow = findViewById(R.id.btn_news);
        spinner = findViewById(R.id.label_spinner);

        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        //function untuk menampilkan spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.label_array, android.R.layout.simple_spinner_item); //label yg akan ditampilkan
        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);



        //memanggil fungsi untuk menampilkan datepicker
        tanggalLahir.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    tanggalLahir.setEnabled(false);
                    DialogFragment dateFragment = new DatePickerFragment();
                    dateFragment.show(getSupportFragmentManager(), "date-picker");
                }else{
                    tanggalLahir.setEnabled(true);
                }
            }
        });
//            @Override
//            public void onClick(View view, boolean bol) {
//                if(bol){
//                    tanggalLahir.setEnabled(false);
//                    DialogFragment dateFragment = new DatePickerFragment();
//                    dateFragment.show(getSupportFragmentManager(), "date-picker");
//                }else{
//                    tanggalLahir.setEnabled(true);
//                }
//                //showDatePicker();
//            }
//        });

        btnNewsShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tanggal_lahir = String.valueOf(tanggalLahir.getText());
                String kategori = String.valueOf(spinner.getSelectedItem());
                Intent intent = new Intent(detailDataUser.this, MainActivity.class);
                intent.putExtra("tanggalLahir", tanggal_lahir);
                intent.putExtra("kategoriTeks", kategori);
                startActivity(intent);
            }
        });
    }

    //function menampilkan datepicker
//    public void showDatePicker() {
//        DialogFragment dateFragment = new DatePickerFragment();
//        dateFragment.show(getSupportFragmentManager(), "date-picker");
//    }

    public void processDatePickerResult(int day, int month, int year){
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month+1);
        String year_string = Integer.toString(year);

        String dateMessage = day_string + " - " + month_string + " - " + year_string;
        tanggalLahir.setText(dateMessage);
    }

    //kondisi dimana ketika spinner diklik
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinners = adapterView.getItemAtPosition(i).toString();
        showSpinnerText();
    }

    private void showSpinnerText() {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}