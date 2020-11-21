

package handlers;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decryp {
	private static final byte[] KEY= new byte[] 
			{20, 32, 1, 93, 
			118, 54,40, 93,
			97, 94, 7, 44, 
			42, 91, 29, 81,
			112, 78, 102, 49,
			7, 103, 116, 125,
			33, 52, 39, 121,
			113, 96, 97, 125}; 
	public static final int IVSIZE=3;
	private byte[] IV = new byte[IVSIZE];
	
	

	private static final int GCM_TAG_LENGTH = 16;
	/**
	 * Initializes the IV for GCM.
	 * Creates a new Decryp object (so we don't have collision between threads)
	 * @param ciphertext
	 */
	public Decryp(byte[] ciphertext)
	{
		if(ciphertext.length>2)
		{
			int ok = 2; 
			System.out.println();
			for(int i = ciphertext.length-1; ok >-1;i--) //init IV in most cursed way possible
			{
				IV[ok]=ciphertext[i];
				ok--;
			}
		}

	}

	public byte[] decrypt(byte[] cipherText) throws Exception
	{
		
		int textLen=cipherText.length-IVSIZE;
		if(textLen < 16)
			throw new Exception();
		
		byte [] trueCipher = new byte[textLen];
		System.out.println(trueCipher.length + " is the size of the true ciphertext");
		//print cipher while we init cipher.
		for(int i = 0; i < trueCipher.length;i++)
		{	trueCipher[i]=cipherText[i]; //initCipher
		//getCipher
			System.out.printf("%d, ", trueCipher[i]);
		}
		
		//Initiate our cipher and prep for decryption
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		SecretKeySpec keySpec = new SecretKeySpec(KEY,"AES");
		GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH*8,IV);
		cipher.init(Cipher.DECRYPT_MODE, keySpec,gcmParameterSpec);
		byte[] decrypted;
		
		//System.out.println("\n"+(int)trueCipher[15]);
		//Decrypt our text
		decrypted= cipher.doFinal(trueCipher);
	
		System.out.println();
		
		
		System.out.println("\ndoneTag " + decrypted.length);
		//int toFlo = (decrypted[13] &0xff| ((decrypted[12]&0xff)<<8) |((0xff& decrypted[11])<<16) |((0xff& decrypted[10])<<24));
		//float asfloat = Float.intBitsToFloat(toFlo);
		//rStrin[8]=String.format("%.2f", asfloat);
		return decrypted ;
	}
}
