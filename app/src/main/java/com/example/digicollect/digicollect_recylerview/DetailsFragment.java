package com.example.digicollect.digicollect_recylerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    TextView formName,projectName,approvalTime,statusID;


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_details, container, false);

        statusID=(TextView)view.findViewById(R.id.statusID_Details);
        formName=(TextView)view.findViewById(R.id.formNAme_Details);
        projectName=(TextView)view.findViewById(R.id.projectName_Details);
        approvalTime=(TextView)view.findViewById(R.id.approvalTime_Details);
        statusID.setText("status_id:"+getArguments().getString("status_id"));
        formName.setText("formName:"+getArguments().getString("formName"));
        projectName.setText("projectName:"+getArguments().getString("projectName"));
        approvalTime.setText("approvalTime:"+getArguments().getString("approvalTime"));


        return view;


    }



}
