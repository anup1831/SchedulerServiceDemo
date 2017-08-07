package com.pathfinder.anup.jobschedulerservicedemo;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JobScheduler jobScheduler = (JobScheduler) getApplicationContext().getSystemService(JOB_SCHEDULER_SERVICE);
        ComponentName componentName = new ComponentName(getApplicationContext(), JobServiceDemo.class);
        JobInfo jobInfo = new JobInfo.Builder(1, componentName).setOverrideDeadline(10).setRequiresCharging(true).build();
        jobScheduler.schedule(jobInfo);

    }
}
