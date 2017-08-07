package com.pathfinder.anup.jobschedulerservicedemo;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by Anup on 8/7/2017.
 */

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobServiceDemo extends JobService {

    JobParameters jobParameters;
    DoInBackgroundThread backgroundThread;
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        this.jobParameters = jobParameters;
        Log.d("TestService", "Work to be called from here");
        backgroundThread = new DoInBackgroundThread();
        backgroundThread.execute();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d("TestService", "System calling to stop the job here");
        if(backgroundThread != null){
            backgroundThread.cancel(true);
        }
        return false;
    }

    private class DoInBackgroundThread extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("DoItTask", "Working here...");
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d("DoItTask", "Clean up the task here and call jobFinished...");
            jobFinished(jobParameters, false);
            super.onPostExecute(aVoid);
        }
    }
}
