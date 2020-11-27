package com.example.a3020.mydict;

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

public class MainActivity extends AppCompatActivity
         {

    MenuItem menuSetting;
    DictionaryFragment dictionaryFragment;
    BookmarkFragment bookmarkFragment;
    AboutFragmant aboutFragmant;
    RatingFragmant ratingFragmant;
    DetailFragment detailFragment;
    DBHelper dbHelper;
    ArrayList<String> source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        source = dbHelper.getWord();


        // the navigato of fragmant
        dictionaryFragment = DictionaryFragment.getInstance(dbHelper);
        bookmarkFragment = BookmarkFragment.getInstance(dbHelper);
        aboutFragmant = new AboutFragmant();
        ratingFragmant = new RatingFragmant();
        goToFragmat(dictionaryFragment);


//
//        dictionaryFragment.setOnFragmentListener(new FragmentListner() {
//            @Override
//            public void onItemClick(String value) {
//
//                 goToFragmat(DetailFragment.getNewInstance(value,dbHelper),false);
//
//
//            }
//        });
//        bookmarkFragment.setOnFragmentListener(new FragmentListner() {
//            @Override
//            public void onItemClick(String value) {
//
//
//                goToFragmat(DetailFragment.getNewInstance(value,dbHelper));
//            }
//        });


//       SearchView edit_search = (SearchView) findViewById(R.id.edit_search);
//
//       edit_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//           @Override
//           public boolean onQueryTextSubmit(String query) {
//               dictionaryFragment.filterValue(query);
//               return false;
//           }
//
//           @Override
//           public boolean onQueryTextChange(String newText) {
//               dictionaryFragment.filterValue(newText);
//               return false;
//           }
//       });



    }

//    @Override
//    public void onBackPressed() {
//            super.onBackPressed();
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //dictionaryFragment.resetDataSource(source);
        int id = item.getItemId();

        if(id==R.id.action_bookmark)
        {
            goToFragmat(bookmarkFragment);
        }
        if(id==R.id.action_aboutUs)
        {
            goToFragmat(aboutFragmant);
        }

//        if(id!=R.id.action_clear)
//        {
//            //SaveState.saveState(this,"dict_type",String.valueOf(id));
//        }

        //allaows for swthing menu
       // if(R.id.action_settings==id)return true;
         // Save the state
        //SaveState.saveState(this,"dict_type", String.valueOf(id));

       // ArrayList<String> source = dbHelper.getWord(id);
        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_aftoam)
//        {
//            dictionaryFragment.resetDataSource(source);
//             menuSetting.setIcon(getDrawable(R.drawable.aftoam));
//        }
//        else if(id == R.id.action_amtoaf)
//        {
//            dictionaryFragment.resetDataSource(source);
//            menuSetting.setIcon(getDrawable(R.drawable.amtoaf));
//
//        }


        return super.onOptionsItemSelected(item);
    }

    void  goToFragmat(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();

    }

}
