package com.ssh.bos.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5加密
 * 
 * @author slzhang
 *
 */
public class Md5Utils {
	/**
	 * md5加密
	 * 
	 * @param plainText 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			// getBytes(String str)方法将一个字符串转化为字节数组
			secretBytes = MessageDigest.getInstance("md5").digest(plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		// 将二进制表示形式的数字转化为BigInteger形式（1表示正数）后转为16进制
		String md5Code = new BigInteger(1, secretBytes).toString(16);
		// 如果数字未满32位，前面补0
		for (int i = 0; i < 32 - md5Code.length(); i++) {
			md5Code = "0" + md5Code;
		}
		return md5Code;
	}

}
