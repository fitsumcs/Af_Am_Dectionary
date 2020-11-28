package com.example.a3020.mydict;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class HistoryFragment extends Fragment implements SearchView.OnQueryTextListener{


    private FragmentListner listener;
    private DBHelper dbHelper;
    ListView historyList ;
    DictionaryAdapter adapter;
    SearchView editsearch;


    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment getInstance(DBHelper dbHelper){

        HistoryFragment historyFragment = new HistoryFragment();
        historyFragment .dbHelper = dbHelper;

        return  historyFragment ;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        historyList = (ListView) view.findViewById(R.id.historyList);
        adapter = new DictionaryAdapter(getActivity(),dbHelper.getHistoryWord() );

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        historyList .setAdapter(adapter);
        adapter.setOnItemClick(new ListItemListner() {
            @Override
            public void onItemClick(int position) {
                if (listener!=null)
                    listener.onItemClick(adapter.getItem(position).toString());
                Word word = dbHelper.getWord(adapter.getItem(position).toString());
                dbHelper.addHistory(word);

            }
        });



        adapter.setOnItemDeleteClick(new ListItemListner() {
            @Override
            public void onItemClick(int postion) {
                adapter.removeItem(postion);

                TextView textView = (TextView)getActivity().findViewById(R.id.tvWord);
                Word word = dbHelper.getWordFormBookMark(textView.getText().toString());

                dbHelper.removeBookmark(word);

                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }
    public  void clearHistory()
    {

        if(adapter.getCount() !=0)
        {
            dbHelper.clearHistory();
            historyList .setAdapter(null);
            adapter.notifyDataSetChanged();
            Toast.makeText(getContext(),"History Cleared !!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(),"History is Empty :) ",Toast.LENGTH_SHORT).show();
        }

    }

    public void setOnFragmentListener(FragmentListner listener) {

        this.listener = listener;

    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String text =s;
        adapter.filter(text);
        return false;
    }
}