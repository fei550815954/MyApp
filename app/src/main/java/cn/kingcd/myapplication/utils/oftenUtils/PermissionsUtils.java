package cn.kingcd.myapplication.utils.oftenUtils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.Html;

/**
 * Created by Administrator on 2016/7/11.
 * <p/>
 * <p/>
 * 混淆-dontwarn com.zhy.m.**
 *-keep class com.zhy.m.** {*;}
 *-keep interface com.zhy.m.** { *; }
 * -keep class **$$PermissionProxy { *; }
 *
 *
 * MPermissions.requestPermissions(MyPicTureUp.this, PermissionsCode.CAMERA, Manifest.permission.CAMERA);
 *
 * @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
 * {
 * MPermissions.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
 * super.onRequestPermissionsResult(requestCode, permissions, grantResults);
 * }
 * @PermissionGrant(PermissionsCode.STORAGE) public void requestSdcardSuccess(){
 * Intent intent = new Intent(MyPicTureUp.this, AlbumActivity.class);
 * Always.poth = 2;
 * startActivity(intent);
 * }
 * @PermissionDenied(PermissionsCode.STORAGE) public void requestSdcardFailed(){
 * Utils.showMissingPermissionDialog(MyPicTureUp.this,"读写手机储存");
 * }
 */
public class PermissionsUtils {

    //相机
    public final static int CAMERA = 1;
    //读写内存
    public final static int STORAGE = 11;


    public static void showMissingPermissionDialog(final Context context, final String permissionName) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(Html.fromHtml("<font color=#000000>温馨提示:</font>"));
        builder.setMessage(Html.fromHtml("当前应用缺少" + "<font color=red>" + permissionName + "</font>" + "权限,导致软件功能无法正常运行。<br><br>请点击设置权限-打开所需权限。<br><br>最后点击后退按钮，即可返回。"));
        builder.setNegativeButton("后退", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings(context);
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public static void startAppSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivity(intent);
    }
}
