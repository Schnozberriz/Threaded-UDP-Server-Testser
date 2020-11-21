import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

 
public class main {


	public static void main(String[] args) 
	{
		try {
			DatagramSocket ds = new DatagramSocket();
			InetAddress ip = InetAddress.getLocalHost();
			String inp = "10001000BBBBBBBFFFTTT";
			byte [] buf=inp.getBytes();
			
		try {
			encryptolator goTime = new encryptolator();
			byte [] Crypted = goTime.crypt(buf);
			DatagramPacket Sending = new DatagramPacket(Crypted,Crypted.length,ip,8000);

			ds.send(Sending);
			System.out.println();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

}
