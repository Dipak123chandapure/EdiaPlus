package com.example.deepak.myapplication.EmailDashboard;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.deepak.myapplication.Database.DAO.ActivitiesDAO;
import com.example.deepak.myapplication.Database.DAO.AttachmentDAO;
import com.example.deepak.myapplication.Database.DAO.BatchDAO;
import com.example.deepak.myapplication.Database.DAO.StudentDAO;
import com.example.deepak.myapplication.Database.DTO.ActivityDTO;
import com.example.deepak.myapplication.Database.DTO.AttachmentDTO;
import com.example.deepak.myapplication.Database.DTO.BatchDTO;
import com.example.deepak.myapplication.Database.DTO.StudentDTO;
import com.example.deepak.myapplication.MainActivity;
import com.example.deepak.myapplication.Network.NetworkExecuter;
import com.example.deepak.myapplication.R;
import com.example.deepak.myapplication.StudentDashboard.StudentDashboard;
import com.example.deepak.myapplication.Utility.Constant;
import com.example.deepak.myapplication.Utility.Utility;
import com.google.gson.Gson;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class EmailDashboardFragment extends Fragment implements View.OnClickListener, EmailDashboardStudentListAdapter.OnSMSToListCallback, EmailAttchmentAdapter.OnAttchmentRemoved, MainActivity.OnMActivityResult {
    RecyclerView s_d_f_l_to_recycler_view, s_d_f_l_add_student_list_recycler_view, email_attachment_recycler_view;
    LinearLayout s_d_f_l_temp_text_ll;
    ImageView s_d_f_l_add_student_image, email_attachment_icon, send_icons;
    EditText subject, body;
    String TYPE = "";

    EmailDashboardToListAdapter toListAdapter;
    EmailDashboardStudentListAdapter studentListAdapter;
    EmailAttchmentAdapter emailAttchmentAdapter;
    ArrayList<AttachmentDTO> atttacmentList = new ArrayList<>();
    ArrayList<StudentDTO> toStudentList = new ArrayList<>();
    ArrayList<StudentDTO> list;
    BatchDTO batch;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.email_dashboard_fragment_layout, container, false);

        if (null != getArguments())
        TYPE = getArguments().getString(Constant.SMS_TYPE);

        MainActivity activity = (MainActivity) getActivity();
        activity.setOnMActivityResult(this);
        inItView(view);
        setUpRecyclerview();
        return view;
    }


    private void inItView(View view) {
        s_d_f_l_to_recycler_view = (RecyclerView) view.findViewById(R.id.s_d_f_l_to_recycler_view);
        s_d_f_l_add_student_list_recycler_view = (RecyclerView) view.findViewById(R.id.s_d_f_l_add_student_list_recycler_view);
        email_attachment_recycler_view = (RecyclerView) view.findViewById(R.id.email_attachment_recycler_view);

        s_d_f_l_temp_text_ll = (LinearLayout) view.findViewById(R.id.s_d_f_l_temp_text_ll);
        s_d_f_l_add_student_image = (ImageView) view.findViewById(R.id.s_d_f_l_add_student_image);
        email_attachment_icon = (ImageView) view.findViewById(R.id.email_attachment_icon);
        email_attachment_icon.setOnClickListener(this);

        send_icons = (ImageView) view.findViewById(R.id.send_icons);
        send_icons.setOnClickListener(this);

        body = (EditText) view.findViewById(R.id.body);
        subject = (EditText) view.findViewById(R.id.subject);

        switch (TYPE) {
            case Constant.SMS_SINGE_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                toStudentList = getArguments().getParcelableArrayList(Constant.SMS_CLIENT_LIST);
                break;

            case Constant.SMS_MULTIPLE_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                toStudentList = getArguments().getParcelableArrayList(Constant.SMS_CLIENT_LIST);
                break;

            case Constant.SMS_GROUP_CLIENT:
                s_d_f_l_add_student_image.setVisibility(View.GONE);
                batch = getArguments().getParcelable(Constant.SMS_BATCH_DTO);
                toStudentList = new ArrayList<>();
                StudentDTO student = new StudentDTO();
                student.setForm1Entity1(batch.getName());
                toStudentList.add(student);
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


    private void setUpRecyclerview() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        s_d_f_l_to_recycler_view.setLayoutManager(manager);
        toListAdapter = new EmailDashboardToListAdapter(getActivity(), toStudentList);
        s_d_f_l_to_recycler_view.setAdapter(toListAdapter);

        s_d_f_l_add_student_list_recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        StudentDAO dao = new StudentDAO(getActivity());
        list = dao.getStudentList(null, 0);
        studentListAdapter = new EmailDashboardStudentListAdapter(getActivity(), list);
        studentListAdapter.setOnSMSClientEntryChanged(this);
        s_d_f_l_add_student_list_recycler_view.setAdapter(studentListAdapter);


        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        email_attachment_recycler_view.setLayoutManager(manager1);
        emailAttchmentAdapter = new EmailAttchmentAdapter(getActivity(), atttacmentList);
        emailAttchmentAdapter.setOnAttchmentRemoved(this);
        email_attachment_recycler_view.setAdapter(emailAttchmentAdapter);
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
            case R.id.email_attachment_icon:
                email_attachment_recycler_view.setVisibility(View.VISIBLE);
                showFileChooser();
                break;

            case R.id.send_icons:
                if (toStudentList.size() > 0) {
                    if (subject.getText().toString().length() != 0 && body.getText().toString().length() != 0) {
                        final SweetAlertDialog sProgressDialog = Utility.showProgressDialog(getActivity());
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                sendEMAIL(sProgressDialog);
                            }
                        }).start();

                    } else
                        Toast.makeText(getActivity(), "Please dont keep subject or body empty", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(getActivity(), "Please Select Student", Toast.LENGTH_SHORT).show();

        }
    }

    private void sendEMAIL(final SweetAlertDialog sProgressDialog) {
        String clientList = buildStudentList();
        Log.d("rohit", "list " + clientList);
        NetworkExecuter.sendSms(clientList, body.getText().toString(), new NetworkExecuter.OnNetworkResponse() {
            public void onSuccess(String response) {
                setUpActivities();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
                });

            }

            public void onFailure(VolleyError response) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Utility.hideProgressDialog(sProgressDialog);
                        Utility.showErrorDialog(getActivity(), "Network Error", "Please try after some time");
                    }
                });

            }

            @UiThread
            public void onNoConnction() {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Utility.hideProgressDialog(sProgressDialog);
                        Utility.showNoInternetDialogWithFinish(getActivity());
                    }
                });
            }
        });
    }

    private void setUpActivities() {
        if (TYPE.equals(Constant.SMS_GROUP_CLIENT)) {
            Log.d("rohit", "adding activities to batch");
            Calendar now = Calendar.getInstance();
            ActivitiesDAO dao = new ActivitiesDAO(getActivity());
            ActivityDTO activity = new ActivityDTO();
            activity.setActvityTypeID(1);
            activity.setStudentID(batch.getId());
            activity.setCreatedDate(now.getTimeInMillis());
            activity.setModificationDate(now.getTimeInMillis());
            activity.setNextActionDate(now.getTimeInMillis());
            activity.setActivityBody(body.getText().toString());
            activity.setActivityComment(body.getText().toString());
            activity.setActivityDataJSON(new Gson().toJson(activity));

            Long result = dao.addBatchActivity(activity);
            activity.setId(result);

            new AttachmentDAO(getActivity()).saveAttachment(batch, atttacmentList);
        } else {
            for (int i = 0; i < toStudentList.size(); i++) {
                Calendar now = Calendar.getInstance();

                StudentDTO student = toStudentList.get(i);
                ActivityDTO activity = new ActivityDTO();
                activity.setActvityTypeID(1);
                activity.setStudentID(student.getId());
                activity.setCreatedDate(now.getTimeInMillis());
                activity.setModificationDate(now.getTimeInMillis());
                activity.setNextActionDate(now.getTimeInMillis());
                activity.setActivityBody(body.getText().toString());
                activity.setActivityComment(body.getText().toString());
                activity.setActivityDataJSON(new Gson().toJson(activity));
                Long activityResult = new ActivitiesDAO(getActivity()).addActivity(activity);
                activity.setId(activityResult);

                student.setUpdatedONMilli(now.getTimeInMillis());
                student.setUpdatedON(now.getTimeInMillis() + "");
                student.setCkecked(false);
                student.setStudentDataJSON(new Gson().toJson(student));

                new StudentDAO(getActivity()).updateStudent(student);

                new AttachmentDAO(getActivity()).saveAttachment(activity, atttacmentList);
            }
        }
    }


    private String buildStudentList() {
        if (TYPE.equals(Constant.SMS_GROUP_CLIENT)) {
            BatchDTO batch = getArguments().getParcelable(Constant.SMS_BATCH_DTO);
            toStudentList = new BatchDAO(getActivity()).getStudentsForBatch(batch.getId());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < toStudentList.size(); i++) {
            if (i != 0)
                builder.append("," + toStudentList.get(i).getForm1Entity4());
            else builder.append(toStudentList.get(i).getForm1Entity4());

        }
        return builder.toString();
    }


    private void showFileChooser() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            new MaterialFilePicker()
                    .withActivity(getActivity())
                    .withRequestCode(10)
                    .start();
        } else {
            ActivityCompat.requestPermissions(
                    getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);
        }

    }


    public void onMActivityResult(Intent data) {
        String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
        AttachmentDTO attachment = new AttachmentDTO();
        attachment.setSection("test section");
        attachment.setUrl(filePath);
        atttacmentList.add(attachment);
        emailAttchmentAdapter.notifyDataSetChanged();
        Log.d("rohit", "file path" + filePath.substring(filePath.lastIndexOf("/") + 1));
    }


    @Override
    public void onSMSCliendAdded(StudentDTO dto) {
        Log.d("rohit", "onAddedClient clicked");
        toStudentList.add(dto);
        toListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSMSCliendRemoved(StudentDTO dto) {
        Log.d("rohit", "onRemovedClient clicked");
        if (toStudentList.contains(dto)) {
            toStudentList.remove(dto);
            toListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMore(int index) {
        Log.d("rohit", "Load more is called");
        StudentDAO handler = new StudentDAO(getActivity());
        ArrayList<StudentDTO> dtolist = handler.getStudentList(null, index);
        list.addAll(dtolist);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                studentListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onAttchmentRemoved(int poition) {
        atttacmentList.remove(poition);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                emailAttchmentAdapter.notifyDataSetChanged();
            }
        });
    }
}
