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

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class FragmentListing extends Fragment implements ListingAdapter.InnerItemOnclickListener{
    private DBListingHelper ldb;
    ArrayList<ListingClass> listingList;
    ListingAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listing,container,false);

        ldb = new DBListingHelper(getActivity());

        ListView listview = view.findViewById(R.id.listingListView);
        listingList = ldb.getListingInfo();

        adapter = new ListingAdapter(getActivity(), listingList);
        adapter.setOnInnerItemOnClickListener(this);
        listview.setAdapter(adapter);

        Button addBtn = view.findViewById(R.id.goToPostListing2);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), PostListing.class), 1000);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void onActivityResult(int listingCode, int resultCode, Intent data) {
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
                Toast.makeText(getActivity(), "delete success", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(), "delete fail", Toast.LENGTH_SHORT).show();
        }
    }
}

