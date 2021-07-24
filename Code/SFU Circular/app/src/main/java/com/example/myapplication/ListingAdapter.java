package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class ListingAdapter extends BaseAdapter implements View.OnClickListener {
    private final Context context;
    private final ArrayList<ListingClass> Listinglist;
    private InnerItemOnclickListener mListener;

    public ListingAdapter(Context context,ArrayList<ListingClass> objects){
        this.context = context;
        this.Listinglist=objects;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Listinglist == null ? 0 : Listinglist.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return Listinglist.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_listing_list_item, null);

        ListingClass listing = (ListingClass)getItem(position);

        TextView tv_name = view.findViewById(R.id.tv_itemName_listingList);
        tv_name.setText("Item name: " + listing.getItemName());

        TextView tv_desc = view.findViewById(R.id.tv_desc_listingList);
        tv_desc.setText("Description: " + listing.getDescription());

        TextView tv_contact = view.findViewById(R.id.tv_phone_listingList);
        tv_contact.setText("Phone: " + listing.getPhone());

        Button delBtn = view.findViewById(R.id.btn_del_listing);
        SharedPreferences sp = context.getSharedPreferences("loginInfo", MODE_PRIVATE);

        int loginuserid = sp.getInt("userid", 0);

        delBtn.setTag(position);
        delBtn.setOnClickListener(this);

        return view;
    }

    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(ListingAdapter.InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
