package com.subhasishlive.noteinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;


public class NoteActivity extends AppCompatActivity {
    // The following constant is used as the key for putextra and getextra method of intents.
    public static final String NOTE_POSITION = "com.subhasishlive.noteinfo.NOTE_POSITION";

    // these constants are to be stored inside bundles...
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.subhasishlive.noteinfo.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE = "com.subhasishlive.noteinfo.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT = "com.subhasishlive.noteinfo.ORIGINAL_NOTE_TEXT";


    public static final int POSITION_NOT_SET = -1;
    private NoteInfo mNote;
    private boolean mIsNewNote;
    private Spinner mSpinnerCourses;
    private EditText mTextNoteTitle;
    private EditText mTextNoteText;
    private int mNotePosition;
    private boolean mIsCancelling;
    private String mOriginalNoteCourseId;
    private String mOriginalNoteTitle;
    private String mOriginalNoteText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        // setting up the toolbar...
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        mSpinnerCourses = (Spinner) findViewById(R.id.planets_spinner);

        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses =
                new ArrayAdapter<CourseInfo>(this, android.R.layout.simple_spinner_item, courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Associate our adapter to our spinner.
        mSpinnerCourses.setAdapter(adapterCourses);


        // method for receiving the values, were sent via intent.putextra() method.
        readDisplayStateValues();
        // if activity is created for the first time,
        // the savedInstanceState == null. so then call saveOriginalNoteValues()
        if(savedInstanceState == null) {
            saveOriginalNoteValues();
        }else {
            restoreOriginalNoteValues(savedInstanceState);
        }

        // getting reference to the edittexts
        mTextNoteTitle = (EditText) findViewById(R.id.text_note_title);
        mTextNoteText = (EditText) findViewById(R.id.text_note_body);

        // following method is called to display the mNote details.
        if(!mIsNewNote){ // checking of it's a new note
            // displayNote() is called only for existing notes...
            displayNote(mSpinnerCourses, mTextNoteTitle, mTextNoteText);
        }

    }

    // restore the value from bundle, when the activity is restarted.
    private void restoreOriginalNoteValues(Bundle savedInstanceState) {
        // getting all the values by the keys those were sent by overriding onSaveInstanceState() method.
        mOriginalNoteCourseId = savedInstanceState.getString(ORIGINAL_NOTE_COURSE_ID);
        mOriginalNoteTitle = savedInstanceState.getString(ORIGINAL_NOTE_TITLE);
        mOriginalNoteText = savedInstanceState.getString(ORIGINAL_NOTE_TEXT);
    }

    private void saveOriginalNoteValues() {
        // if this is new note, there is no value to set.
        // So we return and exit the method. No code after return will be executed...
        if(mIsNewNote)
            return;
        // if it is not a new note, then the following codes get executed.
        mOriginalNoteCourseId = mNote.getCourse().getCourseId();
        mOriginalNoteTitle = mNote.getTitle();
        mOriginalNoteText = mNote.getText();
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(mIsCancelling){
            if(mIsNewNote){
                DataManager.getInstance().removeNote(mNotePosition);
            }
            else{
                // If it is an old note and we cancel, then the values before edit will be kept.
                storePreviousNoteValues();
            }
        }else{
            saveNote();
        }

    }

    private void storePreviousNoteValues() {
        // mOriginalNoteCourseId was saved before in saveOriginalNoteValues
        CourseInfo course = DataManager.getInstance().getCourse(mOriginalNoteCourseId);
        mNote.setCourse(course);
        // mOriginalNoteTitle was saved before in saveOriginalNoteValues
        mNote.setTitle(mOriginalNoteTitle);
        // mOriginalNoteText was saved before in saveOriginalNoteValues
        mNote.setText(mOriginalNoteText);
    }

    // we are storing the values into the bundle by overriding onSaveInstanceState() method.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // putString() takes the name and value pair as parameters.
        outState.putString(ORIGINAL_NOTE_COURSE_ID, mOriginalNoteCourseId);
        outState.putString(ORIGINAL_NOTE_TITLE, mOriginalNoteTitle);
        outState.putString(ORIGINAL_NOTE_TEXT, mOriginalNoteText);
    }

    private void saveNote() {
        mNote.setCourse((CourseInfo) mSpinnerCourses.getSelectedItem());
        mNote.setTitle(mTextNoteTitle.getText().toString());
        mNote.setText(mTextNoteText.getText().toString());
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteBody) {
        // getting the lists of courses from the data manager...
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        // now getting the index of the current item.
        int courseIndex = courses.indexOf(mNote.getCourse());
        // setting the element of that index as the selected item of the spinner.
        spinnerCourses.setSelection(courseIndex);
        textNoteTitle.setText(mNote.getTitle());
        textNoteBody.setText(mNote.getText());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        // getting the extra from the Parcelable...
        int position = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);
        mIsNewNote = position == POSITION_NOT_SET;
        if(mIsNewNote){
            createNewNote();
        }else{
            mNote = DataManager.getInstance().getNotes().get(position);
        }
    }

    private void createNewNote() {
        // getting reference to DataManager class...
        DataManager dm = DataManager.getInstance();
        // calling the createNewNote() method of DataManager class...
        // and returning the position of newly created note...
        mNotePosition = dm.createNewNote();
        // getting that particular note of that position...
        mNote = dm.getNotes().get(mNotePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_note,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        return super.onOptionsItemSelected(item);
        int id = item.getItemId();

        if(id == R.id.action_send_mail){
            sendMail();
            return true;
        }else if(id == R.id.action_cancel){
            // create a flag to indicate that I'm cancelling
            mIsCancelling = true;
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendMail() {
        // got the currently selected item in the spinner.
        CourseInfo course = (CourseInfo) mSpinnerCourses.getSelectedItem();
        String subject = mTextNoteTitle.getText().toString();
//        String text = "Checkout my Android skill \"" + course.getTitle() + "\"\n" + mTextNoteText.getText().toString();
        String text = "Checkout what I learned in the Pluralsight course \"" +
                course.getTitle() +"\"\n" + mTextNoteText.getText().toString();
        // creating implicit intent to send the mail.
        Intent intent = new Intent(Intent.ACTION_SEND);
        // the following Mime type is used for emails
        intent.setType("message/rfc2822");
        // sending the bellow extra informations via mail.
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

}
