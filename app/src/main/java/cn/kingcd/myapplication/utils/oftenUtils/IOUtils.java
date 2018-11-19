package cn.kingcd.myapplication.utils.oftenUtils;

import java.io.Closeable;
import java.io.IOException;

/**
 * 
 * @author FEI
 *
 */
public class IOUtils {

	/**
	 * 关闭流对象
	 * @param closeable
	 */
	public static void close(Closeable closeable){
		try {
			if (closeable!= null) {
				closeable.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
