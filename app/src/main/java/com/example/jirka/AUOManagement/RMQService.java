package com.example.jirka.AUOManagement;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by auo on 6/20/2017.
 */

public class RMQService extends Service implements ConnectionEvent{
        // Binder given to clients
        private final IBinder mBinder;
        // Random number generator
        private final Random mGenerator;

        private ConnectionListenner lst;
        // Conection to RabbitMQ provider
        private RMQConnector connector;

        public RMQService(){
            mBinder = new LocalBinder();
            mGenerator = new Random();
            lst = new ConnectionListenner(this);
        }


        private static final String ACTION_STRING_SERVICE = "ToService";
        private static final String ACTION_STRING_ACTIVITY = "ToActivity";

        private void sendBroadcast(String mess) {
            Intent new_intent = new Intent();
            new_intent.setAction(ACTION_STRING_ACTIVITY);
            new_intent.putExtra("data",mess);
            sendBroadcast(new_intent);
        }

        private BroadcastReceiver serviceReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(getApplicationContext(), "received message in service..!", Toast.LENGTH_SHORT).show();
            }
        };
        //Listenning for OK events
        public void callbackOKCall() {
            sendBroadcast("OK");
        }
        //Listenning for NG events
        public void callbackNGCall() {
            sendBroadcast("NG");
        }



    /**
         * Class used for the client Binder.  Because we know this service always
         * runs in the same process as its clients, we don't need to deal with IPC.
         */
        public class LocalBinder extends Binder {
            RMQService getService() {
                // Return this instance of LocalService so clients can call public methods
                return RMQService.this;
            }
        }

        @Override
        public IBinder onBind(Intent intent) {
            return mBinder;
        }

        /** method for clients */
        /*public int getRandomNumber() {
            return mGenerator.nextInt(100);
        }*/

        @Override
        public void onCreate() {
            // Start up the thread running the service.  Note that we create a
            // separate thread because the service normally runs in the process's
            // main thread, which we don't want to block.  We also make it
            // background priority so CPU-intensive work will not disrupt our UI.
            HandlerThread thread = new HandlerThread("ServiceStartArguments",
                    android.os.Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();

            if (serviceReceiver != null) {
                //Create an intent filter to listen to the broadcast sent with the action "ACTION_STRING_SERVICE"
                IntentFilter intentFilter = new IntentFilter(ACTION_STRING_SERVICE);
                //Map the intent filter to the receiver
                registerReceiver(serviceReceiver, intentFilter);
            }
            //Connecting to RabbitMQ provider
            connector = new RMQConnector(lst);
            Log.d("LOG","RMQService created!");

        }


        @Override
        public void onDestroy() {
            connector.closeConnection();
            unregisterReceiver(serviceReceiver);
            Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
        }

}
