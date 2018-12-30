package es.upm.pproject.tdd.backend;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.upm.pproject.tdd.exceptions.*;

public class HashPin {
	private StringBuilder pin;
	/** Constructor. Hashes a String with SHA-256.
	 * @param number
	 * @throws IncorrectPinFormatException
	 */
	public HashPin(String number) throws IncorrectPinFormatException { 
		if (number == null)
			throw new IncorrectPinFormatException();
		
		if (number.length() != 4)
			throw new IncorrectPinFormatException();

		try { 
			// Static getInstance method is called with hashing SHA 
			MessageDigest md = MessageDigest.getInstance("SHA-256"); 

			// digest() method called 
			// to calculate message digest of an input 
			// and return array of byte 
			byte[] messageDigest = md.digest(number.getBytes()); 

			// Convert byte array into signum representation 
			BigInteger no = new BigInteger(1, messageDigest); 

			// Convert message digest into hex value 
			StringBuilder  hashtext = new StringBuilder(no.toString(16)); 

			while (hashtext.length() < 32) { 
				hashtext.append("0" + hashtext); 
			} 
			this.pin = hashtext;
		} 
		catch (NoSuchAlgorithmException e) { 
			this.pin = null;
		} 
	}

	public String getHashPin() {
		return this.pin.toString();
	}
}
