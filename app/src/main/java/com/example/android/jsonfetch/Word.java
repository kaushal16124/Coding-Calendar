package com.example.android.jsonfetch;

public class Word {

    private String mName;
    private String mPhase;
    private String mDuration;
    private String mDate;

    public Word(String name, String phase, String duration,String dte)
    {
        mName=name;
        mPhase=phase;
        mDuration=duration;
        mDate=dte;
    }


    public String getName(){
        return mName;
    }

    public String getPhase(){
        return mPhase;
    }

    public String getDuration(){
        return mDuration;
    }

    public String getDate(){
        return mDate;
    }
}
