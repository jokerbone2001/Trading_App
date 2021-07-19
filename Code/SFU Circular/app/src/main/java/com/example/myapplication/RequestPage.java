package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class RequestPage extends AppCompatActivity implements RequestAdapter.InnerItemOnclickListener{
    private DBRequestHelper dbRequestHelper;
    List<RequestClass> requestClassList;
    RequestAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);

        dbRequestHelper = new DBRequestHelper(RequestPage.this);

        ListView listview=findViewById(R.id.listview);
        requestClassList = dbRequestHelper.getRequestInfo();

        adapter = new RequestAdapter(this, requestClassList);
        adapter.setOnInnerItemOnClickListener(this);
        listview.setAdapter(adapter);

        Button addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(RequestPage.this,PostRequest.class),1000);
            }
        });
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            RequestClass lastRequest = dbRequestHelper.getLastRequest();
            requestClassList.add(lastRequest);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemClick(View v) {
        int position;
        position = (Integer) v.getTag();
        switch (v.getId()) {
            case R.id.delBtn:
                boolean flag = dbRequestHelper.deleteRequest(requestClassList.get(position).getId()+"");
                if(flag){
                    requestClassList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this,"delete success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this,"delete fail",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}