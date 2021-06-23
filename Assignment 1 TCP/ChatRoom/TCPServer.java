/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(a) TCP: Server code for chat room app
*/

import java.util.*;
import java.io.*;
import java.net.*;

class ClientHandler extends Thread //Class to create thread for each connected client
{
        final DataInputStream dis; //Input stream from client
        final DataOutputStream dos; //Output stream to client
        final Socket socket; //Socket associated with the client
        final String name; //Name of the client
        
        public ClientHandler(Socket socket, DataInputStream dis, DataOutputStream dos, String name)
        {
            this.socket = socket;
            this.dis = dis;
            this.dos = dos;
            this.name = name;
        }

        @Override
        public void run()
        {
            String message = null;
            String instructionMessage = "\n------Successfully Connected!------\n\nStart typing to start chatting (Type 'Exit' to leave)::\n";
            
            try{
                dos.writeUTF(instructionMessage); //Send connection successful message to client
                while(true)
                {
                        
                    message = dis.readUTF(); //Read message from client
                    
                    if(message.equalsIgnoreCase("Exit")) //If client wants to exit
                    {
                        this.socket.close(); //Close socket connection
                        System.out.println();
                        System.out.println("*** " + this.name + " left the chat! ***");
                        System.out.println();
                        break;
                    }     
                    System.out.println(this.name + ": " + message); //Print client message
                }      
            }
            catch(IOException e)
            {
                System.out.println("Server Interaction IOException");
                e.printStackTrace();
            }
 
            try
            {
                //Close all resources
                this.dis.close();
                this.dos.close();
            }
            catch(IOException e)
            {
                System.out.println("Server Stream Closing IOExcetion");
                e.printStackTrace();
            }
        }
}

public class TCPServer{

    public static void main(String[] args) throws IOException
    {
        String name = null;
        ServerSocket server = new ServerSocket(5056); //Start a new server at port 5056
        System.out.println();
        System.out.println("-------SERVER IS LIVE------");
        System.out.println();
        try{
            while(true)
            {
                Socket client = server.accept(); //Accept client connection

                DataInputStream dis = new DataInputStream(client.getInputStream()); //Input stream from client
                DataOutputStream dos = new DataOutputStream(client.getOutputStream()); //Output stream to client

                name = dis.readUTF(); //Read name of client

                System.out.println();
                System.out.println("A new client is connected: " + name);
                System.out.println();
                
                ClientHandler clientThread = new ClientHandler(client,dis,dos,name); //Create a new thread for the client
                new Thread(clientThread).start(); //Start execution of thread
            }
        }
        catch(IOException e)
        {
            System.out.println("Server IOException");
            e.printStackTrace();
        }
        finally
        {
            if(server!=null)
            {
                try
                {
                    server.close();
                }
                catch(IOException e)
                {
                    System.out.println("IOException Server closing");
                    e.printStackTrace();
                }
            }
        }
    }
        
}
