package net.unibave.todolist_java.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_CREATED_AT;
import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_DONE;
import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_ID;
import static net.unibave.todolist_java.model.DatabaseConstants.COLUMN_NAME;
import static net.unibave.todolist_java.model.DatabaseConstants.TABLE_TASK;

public class TaskLocalDatabaseRepository {

    private SQLiteDatabase instancia;
    private Database database;
    SimpleDateFormat dateFormat;

    public TaskLocalDatabaseRepository(Context context) {
        this.database = new Database(context);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public void create(final Task task) {

        ContentValues contentValues = new ContentValues();
        instancia = database.getWritableDatabase();

        contentValues.put(COLUMN_ID, task.getId());
        contentValues.put(COLUMN_CREATED_AT, dateFormat.format(task.getCreatedAt()));
        contentValues.put(COLUMN_NAME, task.getName());
        contentValues.put(COLUMN_DONE, task.isDone());

        instancia.insert(TABLE_TASK, null, contentValues);
        instancia.close();

    }

    public void edit(final Task task) {

        ContentValues contentValues = new ContentValues();
        instancia = database.getWritableDatabase();

        contentValues.put(COLUMN_CREATED_AT, dateFormat.format(task.getCreatedAt()));
        contentValues.put(COLUMN_NAME, task.getName());
        contentValues.put(COLUMN_DONE, task.isDone());

        String where = COLUMN_ID + " = '" + task.getId() + "'";

        instancia.update(TABLE_TASK, contentValues, where, null);
        instancia.close();

    }

    public void delete(Task task) {

        instancia = database.getWritableDatabase();
        String where = COLUMN_ID + " = '" + task.getId() + "'";

        instancia.delete(TABLE_TASK, where, null);
        instancia.close();

    }

    public List<Task> findAll() {

        List<Task> tasks = new ArrayList<>();
        String[] fields = { COLUMN_ID, COLUMN_CREATED_AT, COLUMN_NAME, COLUMN_DONE };
        instancia = database.getReadableDatabase();

        Cursor cursor = instancia.query(TABLE_TASK, fields,
                null, null, null, null,
                COLUMN_DONE + ", " + COLUMN_CREATED_AT);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Date createdAt = new Date();

                try {
                    createdAt = dateFormat.parse(
                            cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATED_AT)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Task task = new Task();
                task.setId(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)));
                task.setCreatedAt(createdAt);
                task.setName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)));
                task.setDone(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DONE))==1);
                tasks.add(task);
            } while (cursor.moveToNext());
        }

        return tasks;

    }

}
