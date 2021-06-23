/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9
Lab: CL-9
Assignment 3: To develop a distributed chat application with CORBA program using JAVA IDL
*/

import ChatApp.*;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;

import java.util.Scanner;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class ChatAppServer{
    public static void main(String[] args)
    {
        try {

			System.out.println("\n---------------SERVER LIVE----------------\n\n");
			ORB orb = ORB.init(args,null);

			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate(); 

			ChatServant chatServant = new ChatServant();

			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(chatServant);

			Chat h_ref = ChatHelper.narrow(ref);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);	

			NameComponent path[] = ncRef.to_name("Chat");
			ncRef.rebind(path, h_ref);

			orb.run();

		}catch(Exception e) {
			e.printStackTrace(System.out);
		}
    }
}

class ChatServant extends ChatPOA {

	public void displayMessage(String str,String clientName) {
        System.out.println(clientName + ": " + str);
	}
}
