package com.example.hoanghiep.t3hnote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Hoang Hiep on 3/5/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter {
    private ArrayList<Note> listNote;
    private Context mContext;
    private DataBase dataBase;
    private Dialog dialog;

    public NoteAdapter(Context mContext) {
        this.mContext = mContext;
        initData();

    }

    private void initData() {

        dataBase = new DataBase();
        dataBase.copyDataBase(mContext);
        listNote = dataBase.getAll();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemNote = View.inflate(mContext, R.layout.item_note, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        itemNote.setLayoutParams(params);
        return new ViewHolder(itemNote);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textViewNote.setText(listNote.get(position).getContent());
        viewHolder.textViewDate.setText(listNote.get(position).getDate());
        viewHolder.linearLayout.setBackgroundColor(listNote.get(position).getColor());
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBase.delete(listNote.get(holder.getAdapterPosition()).getId());
                listNote.remove(holder.getAdapterPosition());
                NoteAdapter.this.notifyDataSetChanged();
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(mContext, listNote.get(holder.getAdapterPosition()),position);
                dialog.show();
                Button buttonxn=(Button)dialog.findViewById(R.id.bt_xacnhan);
                buttonxn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataBase.delete(listNote.get(holder.getAdapterPosition()).getId());
                        listNote.remove(holder.getAdapterPosition());
                        NoteAdapter.this.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                Button buttondl=(Button)dialog.findViewById(R.id.bt_donglai);
                buttondl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println(listNote.size());
        return listNote.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDate, textViewNote;
        private LinearLayout linearLayout;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewDate = (TextView) itemView.findViewById(R.id.tv_date);
            textViewNote = (TextView) itemView.findViewById(R.id.tv_note);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item_note);
            imageView = (ImageView) itemView.findViewById(R.id.iv_close);

        }

    }
    public void delete(Note note,int position){
        dataBase.delete(note.getId());
        listNote.remove(position);
    }
}
