package com.example.hoanghiep.t3hnote;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class ListNoteFrg extends Fragment implements View.OnClickListener {
    private View view;
    private ImageView btAdd;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listnote, container, false);
        initView();
        return view;
    }

    private void initView() {
        btAdd = (ImageView) view.findViewById(R.id.bt_add);
        btAdd.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_note);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        NoteAdapter noteAdapter = new NoteAdapter(view.getContext());
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add:
                ((MainActivity) getActivity()).showCreateScreen();
                break;
        }

    }
}
