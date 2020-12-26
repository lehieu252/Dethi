package vn.itplus.sonnt.cau1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

import vn.itplus.sonnt.cau1.adapter.MyAdapter;
import vn.itplus.sonnt.cau1.database.DBManager;
import vn.itplus.sonnt.cau1.model.Employee;

public class MainActivity extends AppCompatActivity {
    private DBManager db ;
    private MyAdapter myAdapter;
    ArrayList<Employee> listEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBManager(this);

//        db.addEmployee(new Employee("NV-1","Nguyễn Đại Nhân",30));
//        db.addEmployee(new Employee("NV-2","Trần Đại Nghĩa",31));
//        db.addEmployee(new Employee("NV-113","Hoàng Đại Lễ",35));
//        db.addEmployee(new Employee("NV-3","Phạm Đại Trí",33));
//        db.addEmployee(new Employee("NV-4","Trương Đại Tín",30));
//        db.addEmployee(new Employee("NV-5","Hồ Đại Đức",34));

        listEmployee = (ArrayList) db.getAllEmployee();
        ListView lv = (ListView) findViewById(R.id.list);
        myAdapter = new MyAdapter(this, listEmployee, db);
        myAdapter.notifyDataSetChanged();
        lv.setAdapter(myAdapter);


    }
}