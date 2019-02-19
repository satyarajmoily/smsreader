package com.satyaraj.app.testapplication;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.satyaraj.app.testapplication.base.BaseRepository;
import com.satyaraj.app.testapplication.pojo.Message;

import java.util.ArrayList;
import java.util.List;

class MainRepository extends BaseRepository<MainPresenter> {

    private ContentResolver mContentResolver;

    MainRepository (ContentResolver contentResolver){
        this.mContentResolver = contentResolver;
    }

    void getMessages(long time){
        String[] args = { String.valueOf(time) };

        Cursor cursor = mContentResolver.query(Uri.parse("content://sms/inbox"), null, "date > ?", args , "date desc");
        Message message;
        List<Message> messageList = new ArrayList<>(0);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String messageData;
                    String date;
                    messageData = cursor.getString(cursor.getColumnIndex("body"));
                    date = cursor.getString(cursor.getColumnIndex("date"));
                    long datetime = Long.parseLong(date);
                    message = new Message(messageData, datetime, null, 0);
                    messageList.add(message);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        getActions().onRetrievedMessages(messageList);
    }
}
