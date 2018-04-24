package io.vladmad.p0331_sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PrivateKey;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edText;
    Button btnSave, btnLoad;
    SharedPreferences sPref;

    final String SAVED_TEXT = "saved text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edText = findViewById(R.id.etText);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);

        loadText();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                saveText();
                break;
            case  R.id.btnLoad:
                loadText();
                break;
             default:
                 break;
        }
    }

    private void saveText() {
        //Если нужно сохранять данные в отдельном файле, в данном случае My Pref
        sPref = getSharedPreferences("My Pref", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, edText.getText().toString());
        ed.commit();
        Toast.makeText(MainActivity.this, "Text saved", Toast.LENGTH_SHORT).show();
    }


    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(SAVED_TEXT, "");
        edText.setText(savedText);
        Toast.makeText(MainActivity.this, "Text loaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
