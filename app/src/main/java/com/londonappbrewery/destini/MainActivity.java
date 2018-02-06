package com.londonappbrewery.destini;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: Steps 4 & 8 - Declare member variables here:
        int mIndex=0, mStory, mProgress=0, mLevel=0, mAnswerT, mAnswerB;
        TextView storyTextView; Button buttonTop, buttonBottom;
    private StoryProgress[] mStoryLine = new StoryProgress[]{
            new StoryProgress(R.string.T1_Story, true),
            new StoryProgress(R.string.T2_Story, true),
            new StoryProgress(R.string.T3_Story, false),
            new StoryProgress(R.string.T4_End, false),
            new StoryProgress(R.string.T5_End, false),
            new StoryProgress(R.string.T6_End, true)
    };

    private StoryProgress[] mPlayerAnswer = new StoryProgress[]{
            new StoryProgress(R.string.T1_Ans1, true),
            new StoryProgress(R.string.T2_Ans1, true),
            new StoryProgress(R.string.T3_Ans1, true),
            new StoryProgress(R.string.T1_Ans2, false),
            new StoryProgress(R.string.T2_Ans2, false),
            new StoryProgress(R.string.T3_Ans2, false)
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState !=null) {
            mProgress = savedInstanceState.getInt("ProgressKey");
            mLevel = savedInstanceState.getInt("LevelKey");
            mIndex = savedInstanceState.getInt("IndexKey");
        } else {
            mProgress=0;
            mLevel=0;
            mIndex=0;
        }

        // TODO: Step 5 - Wire up the 3 views from the layout to the member variables:
        storyTextView = (TextView) findViewById(R.id.storyTextView);
        buttonTop = (Button) findViewById(R.id.buttonTop);
        buttonBottom = (Button) findViewById(R.id.buttonBottom);

        restartGame();

        // TODO: Steps 6, 7, & 9 - Set a listener on the top button:
        buttonTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStoryProgress(true);
                mLevel +=1;
                updateStory();
            }
        });

        // TODO: Steps 6, 7, & 9 - Set a listener on the bottom button:
        buttonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStoryProgress(false);
                updateStory();
            }
        });

    }

    private void checkStoryProgress(boolean userSelection){

        if (userSelection == true){
            mProgress+=10;
            mLevel = 2;
        } else {
            mProgress+=2;
            mLevel = 1;
        }


    }

    private void updateStory(){
        mIndex  = (mIndex + 1) % (mStoryLine.length/2);

        if (mProgress>=15){

            mStory = mStoryLine[5].getmStoryID();

            BadEndMessage();

        } else if (mProgress == 7 ) {
            mStory = mStoryLine[4].getmStoryID();

            GoodEndMessage();
        } else if (mProgress == 3) {
            mStory = mStoryLine[3].getmStoryID();

            TrueEndMessage();
        }
        else {
            if (mLevel >=2) {
                mStory = mStoryLine[2].getmStoryID();
                mAnswerT = mPlayerAnswer[2].getmStoryID();
                mAnswerB = mPlayerAnswer[5].getmStoryID();
            } else {
                mStory = mStoryLine[1].getmStoryID();
                mAnswerT = mPlayerAnswer[1].getmStoryID();
                mAnswerB = mPlayerAnswer[4].getmStoryID();
            }
            mProgress /= 2;
        }

        storyTextView.setText(mStory);
        buttonTop.setText(mAnswerT);
        buttonBottom.setText(mAnswerB);

    }

    public void restartGame(){
        mIndex=0; mProgress=0; mLevel=0;
        mStory = mStoryLine[0].getmStoryID();
        mAnswerT = mPlayerAnswer[0].getmStoryID();
        mAnswerB = mPlayerAnswer[3].getmStoryID();
        storyTextView.setText(mStory);
        buttonTop.setText(mAnswerT);
        buttonBottom.setText(mAnswerB);
    }

    public void BadEndMessage(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Bad Ending");
        alert.setCancelable(false);
        alert.setMessage("You let the murderer get away!");
        alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        });
        alert.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.show();
    }

    public void GoodEndMessage(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Good Ending");
        alert.setCancelable(false);
        alert.setMessage("At least you got away safely. Way to go!");
        alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        });
        alert.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.show();
    }

    public void TrueEndMessage(){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("True Ending");
        alert.setCancelable(false);
        alert.setMessage("You were unharmed but you have to learn how to fix your car next time, okay?");
        alert.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                restartGame();
            }
        });
        alert.setNegativeButton("Exit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alert.show();
    }

    public void OnSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("ProgressKey", mProgress);
        outState.putInt("IndexKey", mIndex);
        outState.putInt("LevelKey", mLevel);
    }

}
