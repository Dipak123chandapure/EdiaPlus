package com.example.deepak.myapplication.GroupDashboard;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Utility;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    ArrayList<BatchDTO> mList;
    Context mContext;

    public GroupAdapter(Context mContext, ArrayList<BatchDTO> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_dashbard_recycler_item, parent, false);
        return new LeadListViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
        BatchDTO data = mList.get(position);
        if (null != data) {
            headingViewHolder.circular_text.setText(data.getName().charAt(0) + "");
            int colour = Utility.getCircularTextBackground(data.getName().charAt(0));
            headingViewHolder.circular_text.getBackground().setColorFilter(colour, PorterDuff.Mode.SRC_ATOP);
            headingViewHolder.group_name.setText(data.getName());
        }
    }

    public int getItemCount() {
        return mList.size();
    }
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    public interface GroupFroagmetCallback {
        void onGroupItemClicked(BatchDTO dto);
        void onGroupMenuIconClicked(MenuItem item, BatchDTO dto);
    }

    GroupFroagmetCallback mGroupFroagmetCallback;

    public void setGroupFroagmetCallback(GroupFroagmetCallback mGroupFroagmetCallback) {
        this.mGroupFroagmetCallback = mGroupFroagmetCallback;
    }

    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected LinearLayout ll;
        protected TextView circular_text;
        protected TextView group_name;
        protected TextView activity_date;
        protected TextView student_name;
        protected ImageView menu_icon;
        MaterialRippleLayout ripple;


        public LeadListViewHolder(View v) {
            super(v);
            ripple = (MaterialRippleLayout) v.findViewById(R.id.ripple);
            circular_text = (TextView) v.findViewById(R.id.circular_text);
            group_name = (TextView) v.findViewById(R.id.group_name);
            activity_date = (TextView) v.findViewById(R.id.activity_date);
            student_name = (TextView) v.findViewById(R.id.student_name);
            menu_icon = (ImageView) v.findViewById(R.id.menu_icon);
            menu_icon.setOnClickListener(this);
            ripple.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Log.d("rohit", "Liner layout is clicked");
            switch (v.getId()) {
                case R.id.menu_icon:
                    PopupMenu popup = new PopupMenu(v.getContext(), v);
                    popup.inflate(R.menu.groups_menu);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            if (null != mGroupFroagmetCallback) {
                                mGroupFroagmetCallback.onGroupMenuIconClicked(item, mList.get(getAdapterPosition()));
                            }
                            return false;
                        }
                    });

                    popup.show();
                    break;
                default:
                if (null != mGroupFroagmetCallback)
                    mGroupFroagmetCallback.onGroupItemClicked(mList.get(getAdapterPosition()));
                    break;
            }
        }
    }
}
