package cn.kingcd.myapplication.utils.allFileUtils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;

import java.io.File;

import cn.kingcd.myapplication.R;
import cn.kingcd.myapplication.utils.allFileUtils.SuperFileView2;
import cn.kingcd.myapplication.utils.oftenUtils.FileUtils;
import cn.kingcd.myapplication.utils.oftenUtils.L;
import cn.kingcd.myapplication.utils.oftenUtils.SDCardUtils;


public class FileDisplayActivity extends AppCompatActivity {


    private String TAG = "FileDisplayActivity";
    SuperFileView2 mSuperFileView;

    String filePath;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_display);
        init();
    }


    public void init() {
        mSuperFileView = (SuperFileView2) findViewById(R.id.mSuperFileView);
        mSuperFileView.setOnGetFilePathListener(new SuperFileView2.OnGetFilePathListener() {
            @Override
            public void onGetFilePath(SuperFileView2 mSuperFileView2) {
                getFilePathAndShowFile(mSuperFileView2);
            }
        });

        Intent intent = this.getIntent();
        String path = (String) intent.getSerializableExtra("path");
        if (!TextUtils.isEmpty(path)) {
            setFilePath(path);
        }
        mSuperFileView.show();
    }


    private void getFilePathAndShowFile(SuperFileView2 mSuperFileView2) {
        if (getFilePath().contains("http")) {//网络地址要先下载
            dowmFile(mSuperFileView2,getFilePath());
        } else {
            mSuperFileView2.displayFile(new File(getFilePath()));
        }
    }

    /**
     * 下载文件
     * @param mSuperFileView2
     * @param filePath
     */
    private void dowmFile(final SuperFileView2 mSuperFileView2, String filePath) {
        L.e("下载的地址是+"+filePath);
        OkGo.<File>get(filePath)
                .tag(this)
                .execute(new FileCallback(SDCardUtils.getSDCardPath(), FileUtils.getFileMd5Name(filePath)) {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                        L.e("下载到的地址是+"+response.toString());
                       mSuperFileView2.displayFile(response.body());
                    }

                    @Override
                    public void downloadProgress(Progress progress) {

                    }

                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSuperFileView != null) {
            mSuperFileView.onStopDisplay();
        }
    }


    public static void show(Context context, String url) {
        Intent intent = new Intent(context, FileDisplayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("path", url);
        intent.putExtras(bundle);
        context.startActivity(intent);

    }

    public void setFilePath(String fileUrl) {
        this.filePath = fileUrl;
    }

    private String getFilePath() {
        return filePath;
    }


}
