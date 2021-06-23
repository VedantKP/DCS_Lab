/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9
Lab: CL-9
Assignment 2: MPI program for dividing message using Scatter and Gather functions
*/

import mpi.*;

import java.security.SignedObject;
import java.util.*;

public class MessageDividerScatterGather{
    
    public static void main(String[] args) throws MPIException
    {
        Scanner sc = new Scanner(System.in);
        String message = "ABCD";
        StringBuilder builder = new StringBuilder(message);
        int lenString = builder.length();
        
        MPI.Init(args);
        
        int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();

        String processStorage = null;
        String recvString = null;
        char processChar;

        char[] sendBuf = new char[size];
        char[] recvBuf = new char[size];

        if(rank==0)
        {
            System.out.println("Full string is => " + message);

            for(int i=0;i<size;i++)
            {
                sendBuf[i] = message.charAt(i);
            }

        }

        MPI.COMM_WORLD.Scatter(sendBuf,0,1,MPI.CHAR,recvBuf,0,1,MPI.CHAR,0);

        System.out.println("Process " + rank + " has text => " + recvBuf[0]);

        MPI.COMM_WORLD.Gather(recvBuf,0,1,MPI.CHAR,sendBuf,0,1,MPI.CHAR,0);

        if(rank==0)
        {
            builder = new StringBuilder();
            for(int i=0;i<size;i++)
            {
                builder.append(sendBuf[i]);
            }

            System.out.println("Recieved String => " + builder.toString());
        }

        //MPI.Finalize();
    }
}

/*
OUTPUT

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>javac -cp %MPJ_HOME%/lib/mpi.jar;%MPJ_HOME%/lib/mpiExp.jar;%MPJ_HOME%/lib/xdev.jar MessageDividerScatterGather.java

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>%MPJ_HOME%/bin/mpjrun -np 4 MessageDividerScatterGather
MPJ Express (0.44) is started in the multicore configuration
Full string is => ABCD
Process 0 has text => A
Process 1 has text => B
Process 3 has text => D
Process 2 has text => C
Recieved String => ABCD

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>
*/