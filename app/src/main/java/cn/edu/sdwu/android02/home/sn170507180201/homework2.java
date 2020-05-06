package cn.edu.sdwu.android02.home.sn170507180201;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class homework2 extends AppCompatActivity {
    private HomeworkOpenHelper homeworkOpenHelper;
    private EditText editText1,editText2,editText3,editText4;
    private Button insert,showall,clearshow,deleteall,IDdelete,IDupdate,IDselect;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homework2);

        homeworkOpenHelper=new HomeworkOpenHelper(this);

        editText1=(EditText)findViewById(R.id.name);
        editText2=(EditText)findViewById(R.id.age);
        editText3=(EditText)findViewById(R.id.height);
        editText4=(EditText)findViewById(R.id.IDnumber);

        insert=(Button) findViewById(R.id.insert);
        showall=(Button) findViewById(R.id.showall);
        clearshow=(Button) findViewById(R.id.clearshow);
        deleteall=(Button) findViewById(R.id.deleteall);
        IDdelete=(Button) findViewById(R.id.IDdelete);
        IDselect=(Button) findViewById(R.id.IDselect);
        IDupdate=(Button) findViewById(R.id.IDupdate);




    }

    public void insert(View view){
        String name=editText1.getText().toString();
        String age=editText2.getText().toString();
        String height=editText2.getText().toString();
        SQLiteDatabase sqLiteDatabase=homeworkOpenHelper.getWritableDatabase();
       try{
           if(name.equals(null)){
               Toast.makeText(homework2.this,"姓名不能为空！",Toast.LENGTH_SHORT).show();
           }else {
               sqLiteDatabase.beginTransaction();//开始事务
               ContentValues contentValues = new ContentValues();
               contentValues.put("name", name);
               contentValues.put("age", age);
               contentValues.put("height", height);
               sqLiteDatabase.insert("homework", null, contentValues);
               sqLiteDatabase.setTransactionSuccessful();
               Toast.makeText(homework2.this,"添加成功！",Toast.LENGTH_SHORT).show();
           }
       }catch (Exception e) {
           Log.e(homework2.class.toString(),e.toString());
        }finally {
        sqLiteDatabase.endTransaction();//结束事务
        sqLiteDatabase.close();//使用完毕关闭数据库

       }

    }

    public void showall(View view){
        textView=(TextView)findViewById(R.id.showinfo);
        SQLiteDatabase sqLiteDatabase=homeworkOpenHelper.getWritableDatabase();
        try{
            Cursor cursor=sqLiteDatabase.rawQuery("select * from homework",null);
            //Cursor游标 每次都向下变动一个
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));//cursor.getColumnIndex获取列的索引
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String age=cursor.getString(cursor.getColumnIndex("age"));
                String height=cursor.getString(cursor.getColumnIndex("height"));
                textView.setText("id:"+id+",name:"+name+",age:"+age+",height"+height);
            }
            cursor.close();
        }catch (Exception e){
            Log.e(homework2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.close();//使用完毕关闭数据库

        }
    }
    public void clearshow(View view){
        textView=(TextView)findViewById(R.id.showinfo);
        textView.setText("");
    }
    public void deleteall(View view){
        SQLiteDatabase sqLiteDatabase=homeworkOpenHelper.getWritableDatabase();
        try{
            sqLiteDatabase.beginTransaction();//开始事务
            sqLiteDatabase.delete("homework","id=?",new String[]{"1,2,3,4"});
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(homework2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.close();//使用完毕关闭数据库

        }
    }
    public void IDdelete(View view){
        String IDnumber=editText4.getText().toString();
        SQLiteDatabase sqLiteDatabase=homeworkOpenHelper.getWritableDatabase();
        try{
            sqLiteDatabase.beginTransaction();//开始事务
            sqLiteDatabase.delete("homework","id=?",new String[]{IDnumber});
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(homework2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();//使用完毕关闭数据库
        }
    }
    public void IDselect(View view){
        String IDnumber=editText4.getText().toString();
        SQLiteDatabase sqLiteDatabase=homeworkOpenHelper.getWritableDatabase();
        try{
            Cursor cursor=sqLiteDatabase.rawQuery("select * from homework where id=?",new String[]{IDnumber});
            //Cursor游标 每次都向下变动一个
            while(cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));//cursor.getColumnIndex获取列的索引
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String age=cursor.getString(cursor.getColumnIndex("age"));
                String height=cursor.getString(cursor.getColumnIndex("height"));
                Log.i(homework2.class.toString(),"id:"+id+",name:"+name+",age:"+age+",height:"+height);
            }
            cursor.close();
        }catch (Exception e){
            Log.e(homework2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.close();//使用完毕关闭数据库

        }
    }
    public void IDupdate(View view){
        String name=editText1.getText().toString();
        String age=editText2.getText().toString();
        String height=editText2.getText().toString();
        String IDnumber=editText4.getText().toString();
        SQLiteDatabase sqLiteDatabase=homeworkOpenHelper.getWritableDatabase();

        try{
            sqLiteDatabase.beginTransaction();//开始事务

            ContentValues contentValues=new ContentValues();
            contentValues.put("name","Jackson");

            sqLiteDatabase.update("homework",contentValues,"id=?",new String[]{IDnumber});
            sqLiteDatabase.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(homework2.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();
            sqLiteDatabase.close();//使用完毕关闭数据库
        }
    }

}
