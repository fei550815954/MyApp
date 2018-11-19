package cn.kingcd.myapplication.utils.oftenUtils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

import cn.kingcd.myapplication.R;

public class TaskUtils {

	/**
	 * 获得运行中的进程个数
	 * @param act
	 * @return
	 */
	public static int getRunnintProcCount(Context act) {
		ActivityManager am = (ActivityManager)act. getSystemService(Context.ACTIVITY_SERVICE);
		// 运行中进程的个数
		 return am.getRunningAppProcesses().size();
	}

	
	/**
	 * 获得所有的正在运行的进程信息
	 * @param ctx
	 * @return
	 */
	public static List<TaskInfoBean> getAllRunningTaskInfo(Context ctx){
		List<TaskInfoBean> allTaskList = new ArrayList<TaskInfoBean>();

		ActivityManager am = (ActivityManager)ctx.getSystemService(Context.ACTIVITY_SERVICE);
		
		PackageManager pm = ctx.getPackageManager(); //  project Manager 项目经理
		
		
		List<RunningAppProcessInfo> runningAppProcesses = am.getRunningAppProcesses();
		for (RunningAppProcessInfo appProcessInfo : runningAppProcesses) {
//			appProcessInfo.lru; LRU less recent use 
			
			TaskInfoBean taskInfo  =new TaskInfoBean();
			
			allTaskList.add(taskInfo);
			
			// 把进程名程，当做包名来用,大部分情况是可以的，但有些进程是用C代码来写的，那么，就没有 包的概念了
			taskInfo.packageName = appProcessInfo.processName; 
			
			// 输入多少个进程ID，返回多少个 内存信息
			android.os.Debug.MemoryInfo[] processMemoryInfo = am.getProcessMemoryInfo(new int[]{appProcessInfo.pid});
			
			// 获得内存信息
			taskInfo.memSize = processMemoryInfo[0].getTotalPrivateDirty()*1024; // 返回的是kb 而我们要的是byte 
			
			
			try {
				
				ApplicationInfo applicationInfo = pm.getApplicationInfo(taskInfo.packageName, 0);
				
				// 获得图标
				taskInfo.appIcon = applicationInfo.loadIcon(pm);
				
				// 获得名称
				taskInfo.appName = applicationInfo.loadLabel(pm).toString();
				
				// 设置是否是系统应用
				if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!=0){ // 不等于0 说明批配成功
					taskInfo.isSys = true;
				}else{
					taskInfo.isSys = false;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// 如果抛了异常，说明是C代码 
				
				taskInfo.isSys = true;
				// 设置图标
				taskInfo.appIcon = ctx.getResources().getDrawable(R.mipmap.ic_launcher);
				// 设置名称
				taskInfo.appName = appProcessInfo.processName;
				
			}
			
		}
		return allTaskList;
	};
	
	
	
}
