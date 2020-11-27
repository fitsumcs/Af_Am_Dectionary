package com.example.a3020.mydict;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment {

    private  String value = "";
    private TextView tvWord;
    private ImageButton btVol, btBookmark;
    private WebView bttranslate;
    private DBHelper dbHelper;
    private int dicType;




    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment getNewInstance(String value , DBHelper dbHelper)
    {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.value = value;
        detailFragment.dbHelper = dbHelper;
        return detailFragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvWord = (TextView) view.findViewById(R.id.btnWord);
        bttranslate = (WebView) view.findViewById(R.id.wordTranslate);
        btVol = (ImageButton) view.findViewById(R.id.btnVol);
        btBookmark = (ImageButton) view.findViewById(R.id.btnBookmark);
        btBookmark.setTag(0);

        final Word word = dbHelper.getWord(value);

        tvWord.setText(word.key);
        bttranslate.loadDataWithBaseURL(null,word.value, "text/html; charset=utf-8","UTF-8" ,null);
        Word book = dbHelper.getWordFormBookMark(value);

        int isMark = book ==null?0:1;
        btBookmark.setTag(isMark);

        int icon = book ==null?R.drawable.ic_bookmark_border:R.drawable.ic_bookmark_fill;
        btBookmark.setImageResource(icon);

        btBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
              int i = (int) btBookmark.getTag();
              if(i==0)
              {
                  btBookmark.setImageResource(R.drawable.ic_bookmark_fill);
                  btBookmark.setTag(1);
                  dbHelper.addBookmark(word);


              }
              else if(i==1)
              {
                  btBookmark.setImageResource(R.drawable.ic_bookmark_border);
                  btBookmark.setTag(0);
                  dbHelper.removeBookmark(word);

              }
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


}
