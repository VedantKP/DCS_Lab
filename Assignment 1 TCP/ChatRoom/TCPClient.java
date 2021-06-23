/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(a) TCP: Client code for chat room app
*/

import java.util.Scanner;
import java.io.*;
import java.net.*;

public class TCPClient{
    
    public static void main(String[] args)
    {

        if(args.length<1) //To ensure name of the client is provided
        {
            System.out.println("Syntax: Client <name>");
            return;
        }

        try
        {
            Scanner sc = new Scanner(System.in);
            String name = args[0]; //name of the client
            
            InetAddress ip = InetAddress.getByName("localhost"); 
            Socket s = new Socket(ip,5056); //Connects to the ip address at the specified port number

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            dos.writeUTF(name); //Write name to server

            System.out.println(dis.readUTF()); //Recieve connection successful message from server

            while(true)
            {   
                String response = sc.nextLine(); //Read message typed by client from the console
                dos.writeUTF(response); //Write to server

                if(response.equalsIgnoreCase("Exit")) //If client wants to exit
                {
                    System.out.println("\nClosing this connection...");
                    s.close();
                    System.out.println("Connection closed");
                    break;
                }

                System.out.println();
            }
            //Close all resources
            sc.close();
            dis.close();
            dos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}