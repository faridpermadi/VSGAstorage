package com.farid.vsgastorage;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    Button buatFile,ubahFile,bacaFile,hapusFile;
    TextView textView;
    EditText masuk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buatFile = findViewById(R.id.buttonbuat);
        ubahFile = findViewById(R.id.buttonubah);
        bacaFile = findViewById(R.id.buttonbaca);
        hapusFile = findViewById(R.id.buttonhapus);
        textView = findViewById(R.id.hasil);
         masuk = findViewById(R.id.input);

        buatFile.setOnClickListener(this);
        ubahFile.setOnClickListener(this);
        bacaFile.setOnClickListener(this);
        hapusFile.setOnClickListener(this);
    }

    void buatFile() {

        String isiFile = masuk.getText().toString();
        File file = new File(getFilesDir(), FILENAME);
        FileOutputStream outputStream = null;
        if (isiFile.matches("")){
            Toast.makeText(getApplicationContext(), "Text belum ditambahkan", Toast.LENGTH_SHORT).show();
            return;
        }else {
            Toast.makeText(getApplicationContext(), "Text telah ditambahkan", Toast.LENGTH_SHORT).show();
            try {
                file.createNewFile();
                outputStream = new FileOutputStream(file, true);

                outputStream.write(isiFile.getBytes());
                outputStream.flush();
                outputStream.close();
                InputMethodManager inputMgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                EditText editText = (EditText)findViewById(R.id.input);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(),0);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }
    void ubahFile() {
        String ubah =masuk.getText().toString();
        File file = new File(getFilesDir(), FILENAME);

        FileOutputStream outputStream = null;
        Toast.makeText(getApplicationContext(), "Berhasil diubah", Toast.LENGTH_SHORT).show();
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(ubah.getBytes());
            outputStream.flush();
            outputStream.close();

            InputMethodManager inputMgr = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            EditText editText = (EditText)findViewById(R.id.input);
            inputMgr.hideSoftInputFromWindow(editText.getWindowToken(),0);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    void bacaFile() {

    File sdcard = getFilesDir();
    File file = new File(sdcard, FILENAME);

    if (file.exists());
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                text.append(line);
                line = br.readLine();
            }
            br.close();
        }catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
        textView.setText(text.toString());
        }

    void hapusFile(){
    File file = new File (getFilesDir(), FILENAME);
        Toast.makeText(getApplicationContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
    if (file.exists()){
        file.delete();
        textView.setText("");
    }
    }
    @Override
    public void onClick(View v) {
        jalankanPerintah(v.getId());
    }
    public void jalankanPerintah(int id) {
    switch (id) {
        case R.id.buttonbuat:
            buatFile();
            break;
        case R.id.buttonbaca:
            bacaFile();
            break;
        case R.id.buttonubah:
            ubahFile();
            break;
        case R.id.buttonhapus:
            hapusFile();
            break;

    }
    }
}