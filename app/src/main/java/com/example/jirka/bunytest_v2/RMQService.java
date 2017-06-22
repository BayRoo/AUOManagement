package com.example.jirka.bunytest_v2;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by auo on 6/20/2017.
 */

public class RMQService extends Service {
        // Binder given to clients
        private final IBinder mBinder = new LocalBinder();
        // Random number generator
        private final Random mGenerator = new Random();
        // Conection to RabbitMQ provider
        private RMQConnector connector;


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
        public int getRandomNumber() {
            return mGenerator.nextInt(100);
        }

        @Override
        public void onCreate() {
            // Start up the thread running the service.  Note that we create a
            // separate thread because the service normally runs in the process's
            // main thread, which we don't want to block.  We also make it
            // background priority so CPU-intensive work will not disrupt our UI.
            HandlerThread thread = new HandlerThread("ServiceStartArguments",
                    android.os.Process.THREAD_PRIORITY_BACKGROUND);
            thread.start();
            //Connecting to RabbitMQ provider
            connector = new RMQConnector();
            Log.d("LOG","RMQService created!");
        }


        @Override
        public void onDestroy() {
            Toast.makeText(this, "Service done", Toast.LENGTH_SHORT).show();
        }

}
