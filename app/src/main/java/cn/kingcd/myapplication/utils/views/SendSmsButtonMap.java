package cn.kingcd.myapplication.utils.views;

import android.app.Application;

import java.util.Map;

public class SendSmsButtonMap extends Application {

	// 用于存放倒计时时间
	public static Map<String, Long> map;

	public static Map<String, Long> getMap() {
		return map;
	}

	public static void setMap(Map<String, Long> map) {
		SendSmsButtonMap.map = map;
	}
}
