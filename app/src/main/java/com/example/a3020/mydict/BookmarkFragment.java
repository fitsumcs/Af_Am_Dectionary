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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class BookmarkFragment extends Fragment {
    private FragmentListner listener;
    //private String value = "Helllow";
    private DBHelper dbHelper;
    ListView bookmarkList;
    BookmarkAdapter adapter;
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
        adapter = new BookmarkAdapter(getActivity(),dbHelper.getAllWordFromBookMark() );
        bookmarkList.setAdapter(adapter);
        adapter.setOnItemClick(new ListItemListner() {
            @Override
            public void onItemClick(int position) {
                if (listener!=null)
                   listener.onItemClick(String.valueOf(adapter.getItem(position)));

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

    public void setOnFragmentListener(FragmentListner listener) {

        this.listener = listener;

    }


//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_clear,menu);
//        MenuItem item = menu.findItem(R.id.action_clear);
//
//        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                dbHelper.clearBookmark();
//                bookmarkList.setAdapter(null);
//                adapter.notifyDataSetChanged();
//                Toast.makeText(getContext(),"Bookmark Cleared !!",Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });
//        item.setVisible(true);
//        super.onCreateOptionsMenu(menu, inflater);
//
//
//    }


}
