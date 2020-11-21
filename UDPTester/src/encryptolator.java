import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class encryptolator 
{
	private static byte[] IV = new byte[3];
	private static final byte[] KEY= new byte[] 
			{20, 32, 1, 93, 
			118, 54,40, 93,
			97, 94, 7, 44, 
			42, 91, 29, 81,
			112, 78, 102, 49,
			7, 103, 116, 125,
			33, 52, 39, 121,
			113, 96, 97, 125}; 
	
	private static final int GCM_TAG_LENGTH = 16;
	private static Cipher cipher;
	private static SecretKeySpec keySpec;
	private static GCMParameterSpec gcmParameterSpec;
	
	public encryptolator()
	{
		try {
			cipher = Cipher.getInstance("AES/GCM/NoPadding");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("INTERNAL ERROR: AES NOT FOUND");
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			System.out.println("INTERNAL ERROR: NoPadding NOT FOUND");
			e.printStackTrace();
		}
		keySpec = new SecretKeySpec(KEY,"AES");
		
	}

	public byte[] crypt (byte[] plain) throws Exception
	{
			byte[] postCrypt;
			byte[] rBytes;
			SecureRandom SC = new SecureRandom();
			SC.nextBytes(IV);
			
			gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, IV);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmParameterSpec);
			postCrypt=cipher.doFinal();
			rBytes=fillBytes(postCrypt);
			System.out.printf("PostCryptBytes = %d\n",postCrypt.length);
			System.out.printf("PostFillBytes = %d\n", rBytes.length);
			return rBytes;
	
	
	}
	
	private static byte[] fillBytes(byte[] arr1)
	{
		int size = arr1.length+IV.length;
		byte [] returnByte = new byte[size];
		for(int i = 0; i < arr1.length;i++)
		{
			returnByte[i]=arr1[i];
		}
		for(int i = 0; i < IV.length;i++)
		{
			returnByte[i+arr1.length]=IV[i];
		}
		return returnByte;
	}
	
}
