package com.example.testgit;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class TCPClient {

        private final String SERVER_ADDRESS = "192.168.50.203";
        private final int SERVER_PORT = 2000;

        private String gameState = "";


        private class TCPMultiThreading extends Thread {
            public void run() {
                String sentence;
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                Socket clientSocket = null;
                try {
                    clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    sentence = inFromUser.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                gameState = sentence;

            }

        }

        public String getGameState() throws Exception{
            CountDownLatch latch = new CountDownLatch(3);

            TCPMultiThreading t1 = new TCPMultiThreading();
            t1.start();

            latch.await(); // Wait for countdown

            return gameState;
        }

        /*public static void main(String[] args) throws IOException {

            // Why must it throw an IOException?
            //

            Socket server = new Socket("localhost", 2000);
            // Creating a new "socket" object with a specific port?
            //What's the "localhost" for? Is it indicating that the server is hosted locally? YES

            BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream())); // getting messaged from client
            // Break down: Created BufferedReader Object named "input."
            // What is the InputStreamReader and why is it nested within parenthesis
            System.out.println(input.readLine());
            // I imagine this prints whatever was received from the server?

            server.close();
            // closes the server connection
        }
    */
    }

