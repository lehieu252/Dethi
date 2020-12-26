package vn.itplus.sonnt.cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import vn.itplus.sonnt.cau1.database.DBManager;
import vn.itplus.sonnt.cau1.model.Employee;


public class DialogActivity extends AppCompatActivity {

    private EditText ma_nv;
    private EditText ten_nv;
    private EditText tuoi_nv;

    private Button btn_delete;
    private Button btn_back;

    private DBManager db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog);

        bindView();
        db = new DBManager(this);

        final Employee e = (Employee) getIntent().getExtras().getSerializable("employee");

        ma_nv.setText(e.getId());
        ten_nv.setText(e.getName());
        tuoi_nv.setText(""+e.getAge());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteEmployee(e);
                Intent intent = new Intent(DialogActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    void bindView(){
        ma_nv = findViewById(R.id.edt_ma_nv);
        ten_nv = findViewById(R.id.edt_ten_nv);
        tuoi_nv = findViewById(R.id.edt_tuoi_nv);
        btn_back = findViewById(R.id.btn_back);
        btn_delete = findViewById(R.id.btn_delete);
    }

}