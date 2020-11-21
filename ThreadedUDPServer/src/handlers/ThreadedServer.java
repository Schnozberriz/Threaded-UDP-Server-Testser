/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import java.net.SocketException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.Queue;
/**
 * @author Harrison Koll
 */
public class ThreadedServer 
{
	private static int PORTNUM=8000; 
	private static int BUFFERSIZE=19;
	private DatagramSocket dRec ;
	private byte[] receiveBuffer;
	DatagramPacket message=null;
	Queue<DatagramParser> theQueue= new LinkedList<>();
	

	public ThreadedServer() 
	{
		try 
		{
			dRec = new DatagramSocket(PORTNUM);
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.printf("SOCKET %d",PORTNUM);
		}
	
		try
		{
			while(true)
			{
				receiveBuffer= new byte[BUFFERSIZE];
				message = new DatagramPacket(receiveBuffer,BUFFERSIZE);
				dRec.receive(message);

				DatagramParser dp = new DatagramParser(message.getData());
				theQueue.add(dp);
				if(!theQueue.isEmpty()) {
					Thread clientThread = new Thread(theQueue.remove());
					
					clientThread.start();
				}
				
			}

		} 
		catch (IOException ex)
		{
			Logger.getLogger(ThreadedServer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}