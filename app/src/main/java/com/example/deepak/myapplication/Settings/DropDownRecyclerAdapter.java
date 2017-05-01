package com.example.deepak.myapplication.Settings;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.deepak.myapplication.Database.DAO.DropDownDataDAO;
import com.example.deepak.myapplication.Database.DTO.DropDownDataDTO;
import com.example.deepak.myapplication.Database.DTO.ParentDropDownDTO;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.Utility.Constant;
import java.util.ArrayList;


public class DropDownRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ParentDropDownDTO> mList;
    Context mContext;

    public DropDownRecyclerAdapter(Context mContext, ArrayList<ParentDropDownDTO> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drop_down_parent_item, parent, false);
        return new LeadListViewHolder(view);
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != mList) {
            LeadListViewHolder headingViewHolder = (LeadListViewHolder) holder;
            headingViewHolder.attachment_url.setText(mList.get(position).getTitle());
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class LeadListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView attachment_url;

        public LeadListViewHolder(View v) {
            super(v);
            attachment_url = (TextView) v.findViewById(R.id.attachment_url);

            attachment_url.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.attachment_url:
                    DropDownDataDAO dao = new DropDownDataDAO(mContext);
                    ArrayList<DropDownDataDTO> list = dao.getFormData(mList.get(getAdapterPosition()).getDatabaseKey());
                    AddEditDropDownDialog dialog = new AddEditDropDownDialog(mContext, list, mList.get(getAdapterPosition()).getDatabaseKey());
                    dialog.show();
                    break;
            }
        }
    }
}
