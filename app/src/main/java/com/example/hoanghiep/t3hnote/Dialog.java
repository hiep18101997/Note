package com.example.hoanghiep.t3hnote;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class Dialog extends android.app.Dialog{
    private Button btXacNhan, btDongLai;
    private TextView textViewDate, textViewContent;
    private Note note;
    private LinearLayout linearLayout;
    private NoteAdapter noteAdapter;
    private int position;

    public Dialog(Context context, Note note, int position) {
        super(context);
        this.note = note;
        this.position = position;
        noteAdapter = new NoteAdapter(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_dialog);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        initView();
    }

    private void initView() {

        linearLayout = (LinearLayout) findViewById(R.id.ln_dialog);
        linearLayout.setBackgroundColor(note.getColor());
        textViewDate = (TextView) findViewById(R.id.tv_date_dialog);
        textViewContent = (TextView) findViewById(R.id.tv_content_dialog);
        textViewContent.setText(note.getContent());
        textViewDate.setText(note.getDate());
    }
}
