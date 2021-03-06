package com.rainsong.tiantiannews.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rainsong.tiantiannews.R;
import com.rainsong.tiantiannews.widget.CategoryTabStrip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maxliaops on 17-1-10.
 */

public class TabNewsFragment extends Fragment {
    private static final String TAG = "TabNewsFragment";

    private CategoryTabStrip tabs;
    private ViewPager pager;
    private NewsPagerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab_news, container, false);
        tabs = (CategoryTabStrip) rootView.findViewById(R.id.category_strip);
        pager = (ViewPager) rootView.findViewById(R.id.view_pager);
        adapter = new NewsPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);

        tabs.setViewPager(pager);
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public class NewsPagerAdapter extends FragmentStatePagerAdapter {

        private final List<String> catalogs = new ArrayList<String>();
        private Map<String, NewsFragment> fragmentMap = new HashMap<>();

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
            catalogs.add(getString(R.string.category_top));
            catalogs.add(getString(R.string.category_shehui));
            catalogs.add(getString(R.string.category_guonei));
            catalogs.add(getString(R.string.category_guoji));
            catalogs.add(getString(R.string.category_yule));
            catalogs.add(getString(R.string.category_tiyu));
            catalogs.add(getString(R.string.category_junshi));
            catalogs.add(getString(R.string.category_keji));
            catalogs.add(getString(R.string.category_caijing));
            catalogs.add(getString(R.string.category_shishang));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return catalogs.get(position);
        }

        @Override
        public int getCount() {
            return catalogs.size();
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem(): position=" + position);
            String catalog = catalogs.get(position);
            NewsFragment newsFragment = fragmentMap.get(catalog);
            if(newsFragment == null) {
                newsFragment = NewsFragment.newInstance(catalog);
                fragmentMap.put(catalog, newsFragment);
            }
            return newsFragment;
        }

    }
}
