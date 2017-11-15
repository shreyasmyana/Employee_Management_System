package sam.mlog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Shreyas on 08-09-2017.
 */

 public class Database_Helper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Employee.db";
    public static final String TABLE_NAME = "employee_table";
    public static final String COL_1 = "EMP_ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "POSITION";
    public static final String COL_4 = "SALARY";
    public static final String COL_5 = "TODO";

    public Database_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db= this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
 //       String CREATE_EMPLOYEE_TABLE = "create table " + TABLE_NAME + "(" + COL_1 + "TEXT PRIMARY KEY" + COL_2 + "TEXT" + COL_3 + "TEXT" + COL_4 + "TEXT" + ")";
        db.execSQL("create table " + TABLE_NAME +"("+ COL_1 + " Text not null UNIQUE, "+ COL_2 +" TEXT, " + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT NULL"+");");
      //  db.execSQL("DROP TABLE employee_table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

           db.execSQL("DROP TABLE "+TABLE_NAME);
            onCreate(db);


    }

    public boolean UpdateData(String ID, String NAME, String POSITION, String SALARY, String TODO){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, ID);
        values.put(COL_2, NAME);
        values.put(COL_3, POSITION);
        values.put(COL_4, SALARY);
        values.put(COL_5, TODO);
        db.update(TABLE_NAME,values,"Emp_ID = ?",new String[] {ID});
        return true;

    }

    boolean checkIfExist(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { COL_1,
                        COL_2, COL_3, COL_4}, COL_1 + "=?",
                new String[] { name }, null, null, null, null);


        if (cursor.getCount()>0)
            return true;

        else
            return false;

    }

    public boolean addEmployee(String ID, String NAME, String POSITION, String  SALARY) {
        SQLiteDatabase db;
        ContentValues values = new ContentValues();
          String  TODO="null";
  /*      String sql = "select * from "
                + TABLE_NAME + " WHERE placeId = '" + COL_1 + "'";
        Cursor cursorinfo = null;
        try {
            db = this.getReadableDatabase();
            cursorinfo = db.rawQuery(sql, null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!cursorinfo.moveToFirst()) {
        }
*/
      if (checkIfExist(ID) == false) {
            db = this.getWritableDatabase();
            values.put(COL_1, ID);
            values.put(COL_2, NAME);
            values.put(COL_3, POSITION);
            values.put(COL_4, SALARY);
            values.put(COL_5, "null");
            long result = db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);

            return true;
            }
            return false;
        }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;

    }

    public Integer deleteData (String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "EMP_ID = ?",new String[] {ID});
    }
}


