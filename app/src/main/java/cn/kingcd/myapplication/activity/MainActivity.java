package cn.kingcd.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.BaseAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.kingcd.myapplication.R;
import cn.kingcd.myapplication.utils.baseUtils.BaseActivity;

public class MainActivity extends BaseActivity {

    @BindView(R.id.uio)
    TextView uio;

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        super.initData();
        uio.setText("123456456");
    }
}
