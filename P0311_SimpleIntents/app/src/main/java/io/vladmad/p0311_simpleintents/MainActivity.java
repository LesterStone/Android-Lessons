package io.vladmad.p0311_simpleintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnWeb;
    Button btnCalls;
    Button btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWeb = findViewById(R.id.btn_Web);
        btnCalls = findViewById(R.id.btn_Call);
        btnMap = findViewById(R.id.btn_Map);

        btnWeb.setOnClickListener(this);
        btnCalls.setOnClickListener(this);
        btnMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_Web:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"));
                startActivity(intent);
                break;
            case  R.id.btn_Map:
                intent = new Intent();
                intent.setAction(Intent.ACTION_EDIT);
                intent.setData(Uri.parse("geo:53.007588, 36.144020"));
                startActivity(intent);
                break;
            case R.id.btn_Call:
                intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:12345"));
                startActivity(intent);
                break;
        }

    }
}
