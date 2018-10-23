package com.subhasishlive.noteinfo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SubhasishNath on 10/23/2018.
 */
public class DataManagerTest {
    @Test
    public void createNewNote() throws Exception {
        DataManager dm = DataManager.getInstance();
        final CourseInfo course = dm.getCourse("Android_async");
        final String noteTitle = "This is note title";
        final String noteText = "This is the body text of my note";

        int noteIndex = dm.createNewNote();
        NoteInfo newNote = dm.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote = dm.getNotes().get(noteIndex);
        // We can access the Methods just like statements...
        assertSame(newNote,compareNote);

    }

}