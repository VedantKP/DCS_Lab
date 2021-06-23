/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9
Lab: CL-9
Assignment 3: To develop a distributed chat application with CORBA program using JAVA IDL (Client Code)
*/

import ChatApp.*;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class ChatAppClient{
    public static void main(String[] args)
    {
        Chat chatRef = null;
        try {

			ORB orb = ORB.init(args,null);

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);	

			chatRef = ChatHelper.narrow(ncRef.resolve_str("Chat"));

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter name => ");
            String clientName = sc.nextLine();

            while(true)
            {
                System.out.print("Enter message: ");
    		    String str1 = sc.nextLine();  

                if(str1.equalsIgnoreCase("Bye"))
                    break;

			    chatRef.displayMessage(str1,clientName);
                
                //System.out.println("Server: " + str);
            }
			
		}catch(Exception e) {
			e.printStackTrace();
		}
    }
}