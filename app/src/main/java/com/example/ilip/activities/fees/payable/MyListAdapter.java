package com.example.ilip.activities.fees.payable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.Viewholder> {
    Context context;
     ArrayList Particular;
     ArrayList Receivable;
     ArrayList Received;
     ArrayList Discount;
     ArrayList Balance;

    public MyListAdapter(Context context, ArrayList particular, ArrayList receivable, ArrayList received, ArrayList discount, ArrayList balance) {
        this.context = context;
        Particular = particular;
        Receivable = receivable;
        Received = received;
        Discount = discount;
        Balance = balance;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mylist,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.text1.setText((CharSequence) Particular.get(position));
        holder.text2.setText((CharSequence) Receivable.get(position));
        holder.text3.setText((CharSequence) Received.get(position));
        holder.text4.setText((CharSequence) Discount.get(position));
        holder.text5.setText((CharSequence) Balance.get(position));
    }

    @Override
    public int getItemCount() {
        return Particular.size();
    }

    /*public MyListAdapter(Activity context, ArrayList particular, ArrayList receivable, ArrayList received, ArrayList discount, ArrayList balance) {
        super(context, R.layout.mylist, particular);
        this.context = context;
        Particular = particular;
        Receivable = receivable;
        Received = received;
        Discount = discount;
        Balance = balance;
    }*/

    public static class Viewholder extends RecyclerView.ViewHolder{
        TextView text1,text2,text3,text4,text5;
        public Viewholder(@NonNull View rowView) {
            super(rowView);
            text1 =  rowView.findViewById(R.id.Particulars);
            text2 =  rowView.findViewById(R.id.Receivable);
            text3 =  rowView.findViewById(R.id.Received);
            text4 =  rowView.findViewById(R.id.Concession);
            text5 =  rowView.findViewById(R.id.Outstanding);
        }
    }


   /* @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.mylist, null,true);

        TextView text1 = (TextView) rowView.findViewById(R.id.Particulars);
        TextView text2 = (TextView) rowView.findViewById(R.id.Receivable);
        TextView text3 = (TextView) rowView.findViewById(R.id.Received);
        TextView text4 = (TextView) rowView.findViewById(R.id.Concession);
        TextView text5 = (TextView) rowView.findViewById(R.id.Outstanding);

        text1.setText((CharSequence) Particular.get(position));
        text2.setText((CharSequence) Receivable.get(position));
        text3.setText((CharSequence) Received.get(position));
        text4.setText((CharSequence) Discount.get(position));
        text5.setText((CharSequence) Balance.get(position));
        return rowView;
    }*/
}
