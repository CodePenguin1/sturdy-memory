package com.example.testgit;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {
    // Constants for server connection
    private final String SERVER_ADDRESS = "192.168.0.232";
    private final int SERVER_PORT = 2000;

    // Log tag
    private final String LOG_TAG = "TCPClient";

    // Operations that can be performed in the call to TCPClient
    enum Goal {
        GAMESTATE
    }

    // Game state object last retrieved from the server
    private String gameState = "";

    /**
     * Multithreading class to execute all of our client-server communications.
     *
     * This is called by TCPClient to spawn a new thread in which we perform
     * the client-server communication. When its work is completed, it will
     * exit after saving any information received from the server to
     * instance fields in TCPClient.
     */
    private class TCPMultiThreading extends Thread {
        // Operation to be performed in this instance of TCPMultiThreading
        private Goal nextRequest;

        /**
         * Constructor
         * @param nextGoal: the operation that will be performed with this instance
         */
        public TCPMultiThreading(Goal nextGoal) {
            nextRequest = nextGoal;
        }

        /**
         * Create socket to connect to server
         * @return new socket, or null object if unsuccessfull
         */
        private Socket getServerSocket() {
            Socket server;
            try {
                server = new Socket(SERVER_ADDRESS, SERVER_PORT);
            } catch (IOException e) {
                Log.e(LOG_TAG, "could not create server socket connection");
                Log.e(LOG_TAG, e.toString());
                server = null;
            }
            return server;
        }

        /**
         * Wrapper for closing the server socket
         * @param sock: Socket object for the server to which we want to close our connection
         */
        private void closeSocket(Socket sock) {
            try {
                sock.close();
            } catch (IOException e) {
                Log.e(LOG_TAG, "could not close socket");
                Log.e(LOG_TAG, e.toString());
            }
        }

        /**
         * Retrieves the game state from the server
         * and stores it in gameState
         */
        private void retrieveGameState() {
            // Connect to server
            Socket server = getServerSocket();
            if (server == null) {
                //Failed to connect to server
                return;
            }

            // Retrieve game state
            String sentence;
            try {
                // Send request for game state
                DataOutputStream outputStream = new DataOutputStream(server.getOutputStream());
                outputStream.writeBytes("gamestate");
                outputStream.flush();

                // Read in game state
                BufferedReader inputBuff = new BufferedReader(new InputStreamReader(server.getInputStream()));
                sentence = inputBuff.readLine();
            } catch (IOException e) {
                Log.e(LOG_TAG, "could not communicate with server");
                Log.e(LOG_TAG, e.toString());
                return;
            } finally {
                closeSocket(server);
            }
            gameState = sentence;
        }

        /**
         * Thread-runnable method that communicates with the server.
         * Calls the corresponding method based on the value of nextRequest
         */
        public void run() {
            switch (nextRequest) {
                case GAMESTATE:
                    Log.i(LOG_TAG, "requesting new game state");
                    retrieveGameState();
                    break;
                default:
                    Log.e(LOG_TAG, "invalid request for TCPClient");
            }
        }

    }

    /**
     * Update the game state from the server
     * @return updated GameState object
     */
    public String getGameState() {
        Thread t1 = new TCPMultiThreading(Goal.GAMESTATE);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gameState;
    }
}

