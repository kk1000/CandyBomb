package com.endless.android.candybomb;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnTouchListener {
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;
    private CandiesView mCandiesView;
    private String mHighScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.hello_world_text_view);
        mCandiesView = (CandiesView) findViewById(R.id.candies_view);
        mCandiesView.setOnTouchListener(this);

        //   new FetchHighScoreTask().execute();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mTextView.setText("" + mCandiesView.getLevelScore());
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class FetchHighScoreTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            return new HighScoreFetch().FetchHighScore();
        }

        @Override
        protected void onPostExecute(String highScore) {
            mHighScore = highScore;
            mTextView.setText(mHighScore);
        }
    }
}
