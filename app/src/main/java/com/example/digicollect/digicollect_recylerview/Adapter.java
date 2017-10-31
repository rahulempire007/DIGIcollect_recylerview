package com.example.digicollect.digicollect_recylerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by digicollect on 28/6/17.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<JsonInformation> jsonInformations;
    Context ctx;

    static String S1,S2,S3,S4;

  //  private FragmentCommunication mCommunicator;

    //Activity mActivity;
    //public Adapter( Context ctx,FragmentCommunication communication,List<JsonInformation>jsonInformation) {
    //  inflater = LayoutInflater.from(ctx);
//jsonInformations=jsonInformation;
    //      mCommunicator=communication;
    //}


    //private List<JsonInformation> jsonInformations;
    //Context ctx;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView formName, projectName, approavalTime, statusID;
        // List<JsonInformation>jsonInformations=new List<JsonInformation>();
        ArrayList<JsonInformation> jsonInformations = new ArrayList<>();


        Context ctx;

        public MyViewHolder(View view, Context ctx, ArrayList<JsonInformation> jsonInformations) {


            super(view);
            this.jsonInformations = jsonInformations;
            this.ctx = ctx;
            view.setOnClickListener(this);
            statusID=(TextView)view.findViewById(R.id.status_ID);
            formName = (TextView) view.findViewById(R.id.formNAme);
            projectName = (TextView) view.findViewById(R.id.projectName);
            approavalTime = (TextView) view.findViewById(R.id.approvalTime);
        }

        @Override
        public void onClick(View v) {
            int position=getAdapterPosition();
            JsonInformation jsonInformation=this.jsonInformations.get(position);
            S1 = jsonInformation.getStatus_ID();
            S2 = jsonInformation.getFormName();
            S3 = jsonInformation.getProjectName();
            S4 = jsonInformation.getApprovalTime();
            Notifier.getsInstance(ctx).notifyAllListener();

//            int position=getAdapterPosition();
//            JsonInformation jsonInformation=this.jsonInformations.get(position);
//            Intent intent=new Intent(this.ctx,Details.class);
//            intent.putExtra("formname",jsonInformation.getFormName());
//            intent.putExtra("projectName",jsonInformation.getNotifierName());
//            intent.putExtra("approvalTime",jsonInformation.getApprovalTime());
//                this.ctx.startActivity(intent);





/*            DetailsFragment detailsFragment = new DetailsFragment ();
            Bundle args = new Bundle();
            args.putString("formname",jsonInformation.getFormName());
            args.putString("projectName",jsonInformation.getFormName());
            args.putString("approvalTime",jsonInformation.getFormName());
            detailsFragment.setArguments(args);

//Inflate the fragment
            this.ctx.getApplicationContext()beginTransaction().add(R.id.container, detailsFragment).commit();
*/



/*               Intent intent=new Intent(this.ctx,DetailsFragment.class);
            intent.putExtra("formname",jsonInformation.getFormName());
            intent.putExtra("projectName",jsonInformation.getNotifierName());
            intent.putExtra("approvalTime",jsonInformation.getApprovalTime());
            this.ctx.startActivity(intent);

*/

        }
    }

    /* public class MyViewHolder extends RecyclerView.ViewHolder  {
        public TextView formName, projectName, approavalTime;

        public MyViewHolder(View view) {
            super(view);
            formName = (TextView) view.findViewById(R.id.formNAme);
            projectName = (TextView) view.findViewById(R.id.projectName);
            approavalTime = (TextView) view.findViewById(R.id.approvalTime);
        }
    }




    */
    public static class Notifier {
        private static Notifier sInstance;
        private List<Notifier.NotifierSelectionListener> mListeners;


        private Context mContext;
        private static final String LOGTAG = Adapter.class.getSimpleName();


        public interface NotifierSelectionListener {
            //to be changed
            void displayHistoryFragment(String s1, String s2,String s3,String s4);
        }


        private Notifier(Context ctx) {
            mContext = ctx.getApplicationContext();
            mListeners = new ArrayList<NotifierSelectionListener>();
        }

        public void registerListener(Notifier.NotifierSelectionListener listener) {
            if (!mListeners.contains(listener)) {
                mListeners.add(listener);
            }
        }


        public void unregisterListener(Notifier.NotifierSelectionListener listener) {
            if (mListeners.contains(listener)) {
                mListeners.remove(listener);
            }
        }


        public void notifyAllListener() {

            for (Notifier.NotifierSelectionListener listener : mListeners) {
                listener.displayHistoryFragment(S1,S2,S3,S4);
            }

        }

        public synchronized static Notifier getsInstance(Context ctx) {
            if (sInstance == null) {
                sInstance = new Notifier(ctx);
            }
            return sInstance;
        }


    }

    public Adapter(List<JsonInformation> jsonInformations, Context ctx) {
     //   this.mCommunicator = mCommunicator;

        this.jsonInformations = jsonInformations;
        this.ctx = ctx;
    }

    /*@Override
public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_row, parent, false);
    MyViewHolder holder = new MyViewHolder(view,mCommunicator);
    return holder;
}*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemvalue, parent, false);


        return new MyViewHolder(itemView, ctx, (ArrayList<JsonInformation>) jsonInformations);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JsonInformation jsonInformation = jsonInformations.get(position);
        holder.statusID.setText(jsonInformation.getStatus_ID());
        holder.formName.setText(jsonInformation.getFormName());
        holder.projectName.setText(jsonInformation.getProjectName());
        holder.approavalTime.setText(jsonInformation.getApprovalTime());
    }

    @Override
    public int getItemCount() {
        return jsonInformations.size();
    }
}