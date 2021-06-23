/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(a) UDP: Client code for chat room app
*/

import java.util.*;
import java.net.*;
import java.io.*;

public class UDPClient{

    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        byte sendBuffer[] = null; //buffer to store message to send

        String hostname = "localhost";
        int port = 5056;

        try{
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket(); //Socket to send messages to server 

            System.out.println("\nStart typing to start chatting (Type 'Exit' to leave):: ");

            while(true)
            {

                String message = sc.nextLine(); //Read message of client from console

                if(message.equalsIgnoreCase("Exit")) //If client wants to exit
                {
                    socket.close(); //Close socket
                    System.out.println("Socket closed");
                    break;
                }

                sendBuffer = message.getBytes(); //Convert string to byte array

                DatagramPacket data = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
                socket.send(data); //Send byte array data to server

            }
        }
        catch(SocketTimeoutException e)
        {
            System.out.println("Stack timeout exception: " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("IOException: " + e.getMessage());
        }
        finally{
            sc.close();
        }
    }
}