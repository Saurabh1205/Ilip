package com.example.ilip.activities.fees.payable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;
import com.orhanobut.dialogplus.DialogPlus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbstractForPayable extends RecyclerView.Adapter<AbstractForPayable.ViewHolderPayable> {
    ArrayList<ModelForPayable> arrayList;
    Context context;
    ModelForPayable modelForPayable;
    String ScreenName="";
    public AbstractForPayable(ArrayList<ModelForPayable> arrayList, Context context, ModelForPayable modelForPayable,String screen) {
        this.arrayList = arrayList;
        this.context = context;
        this.modelForPayable = modelForPayable;
        this.ScreenName = screen;
    }

    @NonNull
    @Override
    public ViewHolderPayable onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_payables,parent,false);
        return new ViewHolderPayable(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPayable holder, @SuppressLint("RecyclerView") int position) {

    holder.txt_name.setText(arrayList.get(position).Fee_Type);
    holder.txt_value.setText(arrayList.get(position).Fee_value);
    holder.txt_name.setTag(position);

    holder.txt_name.setOnClickListener(v -> {
      if(holder.txt_name.getText().equals("Examination")){
          show_PopupView("Examination",arrayList.get(position).getJsonArrayExamination());
//              show_PopupView("Examination",arrayList.get(position).getJsonArrayExamination());
      }
      if(holder.txt_name.getText().equals("Structural Fee")){
          show_PopupView("Structural Fee",arrayList.get(position).getJsonArrayStructural());
//              show_PopupView("Structural Fee",arrayList.get(position).getJsonArrayStructural());
      }
      if(holder.txt_name.getText().equals("Non-Structural Fee")){
          show_PopupView("Non-Structural Fee",arrayList.get(position).getJsonArrayNonStructural());
//              show_PopupView("Non-Structural Fee",arrayList.get(position).getJsonArrayNonStructural());
      }

    });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderPayable extends RecyclerView.ViewHolder{
    TextView txt_name,txt_value;
    public ViewHolderPayable(@NonNull View itemView){
        super(itemView);
        txt_name = itemView.findViewById(R.id.text_name);
        txt_value = itemView.findViewById(R.id.text_value);
        if(ScreenName.equals("Home")){
            txt_name.setEnabled(false);
        }
    }
}
    /*public void show_PopupViewNew(String TitleName, JSONArray jsonArray){
        Log.e("Json Array ","Data=="+jsonArray);
        JSONArray jsonArray1;
        ArrayList<ModelForPayable> arrayList1 = new ArrayList<>();
        ArrayList<String> array1 = new ArrayList<>();
        ArrayList<String> array2 = new ArrayList<>();
        ArrayList<String> array3 = new ArrayList<>();
        ArrayList<String> array4 = new ArrayList<>();
        ArrayList<String> array5 = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
            try {
                modelForPayable = new ModelForPayable();
                JSONObject jObj = (JSONObject) jsonArray.get(i);

                modelForPayable.setFEE_INSTALLMENT_NO(jObj.getString("FEE_INSTALLMENT_NO"));
                    array1.add(jObj.getString("FEE_SUB_TYPE_DESC"));
                    array2.add(jObj.getString("RECEIVABLE_AMOUNT"));
                    array3.add(jObj.getString("FEE_RECEIPT_AMOUNT"));
                    array4.add(jObj.getString("DISCOUNT_AMOUNT"));
                    array5.add(jObj.getString("BALANCE_AMOUNT"));

                modelForPayable.setFEE_TYPE_DESC(array1);
                modelForPayable.setRECEIVABLE_AMOUNT(array2);
                modelForPayable.setFEE_RECEIPT_AMOUNT(array3);
                modelForPayable.setDISCOUNT_AMOUNT(array4);
                modelForPayable.setBALANCE_AMOUNT(array5);
//                modelForPayable.setArrayList(array);
                arrayList1.add(modelForPayable);

            } catch (Exception e) {
                Log.e("asds","asdfas"+e);
            }

        }


        final DialogPlus inflate = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.popup_for_installment_dtl))
                .setExpanded(true, 1000)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4Installment);
        toolbar.setTitle("");

        toolbar.setNavigationOnClickListener(view -> {
            inflate.dismiss();
        });
        TextView Heading = (TextView) inflate.findViewById(R.id.TitleOfModel);
        Heading.setText(TitleName);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.Structural_Data_list);
        AbstractforpayableIndetails abstractforpayableIndetails = new AbstractforpayableIndetails(context,arrayList1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(abstractforpayableIndetails);
        abstractforpayableIndetails.notifyDataSetChanged();
        //abstractforpayableIndetails.setHasStableIds(true);
        inflate.show();
    }*/
    public void show_PopupView(String TitleName, JSONArray jsonArray){
        Log.e("Json Array ","Data=="+jsonArray);
        JSONArray jsonArray1;
        ArrayList<ModelForPayable> arrayList1 = new ArrayList<>();
        for(int i=0;i<jsonArray.length();i++){
                    try {
//                        List<String> array = new ArrayList<>();
                        ArrayList<String> array1 = new ArrayList<>();
                        ArrayList<String> array2 = new ArrayList<>();
                        ArrayList<String> array3 = new ArrayList<>();
                        ArrayList<String> array4 = new ArrayList<>();
                        ArrayList<String> array5 = new ArrayList<>();

                        modelForPayable = new ModelForPayable();
                        JSONObject jObj = (JSONObject) jsonArray.get(i);

                        modelForPayable.setFEE_INSTALLMENT_NO(jObj.getString("FEE_INSTALLMENT_NO"));
                        modelForPayable.setINS_RECEIVABLE_TOTAL(jObj.getString("INS_RECEIVABLE_TOTAL"));
                        modelForPayable.setINS_RECEIVED_TOTAL(jObj.getString("INS_RECEIVED_TOTAL"));
                        modelForPayable.setINS_BALANCE_TOTAL(jObj.getString("INS_BALANCE_TOTAL"));
                        modelForPayable.setINS_CONSESSION_TOTAL(jObj.getString("INS_CONSESSION_TOTAL"));

                        jsonArray1 = jObj.getJSONArray("Child");
                        for(int j=0;j<jsonArray1.length();j++){
                            JSONObject jObj1 = (JSONObject) jsonArray1.get(j);

                            array1.add(jObj1.getString("FEE_SUB_TYPE_DESC"));
                            array2.add(jObj1.getString("RECEIVABLE_AMOUNT"));
                            array3.add(jObj1.getString("FEE_RECEIPT_AMOUNT"));
                            array4.add(jObj1.getString("DISCOUNT_AMOUNT"));
                            array5.add(jObj1.getString("BALANCE_AMOUNT"));
                        }
                    modelForPayable.setFEE_TYPE_DESC(array1);
                    modelForPayable.setRECEIVABLE_AMOUNT(array2);
                    modelForPayable.setFEE_RECEIPT_AMOUNT(array3);
                    modelForPayable.setDISCOUNT_AMOUNT(array4);
                    modelForPayable.setBALANCE_AMOUNT(array5);
//                modelForPayable.setArrayList(array);
                arrayList1.add(modelForPayable);

            } catch (Exception e) {
                Log.e("asds","asdfas"+e);
            }

        }


        final DialogPlus inflate = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.popup_for_installment_dtl))
                .setExpanded(true, 1000)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4Installment);
        toolbar.setTitle("");

        toolbar.setNavigationOnClickListener(view -> inflate.dismiss());
        TextView Heading = (TextView) inflate.findViewById(R.id.TitleOfModel);
        Heading.setText(TitleName);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.Structural_Data_list);
        AbstractforpayableIndetails abstractforpayableIndetails = new AbstractforpayableIndetails(context,arrayList1);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(abstractforpayableIndetails);
        abstractforpayableIndetails.notifyDataSetChanged();
        //abstractforpayableIndetails.setHasStableIds(true);
        inflate.show();
    }

}
