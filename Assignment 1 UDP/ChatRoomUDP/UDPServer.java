/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(a) UDP: Server code for chat room app
*/

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPServer{
    private DatagramSocket socket; //Socket of the server

    public UDPServer(int port) throws SocketException
    {
        socket = new DatagramSocket(port); //Server at given port
    }

    public static void main(String[] args)
    {
        int port = 5056;

        System.out.println();
        System.out.println("-------SERVER IS LIVE------");
        System.out.println();

        try
        {
            UDPServer server = new UDPServer(port); //Start a new server
            server.service(); //Start listening at given port
        }
        catch(SocketException e)
        {
            System.out.println("Socket exception " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("I/O exception " + e.getMessage());
        }
    }

    private static StringBuilder data(byte[] a) //Function to convert bytes to string 
    { 
        if (a == null) //If empty array 
            return null; 
        StringBuilder ret = new StringBuilder(); 
        int i = 0; 
        while (a[i] != 0) //Traverse whole array
        { 
            ret.append((char) a[i]); //Append character equivalent of each byte to string builder
            i++; 
        } 
        return ret; //Return string of message
    }

    private void service() throws IOException
    {
        byte[] messageBuffer = new byte[65535]; //Buffer to recieve messages from client
        while(true)
        {
            DatagramPacket message = new DatagramPacket(messageBuffer, messageBuffer.length);
            socket.receive(message); //Recieve datagram from client

            System.out.println(data(messageBuffer)); //Print message of client

            messageBuffer = new byte[65535]; //New empty buffer            
            System.out.println();
        }
    }
}