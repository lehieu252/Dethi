package vn.itplus.sonnt.cau1.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import vn.itplus.sonnt.cau1.DialogActivity;
import vn.itplus.sonnt.cau1.R;
import vn.itplus.sonnt.cau1.database.DBManager;
import vn.itplus.sonnt.cau1.model.Employee;


public class MyAdapter extends BaseAdapter {
    ArrayList<Employee> listEmployee;
    DBManager db;
    Activity context;

    public MyAdapter(Activity context, ArrayList<Employee> listEmployee, DBManager db) {
        this.context = context;
        this.listEmployee = listEmployee;
        this.db = db;
    }

    @Override
    public int getCount() {
        return listEmployee.size();
    }

    @Override
    public Object getItem(int position) {
        return listEmployee.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = View.inflate(parent.getContext(), R.layout.item_row, null);
        } else {
            v = convertView;
        }

        Employee e = (Employee) getItem(position);
        TextView txtInfo = (TextView) v.findViewById(R.id.info);
        txtInfo.setText(e.toString());
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(context, DialogActivity.class);
                intent.putExtra("employee",listEmployee.get(position));
                context.startActivity(intent);
                return true;
            }
        });
        return v;
    }

}
