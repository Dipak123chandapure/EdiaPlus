package com.example.deepak.myapplication.StudentDashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.zip.Inflater;


public class StudentsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<StudentDTO> mList;

    public StudentsListAdapter(Context mContext, ArrayList<StudentDTO> mList) {
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item, parent, false);
        return new LeadListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder mHolder = (LeadListViewHolder) holder;
        StudentDTO data = mList.get(position);
        mHolder.campaign_name.setText(data.getForm1Entity1() + " " + data.getForm1Entity2());
        mHolder.circular_text.setText(data.getForm1Entity1().charAt(0) + "");
        int colour = Utility.getCircularTextBackground(data.getForm1Entity1().charAt(0));

        mHolder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
        mHolder.email_id.setText(data.getForm1Entity3());
        mHolder.mobile_num.setText(data.getForm1Entity4());

        if (position == (mList.size() - 5))
            loadMore(mList.size());
    }

    private void loadMore(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mOnGroupStudentCallback.loadMore(index);
            }
        }).start();
    }


    OnGroupStudentCallback mOnGroupStudentCallback;

    public void setOnGroupStudentCallback(OnGroupStudentCallback mOnGroupStudentCallback) {
        this.mOnGroupStudentCallback = mOnGroupStudentCallback;
    }

    public interface OnGroupStudentCallback {
        void loadMore(int index);

        void studentClicked(int position);

        void popupMenuClicked(MenuItem menuItem, StudentDTO dto);
    }

    @Override
    public int getItemCount() {
        if (null != mList) {
            return mList.size();
        }
        return 0;
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        LinearLayout linearLayout;
        TextView circular_text;
        TextView campaign_name;
        TextView email_id;
        TextView mobile_num;
        ImageView menu_icon;
        MaterialRippleLayout ripple;

        public LeadListViewHolder(View v) {
            super(v);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            circular_text = (TextView) itemView.findViewById(R.id.circular_text);
            campaign_name = (TextView) itemView.findViewById(R.id.campaign_name);
            ripple = (MaterialRippleLayout) itemView.findViewById(R.id.ripple_layout);
            linearLayout.setOnLongClickListener(this);
            linearLayout.setOnClickListener(this);
            email_id = (TextView) itemView.findViewById(R.id.email_id);
            mobile_num = (TextView) itemView.findViewById(R.id.mobile_num);
            menu_icon = (ImageView) itemView.findViewById(R.id.menu_icon);
            menu_icon.setOnClickListener(this);
        }


        public void onClick(View v) {
            Log.d("rohit", "material ripple clicked"+v.getId()+" "+R.id.ripple_layout);
            switch (v.getId()) {
                case R.id.linear_layout:
                    if (null != mOnGroupStudentCallback)
                        mOnGroupStudentCallback.studentClicked(getAdapterPosition());
                    break;

                case R.id.menu_icon:
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    popup.inflate(R.menu.studeny_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            if (null != mOnGroupStudentCallback) {
                                mOnGroupStudentCallback.popupMenuClicked(item, mList.get(getAdapterPosition()));
                            }
                            return false;
                        }
                    });

                    popup.show();

                    break;

            }
        }

        @Override
        public boolean onLongClick(View v) {
            Log.d("rohit", "material ripple clicked");
            return false;
        }
    }
}
