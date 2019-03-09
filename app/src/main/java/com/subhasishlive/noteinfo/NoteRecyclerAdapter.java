package com.subhasishlive.noteinfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by SubhasishNath on 10/28/2018.
 * This class is used as RecyclerView Adapter...
 */

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    // Created our LayoutInflater
    private final LayoutInflater layoutInflater;

    // constructor that is taking the context as parameter.
    public NoteRecyclerAdapter(Context context) {
        this.mContext = context;
        // LayoutInflater.from() class accepts a context as its parameter.
        layoutInflater = LayoutInflater.from(mContext);
    }

    // Create item view
    // Store item view references in ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // First We are creating a view using our LayoutInflater.
        // R.layout.item_note_list refers to the entire layout for individual layout item.
        View itemView = layoutInflater.inflate(R.layout.item_note_list,parent,false);
        // Returning a ViewHolder that is holding our newly created view.
        return new ViewHolder(itemView);
    }
    // Receives view holder and display position as parameters
    // Set display values using view holder
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    // Return number of items
    @Override
    public int getItemCount() {
        return 0;
    }

    /*
        * NoteRecyclerAdapter.ViewHolder will hold individual views
        * */
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextCourse;
        public final TextView mTextTitle;

        public int mCurrentPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextCourse = (TextView) itemView.findViewById(R.id.text_course);
            mTextTitle = (TextView) itemView.findViewById(R.id.text_title);

        }
    }

}
