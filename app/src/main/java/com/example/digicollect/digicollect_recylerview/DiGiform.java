package com.example.digicollect.digicollect_recylerview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiGiform extends Fragment implements Adapter.Notifier.NotifierSelectionListener {
    String jsonData;

    private List<JsonInformation> jsonInformations = new ArrayList<>();
    private RecyclerView recyclerView;
    private Adapter myAdapter;


    public DiGiform() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_di_giform, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        //myAdapter = new Adapter(jsonInformations);
        jsonInformations = new ArrayList<>();
        myAdapter = new Adapter(jsonInformations, getContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
        try {
            jsonInformationData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Adapter.Notifier.getsInstance(getActivity()).registerListener(this);
    }

    @Override
    public void onStop() {
        Adapter.Notifier.getsInstance(getActivity()).unregisterListener(this);
        super.onStop();
    }

    private void jsonInformationData() throws JSONException {


        jsonData = "{\\\"submissions\\\":[{\\\"status_id\\\": 1, \\\"projectName\\\": \\\"Forms team\\\", \\\"formId\\\": \\\"5919e704359dcf02ae9456b4\\\", \\\"approval_time\\\": \\\"23 Jun,2017 13:29:18\\\", \\\"_id\\\": \\\"197399a8017b44aa92d4883ff37f998e\\\", \\\"formName\\\": \\\"Attendance form - 1\\\"}, {\\\"status_id\\\": 1, \\\"projectName\\\": \\\"Forms team\\\", \\\"formId\\\": \\\"5919e704359dcf02ae9456b4\\\", \\\"approval_time\\\": \\\"22 Jun,2017 14:45:19\\\", \\\"_id\\\": \\\"90002857bf1a4e179030da4ea02d80b3\\\", \\\"formName\\\": \\\"Attendance form - 1\\\"}, {\\\"status_id\\\": 1, \\\"projectName\\\": \\\"Forms team\\\", \\\"formId\\\": \\\"5919e704359dcf02ae9456b4\\\", \\\"approval_time\\\": \\\"26 Jun,2017 13:48:16\\\", \\\"_id\\\": \\\"5950c330a2b6b54329609c18\\\", \\\"formName\\\": \\\"Attendance form - 1\\\"}]}";
        // jsonData="{\\\"submissions\\\":[{\\\"status_id\\\": 1, \\\"projectName\\\":Forms team 1, \\\"Forms team\\\", \\\"formId\\\": \\\"5919e704359dcf02ae9456b4\\\", \\\"approval_time\\\": \\\"23 Jun,2017 13:29:18\\\", \\\"_id\\\": \\\"197399a8017b44aa92d4883ff37f998e\\\", \\\"formName\\\": \\\"Attendance form - 1\\\"}, {\\\"status_id\\\": 1, \\\"projectName\\\": \\\"Forms team\\\", \\\"formId\\\": \\\"5919e704359dcf02ae9456b4\\\", \\\"approval_time\\\": \\\"22 Jun,2017 14:45:19\\\", \\\"_id\\\": \\\"90002857bf1a4e179030da4ea02d80b3\\\", \\\"formName\\\": \\\"Attendance form - 1\\\"}, {\\\"status_id\\\": 1, \\\"projectName\\\": \\\"Forms team\\\", \\\"formId\\\": \\\"5919e704359dcf02ae9456b4\\\", \\\"approval_time\\\": \\\"26 Jun,2017 13:48:16\\\", \\\"_id\\\": \\\"5950c330a2b6b54329609c18\\\", \\\"formName\\\": \\\"Attendance form - 1\\\"}]}";
        jsonData = jsonData.replace("\\", "");


        {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = null;
            try {
                jsonArray = jsonObject.getJSONArray("submissions");
                Log.d("json", jsonArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = null;

                try {
                    jsonObject1 = jsonArray.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonInformation jsonInformation = new JsonInformation();

                try {
                    jsonInformation.setStatus_ID(jsonObject1.getString("status_id"));
                    jsonInformation.setFormName(jsonObject1.getString("formName"));
                    jsonInformation.setProjectName(jsonObject1.getString("projectName"));
                    jsonInformation.setApprovalTime(jsonObject1.getString("approval_time"));
                    jsonInformations.add(jsonInformation);
                    myAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public void displayHistoryFragment(String s1,String s2,String s3,String s4) {

        Bundle bundle = new Bundle();
        bundle.putString("status_id", s1);
        bundle.putString("formName", s2);
        bundle.putString("projectName", s3);
        bundle.putString("approval_time", s4);




        FragmentManager manager=getFragmentManager();
        DetailsFragment detailsFragment=new DetailsFragment();
        detailsFragment.setArguments(bundle);
        FragmentTransaction transaction=manager.beginTransaction();

        transaction.replace(R.id.container, detailsFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.addToBackStack(null);
        transaction.commit();







        // Toast.makeText(getActivity(), ""+s1+""+s2+""+s3+""+s4, Toast.LENGTH_SHORT).show();
    }
}
