package com.example.ilip.activities.e_Learning.assignment;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * create an instance of this fragment.
 */
public class AssignmentFragment extends Fragment implements View.OnClickListener {
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_FILE_REQUEST = 1;
    PowerManager.WakeLock wakeLock;
    private final int REQUEST_READ_PHONE_STATE = 1;
    private String selectedFilePath;
    long fileSizeInKB;
    private Button act_submitted, act_publishASSi;
    AssignmentModel assignmentModel;
    public static ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList;
    Assignment_List_Adapter assignment_list_adapter;
    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    CommonModel commonModel;
    private RecyclerView recycle_view;
    private LinearLayout lay_view;
    String AssignmentList;
    DownloadManager downloadManager;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignment, container, false);
        if (checkPermission()) {
            requestPermissionAndContinue();
        }
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        getActivity().registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        recycle_view = view.findViewById(R.id.recycle_view);
//        act_submitted = (Button) view.findViewById(R.id.act_submitted);
//        act_publishASSi = (Button) view.findViewById(R.id.act_publishASSi);
        lay_view = view.findViewById(R.id.lay_view);

//        act_publishASSi.setOnClickListener(this);
//        act_submitted.setOnClickListener(this);
        AssignmentList = "Published Assignments";

        funGetAssgnDtl(AssignmentList);

        return view;
    }

    private void funGetAssgnDtl(String AssignmentListName) {
        String url = getString(R.string.URL_WEB) + ConstantAPIsClass.getStudentAssignments;
        Map<String, String> params = new HashMap<>();
        params.put("PI_STUDENT_ID", commonModel.getSTUDENT_ID());
        params.put("PI_BRANCH_STANDARD_ID", commonModel.getBRANCH_STANDARD_ID());
        params.put("PI_SEMESTER_TYPE", "O");
        params.put("PI_REPORT_TYPE", "ALL");
        params.put("PI_CENTRE_CODE", commonModel.getCENTRE_CODE());
        params.put("PI_SESSION_ID", commonModel.getACAD_SESSION_ID());
        Log.e(TAG, "Url & Posting params: " +url+ new JSONObject(params));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), response -> {
            Log.e("Reesponse is", "===" + response);
            try {

                Gson gson = new Gson();
                assignmentModel = gson.fromJson(String.valueOf(response), AssignmentModel.class);
                if (assignmentModel.getStatus() == 1) {
                    studAssignDtlsArrayList = (ArrayList<AssignmentModel.StudAssignDtlsBean>) assignmentModel.getData();
                    ArrayList<AssignmentModel.StudAssignDtlsBean> ff = new ArrayList<>();

                    for (int i = 0; i < studAssignDtlsArrayList.size(); i++) {
                        if (studAssignDtlsArrayList.get(i).getSTU_SUBMISSION_STATUS().equalsIgnoreCase("SUBMITTED")) {
                            ff.add(studAssignDtlsArrayList.get(i));
                        }
                    }
                    if (studAssignDtlsArrayList != null) {
                        if (AssignmentListName.equalsIgnoreCase("Submitted Assignments")) {

                            assignment_list_adapter = new Assignment_List_Adapter(getActivity(), ff, AssignmentListName);
                            recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycle_view.setAdapter(assignment_list_adapter);
                            assignment_list_adapter.notifyDataSetChanged();


                            assignment_list_adapter.setOnItemClickListener((itemView, position, download) -> {

                                if (download.equalsIgnoreCase("download")) {
                                    String strfileName = ff.get(position).getTASK_FILE_NAME();
                                    String fileName, Extent;
                                    String ORG, Path;
                                    String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                    String originalfileName = filenameNextension[0];

                                    //"\\\\" +
                                    String sourcePath = ff.get(position).getDOWNLOAD_PATH();
                                    String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                    String[] value = str.split(Pattern.quote("."));
                                    fileName = value[0];
                                    Extent = value[1];


                                    String[] colon = sourcePath.split(Pattern.quote(":"));
                                    ORG = colon[0];
                                    Path = colon[1];


                                    String[] data = fileName.split(Pattern.quote("_"));
                                    String employeeId = data[0];
                                    employeeId = employeeId.substring(1);

                                    String sourcePathsplit = "\\\\" + ff.get(position).getDOWNLOAD_PATH();
                                    String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                    String originalSource = source[0] + "Employee\\" + employeeId;

                                    //String comUrl = BASE_URL;          //http://117.247.82.252:500/api/
                                    //  String[] s = comUrl.split(Pattern.quote("api"));
                                    //String downloadURL = s[0];


                                    String test = studAssignDtlsArrayList.get(position).getASSIGN_FILE_DETAILS();
                                    String[] sou = test.split(Pattern.quote("\\"));
                                    //  String origin = sou[1];
                                    String origin = sou[0] + "/" + sou[1];
                                    String uu = "";
                                    String q, w;
                                    String str1 = test.substring(test.lastIndexOf("\\") + 1);
                                    String[] filenameNextension1 = str1.split(Pattern.quote("."));
                                    q = filenameNextension1[0];
                                    w = filenameNextension1[1];
                                    String a = q + w;
                                    String filepathname = studAssignDtlsArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH().replace("\\", "/");
                                    Log.e("filePath==", "==" + filepathname);
                                    try {
                                        Log.e("filePath==", "==" + test);
                                        uu = studAssignDtlsArrayList.get(position).getSTATIC_IP() + "" + filepathname + "/" + origin;
                                        // uu =  studAssignDtlsArrayList.get(position).getSTATIC_IP()+studAssignDtlsArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH()+"//"+test;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    String path = "http://" + uu;
                                    funDownloadFile(path, str1);
                                } else if (download.equalsIgnoreCase("upload")) {
                                    // assignmentPos = position;

                                    if (!checkPermission()) {
                                        showFileChooser();
                                    } else {
                                        if (checkPermission()) {
                                            requestPermissionAndContinue();
                                        } else {
                                            showFileChooser();
                                        }

                                    }
                                    // submitUploadDoc(position);
                                }


                            });
                        } else {
                            assignment_list_adapter = new Assignment_List_Adapter(getActivity(), studAssignDtlsArrayList, AssignmentListName);
                            recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycle_view.setAdapter(assignment_list_adapter);
                            assignment_list_adapter.notifyDataSetChanged();


                            assignment_list_adapter.setOnItemClickListener((itemView, position, download) -> {

                                if (download.equalsIgnoreCase("download")) {
                                    String strfileName = studAssignDtlsArrayList.get(position).getTASK_FILE_NAME();
                                    String fileName, Extent;
                                    String ORG, Path;
                                    String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                    String originalfileName = filenameNextension[0];

                                    //"\\\\" +
                                    String sourcePath = studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                    String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                    String[] value = str.split(Pattern.quote("."));
                                    fileName = value[0];
                                    Extent = value[1];


                                    String[] colon = sourcePath.split(Pattern.quote(":"));
                                    ORG = colon[0];
                                    Path = colon[1];


                                    String[] data = fileName.split(Pattern.quote("_"));
                                    String employeeId = data[0];
                                    employeeId = employeeId.substring(1);

                                    String sourcePathsplit = "\\\\" + studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                    String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                    String originalSource = source[0] + "Employee\\" + employeeId;


//                                                    String comUrl = BASE_URL;          //http://117.247.82.252:500/api/
//                                                    String[] s = comUrl.split(Pattern.quote("api"));
//                                                    String downloadURL = s[0];
//
//                                                    progBarAssi.setVisibility(View.VISIBLE);


                                    String test = studAssignDtlsArrayList.get(position).getASSIGN_FILE_DETAILS();
                                    String[] sou = test.split(Pattern.quote("\\"));
                                    String datas = sou[0] + "/" + sou[1];
                                    String origin = sou[1];

                                    String uu = "";
                                    String q, w;
                                    String str1 = test.substring(test.lastIndexOf("\\") + 1);
                                    String[] filenameNextension1 = str1.split(Pattern.quote("."));
                                    q = filenameNextension1[0];
                                    w = filenameNextension1[1];
                                    String a = q + w;

                                    String filepathname = studAssignDtlsArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH().replace("\\", "/");
                                    Log.e("filePath==", "==" + filepathname);
//                                                try {
                                    uu = studAssignDtlsArrayList.get(position).getSTATIC_IP() + "" + filepathname + "/" + datas;
//                                                }catch (Exception e){
//                                                    e.printStackTrace();
//                                                }
                                    String path = "http://" + uu;
                                    funDownloadFile(path, str1);


                                } else if (download.equalsIgnoreCase("upload")) {
                                    // assignmentPos = position;

                                    if (!checkPermission()) {
                                        showFileChooser();
                                    } else {
                                        if (checkPermission()) {
                                            requestPermissionAndContinue();
                                        } else {
                                            showFileChooser();
                                        }

                                    }
                                    // submitUploadDoc(position);
                                }


                            });
                        }




                    /*assignment_list_adapter = new Assignment_List_Adapter(getActivity(), studAssignDtlsArrayList,AssignmentListName);
                    recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recycle_view.setAdapter(assignment_list_adapter);
                    assignment_list_adapter.notifyDataSetChanged();


                    assignment_list_adapter.setOnItemClickListener(new Assignment_List_Adapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position ,String download) {

                            if(download.equalsIgnoreCase("download")){
                                String strfileName = studAssignDtlsArrayList.get(position).getTASK_FILE_NAME();
                                String fileName, Extent;
                                String ORG,Path;
                                String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                String originalfileName = filenameNextension[0];

                                //"\\\\" +
                                String sourcePath = studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                String[] value = str.split(Pattern.quote("."));
                                fileName = value[0];
                                Extent = value[1];


                                String[] colon = sourcePath.split(Pattern.quote(":"));
                                ORG= colon[0];
                                Path= colon[1];


                                String[] data = fileName.split(Pattern.quote("_"));
                                String employeeId = data[0];
                                employeeId = employeeId.substring(1);

                                String sourcePathsplit = "\\\\" + studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                String originalSource = source[0] + "Employee\\" + employeeId;
                                String org ="\\\192.168.1.72/"+ORG+Path;
                                String uu = org;

                                funDownloadURL(fileName, Extent, org, "file", employeeId, fileName);
                            }else if (download.equalsIgnoreCase("upload")){
                                assignmentPos = position;

                                if (!checkPermission()) {
                                    showFileChooser();
                                } else {
                                    if (checkPermission()) {
                                        requestPermissionAndContinue();
                                    } else {
                                        showFileChooser();
                                    }

                                }
                                // submitUploadDoc(position);
                            }



                        }
                    });
*/

                    } else {
                        Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } else if (assignmentModel.getStatus() == 0) {
                    Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            } catch (Exception e) {

                Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Log.d(TAG, "Error : " + e.getMessage());
                // progBarAssi.setVisibility(View.GONE);
                // utilityClassObj.stopLoader();
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

    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            Log.e("INSIDE", "" + referenceId);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getContext())
                            .setSmallIcon(R.drawable.companylogo2x)
                            .setContentTitle("ECHOAPPDOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };


    private void funDownloadFile(String URL, String fileName) {
        Log.e("Download Path", "URL==" + URL);
//
        DownloadManager downloadmanager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(URL);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "/StudentFolder/Assignment/" + fileName);
        request.setTitle(fileName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadmanager.enqueue(request);

        Snackbar.make(lay_view, "File downloaded successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission Necessary");
                alertBuilder.setMessage("Storage permission is necessary");
                alertBuilder.setPositiveButton(android.R.string.yes, (dialog, which) -> ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE
                        , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE));
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            showFileChooser();
        }
    }

    private void showFileChooser() {

        String[] mimeTypes =
                {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "application/pdf",
                        "application/jpg"};

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }

        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }

    @Override
    public void onClick(View v) {

    }
}