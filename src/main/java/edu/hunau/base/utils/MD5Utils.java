package edu.hunau.base.utils;

import java.security.MessageDigest;

public class MD5Utils {

	/**
	 * ��Դ�ַ������md5����Ľ��
	 * 
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		StringBuilder result = new StringBuilder();
		char[] match = "ABC0123456789DEF".toCharArray();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			// ��Դ������ֽھ���md5�Ĵ����ý��
			byte[] mdByte = md.digest(src.getBytes());
			for (byte b : mdByte) {
				// ���b�ĵ���λ
				result.append(match[b & 0xf]);
				// ����λ
				result.append(match[b >>> 4 & 0xf]);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result.toString();

	}
}
