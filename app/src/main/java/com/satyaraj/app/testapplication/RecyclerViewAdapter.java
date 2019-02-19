package com.satyaraj.app.testapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.satyaraj.app.testapplication.pojo.Message;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.satyaraj.app.testapplication.MainPresenter.HEADER;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> mMessageList = new ArrayList<>(0);

    RecyclerViewAdapter (){

    }

    void updateList(List<Message> messageList){
        mMessageList.addAll(messageList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessageList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view;

        if (viewType == HEADER) {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_header, parent, false);
            viewHolder = new ViewHeader(view);
        } else {
            view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_sms, parent, false);
            viewHolder = new ViewItem(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = mMessageList.get(position);

        if (holder instanceof ViewItem) {
            ((ViewItem) holder).onBind(message);
        } else if (holder instanceof ViewHeader) {
            ((ViewHeader) holder).onBind(message);
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    private class ViewHeader extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHeader(View view) {
            super(view);
            textView = view.findViewById(R.id.text_header);
        }

        void onBind(Message message){
            textView.setText(message.getTitle());
        }
    }

    private class ViewItem extends RecyclerView.ViewHolder {

        TextView textView;

        ViewItem(View view) {
            super(view);
            textView = view.findViewById(R.id.message_body);
        }

        void onBind(Message message){
            textView.setText(message.getBody());
        }
    }
}
