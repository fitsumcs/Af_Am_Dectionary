package com.example.a3020.mydict;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class BookmarkFragment extends Fragment {
    private FragmentListner listener;
    //private String value = "Helllow";

    public BookmarkFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
           setHasOptionsMenu(true);

        ListView bookmarkList = (ListView) view.findViewById(R.id.bookmarkList);
        final BookmarkAdapter adapter = new BookmarkAdapter(getActivity(),getListOfWords() );
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

    String[]  getListOfWords()
    {
        String[] source =new String[] {
                "a",
                "abandon",
                "ablity",
                "able",
                "about",
                "above",
                "abroad",
                "after",
                "afterwards",
                "again",
                "against",
                "all",
                "almost",
                "alone",
                "along",
                "already",
                "also",
                "although",
                "always",
                "am",
                "among",
                "amongst",
                "amoungst",
                "amount",
                "an",
                "and",
                "another",
                "any",
                "anyhow",
                "anyone",
                "anything",


        };

        return source;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_clear,menu);
    }
}
