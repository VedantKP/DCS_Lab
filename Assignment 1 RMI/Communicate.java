/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9

Assignment 1(b) RMI: Interface
*/

import java.rmi.Remote;
import java.rmi.RemoteException;

// Creating Remote interface  
public interface Communicate extends Remote {  
    void printMsg(String name, String message) throws RemoteException;  
 }