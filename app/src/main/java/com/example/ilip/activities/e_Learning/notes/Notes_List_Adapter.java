package com.example.ilip.activities.e_Learning.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;

public class Notes_List_Adapter extends RecyclerView.Adapter<Notes_List_Adapter.ViewHolder> {

    Context Mcontext;
    private final ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetailArrayList;
   // private AssignmentAdapter.AssignDownUpListner assignDownUpListner;
    private SparseBooleanArray selectedItems;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String download);
    }
    private Notes_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Notes_List_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }
    public Notes_List_Adapter(Context context , ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetail) {
        this. Mcontext = context;
        this. notesDetailArrayList = notesDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_adapter_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.txt_subject.setText(notesDetailArrayList.get(position).getNOTES_DESCRIPTION());
        holder.tv_LastDate.setText(notesDetailArrayList.get(position).getPUBLISH_DATE());
        holder.txt_facultyNme.setText(notesDetailArrayList.get(position).getFACULTY_NAME());
        holder.txt_Des.setText(notesDetailArrayList.get(position).getSUBJECT_DESCRIPTION());

        holder.txt_Des.setText(notesDetailArrayList.get(position).getSUBJECT_DESCRIPTION());

        holder.txt_like.setText(notesDetailArrayList.get(position).getLIKE_COUNT());

        holder.txt_unlike.setText(notesDetailArrayList.get(position).getDISLIKE_COUNT());

      /*  if (notesDetailArrayList.get(position).getASSIGN_SUBMISSION_TYPE().equalsIgnoreCase("SOFT COPY")){
            holder.layout_avlType.setVisibility(View.VISIBLE);
        }else {
            holder.layout_avlType.setVisibility(View.GONE);
        }*/

        holder.lay_download.setOnClickListener(v -> {
            if (mCallbacks != null) {
                String download="download";

                if (position != RecyclerView.NO_POSITION) {
                    mCallbacks.onItemClick(v, position,download);
                }
            }
        });
        holder.txt_like_view.setOnClickListener(v -> {
            String download="like";
            if (position != RecyclerView.NO_POSITION) {
                mCallbacks.onItemClick(v, position,download);
            }
        });
        holder.txt_unlikeView.setOnClickListener(v -> {
            String download="unlike";
            if (position != RecyclerView.NO_POSITION) {
                mCallbacks.onItemClick(v, position,download);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notesDetailArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_Des,txt_copyTex,txt_sub_type,tv_LastDate,txt_sub_Last_date,txt_subject,txt_download,txt_upload,txt_faculty,txt_facultyNme;
        private final LinearLayout lay_like;
        private final LinearLayout lay_unlike;
        private final Button lay_download;
        private ImageView img_download,img_upload;
        private final TextView txt_like;
        private final TextView txt_like_view;
        private final TextView txt_unlike;
        private final TextView txt_unlikeView;
        public ViewHolder(View itemView) {
            super(itemView);

            txt_Des = itemView.findViewById(R.id.txt_Des);
            txt_faculty = itemView.findViewById(R.id.txt_faculty);
            txt_facultyNme = itemView.findViewById(R.id.txt_facultyNme);
            // txt_sub_type =(TextView) itemView.findViewById(R.id.txt_sub_type);
            tv_LastDate = itemView.findViewById(R.id.tv_LastDate);
            txt_sub_Last_date = itemView.findViewById(R.id.txt_sub_Last_date);
            txt_subject  = itemView.findViewById(R.id.txt_subject);
            lay_download = itemView.findViewById(R.id.lay_download);
            lay_like = itemView.findViewById(R.id.lay_like);
            lay_unlike= itemView.findViewById(R.id.lay_unlike);

           // txt_download =(TextView) itemView.findViewById(R.id.txt_download);
            txt_like = itemView.findViewById(R.id.txt_like);
            txt_like_view = itemView.findViewById(R.id.txt_like_view);
            txt_unlike = itemView.findViewById(R.id.txt_unlike);
            txt_unlikeView = itemView.findViewById(R.id.txt_unlikeView);
           // img_download =(ImageView) itemView.findViewById(R.id.img_download);


//            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
//            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");
//
//            txt_subject.setTypeface(type_faceHeading);
//            txt_sub_Last_date.setTypeface(type_faceContent);
//            tv_LastDate.setTypeface(type_faceContent);
//            txt_like_view.setTypeface(type_faceHeading);
//            txt_unlikeView.setTypeface(type_faceHeading);
//            txt_facultyNme.setTypeface(type_faceContent);
//            txt_Des.setTypeface(type_faceContent);

        }
    }


}
