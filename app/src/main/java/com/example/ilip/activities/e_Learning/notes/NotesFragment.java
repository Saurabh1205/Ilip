package com.example.ilip.activities.e_Learning.notes;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;

import com.example.ilip.R;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {
 ConstantAPIsClass constantAPIsClass;
 CommonModel commonModel;
 RequestQueue requestQueue;
 RecyclerView  recyclerView;
    private DownloadManager downloadManager;
    Notes_List_Adapter notes_list_adapter;
    public static NotesDetailModel notesDetailModel;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetailArrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        constantAPIsClass = new ConstantAPIsClass();
        requestQueue = Volley.newRequestQueue(getContext());
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FrameDayWise", "Data" + commonModel);
//        if(commonModel.getLoginType().equals("Student")){
//            userTypeSymbol="S";
//        }else{
//            userTypeSymbol="P";
//        }
        funNotesDetails();
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        recyclerView = view.findViewById(R.id.notes_List);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        getActivity().registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    11);
        }
        return view;
    }

    public void funNotesDetails(){
        String url = getString(R.string.URL_WEB1)+ ConstantAPIsClass.getStudentNotes;
        Map<String,String> postParam =  new HashMap<>();
        postParam.put("PI_STUDENT_ID",commonModel.getSTUDENT_ID());
        postParam.put("PI_BRANCH_STANDARD_GRP_ID",commonModel.getBRANCH_STANDARD_GRP_ID());
        postParam.put("PI_SEMESTER_TYPE","O");
        postParam.put("PI_DEPT_NUMBER","0");
        postParam.put("PI_CENTRE_CODE",commonModel.getCENTRE_CODE());
        Log.e("Url Notes","is=="+url + new JSONObject(postParam));
        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.POST, url, new JSONObject(postParam), response -> {
            Log.e("response is", "==" + response);
            try {

                Gson gson = new Gson();
                notesDetailModel = gson.fromJson(String.valueOf(response), NotesDetailModel.class);

                if (notesDetailModel.getStatus() == 1) {
                    //  utilityClassObj.stopLoader();
                    notesDetailArrayList = (ArrayList<NotesDetailModel.StudNotesDtlsBean>) notesDetailModel.getData();
                    if (notesDetailArrayList != null) {


                        notes_list_adapter = new Notes_List_Adapter(getActivity(), notesDetailArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.setAdapter(notes_list_adapter);


                        notes_list_adapter.setOnItemClickListener((itemView, position, download) -> {

                            if (download.equalsIgnoreCase("download")) {

                                String test = notesDetailArrayList.get(position).getNOTES_FILE_PATH();

                                String[] sou = test.split(Pattern.quote("\\"));

                                String origin = sou[1];

                                String str = origin;

                                String path = notesDetailArrayList.get(position).getURL();

                                funDownloadFile(path, str);

                            } else if (download.equalsIgnoreCase("like")) {
                                NotesDetailModel.StudNotesDtlsBean note = notesDetailArrayList.get(position);
                                String LIKE_STATUS = note.getLIKE_STATUS();
                                String LIKE_COUNT = note.getLIKE_COUNT();
                                String DISLIKE_STATUS = note.getDISLIKE_STATUS();
                                String DISLIKE_COUNT = note.getDISLIKE_COUNT();
                                String type = "LIKE";
                                String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                if (LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("F")) {
                                    note.setLIKE_STATUS("T");
                                    note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT) + 1));
                                    notes_list_adapter.notifyDataSetChanged();
                                    funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                } else if (LIKE_STATUS.equalsIgnoreCase("T") && DISLIKE_STATUS.equalsIgnoreCase("F")) {
                                    note.setLIKE_STATUS("F");
                                    note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT) - 1));
                                    notes_list_adapter.notifyDataSetChanged();
//                                            funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                } else if (LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("T")) {
                                    note.setLIKE_STATUS("T");
                                    note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT) + 1));
                                    note.setDISLIKE_STATUS("F");
                                    note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT) - 1));
                                    notes_list_adapter.notifyDataSetChanged();
                                }


                            } else if (download.equalsIgnoreCase("unlike")) {
                                NotesDetailModel.StudNotesDtlsBean note = notesDetailArrayList.get(position);

                                String LIKE_STATUS = note.getLIKE_STATUS();
                                String LIKE_COUNT = note.getLIKE_COUNT();
                                String DISLIKE_STATUS = note.getDISLIKE_STATUS();
                                String DISLIKE_COUNT = note.getDISLIKE_COUNT();
                                String type = "DISLIKE";
                                String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                if (DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("F")) {
                                    note.setDISLIKE_STATUS("T");
                                    note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT) + 1));
                                    notes_list_adapter.notifyDataSetChanged();
                                    funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                } else if (DISLIKE_STATUS.equalsIgnoreCase("T") && LIKE_STATUS.equalsIgnoreCase("F")) {
                                    note.setDISLIKE_STATUS("F");
                                    note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT) - 1));
                                    notes_list_adapter.notifyDataSetChanged();
//                                            funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                } else if (DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("T")) {
                                    note.setDISLIKE_STATUS("T");
                                    note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT) + 1));
                                    note.setLIKE_STATUS("F");
                                    note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT) - 1));
                                    notes_list_adapter.notifyDataSetChanged();
                                }
                            }
                        });

                    } else {
//                            Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
//                                    .setAction("Action", null).show();
//                            utilityClassObj.stopLoader();
                    }


                } else if (notesDetailModel.getStatus() == 0) {

//                        Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
//                                .setAction("Action", null).show();
//                        utilityClassObj.stopLoader();
                }


            } catch (Exception e) {

//                    Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();

                Log.d(TAG, "Error is ====: " + e.getMessage());
//                    utilityClassObj.stopLoader();
            }
        }, error -> {

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private void funLikeUnlike(final String ACAD_NOTES_MASTER_ID, final String type) {
        String url = getString(R.string.URL_WEB1)+ ConstantAPIsClass.getLikeDisLike;
        Map<String, String> params = new HashMap<>();
        params.put("P_ACAD_NOTES_MASTER_ID", ACAD_NOTES_MASTER_ID);
        params.put("P_PERSON_TYPE", "STUDENT");
        params.put("P_PERSON_ID", commonModel.getSTUDENT_ID());
        params.put("P_CENTRE_CODE", commonModel.getCENTRE_CODE());
        params.put("P_IS_ACTIVE", "Y");
        params.put("P_MARK_TYPE", type);
        Log.e(TAG, "Url & Posting params: " +url+ new JSONObject(params));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), response -> {

        }, error -> {

        }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        requestQueue.add(jsonObjectRequest);


    }
    private void funDownloadFile(String URL,String fileName) {
        Log.e("File Path","is="+URL+"File Extension"+fileName);
        DownloadManager downloadmanager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(URL);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/StudentFolder/Notes/"+fileName);
        request.setTitle(fileName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadmanager.enqueue(request);


//        Snackbar.make(lay_view, "File downloaded successfully", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show();

/*
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(originalfilename));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading " + originalfilename);
        request.setDescription("Downloading... " +originalfilename);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);

        refid = downloadManager.enqueue(request);

        Log.e("OUT", "" + refid);*/
    }
    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            Log.e("INSIDE", "" + referenceId);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getActivity())
                            .setSmallIcon(R.drawable.companylogo2x)
                            .setContentTitle("SASHA APP DOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };
}