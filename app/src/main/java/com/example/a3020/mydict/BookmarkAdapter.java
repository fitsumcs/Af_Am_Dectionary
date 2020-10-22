package com.example.a3020.mydict;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class BookmarkAdapter extends BaseAdapter
{
    private ListItemListner listner;
    private ListItemListner listnerBtnDelete;
    Context mContext;
    ArrayList<String> mSource;

    public BookmarkAdapter(Context context,ArrayList<String> source)
    {
         this.mContext = context;
         this.mSource = source;

    }


    @Override
    public int getCount() {
        return mSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null)
        {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.bookmark_layout_item, viewGroup,false);
            viewHolder.textView =  view.findViewById(R.id.tvWord);
            viewHolder.btnDelete =  view.findViewById(R.id.btnDelete);

            view.setTag(viewHolder);

        }
        else
        {
             viewHolder = (ViewHolder) view.getTag();

        }

         viewHolder.textView.setText(mSource.get(position));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listner!=null)
                listner.onItemClick(position);
            }
        });

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listnerBtnDelete!=null)
                    listnerBtnDelete.onItemClick(position);

            }
        });


        return view;
    }

    public void removeItem(int position)
    {

        mSource.remove(position);

    }




    class  ViewHolder
    {
      TextView textView;
      ImageView btnDelete;


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
