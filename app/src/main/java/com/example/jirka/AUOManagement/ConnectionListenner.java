package com.example.jirka.AUOManagement;

/**
 * Created by auo on 6/30/2017.
 */

public class ConnectionListenner {
    private ConnectionEvent ie;
    public ConnectionListenner (ConnectionEvent event)
    {
        // Save the event object for later use.
        ie = event;
    }
    //Invoke when Connection is established
    public void connectionEstablished ()
    {
        //make OK event for handler
        ie.callbackOKCall();
    }
    //Invoke when Connection is failed
    public void connectionFailed ()
    {
        //make NG event for handler
        ie.callbackNGCall();
    }
}
