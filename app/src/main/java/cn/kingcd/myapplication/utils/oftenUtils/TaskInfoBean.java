package cn.kingcd.myapplication.utils.oftenUtils;

import android.graphics.drawable.Drawable;

public class TaskInfoBean {

	
	/**
	 *  判断当前条目是否选中
	 */
	public boolean isSelect;
	
	/**
	 * 应用的包名
	 */
	public String packageName;
	
	public String appName;
	
	public Drawable appIcon;
	
	/**
	 * 当前应用所占内存大小
	 */
	public long memSize;
	
	/**
	 * 是否是系统应用
	 */
	public boolean isSys;
	
}
