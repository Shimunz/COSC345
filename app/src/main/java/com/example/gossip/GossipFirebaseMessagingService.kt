package com.example.gossip

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

/**
 * Some features are yet to be implemented
 */
class GossipFirebaseMessagingService : FirebaseMessagingService(){

    /**
     * Called when message is received
     *
     * @param remoteMessage Object representing the message from Firebase cloud messaging
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // TODO (Do something on message received)
        Log.d("MessageReceived", remoteMessage.toString())
    }

    /**
     * Called when token is changed
     *
     * @param token is the new token in, String format
     */
    override fun onNewToken(token : String) {
        Log.d("Token", "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    /**
     * Sends token to the server
     *
     * @param token the token we are sending to server, String format
     */
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d("TokenToServer", "sendRegistrationTokenToServer($token)")
    }

    /**
     * Sends out notifications
     *
     * @param messageBody is the message that is we are sending in the notification
     */
    private fun sendNotification(messageBody: String) {

    }
}