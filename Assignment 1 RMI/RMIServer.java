/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(b) RMI: Server code for RMI Chat room app
*/

import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry; 
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class RMIServer extends ImplementationClass { 
 
   public static void main(String args[]) 
   { 
      try 
      {  
         ImplementationClass obj = new ImplementationClass(); // Instantiating the implementation class
         Communicate stub = (Communicate) UnicastRemoteObject.exportObject(obj, 8080);  
         Registry registry = LocateRegistry.getRegistry(); 
         registry.bind("Communicate", stub);  // Binding the remote object (stub) in the registry 
         System.out.println();
         System.out.println("-------SERVER IS LIVE------");
         System.out.println();
      } 
      catch (Exception e) 
      { 
         System.err.println("Server exception: " + e.toString()); 
         e.printStackTrace(); 
      } 
   } 
} 