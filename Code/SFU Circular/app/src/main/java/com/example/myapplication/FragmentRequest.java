package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class FragmentRequest extends Fragment implements RequestAdapter.InnerItemOnclickListener{
    private DBRequestHelper dbRequestHelper;
    List<RequestClass> requestClassList;
    RequestAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request,container,false);

        dbRequestHelper = new DBRequestHelper(getActivity());
        ListView listView = view.findViewById(R.id.requestListView2);
        requestClassList = dbRequestHelper.getRequestInfo();
        adapter = new RequestAdapter(getActivity(),requestClassList);
        adapter.setOnInnerItemOnClickListener(this);
        listView.setAdapter(adapter);

        Button addBtn = view.findViewById(R.id.addBtn2);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Action: switch to add item");
                startActivityForResult(new Intent(getActivity(),PostRequest.class),1000);
            }
        });
        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
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
                    Toast.makeText(getContext(),"delete success",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(),"delete fail",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}