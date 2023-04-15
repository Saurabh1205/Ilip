package com.example.ilip.activities.e_Learning.assignment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;

public class Assignment_List_Adapter  extends RecyclerView.Adapter<Assignment_List_Adapter.ViewHolder> {

    Context Mcontext;
    private final ArrayList<AssignmentModel.StudAssignDtlsBean> assignDtlsBeans;
//    private AssignmentAdapter.AssignDownUpListner assignDownUpListner;
    private SparseBooleanArray selectedItems;
    private final String AssignmentListName;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String download);
    }

    private Assignment_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Assignment_List_Adapter.OnItemClickListener mCallbacks) {
        this.mCallbacks = mCallbacks;
    }

    public Assignment_List_Adapter(Context context, ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList, String Assign) {
        this.Mcontext = context;
        this.assignDtlsBeans = studAssignDtlsArrayList;
        this.AssignmentListName = Assign;

    }

  /*  public Assignment_List_Adapter(Context context, ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList, AssignDownUpListner assignDownUpListner) {
        this. Mcontext = context;
        this. assignDtlsBeans = studAssignDtlsArrayList;
        selectedItems = new SparseBooleanArray();
        this.assignDownUpListner = (AssignmentAdapter.AssignDownUpListner) assignDownUpListner;}
*/


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {


        holder.txt_subject.setText(assignDtlsBeans.get(position).getSUBJECT_DESCRIPTION());
        holder.tv_LastDate.setText(assignDtlsBeans.get(position).getASSIGN_SUBMI_FROM_DATE());
        holder.txt_copyTex.setText(assignDtlsBeans.get(position).getASSIGN_SUBMI_UPTO_DATE());
        holder.txt_Des.setText(assignDtlsBeans.get(position).getSUBJECT_DESCRIPTION());


        if (assignDtlsBeans.get(position).getASSIGN_SUBMISSION_TYPE().equalsIgnoreCase("SOFT COPY")) {
            holder.lay_upload.setVisibility(View.VISIBLE);
        } else {
            holder.lay_upload.setVisibility(View.GONE);
        }


        holder.lay_download.setOnClickListener(v -> {
            if (mCallbacks != null) {
                String download = "download";

                if (position != RecyclerView.NO_POSITION) {
                    mCallbacks.onItemClick(v, position, download);
                }
            }
        });
        holder.lay_upload.setOnClickListener(v -> {
            String download = "upload";
            if (position != RecyclerView.NO_POSITION) {
                mCallbacks.onItemClick(v, position, download);
            }
        });


    }

    @Override
    public int getItemCount() {
        return assignDtlsBeans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_Des, txt_copyTex, txt_sub_type, tv_LastDate, txt_sub_Last_date, txt_subject, txt_download, txt_upload;
        private final LinearLayout lay_download;
        private final LinearLayout lay_upload;
        private final LinearLayout layout_avlType;
        private final ImageView img_download;
        private final ImageView img_upload;

        public ViewHolder(View itemView) {
            super(itemView);


            txt_Des = itemView.findViewById(R.id.txt_Des);
            txt_copyTex = itemView.findViewById(R.id.txt_copyTex);
            txt_sub_type = itemView.findViewById(R.id.txt_sub_type);
            tv_LastDate = itemView.findViewById(R.id.tv_LastDate);
            txt_sub_Last_date = itemView.findViewById(R.id.txt_sub_Last_date);
            txt_subject = itemView.findViewById(R.id.txt_subject);
            layout_avlType = itemView.findViewById(R.id.layout_avlType);
            lay_download = itemView.findViewById(R.id.lay_download);
            lay_upload = itemView.findViewById(R.id.lay_upload);
            txt_download = itemView.findViewById(R.id.txt_download);
            txt_upload = itemView.findViewById(R.id.txt_upload);
            img_download = itemView.findViewById(R.id.img_download);
            img_upload = itemView.findViewById(R.id.img_upload);


//            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
//            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");
//
//
//            txt_subject.setTypeface(type_faceHeading);
//            txt_sub_Last_date.setTypeface(type_faceContent);
//            tv_LastDate.setTypeface(type_faceContent);
//            txt_sub_type.setTypeface(type_faceContent);
//            txt_copyTex.setTypeface(type_faceContent);
//            txt_Des.setTypeface(type_faceContent);

        }
    }
}

