package com.android15dev.nursuingcanteen.views.activities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android15dev.nursuingcanteen.R;
import com.android15dev.nursuingcanteen.views.adapters.ListAdapter;
import com.android15dev.nursuingcanteen.views.adapters.VerticalSpaceItemDecoration;

public class ListActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(getIntent().getIntExtra("pos", 0));

    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_LIST = "list";

        public PlaceholderFragment () {
        }

        public static PlaceholderFragment newInstance (String[] list) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putStringArray(ARG_LIST, list);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_list, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.addItemDecoration(new VerticalSpaceItemDecoration(20));

            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setFocusable(false);

            recyclerView.setAdapter(new ListAdapter(getActivity(), getArguments().getStringArray(ARG_LIST)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final String[] arr;
        private final TypedArray catdetails;
//        private List<String[]> listing = new ArrayList<>();


        public SectionsPagerAdapter (FragmentManager fm) {
            super(fm);
            arr = getResources().getStringArray(R.array.categories);
            catdetails = getResources().obtainTypedArray(R.array.catdetails);
           /* listing.add(getResources().getStringArray(R.array.paranathas));
            listing.add(getResources().getStringArray(R.array.snacks));
            listing.add(getResources().getStringArray(R.array.drinks));
            listing.add(getResources().getStringArray(R.array.shakes));
            listing.add(getResources().getStringArray(R.array.soups));
            listing.add(getResources().getStringArray(R.array.chinese));
            listing.add(getResources().getStringArray(R.array.rice));
            listing.add(getResources().getStringArray(R.array.indian));*/
        }

        @Override
        public Fragment getItem (int position) {
            return PlaceholderFragment.newInstance(getResources().getStringArray(catdetails.getResourceId(position, -1)));
        }

        @Override
        public int getCount () {
            return arr.length;
        }

        @Override
        public CharSequence getPageTitle (int position) {
            return arr[position];
        }
    }
}
