package net.unibave.todolist_java.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_CREATED_AT;
import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_DONE;
import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_ID;
import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_NAME;
import static net.unibave.todolist_java.model.DatabaseConstants.DB_NAME;
import static net.unibave.todolist_java.model.DatabaseConstants.DB_VERSION;
import static net.unibave.todolist_java.model.DatabaseConstants.TABLE_TASK;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String createTableTask = "CREATE TABLE " + TABLE_TASK + " ( " +
                COLUMN_ID + " varchar PRIMARY KEY, " +
                COLUMN_CREATED_AT + " datetime DEFAULT CURRENT_TIMESTAMP, " +
                COLUMN_NAME + " varchar, " +
                COLUMN_DONE + " integer)";

        db.execSQL(createTableTask);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String updateVersion2 = "";
        final String updateVersion3 = "";
        final String updateVersion4 = "";

        if (oldVersion == 1) {
            db.execSQL(updateVersion2);
            db.execSQL(updateVersion3);
            db.execSQL(updateVersion4);
        } else if (oldVersion == 2) {
            db.execSQL(updateVersion3);
            db.execSQL(updateVersion4);
        } else if (oldVersion == 3) {
            db.execSQL(updateVersion4);
        }

    }
}


