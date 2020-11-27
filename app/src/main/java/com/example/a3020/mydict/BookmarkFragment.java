package com.example.a3020.mydict;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BookmarkFragment extends Fragment implements SearchView.OnQueryTextListener{
    private FragmentListner listener;
    //private String value = "Helllow";
    private DBHelper dbHelper;
    ListView bookmarkList;
    BookmarkAdapter adapter;
    SearchView editsearch;
    public BookmarkFragment() {
        // Required empty public constructor
    }

    public static BookmarkFragment getInstance(DBHelper dbHelper){

        BookmarkFragment bookmarkFragment = new BookmarkFragment();
        bookmarkFragment.dbHelper = dbHelper;

        return  bookmarkFragment;

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
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        bookmarkList = (ListView) view.findViewById(R.id.bookmarkList);
        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) view.findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);
        adapter = new BookmarkAdapter(getActivity(),dbHelper.getAllWordFromBookMark() );
        bookmarkList.setAdapter(adapter);
        adapter.setOnItemClick(new ListItemListner() {
            @Override
            public void onItemClick(int position) {
                if (listener!=null)
                   listener.onItemClick(adapter.getItem(position).toString());

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

   public  void clearBookmark()
   {

       if(adapter.getCount() !=0)
       {
           dbHelper.clearBookmark();
           bookmarkList.setAdapter(null);
           adapter.notifyDataSetChanged();
           Toast.makeText(getContext(),"Bookmark Cleared !!",Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(getContext(),"Bookmark is Empty :) ",Toast.LENGTH_SHORT).show();
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

        String text = s;
        adapter.filter(text);
        return false;
    }
}
