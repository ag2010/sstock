package com.xingrongjinfu.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.HashMap;
/**
 * Created by dkyear on 2016/3/4. DES 加密、解密 ISO ANDRIOD 通用
 */
public class DesUtils {
	private final static String DES = "DES";
	// 加密键byte数组
	private final static String key = "XGF@171225%API";//XGF@171225%API

	private final static Logger logger = LoggerFactory.getLogger(DesUtils.class);

	/**
	 * Description 根据键值进行加密
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws EncryptExcption {
		try {
			byte[] bt = encrypt(data.getBytes("utf-8"), key.getBytes());
			String strs = new BASE64Encoder().encode(bt);
			//String str = Des.strEnc(data);
			return strs;
		} catch (Exception e) {
			throw new EncryptExcption("加密出错");
		}
	}


	/**
	 * 解密参数，返回JsonString
	 * @param data
	 * @return
	 * @throws DecryptExcption
     */
	public static String decrypt(String data) throws DecryptExcption {
		try {
			
			if (data == null)
				return null;
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] buf = decoder.decodeBuffer(data);
			byte[] bt = decrypt(buf, key.getBytes());
			return new String(bt, "UTF-8").replaceAll("\\r\\n", "");

			//String dec = Des.strDec(data);
			//return  dec;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new DecryptExcption("解密出错");
		}
	}

	/**
	 * 解密参数，返回Object
	 * @param data
	 * @param cla
	 * @return
	 * @throws DecryptExcption
     */
	public static Object decrypt(String data,Class cla) throws DecryptExcption {
		try {
			String s = DesUtils.decrypt(data);
			JSONObject j = JSONObject.fromObject(s);
			Object o = JSONObject.toBean(j, cla);
			return o;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new DecryptExcption("解密出错");
		}
	}
	
	
	public static JSONArray decryptToJSONArry(String data,String qualifyList) throws DecryptExcption{
		String s = DesUtils.decrypt(data);
		JSONObject jo = JSONObject.fromObject(s);
		String sObj=jo.getString(qualifyList);
		JSONArray j = JSONArray.fromObject(sObj);
		return j;
	}
	
	
	
	/**
	 * 解密参数，返回Map
	 * @param data
	 * @return
	 * @throws DecryptExcption
     */
	public static HashMap decryptToMap(String data) throws DecryptExcption {
		try {
			//String s = DesUtils.decrypt(data);
			JSONObject j = JSONObject.fromObject(data);
//			JSONObject j = JSONObject.fromObject(data);
			HashMap<String, Object> map = (HashMap<String, Object>) JSONObject.toBean(j, HashMap.class);
			return map;
		} catch (Exception e) {
			throw new DecryptExcption("解密出错");
		}
	}
	/**
	 * json转map
	 * @param data
	 * @return
	 * @throws DecryptExcption
	 */
	public static HashMap jsonToMap(String data) throws DecryptExcption {
	        JSONObject j = JSONObject.fromObject(data);
	        HashMap<String, Object> map = (HashMap<String, Object>) JSONObject.toBean(j, HashMap.class);
	        return map;
	}

	/**
	 * Description 根据键值进行加密
	 * @param data
	 * @param key 加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);

	}

	/**
	 * Description 根据键值进行解密
	 * @param data
	 * @param key 加密键byte数组
	 * @return
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {

		// 生成一个可信任的随机数源
		SecureRandom sr = new SecureRandom();

		// 从原始密钥数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);

		// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);

		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);

		// 用密钥初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(data);

	}
	/*
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	*/

	/**
	 * 解密参数，返回Map
	 * @param data
	 * @return
	 * @throws DecryptExcption
     */
	public static HashMap stringToMap(String data) throws DecryptExcption {
		try {
			JSONObject j = JSONObject.fromObject(data);
			HashMap<String, Object> map = (HashMap<String, Object>) JSONObject.toBean(j, HashMap.class);
			return map;
		} catch (Exception e) {
			throw new DecryptExcption("解析出错");
		}
	}
	
	
	


}
