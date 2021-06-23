/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: P9
Lab: CL-9
Assignment 2: MPI program for dividing message using Send and Recv functions
*/

import mpi.*;
import java.util.*;

public class MessageDividerSendRecv{
    public static void main(String[] args) throws MPIException
    {
        Scanner sc = new Scanner(System.in);
        String message = "This is the message that needs to be sent to multiple processes";
        StringBuilder builder = new StringBuilder(message);
        
        int lenString = builder.length();
        
        MPI.Init(args);
        
        int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();

        int divLength = (int)Math.floor(lenString/size);
        double startTime = 0;
        String processStorage = null;
        String recvString = null;
        char[] processCharArray = new char[divLength*2];
        if(rank != size - 1)
        {
            processCharArray = new char[divLength];
        }
    
        if(rank==0)
        {
            int end = divLength;
            processCharArray = builder.substring(0, end).toCharArray();
            System.out.println("Full string is => " + message);

            int start = end;
            end = end + divLength;
            
            for(int i=1;i<size;i++)
            {
                if((end + divLength) >= lenString)
                    end = lenString;
                String sendBuf = builder.substring(start, end);
                MPI.COMM_WORLD.Send(sendBuf.toCharArray(),0,sendBuf.length(),MPI.CHAR,i,0);
                start = end;
                end = end + divLength;
            }
        }
        else if(rank==size-1)
        {
            MPI.COMM_WORLD.Recv(processCharArray,0,divLength*2,MPI.CHAR,0,0);
        }
        else
        {
            MPI.COMM_WORLD.Recv(processCharArray,0,divLength,MPI.CHAR,0,0);
        }

        System.out.println("Process " + rank + " has text => " + String.valueOf(processCharArray));

        if(rank!=0)
        {
            MPI.COMM_WORLD.Send(processCharArray,0,String.valueOf(processCharArray).length(),MPI.CHAR,0,0);
        }
        else
        {
            recvString = String.valueOf(processCharArray);
            builder = new StringBuilder(recvString);

            for(int i=1;i<size;i++)
            {
                char[] recvBuff = new char[divLength*2];
                if(i!=size-1)
                {
                    recvBuff = new char[divLength];    
                    MPI.COMM_WORLD.Recv(recvBuff,0,recvBuff.length,MPI.CHAR,i,0);
                }
                else
                    MPI.COMM_WORLD.Recv(recvBuff,0,recvBuff.length,MPI.CHAR,i,0);
                builder.append(String.valueOf(recvBuff));
            }
            System.out.println("Recieved String => " + builder.toString());
        }

        MPI.Finalize();
    }
}

/*
OUTPUT

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>javac -cp %MPJ_HOME%/lib/mpi.jar;%MPJ_HOME%/lib/mpiExp.jar;%MPJ_HOME%/lib/xdev.jar MessageDividerSendRecv.java

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>%MPJ_HOME%/bin/mpjrun -np 4 MessageDividerSendRecv
MPJ Express (0.44) is started in the multicore configuration
Full string is => This is the message that needs to be sent to multiple processes
Process 0 has text => This is the mes
Process 1 has text => sage that needs
Process 2 has text =>  to be sent to
Process 3 has text => multiple processes
Recieved String => This is the message that needs to be sent to multiple processes

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>%MPJ_HOME%/bin/mpjrun -np 2 MessageDividerSendRecv
MPJ Express (0.44) is started in the multicore configuration
Full string is => This is the message that needs to be sent to multiple processes
Process 0 has text => This is the message that needs
Process 1 has text => to be sent to multiple processes
Recieved String => This is the message that needs to be sent to multiple processes

C:\Users\vedan\Documents\College\Final_Year\CL9\Assignment 2>
*/