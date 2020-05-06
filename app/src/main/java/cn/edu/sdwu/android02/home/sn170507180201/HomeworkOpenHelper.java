package cn.edu.sdwu.android02.home.sn170507180201;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Thinkpad on 2020/5/3.
 */

public class HomeworkOpenHelper extends SQLiteOpenHelper {
    private String HOMEWORK_TB_SQL="create table homework(id integer primary key autoincrement,name text,age text,height text)";
    //创建数据库
    public HomeworkOpenHelper(Context context){
        super(context,"home.db",null,1);
    }
    //打开数据库并不存在时 自动创建数据库 数据库兑现的创建
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(HOMEWORK_TB_SQL);
        Log.i(HomeworkOpenHelper.class.toString(),"onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
