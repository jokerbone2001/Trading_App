package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListingPage extends AppCompatActivity implements ListingAdapter.InnerItemOnclickListener {

    private DBListingHelper ldb;
    ArrayList<ListingClass> listingList;
    ListingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_page);

        ldb = new DBListingHelper(ListingPage.this);

        ListView listview = findViewById(R.id.listingListView);
        listingList = ldb.getListingInfo();

        adapter = new ListingAdapter(this, listingList);
        adapter.setOnInnerItemOnClickListener(this);
        listview.setAdapter(adapter);

        Button addBtn = findViewById(R.id.goToPostListing);
        addBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ListingPage.this, PostListing.class), 1000);
            }
        });
    }

    protected void onActivityResult(int listingCode, int resultCode, Intent data) {
        super.onActivityResult(listingCode, resultCode, data);

        if (listingCode == 1000 && resultCode == RESULT_OK) {
            listingList.add(ldb.getLastListing());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void itemClick(View v) {
        int position = (Integer) v.getTag();

        if (v.getId() == R.id.btn_del_listing) {
            boolean flag = ldb.deleteListing(listingList.get(position).getId() + "");
            if (flag) {
                listingList.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "delete fail", Toast.LENGTH_SHORT).show();
        }
    }
}
