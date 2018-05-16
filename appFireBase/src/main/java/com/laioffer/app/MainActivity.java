package com.laioffer.app;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import android.support.v4.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity implements EventFragment.OnItemSelectListener, CommentFragment.OnItemSelectListener
{
    private EventFragment mListFragment;
    private CommentFragment mGridFragment;

    @Override
    public void onItemSelected(int position){
        mGridFragment.onItemSelected(position);
    }

    @Override
    public void onCommentSelected(int position) {
        mListFragment.onItemSelected(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Show different fragments based on screen size.
        if (findViewById(R.id.fragment_container) != null) {
            Fragment fragment = isTablet() ? new CommentFragment() : new EventFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        //add list view
        mListFragment = new EventFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.event_container,     mListFragment).commit();


        //add Gridview
        if (isTablet()) {
            mGridFragment = new CommentFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.comment_container, mGridFragment).commit();
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        // Get ListView object from xml.
//
//
//        ListView eventListView = (ListView) findViewById(R.id.event_list);
//        LinearLayout linearLayout = findViewById(R.id.view_attach);
//        View view = LayoutInflater.from(this).inflate(R.layout.textview, linearLayout, false);
//        linearLayout.addView(view);
//
//        // Initialize an adapter.
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
////                this,
////                R.layout.event_item,
////                R.id.event_name,
////                getEventNames());
////
////        // Assign adapter to ListView.
////        EventAdapter adapter = new EventAdapter(this);
////        //
////        eventListView.setAdapter(adapter);
//
//        if (findViewById(R.id.fragment_container) != null) {
//            Fragment fragment = isTablet() ? new CommentFragment() : new EventFragment();
//            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
//    }


//    private boolean isTablet() {
//        return (getApplicationContext().getResources().getConfiguration().screenLayout &
//                Configuration.SCREENLAYOUT_SIZE_MASK) >=
//                Configuration.SCREENLAYOUT_SIZE_LARGE;
//    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    /**
     * A dummy function to get fake event names.
     *
     * @return an array of fake event names.
     */
    private String[] getEventNames() {
        String[] names = {
                "Event1", "Event2", "Event3",
                "Event4", "Event5", "Event6",
                "Event7", "Event8", "Event9",
                "Event10", "Event11", "Event12"};
        return names;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Life cycle test", "We are at onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Life cycle test", "We are at onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //important
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        if(isChangingConfigurations()) {
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
        Log.e("Life cycle test", "We are at onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Life cycle test", "We are at onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Life cycle test", "We are at onDestroy()");
    }




}
