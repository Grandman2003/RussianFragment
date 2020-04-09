package com.example.fragmentrussian1;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragmentrussian1.RussianItemFragment.OnListFragmentInteractionListener;
import com.example.fragmentrussian1.dummy.DummyContent.Rule;

import java.util.Iterator;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Rule} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class RussianRecyclerViewAdapter extends RecyclerView.Adapter<RussianRecyclerViewAdapter.ViewHolder> {

    private final List<Rule> mValues;
    private final OnListFragmentInteractionListener mListener;

    public RussianRecyclerViewAdapter(List<Rule> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_russianitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
        holder.mSign.setText(mValues.get(position).sign);
        if (holder.mItem.getDetails().equals("выполнено")){
            if(holder.mSign.getText()==""){
                if(holder.mIdView.getText().toString().toCharArray().length==1){
                holder.mContentView.setTextColor(Color.GREEN);}
            }else{
                holder.mContentView.setTextColor(Color.BLACK);
            }
        }else if(holder.mItem.getDetails().equals("выполняется")){
            if(holder.mSign.getText()==""){
                if(holder.mIdView.getText().toString().toCharArray().length==1){
                holder.mContentView.setTextColor(Color.parseColor("#D5BB05"));}
            }else{
                holder.mContentView.setTextColor(Color.BLACK);
            }
        }else{
            holder.mContentView.setTextColor(Color.BLACK);
        }
        if(holder.mItem.getDetails().equals("молодец!")){
            holder.mSign.setTextColor(Color.parseColor("#05D57C"));
        }else{
            holder.mSign.setTextColor(Color.RED);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                    try{ int i=0;
                   while(i<holder.mPoints.size()){
                        Log.e("Touch","Wrong Functionality");
                        ViewHolder viewHolder=holder.mPoints.get(i);
                        onBindViewHolder( viewHolder,position);
                        i++;}
                    }catch (NullPointerException e){
                       e.printStackTrace();
                   }

                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public TextView mIdView;
        public TextView mContentView;
        public Rule mItem;
        public TextView mSign;
        public List<ViewHolder> mPoints;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
            mSign=(TextView)view.findViewById(R.id.contrum);
        }



        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
