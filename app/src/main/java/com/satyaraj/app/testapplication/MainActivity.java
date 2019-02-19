package com.satyaraj.app.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.satyaraj.app.testapplication.pojo.Message;

import java.util.List;

public class MainActivity extends AppCompatActivity implements Contract.View {

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        MainRepository mainRepository = new MainRepository(getContentResolver());

        mainPresenter = new MainPresenter(this, mainRepository);

        requestSmsPermission();

    }

    @Override
    public void displayMessages(List<Message> messageList) {
        adapter.updateList(messageList);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mainPresenter.getMessages();
            } else {
                Toast.makeText(MainActivity.this,"permission not granted", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.READ_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }else {
            mainPresenter.getMessages();
        }
    }
}
