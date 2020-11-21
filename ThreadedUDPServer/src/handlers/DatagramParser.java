package handlers;
public class DatagramParser implements Runnable 
{


	private byte[] received;
	
	
	
	public DatagramParser(byte[] byteMsg)
	{
		received=byteMsg;
	}
	@Override
	public void run() 
	{
		byte output[];
		System.out.println("Processing bytes: " );
		for(int i = 0; i < received.length;i++)
			System.out.printf("%d, ",received[i]);
		try {
			Decryp deCryptor = new Decryp(received);
			output=deCryptor.decrypt(received);
			System.out.println();
			for(int i = 0; i < output.length;i++)
			{
				System.out.printf("%c", output[i]);
			}
			//SQLHandler handleIt = new SQLHandler(output);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(received);
			System.out.println(e.toString());
			
			System.out.println("COULD NOT DECRYPT BLOCK");
			e.printStackTrace();
		}
		
	}

}
