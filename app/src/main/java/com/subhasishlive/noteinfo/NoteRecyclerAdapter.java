package com.subhasishlive.noteinfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SubhasishNath on 10/28/2018.
 * This class is used as RecyclerView Adapter...
 */

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {
    // Create item view
    // Store item view references in ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
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

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
