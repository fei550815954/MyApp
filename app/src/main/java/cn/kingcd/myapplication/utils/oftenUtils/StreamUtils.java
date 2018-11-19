package cn.kingcd.myapplication.utils.oftenUtils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 流操作的工具类
 */
public class StreamUtils {

	/**
	 * 把输入流转化成字符串
	 * @return
	 */
	public static String stream2String(InputStream is){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024*8];
			int len = 0;
			while((len=is.read(buffer)) != -1){
				baos.write(buffer, 0, len);
			}
			String text = new String(baos.toByteArray());
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
