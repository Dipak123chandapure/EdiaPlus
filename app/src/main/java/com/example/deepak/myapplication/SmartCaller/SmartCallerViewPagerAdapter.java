package com.example.deepak.myapplication.SmartCaller;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deepak.myapplication.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class SmartCallerViewPagerAdapter extends PagerAdapter {
    Context mContext;
    RecyclerView smart_caller_exisiting_student_recycler;
    public SmartCallerViewPagerAdapter(Context mContext){
        this.mContext = mContext;
    }
    public int getCount() {
        return 3;
    }

    public boolean isViewFromObject(View container, Object object) {
        return container == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = null;

        switch (position) {
            case 0:
                view = new View(mContext);
                container.addView(view, 0);
                break;
            case 1:
                view = inflater.inflate(R.layout.smart_caller_dial_main_view_layout, null);
                container.addView(view, 1);
                break;
            case 2:
                view = new View(mContext);
                container.addView(view, 2);
                break;
        }

        return view;
    }

    public void destroyItem(ViewGroup collection, int position, Object view) {
        Log.d("rohit", "on item destroy " + position);
        ((ViewPager) collection).removeView((View) view);
    }
}
