package com.londonappbrewery.destini;

/**
 * Created by iasjem on 05/02/2018.
 */

public class StoryProgress {
    private int mStoryID;
    private boolean mAnswer;

    public StoryProgress(int storyResourceID, boolean answer) {
        mStoryID = storyResourceID;
        mAnswer = answer;
    }

    public int getmStoryID() {
        return mStoryID;
    }

    public void setmStoryID(int mStoryID) {
        this.mStoryID = mStoryID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public boolean getmAnswer() {
        return mAnswer;
    }

    public void setmAnswer(boolean mAnswer) {
        this.mAnswer = mAnswer;
    }
}
