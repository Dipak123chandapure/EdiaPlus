package com.example.deepak.myapplication.SMSDashbard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.Network.NetworkExecuter;
import com.example.deepak.myapplication.Network.NetworkUtil;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.StudentDashboard.StudentDashboard;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.Utility;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SMSDashboardFragment extends Fragment implements View.OnClickListener, SMSDashboardStudentListAdapter.OnSMSToListCallback {
    RecyclerView s_d_f_l_to_recycler_view, s_d_f_l_add_student_list_recycler_view;
    LinearLayout s_d_f_l_temp_text_ll;
    ImageView s_d_f_l_add_student_image, send_icon;
    EditText msg_text;
    TextView template;

    String TYPE = "";
    ArrayList<StudentDTO> mList = new ArrayList<>();
    ArrayList<StudentDTO> list;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sms_dashboard_fragment_layout, container, false);
        TYPE = getArguments().getString(Constant.SMS_TYPE);
        inItView(view);
        setUpRecyclerview();
        return view;
    }


    private void inItView(View view) {
        s_d_f_l_to_recycler_view = (RecyclerView) view.findViewById(R.id.s_d_f_l_to_recycler_view);
        s_d_f_l_add_student_list_recycler_view = (RecyclerView) view.findViewById(R.id.s_d_f_l_add_student_list_recycler_view);
        s_d_f_l_temp_text_ll = (LinearLayout) view.findViewById(R.id.s_d_f_l_temp_text_ll);
        s_d_f_l_add_student_image = (ImageView) view.findViewById(R.id.s_d_f_l_add_student_image);

        send_icon = (ImageView) view.findViewById(R.id.send_icon);
        send_icon.setOnClickListener(this);
        msg_text = (EditText) view.findViewById(R.id.msg_text);
        template = (TextView) view.findViewById(R.id.template);

        switch (TYPE) {
            case Constant.SMS_SINGE_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                mList = getArguments().getParcelableArrayList(Constant.SMS_CLIENT_LIST);
                break;

            case Constant.SMS_MULTIPLE_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                mList = getArguments().getParcelableArrayList(Constant.SMS_CLIENT_LIST);
                break;

            case Constant.SMS_GROUP_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                BatchDTO dto = getArguments().getParcelable(Constant.SMS_BATCH_DTO);
                mList = new ArrayList<>();
                StudentDTO batch = new StudentDTO();
                batch.setForm1Entity1(dto.getName());
                mList.add(batch);
                break;

            case Constant.SMS_NO_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.VISIBLE);
                s_d_f_l_add_student_image.setOnClickListener(this);
                break;

            default:
                s_d_f_l_add_student_image.setVisibility(View.VISIBLE);
                s_d_f_l_add_student_image.setOnClickListener(this);
                break;
        }
    }

    SMSDashboardToListAdapter adapter;
    SMSDashboardStudentListAdapter adapter1;

    private void setUpRecyclerview() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        s_d_f_l_to_recycler_view.setLayoutManager(manager);
        adapter = new SMSDashboardToListAdapter(getActivity(), mList);
        s_d_f_l_to_recycler_view.setAdapter(adapter);

        s_d_f_l_add_student_list_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        StudentDAO dao = new StudentDAO(getActivity());
        list = dao.getStudentList(null, 0);
        adapter1 = new SMSDashboardStudentListAdapter(getActivity(), list);
        adapter1.setOnSMSClientEntryChanged(this);
        s_d_f_l_add_student_list_recycler_view.setAdapter(adapter1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.s_d_f_l_add_student_image:
                if (s_d_f_l_add_student_list_recycler_view.getVisibility() != View.VISIBLE) {
                    s_d_f_l_temp_text_ll.setVisibility(View.GONE);
                    s_d_f_l_add_student_list_recycler_view.setVisibility(View.VISIBLE);
                } else {
                    s_d_f_l_add_student_list_recycler_view.setVisibility(View.GONE);
                    s_d_f_l_temp_text_ll.setVisibility(View.VISIBLE);
                }
                break;

            case R.id.send_icon:
                if (mList.size() > 0) {
                    if (msg_text.getText().toString().length() > 0) {
                        final SweetAlertDialog sProgressDialog = Utility.showProgressDialog(getActivity());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sendSMS(sProgressDialog);
                            }
                        }).start();

                    } else
                        Toast.makeText(getActivity(), "Please enter the test", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getActivity(), "Please add student", Toast.LENGTH_LONG).show();
                break;

        }
    }

    private void sendSMS(final SweetAlertDialog sProgressDialog) {
        String clientList = buildStudentList();
        Log.d("rohit", "list " + clientList);
        NetworkExecuter.sendSms(clientList, msg_text.getText().toString(), new NetworkExecuter.OnNetworkResponse() {
            public void onSuccess(String response) {
                setUpActivities();
                Utility.hideProgressDialog(sProgressDialog);
                Utility.showSuccessDialogWithFinish(getActivity(), "Successfully Send", "Successfully send sms", new SweetAlertDialog.OnSweetClickListener() {
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        getActivity().getSupportFragmentManager().beginTransaction().
                                setCustomAnimations(R.anim.exit_anim, R.anim.enter_anim)
                                .replace(R.id.main_frame_layout, new StudentDashboard()).commit();

                    }
                });
            }

            public void onFailure(VolleyError response) {
                Utility.hideProgressDialog(sProgressDialog);
                Utility.showErrorDialog(getActivity(), "Network Error", "Please try after some time");
            }

            public void onNoConnction() {
                Utility.hideProgressDialog(sProgressDialog);
                Utility.showNoInternetDialogWithFinish();
            }
        });
    }

    private void setUpActivities() {
        if (TYPE.equals(Constant.SMS_BATCH_DTO)) {

        } else {
            for (int i = 0; i < mList.size(); i++) {
                Calendar now = Calendar.getInstance();

                StudentDTO student = mList.get(i);
                ActivityDTO activity = new ActivityDTO();
                activity.setActvityTypeID(1);
                activity.setStudentID(student.getId());
                activity.setCreatedDate(now.getTimeInMillis());
                activity.setModificationDate(now.getTimeInMillis());
                activity.setNextActionDate(now.getTimeInMillis());
                activity.setActivityBody(msg_text.getText().toString());
                activity.setActivityComment(msg_text.getText().toString());
                activity.setActivityDataJSON(new Gson().toJson(activity));
                new ActivitiesDAO(getActivity()).addActivity(activity);

                student.setUpdatedONMilli(now.getTimeInMillis());
                student.setUpdatedON(now.getTimeInMillis()+"");
                student.setCkecked(false);
                student.setStudentDataJSON(new Gson().toJson(student));

                new StudentDAO(getActivity()).updateStudent(student);
            }
        }
    }

    private String buildStudentList() {
        if (TYPE.equals(Constant.SMS_GROUP_CLIENT)) {
            BatchDTO batch = getArguments().getParcelable(Constant.SMS_BATCH_DTO);
            mList = new BatchDAO(getActivity()).getStudentsForBatch(batch.getId());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mList.size(); i++) {
            if (i != 0)
                builder.append("," + mList.get(i).getForm1Entity4());
            else builder.append(mList.get(i).getForm1Entity4());

        }
        return builder.toString();
    }

    public void onSMSCliendAdded(StudentDTO dto) {
        Log.d("rohit", "onAddedClient clicked");
        mList.add(dto);
        adapter.notifyDataSetChanged();

    }

    public void onSMSCliendRemoved(StudentDTO dto) {
        Log.d("rohit", "onRemovedClient clicked");
        if (mList.contains(dto)) {
            mList.remove(dto);
            adapter.notifyDataSetChanged();
        }
    }

    public void loadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> dtolist = handler.getStudentList(null, index);
        list.addAll(dtolist);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter1.notifyDataSetChanged();
            }
        });
    }
}
