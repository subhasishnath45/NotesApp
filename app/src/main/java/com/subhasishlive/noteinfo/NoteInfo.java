package com.subhasishlive.noteinfo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by SubhasishNath on 10/6/2018.
 */

public final class NoteInfo implements Parcelable {
    private CourseInfo mCourse;
    private String mTitle;
    private String mText;

    public NoteInfo(CourseInfo course, String title, String text) {
        mCourse = course;
        mTitle = title;
        mText = text;
    }

    // In this private constructor, I shall read all the values from parcel.
    private NoteInfo(Parcel source) {
        mCourse = source.readParcelable(CourseInfo.class.getClassLoader());
        mTitle = source.readString();
        mText = source.readString();
    }

    public CourseInfo getCourse() {
        return mCourse;
    }

    public void setCourse(CourseInfo course) {
        mCourse = course;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    private String getCompareKey() {
        return mCourse.getCourseId() + "|" + mTitle + "|" + mText;
    }

//    equals() method is used to compare Objects for equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

//    hashCode() is used to generate an integer code corresponding to that object.
    @Override
    public int hashCode() {
        return getCompareKey().hashCode();
    }

    @Override
    public String toString() {
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // the following method
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // for CourseInfo mCourse field, CourseInfo itself is a reference type.
        // writeParcelable method is used to write other parcelable types in the parcelable.
        dest.writeParcelable(mCourse,0);
        dest.writeString(mTitle);
        dest.writeString(mText);
    }


    public final static Parcelable.Creator<NoteInfo> CREATOR =
            new Parcelable.Creator<NoteInfo>(){

                @Override
                public NoteInfo createFromParcel(Parcel source) {
                    return new NoteInfo(source);
                }

                @Override
                public NoteInfo[] newArray(int size) {
                    return new NoteInfo[size];
                }
            };

}