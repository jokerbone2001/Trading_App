package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class RequestAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<RequestClass> list;
    private InnerItemOnclickListener mListener;

    public RequestAdapter(Context context,List<RequestClass> objects){
        this.context = context;
        this.list=objects;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.request_list_item, null);
        RequestClass requestClass = (RequestClass)getItem(position);
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText("Item name:"+requestClass.getName());
        TextView tv_desc = view.findViewById(R.id.tv_desc);
        tv_desc.setText(requestClass.getDescription());
        TextView tv_contact = view.findViewById(R.id.tv_contact);
        tv_contact.setText(requestClass.getContact_info());
        Button delBtn = view.findViewById(R.id.delBtn);
        SharedPreferences sp = context.getSharedPreferences("loginInfo",MODE_PRIVATE);
        int loginuserid = sp.getInt("userid",0);
        if(loginuserid==Integer.parseInt(requestClass.getUserid())){
            delBtn.setVisibility(View.VISIBLE);
        }else{
            delBtn.setVisibility(View.GONE);
        }
        delBtn.setTag(position);
        delBtn.setOnClickListener(this);
        return view;
    }

    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
