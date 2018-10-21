package com.subhasishlive.noteinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NoteListActivity.this,NoteActivity.class));

            }
        });

        initializeDisplayContent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNotes.notifyDataSetChanged();
    }

    private void initializeDisplayContent() {
        final ListView listNotes = (ListView) findViewById(R.id.list_notes);

        // the following List of type NoteInfo will hold all the notes...
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        // Creating the new Adapter for our notes
        mAdapterNotes = new ArrayAdapter<NoteInfo>(this,android.R.layout.simple_list_item_1,notes);
        // setting up the Adapter to the list view.
        listNotes.setAdapter(mAdapterNotes);

        listNotes.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // The body of this method will get called every time user clicks in a list item
                        Intent intent = new Intent(NoteListActivity.this,NoteActivity.class);
                        // fetching the position of the current notes item and assigning it to note field.
//                        NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
                        // I'm passing the position via intent.putextra() here.
                        intent.putExtra(NoteActivity.NOTE_POSITION,position);
                        startActivity(intent);
                    }
                }
        );

    }

}
