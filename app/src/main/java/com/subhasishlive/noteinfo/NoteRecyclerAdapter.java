package com.subhasishlive.noteinfo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SubhasishNath on 10/28/2018.
 * This class is used as RecyclerView Adapter...
 */

public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.ViewHolder> {

    private final Context mContext;
    // following list will hold all of our notes
    private final List<NoteInfo> mNotes;
    // Created our LayoutInflater
    private final LayoutInflater layoutInflater;

    // constructor that is taking the context as parameter.
    public NoteRecyclerAdapter(Context context, List<NoteInfo> mNotes) {
        this.mContext = context;
        this.mNotes = mNotes;
        // LayoutInflater.from() class accepts a context as its parameter.
        layoutInflater = LayoutInflater.from(mContext);
    }

    // Create item view
    // Store item view references in ViewHolder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // First We are creating a view using our LayoutInflater.
        // R.layout.item_note_list refers to the entire layout for individual layout item.
        // So itemView contains a single item view.
        View itemView = layoutInflater.inflate(R.layout.item_note_list,parent,false);
        // Returning a new ViewHolder instance that is holding our newly created view.
        return new ViewHolder(itemView);
    }
    // Receives view holder and display position as parameters
    // Set display values using view holder
    // 1st parameter is the ViewHolder that I've created inside onCreateViewHolder() method
    // 2nd parameter is the position of the view that I want to bind to.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // I have to bind our data to our views.
        NoteInfo note = mNotes.get(position);
        holder.mTextCourse.setText(note.getCourse().getTitle());
        holder.mTextTitle.setText(note.getTitle());
    }


    // Return number of items
    @Override
    public int getItemCount() {
        // returning the size of our mNotes list as the return value of getItemCount() method.
        return mNotes.size();
    }

    /*
        * NoteRecyclerAdapter.ViewHolder will hold individual views
        * */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // getting references to each of our textViews from item_note_list.xml layout
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
