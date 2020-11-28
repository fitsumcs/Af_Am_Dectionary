package com.example.a3020.mydict;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MenuItem menuSetting;
    DictionaryFragment dictionaryFragment;
    HistoryFragment historyFragment;
    BookmarkFragment bookmarkFragment;
    AboutFragmant aboutFragmant;
    DetailFragment detailFragment;
    DBHelper dbHelper;
    ArrayList<Word> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        source = dbHelper.getWord();


        // the navigato of fragmant
        dictionaryFragment = DictionaryFragment.getInstance(dbHelper);
        historyFragment = HistoryFragment.getInstance(dbHelper);
        bookmarkFragment = BookmarkFragment.getInstance(dbHelper);
        aboutFragmant = new AboutFragmant();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, dictionaryFragment).commit();


        dictionaryFragment.setOnFragmentListener(new FragmentListner() {
            @Override
            public void onItemClick(String value) {

                goToFragmat(DetailFragment.getNewInstance(value, dbHelper));


            }
        });
        bookmarkFragment.setOnFragmentListener(new FragmentListner() {
            @Override
            public void onItemClick(String value) {


                goToFragmat(DetailFragment.getNewInstance(value, dbHelper));
            }
        });

        historyFragment.setOnFragmentListener(new FragmentListner() {
            @Override
            public void onItemClick(String value) {


                goToFragmat(DetailFragment.getNewInstance(value, dbHelper));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_bookmark) {
            goToFragmat(bookmarkFragment);
        }
        if (id == R.id.action_aboutUs) {
            goToFragmat(aboutFragmant);
        }
        if (id == R.id.action_history) {
            goToFragmat(historyFragment);
        }
        if (id == R.id.action_LikeUs) {
            startWeb();
        }
        if (id == R.id.action_share) {
            shareIt();
        }
        if (id == R.id.action_clear) {
            String activefragmant = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
            if (activefragmant.equals(BookmarkFragment.class.getSimpleName()))
            {
                bookmarkFragment.clearBookmark();
            }
            else if(activefragmant.equals(HistoryFragment.class.getSimpleName()))
            {
                historyFragment.clearHistory();
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String activefragmant = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
        if (activefragmant.equals(DictionaryFragment.class.getSimpleName()))
        {
            invalidateOptionsMenu();
            menu.findItem(R.id.action_clear).setVisible(false);
            getSupportActionBar().setTitle("English  to Afar Dictionary");
        }
        if (activefragmant.equals(AboutFragmant.class.getSimpleName()))
        {
            invalidateOptionsMenu();
            menu.findItem(R.id.action_clear).setVisible(false);
            getSupportActionBar().setTitle("English  to Afar Dictionary");
        }
        if (activefragmant.equals(DetailFragment.class.getSimpleName()))
        {
            invalidateOptionsMenu();
            menu.findItem(R.id.action_clear).setVisible(false);
            getSupportActionBar().setTitle("English  to Afar Dictionary");
        }
        if (activefragmant.equals(BookmarkFragment.class.getSimpleName()))
        {
            invalidateOptionsMenu();
            menu.findItem(R.id.action_history).setVisible(false);
            menu.findItem(R.id.action_bookmark).setVisible(false);
            menu.findItem(R.id.action_share).setVisible(false);
            menu.findItem(R.id.action_aboutUs).setVisible(false);
            menu.findItem(R.id.action_LikeUs).setVisible(false);
           getSupportActionBar().setTitle("BookMark");
        }
        if (activefragmant.equals(HistoryFragment.class.getSimpleName()))
        {
            invalidateOptionsMenu();
            menu.findItem(R.id.action_history).setVisible(false);
            menu.findItem(R.id.action_bookmark).setVisible(false);
            menu.findItem(R.id.action_share).setVisible(false);
            menu.findItem(R.id.action_aboutUs).setVisible(false);
            menu.findItem(R.id.action_LikeUs).setVisible(false);
            getSupportActionBar().setTitle("History");
        }
        return super.onPrepareOptionsMenu(menu);
    }
    void goToFragmat(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("my_fragment").commit();

    }

    private void shareIt() {

        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "English  to Afar Dictionary");
        intent.putExtra(Intent.EXTRA_TEXT,"Check this Awesome Dictionary App https://play.google.com/store/apps/ENtoAF ");
        intent.setType("text/plain");
        startActivity(intent);

    }

    private void startWeb()
    {
        Uri uri = Uri.parse("http://www.google.com"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
