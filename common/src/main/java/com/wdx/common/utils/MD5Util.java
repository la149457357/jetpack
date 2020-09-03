package com.wdx.common.utils;



import java.security.MessageDigest;

public class MD5Util implements java.io.Serializable {

	/**
	 * 加密算法
	 * @param plainText
	 * @return
	 */
	public static String encodeByMd5(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i = 0;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return buf.toString(); //32位的加密
		return buf.toString().substring(8,24); // 16位的加密
	}
	
	/**
	 * 加密算法
	 * @param plainText
	 * @return
	 */
	public static String encodeByMd5_32(String plainText) {
		StringBuffer buf = new StringBuffer("");
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i = 0;
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString(); //32位的加密
	}
	

}
