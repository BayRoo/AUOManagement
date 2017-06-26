package com.example.jirka.AUOManagement;

import android.util.Log;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.sleep;

/**
 * Created by Jirka on 29. 12. 2016.
 */

public class RMQConnector {

    private Thread publishThread;
    private Connection connection = null;
    private Channel ch;
    private volatile boolean cState;
    public RMQConnector(){
        cState = false;
        createConnection();
    }

    public void createConnection(){
        Log.d("LOG","Try to connect ...");
        publishThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost("10.44.15.67");
                factory.setUsername("admin");
                factory.setPassword("zajda");
                try {
                    connection = factory.newConnection();
                    ch = connection.createChannel();
                    ch.confirmSelect();
                    //name,type,durable,auto-delete,internal,mapobject(null)
                    ch.exchangeDeclare("testing","topic",true,true,false,null);
                    Log.d("LOG","Connection established");
                    cState = true;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                Log.d("LOG", String.valueOf(connection));
            }
        });
        publishThread.start();

    }

    public void closeConnection(){
        publishThread.interrupt();
        publishThread = null;
    }

    public boolean getState(){
        return cState;
    }


    public void publishToAMQP()
    {
            String message = "Hi for AUO";
            try{
                ch.basicPublish("testing", "chat", null, message.getBytes());
                Log.d("LOG", "[s] " + message);

            } catch (Exception e){
                Log.d("LOG","[f] " + message);
            }
      }

}
