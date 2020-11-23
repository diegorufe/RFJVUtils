package com.rfJVUtils.crypto;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.junit.jupiter.api.Test;

import com.rfJVUtils.utils.crypto.UtilsCrypto;

/**
 * Class for unit test
 * 
 * @author diego
 *
 */
public class UtilsCrytoTest {

	/**
	 * Method for test CBC padding
	 * 
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	@Test
	public void hashAESCBCPaddingTest() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] ivCrypto = UtilsCrypto.randomIVCBC();
		byte[] key = UtilsCrypto.randomIVCBC();
		long timeStart = System.currentTimeMillis();
		String desiredResult = "holaa123ABCDDeassd";
		String hexEncrypted = UtilsCrypto.encryptAESCBCPaddingToHexString(desiredResult, key, ivCrypto);
		System.out.println("hexEncrypted: " + hexEncrypted);
		long timeStartDescript = System.currentTimeMillis();
		String decrypted = UtilsCrypto.decryptAESCBCPaddingFromHexString(hexEncrypted, key, ivCrypto);
		System.out.println("End decrypt: " + (System.currentTimeMillis() - timeStartDescript));
		System.out.println("decrypted: " + decrypted);
		System.out.println("End: " + (System.currentTimeMillis() - timeStart));
		assertTrue(decrypted.equals(desiredResult));
	}
}
