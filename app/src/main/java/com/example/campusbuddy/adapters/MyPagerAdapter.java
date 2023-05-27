package com.example.campusbuddy.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.campusbuddy.fragments.AboutFragment;
import com.example.campusbuddy.fragments.DetailsFragment;
import com.example.campusbuddy.fragments.PhotosFragment;

public class MyPagerAdapter extends FragmentPagerAdapter {
//    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
//        super(fragmentActivity);
//    }


    private Context myContext;
    private int totalTabs;

    public MyPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

//     this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DetailsFragment detailsFragment = new DetailsFragment();
                return detailsFragment;
            case 1:
                AboutFragment aboutFragment = new AboutFragment();
                return aboutFragment;
            case 2:
                PhotosFragment photosFragment = new PhotosFragment();
                return photosFragment;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return totalTabs;
    }

//    @NonNull
//    @Override
//    public Fragment createFragment(int position) {
//        switch (position) {
//            case 0:
//                DetailsFragment detailsFragment = new DetailsFragment();
//                return detailsFragment;
//            case 1:
//                AboutFragment aboutFragment = new AboutFragment();
//                return aboutFragment;
//            case 2:
//                PhotosFragment photosFragment = new PhotosFragment();
//                return photosFragment;
//            default:
//                return null;
//        }
//
//    }
//
//    @Override
//    public int getItemCount () {
//        return 3;
//    }

}