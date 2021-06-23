/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(b) RMI: Client code for RMI Chat room app
*/

import java.rmi.registry.LocateRegistry; 
import java.rmi.registry.Registry;  
import java.util.Scanner;

public class RMIClient { 
   public static void main(String[] args) 
   {  
      if(args.length<1) //To ensure name of the client is provided
      {
            System.out.println("Syntax: RMIClient <name>");
            return;
      }
      try 
      {      
         Registry registry = LocateRegistry.getRegistry(null); // Get the registry
         Communicate stub = (Communicate) registry.lookup("Communicate"); // Looking up the registry for the remote object 
         System.out.println("Start typing to start chatting (Type 'Exit' to stop) :: \n");
         Scanner sc = new Scanner(System.in);
         String name = args[0]; //name of the client
         while(true)
         {
            String message = sc.nextLine();   
            if(message.equalsIgnoreCase("Exit"))
               break;
            stub.printMsg(name,message); // Calling the remote method using the obtained object
         }
         sc.close();
      } 
      catch (Exception e) 
      {
         System.err.println("Client exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
      
   } 
}