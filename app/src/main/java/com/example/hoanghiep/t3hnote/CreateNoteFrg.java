package com.example.hoanghiep.t3hnote;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class CreateNoteFrg extends Fragment implements View.OnClickListener {
    private View view;
    private TextView btCreate;
    private EditText edtNote;
    private ImageView ivColor1, ivColor2, ivColor3, ivColor4, ivColor5;
    private DataBase dataBase = new DataBase();
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_createnote, container, false);
        initView();
        return view;
    }

    private void initView() {
        datePicker = (DatePicker) view.findViewById(R.id.datepicker);
        btCreate = (TextView) view.findViewById(R.id.bt_create);
        edtNote = (EditText) view.findViewById(R.id.edt_note);
        edtNote.setContentDescription("0099CC");
        ivColor1 = (ImageView) view.findViewById(R.id.iv_color_1);
        ivColor2 = (ImageView) view.findViewById(R.id.iv_color_2);
        ivColor3 = (ImageView) view.findViewById(R.id.iv_color_3);
        ivColor4 = (ImageView) view.findViewById(R.id.iv_color_4);
        ivColor5 = (ImageView) view.findViewById(R.id.iv_color_5);

        btCreate.setOnClickListener(this);
        ivColor1.setOnClickListener(this);
        ivColor2.setOnClickListener(this);
        ivColor3.setOnClickListener(this);
        ivColor4.setOnClickListener(this);
        ivColor5.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_create:
                String content = edtNote.getText().toString();
                int color = Color.parseColor("#" + edtNote.getContentDescription());
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                String date = day + "/" + month + "/" + year;
                dataBase.insert(new Note(date, content, color));
                edtNote.setText("");
                ((MainActivity) getActivity()).showListNoteScreen();
                break;
            case R.id.iv_color_1:
                edtNote.setBackgroundColor(getResources().getColor(R.color.color1));
                edtNote.setContentDescription(ivColor1.getContentDescription());

                break;
            case R.id.iv_color_2:
                edtNote.setBackgroundColor(getResources().getColor(R.color.color2));
                edtNote.setContentDescription(ivColor2.getContentDescription());
                break;
            case R.id.iv_color_3:
                edtNote.setBackgroundColor(getResources().getColor(R.color.color3));
                edtNote.setContentDescription(ivColor3.getContentDescription());
                break;
            case R.id.iv_color_4:
                edtNote.setBackgroundColor(getResources().getColor(R.color.color4));
                edtNote.setContentDescription(ivColor4.getContentDescription());
                break;
            case R.id.iv_color_5:
                edtNote.setBackgroundColor(getResources().getColor(R.color.color5));
                edtNote.setContentDescription(ivColor5.getContentDescription());
                break;


        }

    }
}
