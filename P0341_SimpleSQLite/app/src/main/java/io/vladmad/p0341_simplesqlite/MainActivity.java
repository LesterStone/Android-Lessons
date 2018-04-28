package io.vladmad.p0341_simplesqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etName, etID;
    EditText etEmail;
    Button btnAdd;
    Button btnRead;
    Button btnClear, btnUpd, btnDel;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.et_Name);
        etEmail = findViewById(R.id.et_Email);
        etID = findViewById(R.id.et_ID);

        btnAdd = findViewById(R.id.btn_Add);
        btnAdd.setOnClickListener(this);

        btnRead = findViewById(R.id.btn_Read);
        btnRead.setOnClickListener(this);

        btnClear = findViewById(R.id.btn_Clear);
        btnClear.setOnClickListener(this);

        btnUpd = findViewById(R.id.btn_Upd);
        btnUpd.setOnClickListener(this);

        btnDel = findViewById(R.id.btn_Del);
        btnDel.setOnClickListener(this);

        dbHelper = new DBHelper(this);

    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etID.getText().toString();


        SQLiteDatabase database = dbHelper.getWritableDatabase();

        //класс, который может обрабатывать какие-либо значения
        ContentValues contentValues = new ContentValues();

        switch (v.getId()) {
            case R.id.btn_Add:
                //в контент вал по ключу DBHelper помещаем данные(имя, емэйл)
                contentValues.put(DBHelper.KEY_NAME, name);
                contentValues.put(DBHelper.KEY_MAIL, email);

                database.insert(DBHelper.TABLE_CONTACTS, null, contentValues);
                break;

            case R.id.btn_Clear:
                database.delete(DBHelper.TABLE_CONTACTS, null, null);
                break;
            case  R.id.btn_Read:
                Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, null, null, null, null, null, null);

                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                    int nameIndex = cursor.getColumnIndex(DBHelper.KEY_NAME);
                    int emailIndex = cursor.getColumnIndex(DBHelper.KEY_MAIL);
                        do {
                            Log.d("myLog", "ID = " + cursor.getInt(idIndex) +
                                    ", name = " + cursor.getString(nameIndex) +
                                    ", email = " + cursor.getString(emailIndex));
                        } while (cursor.moveToNext());
                } else
                    Log.d("mLog","0 rows");

                cursor.close();
                break;
            case R.id.btn_Upd:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                contentValues.put(DBHelper.KEY_MAIL, email);
                contentValues.put(DBHelper.KEY_NAME, name);
                int updCount = database.update(DBHelper.TABLE_CONTACTS, contentValues, DBHelper.KEY_ID + "= ?", new String[] {id});
                Log.d("myLog", "updates rows count = " + updCount);
                break;
            case R.id.btn_Del:
                if(id.equalsIgnoreCase("")){
                    break;
                }
                int delCounts = database.delete(DBHelper.TABLE_CONTACTS, DBHelper.KEY_ID + "= " + id, null);
                Log.d("myLog", "delete rows counts = " + delCounts);

        }
    dbHelper.close();
    }
}
