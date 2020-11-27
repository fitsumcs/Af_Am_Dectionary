package com.example.a3020.mydict;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity
         {

    MenuItem menuSetting;
    DictionaryFragment dictionaryFragment;
    BookmarkFragment bookmarkFragment;
    AboutFragmant aboutFragmant;
    RatingFragmant ratingFragmant;
    DetailFragment detailFragment;
    Toolbar toolbar;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        setSupportActionBar(toolbar);



        // the navigato of fragmant
        dictionaryFragment = new DictionaryFragment();
        bookmarkFragment = BookmarkFragment.getInstance(dbHelper);
        aboutFragmant = new AboutFragmant();
        ratingFragmant = new RatingFragmant();
        goToFragmat(dictionaryFragment,true);

        dictionaryFragment.setOnFragmentListener(new FragmentListner() {
            @Override
            public void onItemClick(String value) {

                 goToFragmat(DetailFragment.getNewInstance(value,dbHelper),false);


            }
        });
        bookmarkFragment.setOnFragmentListener(new FragmentListner() {
            @Override
            public void onItemClick(String value) {


                goToFragmat(DetailFragment.getNewInstance(value,dbHelper),false);
            }
        });


       SearchView edit_search = (SearchView) findViewById(R.id.edit_search);

       edit_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               dictionaryFragment.filterValue(query);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               dictionaryFragment.filterValue(newText);
               return false;
           }
       });



    }

//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menuSetting =  menu.findItem(R.id.action_settings);

       // String id= SaveState.getState(this,"dict_type");

//        if(id!= null)
//            onOptionsItemSelected(menu.findItem(Integer.valueOf(id)));
//        else
//            {
//                ArrayList<String> source = dbHelper.getWord(R.id.action_aftoam);
//                dictionaryFragment.resetDataSource(source);
//        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id!=R.id.action_clear)
        {
            //SaveState.saveState(this,"dict_type",String.valueOf(id));
        }

        //allaows for swthing menu
        if(R.id.action_settings==id)return true;
         // Save the state
        //SaveState.saveState(this,"dict_type", String.valueOf(id));

        ArrayList<String> source = dbHelper.getWord(id);
        //noinspection SimplifiableIfStatement
        System.out.println(String.valueOf(id));
        if (id == R.id.action_aftoam)
        {
            dictionaryFragment.resetDataSource(source);
             menuSetting.setIcon(getDrawable(R.drawable.aftoam));
        }
        else if(id == R.id.action_amtoaf)
        {
            dictionaryFragment.resetDataSource(source);
            menuSetting.setIcon(getDrawable(R.drawable.amtoaf));

        }

     



        return super.onOptionsItemSelected(item);
    }

    void  goToFragmat(Fragment fragment, boolean isTop)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction  fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (!isTop)
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String activefragmant = getSupportFragmentManager().findFragmentById(R.id.fragment_container).getClass().getSimpleName();
        if(activefragmant.equals(RatingFragmant.class.getSimpleName()))
        {
            menuSetting.setVisible(false);
            toolbar.findViewById(R.id.edit_search).setVisibility(View.GONE);
            toolbar.setTitle("Rate Us");

        }
        else if (activefragmant.equals(BookmarkFragment.class.getSimpleName()))
        {
            menuSetting.setVisible(false);
            toolbar.findViewById(R.id.edit_search).setVisibility(View.GONE);
            toolbar.setTitle("Bookmark");
        }
        else if(activefragmant.equals(AboutFragmant.class.getSimpleName()))
        {
            menuSetting.setVisible(false);
            toolbar.findViewById(R.id.edit_search).setVisibility(View.GONE);
            toolbar.setTitle("About");

        }

        else if(activefragmant.equals(DictionaryFragment.class.getSimpleName()))
        {
            menuSetting.setVisible(true);
            toolbar.findViewById(R.id.edit_search).setVisibility(View.VISIBLE);
            //toolbar.setTitle("");

        }
          return true;
    }
}
