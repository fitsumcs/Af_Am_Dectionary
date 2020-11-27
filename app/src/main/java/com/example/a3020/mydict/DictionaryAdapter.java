package com.example.a3020.mydict;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class DictionaryAdapter extends BaseAdapter {


    private ListItemListner listner;
    private ListItemListner listnerBtnDelete;
    Context mContext;
    ArrayList<Word> mSource;
    private ArrayList<Word> arraylist;

    public DictionaryAdapter(Context context,ArrayList<Word> source)
    {
        this.mContext = context;
        this.mSource = source;
        this.arraylist = new ArrayList<Word>();
        this.arraylist.addAll(mSource);

    }


    @Override
    public int getCount() {
        return mSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mSource.get(position).getKey();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        DictionaryAdapter.ViewHolder viewHolder;

        if (view == null)
        {
            viewHolder = new DictionaryAdapter.ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.dict_layout_item, viewGroup,false);
            viewHolder.textView =  view.findViewById(R.id.tvWord);
            view.setTag(viewHolder);

        }
        else
        {
            viewHolder = (DictionaryAdapter.ViewHolder) view.getTag();

        }

        viewHolder.textView.setText(mSource.get(position).getKey());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner!=null)
                    listner.onItemClick(position);
            }
        });


        return view;
    }

    public void removeItem(int position)
    {

        mSource.remove(position);

    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mSource.clear();
        if (charText.length() == 0) {
            mSource.addAll(arraylist);
        } else {
            for (Word wp : arraylist) {
                if (wp.getKey().toLowerCase(Locale.getDefault()).contains(charText)) {
                    mSource.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    class  ViewHolder
    {
        TextView textView;

    }
    public  void setOnItemClick(ListItemListner listItemListner)
    {

        this.listner = listItemListner;

    }
    public  void setOnItemDeleteClick(ListItemListner listItemListner)
    {

        this.listnerBtnDelete = listItemListner;

    }

}
