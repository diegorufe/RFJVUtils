package com.rfJVUtils.utils.crypto;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.rfJVUtils.utils.commons.UtilsString;

/**
 * Clase utilities for encrypt
 * 
 * <p>
 * AES
 * </p>
 * <ul>
 * <li>{@link #encryptAESCBCPaddingToHexString(String, byte[], byte[])}</li>
 * <li>{@link #decryptAESCBCPaddingFromHexString(String, byte[], byte[])}</li>
 * </ul>
 * 
 * @author diego
 *
 */
public final class UtilsCrypto {

	public static final String AES_CBC_PKCS5_PADDING = "AES/CBC/PKCS5Padding";

	public static final int IV_KEY_SIZE_AES_CBC_PADDING = 16;

	public static final String AES_ALGO = "AES";

	private UtilsCrypto() {

	}

	/**
	 * Method for generate random IV
	 * 
	 * @return iv random
	 */
	public static byte[] randomIVCBC() {
		byte[] ivCrypto = new byte[IV_KEY_SIZE_AES_CBC_PADDING];
		ivCrypto = new byte[IV_KEY_SIZE_AES_CBC_PADDING];
		SecureRandom random = new SecureRandom();
		random.nextBytes(ivCrypto);
		return ivCrypto;
	}

	/**
	 * Method for encrypt AES CBC padding to hex string
	 * 
	 * @param plainText to encrypt
	 * @param key       for encryption
	 * @param iv        for encryption
	 * @return hex string with plainText encrypt
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encryptAESCBCPaddingToHexString(String plainText, byte[] key, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String result = null;
		byte[] clean = plainText.getBytes();

		if (UtilsString.isNotEmpty(plainText) && key != null && key.length > 0 && iv != null && iv.length > 0) {
			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGO);
			// Encrypt.
			Cipher cipher = Cipher.getInstance(AES_CBC_PKCS5_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
			byte[] encrypted = cipher.doFinal(clean);

			result = UtilsString.byteArrayToHexString(encrypted);
		}
		return result;
	}

	/**
	 * Method for decrypt AES CBC padding from hex string
	 * 
	 * @param hexEncrypted to be decrypted
	 * @param key          for decrypt
	 * @param iv           decrypt
	 * @return plainText encrypted
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptAESCBCPaddingFromHexString(String hexEncrypted, byte[] key, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String result = null;
		if (UtilsString.isNotEmpty(hexEncrypted) && key != null && key.length > 0 && iv != null && iv.length > 0) {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key, AES_ALGO);
			// Decrypt.
			Cipher cipherDecrypt = Cipher.getInstance(AES_CBC_PKCS5_PADDING);

			IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
			cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

			byte[] decrypted = cipherDecrypt.doFinal(UtilsString.hexStringToByteArray(hexEncrypted));

			result = new String(decrypted);
		}

		return result;
	}
}
