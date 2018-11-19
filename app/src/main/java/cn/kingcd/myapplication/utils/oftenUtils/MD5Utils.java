package cn.kingcd.myapplication.utils.oftenUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5
 * @author FEI
 *
 */
public class MD5Utils {
		//来点佐料
	private static String SALT = "asdjh@@^*%%jhsha~";
	/**
	 * 原来密码和佐料混合然后MD5加密
	 * @param password
	 * @return
	 */
	public static String encrypt(String password) {
				//初始化一个加密密码
		String encryptPassword = null;
		//MD5加密
		try {
			
			String msg = SALT+password+SALT;
			StringBuilder sb = new StringBuilder();
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] encryptBytes =  md5.digest(msg.getBytes());
			for(byte b:encryptBytes){
				//变换成绝对值
				int i = b&0xFF;
				//把小于255的数转换成16进制的字符串
				String hexString =  Integer.toHexString(i);
				//长度等于1补0
				if(hexString.length()==1){
					hexString = "0"+hexString;
				}
				
				sb.append(hexString);
				
				
			}
			
			encryptPassword = sb.toString();
			
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
		return encryptPassword;
		
	}


	private static String bytesToHexString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(0xFF & bytes[i]);
			if (hex.length() == 1) {
				sb.append('0');
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static String hashKey(String key) {
		String hashKey;
		try {
			final MessageDigest mDigest = MessageDigest.getInstance("MD5");
			mDigest.update(key.getBytes());
			hashKey = bytesToHexString(mDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			hashKey = String.valueOf(key.hashCode());
		}
		return hashKey;
	}


}
