package com.example.digicollect.digicollect_recylerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView formName,projectName,approvalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        formName=(TextView)findViewById(R.id.formNAme_Details);
        projectName=(TextView)findViewById(R.id.projectName_Details);
        approvalTime=(TextView)findViewById(R.id.approvalTime_Details);

        formName.setText("formName:"+getIntent().getStringExtra("formName"));
        projectName.setText("projectName:"+getIntent().getStringExtra("projectName"));
        approvalTime.setText("approvalTime:"+getIntent().getStringExtra("approvalTime"));




    }
}
