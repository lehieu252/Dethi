package vn.itplus.sonnt.cau1.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import vn.itplus.sonnt.cau1.model.Employee;

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "sonnt2";
    private static final String TABLE_NAME = "employee";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String AGE = "age";

    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }


    //Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                ID + " TEXT PRIMARY KEY ," +
                NAME + " TEXT," +
                AGE + " INTEGER )";
        db.execSQL(sqlQuery);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addEmployee(Employee e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,e.getId());
        values.put(NAME, e.getName());
        values.put(AGE, e.getAge());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Employee getEmployeeById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{ID,
                        NAME, AGE}, ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Employee e = new Employee(cursor.getString(0), cursor.getString(1), cursor.getInt(2));
        cursor.close();
        db.close();
        return e;
    }


    public List<Employee> getAllEmployee() {
        List<Employee> listEmployee = new ArrayList<Employee>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Employee e = new Employee();
                e.setId(cursor.getString(0));
                e.setName(cursor.getString(1));
                e.setAge(cursor.getInt(2));
                listEmployee.add(e);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listEmployee;
    }

    public void deleteEmployee(Employee e) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ?",
                new String[]{String.valueOf(e.getId())});
        db.close();
    }


}


