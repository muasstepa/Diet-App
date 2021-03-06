package com.example.muasmakkode.diet;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muasmakkode.diet.Data.CardViewMakananAdapter;
import com.example.muasmakkode.diet.Data.ItemClickSupport;
import com.example.muasmakkode.diet.Data.MakananData;
import com.example.muasmakkode.diet.Data.MakananModel;
import com.example.muasmakkode.diet.UI.DetailMakanan;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MakananFragment extends Fragment {


    @BindView(R.id.recycle_view_makanan)
    RecyclerView recycleViewMakanan;
    Unbinder unbinder;
    private ArrayList<MakananModel> list;

    public MakananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_makanan, container, false);
        unbinder = ButterKnife.bind(this, view);


        recycleViewMakanan.setHasFixedSize(true);

        list = new ArrayList<>();
        list.addAll(MakananData.getListData());

        showRecyclerCardView();

        return view;
    }

    private void showRecyclerCardView() {
        recycleViewMakanan.setLayoutManager(new LinearLayoutManager(getContext()));
        CardViewMakananAdapter cardViewMakananAdapter = new CardViewMakananAdapter();
        cardViewMakananAdapter.setListMakanan(list);
        recycleViewMakanan.setAdapter(cardViewMakananAdapter);

        ItemClickSupport.addTo(recycleViewMakanan).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedMakanan(list.get(position));
            }
        });
    }

    private void showSelectedMakanan(MakananModel makananModel) {
        Intent i = new Intent(getContext(), DetailMakanan.class);
        makananModel.getNama_makanan();
        makananModel.getUkuran_saji();
        makananModel.getKalori_makanan();
        makananModel.getKarbo_makanan();
        makananModel.getProtein_makanan();
        makananModel.getLemak_makanan();

        i.putExtra("makananModel", makananModel);
        startActivity(i);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
