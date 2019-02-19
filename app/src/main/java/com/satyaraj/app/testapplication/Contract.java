package com.satyaraj.app.testapplication;

import com.satyaraj.app.testapplication.pojo.Message;

import java.util.List;

public interface Contract {
    interface View{
        void displayMessages(List<Message> messageList);
    }

    interface Action{
        void getMessages();
    }
}
