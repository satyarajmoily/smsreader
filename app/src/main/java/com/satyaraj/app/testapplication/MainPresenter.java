package com.satyaraj.app.testapplication;

import com.satyaraj.app.testapplication.base.BasePresenter;
import com.satyaraj.app.testapplication.custom.TimeAgo;
import com.satyaraj.app.testapplication.pojo.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainPresenter extends BasePresenter implements Contract.Action {

    static int HEADER = 100;
    private static int ITEM = 101;

    private MainRepository mainRepository;
    private Contract.View mView;

    MainPresenter(MainActivity activity, MainRepository mainRepository){
        this.mView = activity;
        this.mainRepository = mainRepository;
        this.mainRepository.onAttach(this);
    }

    public void getMessages(){
        Calendar calendar = Calendar.getInstance();
        long time =  calendar.getTimeInMillis() - (24 * 60 * 60 * 1000);
        mainRepository.getMessages(time);
    }

    void onRetrievedMessages(List<Message> messageList) {

        List<Message> sortedMessages = new ArrayList<>();
        String tempTime = null ;

        for (int i = 0; i < messageList.size(); i++){
            long time = messageList.get(i).getTime();
            String timeToText = TimeAgo.covertTimeToText(time);
            if (tempTime == null){
                tempTime = timeToText;
                Message message = new Message(null,0, timeToText, HEADER);
                sortedMessages.add(message);
            }else if (tempTime.equals(timeToText)){
                messageList.get(i).setType(ITEM);
                sortedMessages.add(messageList.get(i));
            }else {
                tempTime = timeToText;
                Message message = new Message(null,0,timeToText,HEADER);
                sortedMessages.add(message);
            }
        }

        mView.displayMessages(sortedMessages);
    }
}
