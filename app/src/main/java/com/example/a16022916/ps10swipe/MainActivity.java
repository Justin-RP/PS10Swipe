package com.example.a16022916.ps10swipe;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList <Fragment> a1;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    ArrayList <String> asl;
    int requestCode = 123, notificationID = 888;
//    String[] strArray;

    Button btnReadLater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyFragment myFragment = new MyFragment();
        final MyFragment secondFragment = new MyFragment();
        final MyFragment thirdFragment = new MyFragment();
        final MyFragment fourthFragment = new MyFragment();

        myFragment.setData("1: Justin");
        secondFragment.setData("2: IS");
        thirdFragment.setData("3: Cool");
        fourthFragment.setData("4: YAYYYY");


        vPager = findViewById(R.id.mainViewPager);

        btnReadLater = findViewById(R.id.mainBtnReadLater);

        FragmentManager fm = getSupportFragmentManager();

        a1 = new ArrayList<>();
        a1.add(myFragment);
        a1.add(secondFragment);
        a1.add(thirdFragment);
        a1.add(fourthFragment);

        adapter = new MyFragmentPagerAdapter(fm, a1);

        vPager.setAdapter(adapter);

        btnReadLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                {
//                    NotificationChannel channel = new NotificationChannel("default",
//                            "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);

                    // Set Notification to High Priority (Additional A)
                    NotificationChannel channel = new NotificationChannel("default",
                            "Default Channel", NotificationManager.IMPORTANCE_HIGH);

                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }

                Context thisContext = MainActivity.this;
                Class toClass = MainActivity.class;
                Intent intent = new Intent(thisContext,toClass);
                PendingIntent pIntent = PendingIntent.getActivity(thisContext, requestCode,
                        intent,PendingIntent.FLAG_CANCEL_CURRENT);

                // Set Big Text!! (Additional B)
                NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
                bigText.setBigContentTitle("Enter Now");
                bigText.bigText("Continue Reading: Know Your Facts");
                bigText.setSummaryText("Do you know your facts?");

                // Build notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(thisContext,"default");
                builder.setContentTitle("Continue Reading: Know Your Facts");
                builder.setContentText("Enter now");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setStyle(bigText);  // SetStyle to Big Text (Additional B)
                builder.setAutoCancel(true);

                // To Set High Priority (Additional A)
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(uri);
                builder.setPriority(Notification.PRIORITY_HIGH);

                Notification n = builder.build();

                // An integer good to have, for you to programmatically cancel
                notificationManager.notify(notificationID,n);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionPrevious:
                int previousPage = vPager.getCurrentItem() - 1;
                vPager.setCurrentItem(previousPage,true);
                break;
            case R.id.actionNext:
                int max = vPager.getChildCount();
                if (vPager.getCurrentItem() < max - 1) {
                    int nextPage = vPager.getCurrentItem() + 1;
                    vPager.setCurrentItem(nextPage,true);
                }
                break;
            case R.id.actionRandom:
                Random rand = new Random();
                int maxValue = vPager.getChildCount();
                int pageVal = rand.nextInt(maxValue);
                vPager.setCurrentItem(pageVal, true);

        }
        return super.onOptionsItemSelected(item);
    }


}
